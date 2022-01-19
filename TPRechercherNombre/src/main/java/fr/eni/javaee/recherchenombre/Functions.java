package fr.eni.javaee.recherchenombre;

public class Functions {
	
	public static boolean isNumeric(final String str) {

        if (str == null || str.length() == 0) {
            return false;
        }

        try {

            Integer.parseInt(str);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }

    }
}
