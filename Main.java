import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//Brayden Kim
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            System.out.println("Please enter a valid email address:");
            String input = sc.nextLine().trim();
            if (input.matches("[a-zA-Z0-9]+([._-][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([._-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,}")) {
                System.out.println("Valid email: " + input);
                valid = true;
            } else {
                System.out.println("Invalid email.\n");
            }
        }

        while (true) {
            System.out.println("Please enter a Password (at least one capital letter, one number, one special character, 8+ characters):");
            String password = sc.nextLine();
            boolean hasUpper   = password.matches(".*[A-Z].*");
            boolean hasDigit   = password.matches(".*[0-9].*");
            boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}|;':\",./<>?~`].*");
            boolean hasLength  = password.length() >= 8;

            if (hasUpper && hasDigit && hasSpecial && hasLength) {
                System.out.println("Valid password\n");
                break;
            } else {
                if (!hasLength)  System.out.println("  - Must be at least 8 characters.");
                if (!hasUpper)   System.out.println("  - Must contain at least one uppercase letter.");
                if (!hasDigit)   System.out.println("  - Must contain at least one digit.");
                if (!hasSpecial) System.out.println("  - Must contain at least one special character.");
                System.out.println();
            }
        }
        System.out.println("Enter a sentence to split into words (punctuation will be removed):");
        String input = sc.nextLine();
        String temp = input.replaceAll("[^a-zA-Z0-9 ]", "");
        temp = temp.replaceAll(" ", "\n");
        Scanner scanner = new Scanner(temp);
        while (scanner.hasNextLine()) {
            String split = scanner.nextLine();
            System.out.println(split);
        }

        valid = false;
        while (!valid) {
            System.out.println("Enter a phone number (10 digits, country code, separators allowed):");
            String phone = sc.nextLine().trim();
            String digits = phone.replaceAll("[^0-9]", "");
            boolean format = phone.matches("[+]?[0-9()\\s.\\-]+");
            boolean parens = true;
            if (phone.contains("(") || phone.contains(")")) {
                parens = phone.matches(".*\\(\\d{3}\\).*");
            }
            boolean length = digits.length() >= 10 && digits.length() <= 13;
            if (format && parens && length) {
                System.out.println("Valid phone number: " + digits);
                valid = true;
            } else {
                System.out.println("Invalid phone number.");
            }
        }
        System.out.println();

        System.out.println("Reading TheRaven.dat (words with more than 3 characters)");
        try {
            Scanner fileScanner = new Scanner(new File("TheRaven.dat"));
            StringBuilder tempP= new StringBuilder();
            while (fileScanner.hasNextLine()) {
                tempP.append(fileScanner.nextLine());
            }
            String newS = tempP.toString();
            newS = newS.replaceAll("[^a-zA-Z ]", " ");
            newS = newS.replaceAll("\\s+", "\n");
            Scanner scannerP = new Scanner(newS);
            while (scannerP.hasNextLine()) {
                String split = scannerP.nextLine();
                if(split.length()>3)
                    System.out.println(split);
            }
        } catch (FileNotFoundException e) {
        }
        System.out.println();

        System.out.println("Enter a message with #hashtags:");
        String message = sc.nextLine();
        Pattern hashP = Pattern.compile("#[a-zA-Z0-9_]+");
        Matcher hashM = hashP.matcher(message);
        System.out.println("Hashtags found:");
        boolean foundAny = false;
        while (hashM.find()) {
            System.out.println(hashM.group());
            foundAny = true;
        }
        if (!foundAny) {
            System.out.println("none");
        }
    }
}