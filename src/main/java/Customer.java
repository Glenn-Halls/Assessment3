import java.util.Scanner;

public class Customer {

    // Instance variables
    private final String customerName;
    private final String customerEmail;
    private final String customerAddress;

    // Constants for error highlighting text color.
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    // Parameterised constructor to create customer object.
    public Customer(String name, String email, String address) {
        customerName = name;
        customerEmail = email;
        customerAddress = address;
    }

    // Non-parameterised constructor uses getString() method to receive input.
    public Customer() {
        this(
                getString("name"),
                getString("email", '@', '.'),
                getString("address")
        );
    }

    // Accessor methods for Customer object
    String getCustomerName() {return customerName;}
    String getCustomerEmail() {return customerEmail;}
    String getCustomerAddress() {return customerAddress;}

    /**
     * Helper method for non-parameterised constructor. Prompts user to input
     * "requiredField" and displays helper error messages in the case of
     * invalid user input.
     * @param requiredField required user input field: name / address / email
     * @param args required characters for input field such as '@' for email
     * @return String
     */
    public static String getString(String requiredField, char... args) {
        // Helper object.
        Scanner keyboard = new Scanner(System.in);
        // Booleans must be true for input to be valid.
        boolean validInput;
        boolean requiredChars = true;
        // Output string.
        String output;

        /*
         * While loop will prompt user to enter required information and provide
         * relevant error messages and prompt user to try again if input is
         * invalid.
         */
        System.out.printf("Please enter your %s: ", requiredField);
        do {
            String input = keyboard.nextLine();
            for (char arg : args) {
                if (input.indexOf(arg) == -1) {
                    System.out.printf("%sError: your %s input should contain"
                                    + " a '%c'%s%n", ANSI_RED, requiredField,
                                    arg, ANSI_RESET);
                    requiredChars = false;
                    break;
                } else {
                    requiredChars = true;
                }
            }
            if ((input.length() < 1) || !requiredChars) {
                System.out.print("Please try again: ");
                output = "";
                validInput = false;
            } else {
                validInput = true;
                output = input;
            }
        } while (!validInput);
        return output;
    }
}
