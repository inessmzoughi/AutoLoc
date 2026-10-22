package tn.esprit.autoloc;

public class Utils {

    private Utils() {
    }

    public static boolean isNotEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }
}