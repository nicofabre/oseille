package com.comencau.oseille;

import com.comencau.oseille.utils.DaoUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExportTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() throws IOException {
        LocalDate start = LocalDate.of(2016, 1, 1);
        LocalDate end = LocalDate.of(2018, 4, 1);
        File export = export(start, end);
        export.renameTo(new File(System.getProperty("user.home") + "/Desktop/" + export.getName()));
    }

    public File export(LocalDate start, LocalDate end) throws IOException {
        TreeMap<LocalDate, Double> amounts = new TreeMap<>();
        jdbcTemplate.query("SELECT date, amount FROM transaction WHERE date <= ?", new Object[]{end}, rs -> {
            while (rs.next()) {
                LocalDate d = DaoUtils.getLocalDate(rs, "date");
                double v = rs.getBigDecimal(2).doubleValue();
                amounts.merge(d, v, (oldValue, newValue) -> oldValue + newValue);
            }
            return null;
        });

        double solde = 0.0;
        TreeMap<LocalDate, Double> soldes = new TreeMap<>();
        for (Map.Entry<LocalDate, Double> e : amounts.entrySet()) {
            solde += e.getValue();
            soldes.put(e.getKey(), solde);
        }

        LocalDate date = start;
        LocalDate min = soldes.keySet().stream().min(LocalDate::compareTo).get();
        if (date.compareTo(min) < 0) date = min;

        LocalDate max = soldes.keySet().stream().max(LocalDate::compareTo).get();
        LocalDate limit = end;
        if (limit.compareTo(max) > 0) limit = max;

        TreeMap<LocalDate, Double> map = new TreeMap<>();
        while (date.compareTo(limit) <= 0) {
            Double soldeAtThisDate = soldes.get(date);
            int i = 0;
            while (soldeAtThisDate == null) {
                soldeAtThisDate = soldes.get(date.minusDays(++i));
            }
            map.put(date, soldeAtThisDate);
            date = date.plusDays(1);
        }

        File f = File.createTempFile("export", ".csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            for (Map.Entry<LocalDate, Double> e : map.entrySet()) {
                System.out.println(e.getKey() + ";" + decimalFormat.format(e.getValue()));
                bw.write(e.getKey() + ";" + decimalFormat.format(e.getValue()));
                bw.newLine();
            }
        }
        return f;
    }

}
