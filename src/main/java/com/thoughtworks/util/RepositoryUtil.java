package com.thoughtworks.util;

import com.thoughtworks.repository.Repository;

public class RepositoryUtil {
    public static String lookupSymbolsFromVariables(Repository repository, String[] keys) {
        StringBuffer symbols = new StringBuffer();

        for (String key : keys) {
            symbols.append(repository.get(key));
        }

        return symbols.toString();
    }
}
