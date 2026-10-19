package com.example.app1.util;
public final class InputValidator {

    private InputValidator() {} // prevent instantiation

    public static Integer positiveInt(String value) {
        try {
            int v = Integer.parseInt(value.trim());
            return v > 0 ? v : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Double positiveDouble(String value) {
        try {
            double v = Double.parseDouble(value.trim());
            return v > 0 ? v : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String nonEmpty(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        return value.trim();
    }

    public static Boolean booleanValue(String value) {
        if (value == null) return null;

        value = value.trim().toLowerCase();
        if (value.equals("true") || value.equals("yes")) return true;
        if (value.equals("false") || value.equals("no")) return false;

        return null;
    }
    public static Integer tryParsePositiveInt(String value) {
        try {
            int parsed = Integer.parseInt(value.trim());
            return parsed > 0 ? parsed : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Double tryParsePositiveDouble(String value) {
        try {
            double parsed = Double.parseDouble(value.trim());
            return parsed > 0 ? parsed : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String tryParseNonEmptyString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return value.trim();
    }

    public static Boolean tryParseBoolean(String value) {
        if (value == null) return null;

        value = value.trim().toLowerCase();
        if (value.equals("true") || value.equals("yes")) return true;
        if (value.equals("false") || value.equals("no")) return false;

        return null;
    }

}

