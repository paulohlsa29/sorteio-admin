package br.com.admin.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AdminUtil {
    public static boolean isNullOrEmpty(String any) {
        return null == any || "".equals(any);
    }

    public static boolean startsWith(String text, String prefix) {
        return text.startsWith(prefix);
    }

    public static String removeSpecialCharacters(String text) {
        return text.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static boolean isTrue(Boolean flag) {
        return flag != null && Boolean.TRUE.equals(flag);
    }
}
