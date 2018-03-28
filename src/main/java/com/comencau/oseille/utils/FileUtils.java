package com.comencau.oseille.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author Nicolas FABRE
 * @since 2018-03-27
 */
public class FileUtils {

    public static final String[] SEPARATORS = new String[]{";", ",", "\t"};

    public static String guessSeparator(File file) throws IOException {
        int nbLines = 10;
        Map<String, Integer> separatorCounts = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int n = 0;
            while ((line = br.readLine()) != null && n < nbLines) {
                for (String s : SEPARATORS) {
                    int m = StringUtils.countMatches(line, s);
                    separatorCounts.merge(s, m, Integer::sum);
                }
                n++;
            }
        }
        return separatorCounts.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

}
