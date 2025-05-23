package me.kryz.mymessage.common;

public final class Prefix {
    private static String prefix;

    public Prefix() {
    }

    public static void setPrefix(String pr) {
        prefix = pr;
    }

    public static boolean startsWith(String message) {
        if(message == null) return false;
        return message.startsWith(prefix);
    }

    public static String getPrefix() {
        return prefix;
    }
}
