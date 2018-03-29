package com.comencau.oseille.core;

import com.comencau.oseille.utils.DaoUtils;
import com.comencau.oseille.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

/**
 * TODO
 *
 * @author Nicolas FABRE
 * @since 2018-03-27
 */
@Service
public class ImportServiceImpl implements ImportService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<Transaction> parseFile(File file, int nbLinesToSkip, Function<String[], Transaction> f) throws IOException {
        String sep = FileUtils.guessSeparator(file);
        List<Transaction> txs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int n = 0;
            String line = null;
            try {
                while ((line = br.readLine()) != null) {
                    n++;
                    if (n <= nbLinesToSkip) continue;
                    Transaction tx = f.apply(line.split(sep));
                    txs.add(tx);
                }
            } catch (Exception e) {
                throw new RuntimeException("Problem when parsing line # " + n + " : " + line, e);
            }
        }
        return txs;
    }

    @Override
    public void importING(File file) throws IOException {
        logger.debug("Import " + file.getAbsolutePath());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Transaction> transactions = this.parseFile(file, 0, split -> {
            Transaction tx = new Transaction();
            tx.setDate(LocalDate.parse(split[0], formatter));
            tx.setLabel(split[1]);
            tx.setAmount(new BigDecimal(split[3].replaceAll(",", ".")));
            return tx;
        });

        LocalDate max = transactions.stream().map(Transaction::getDate).max(LocalDate::compareTo).get();
        LocalDate min = transactions.stream().map(Transaction::getDate).min(LocalDate::compareTo).get();
        logger.debug("Max date : {}", max);
        logger.debug("Min date : {}", min);

        Set<Transaction> txs = new LinkedHashSet<>(transactions);
        jdbcTemplate.query("SELECT * FROM transaction WHERE date >= ? AND date <= ?", new Object[]{min, max}, rs -> {
            TransactionRowMapper rowMapper = new TransactionRowMapper();
            while (rs.next()) {
                Transaction tx = rowMapper.mapRow(rs, 0);
                txs.remove(tx);
            }
            return null;
        });
        this.persistTransactions(txs);
    }

    private void persistTransactions(Collection<Transaction> transactions) {
        logger.debug("Persist {} transactions", transactions.size());
        for (Transaction tx : transactions) {
            Long id = jdbcTemplate.queryForObject("CALL NEXT VALUE FOR transaction_s", Long.class);
            tx.setId(id);
        }

        jdbcTemplate.batchUpdate("INSERT INTO transaction (id, date, label, amount) VALUES (?,?,?,?)", transactions, 1000, (ps, tx) -> {
            ps.setLong(1, tx.getId());
            ps.setDate(2, DaoUtils.toSqlDate(tx.getDate()));
            ps.setString(3, tx.getLabel());
            ps.setBigDecimal(4, tx.getAmount());
        });
    }

}
