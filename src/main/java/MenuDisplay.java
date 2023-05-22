import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Manages the retrieval of cars that are available for hire from
 * specified CSV file and displays them to the user.
 */
public class MenuDisplay {

    /**
     * Width of canvas to be used for drawing on screen.
     */
    private static final int SCREEN_WIDTH = 80;

    /**
     * Path of CSV file to be used.
     */
    private static final String FILE_PATH = "src/res/CarList.csv";

    /**
     * Red color for error messages and reset color to revert.
     */
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public MenuDisplay() {}


    /**
     * Displays a table of available cars, the number of cars available
     * and a note on insurance premium surcharge and returns the number of
     * cars available.
     * @return integer number of cars available.
     */
    int displayCarList() {

        // Table display, car count and cursor position variables.
        int carCount = 0;
        final int numColumns = 6;
        final int[] columnStart = {0, 10, 29, 38, 54, 69};
        final String[] columnName = {"Car No.", "Car Name", "Seats",
                "Transmission", "Car Type", "Rate/Day($)"};
        File CarList = new File(FILE_PATH);
        int cursorAt = 0;

        // Display welcome banner.
        displayBanner('*');
        whitespace(23);
        System.out.print("Welcome to Carrington Car Rentals\n");
        displayBanner('*');
        System.out.println("Cars currently available for hire:");
        displayBanner('-');

        // Display column headings.
        for (int i = 0; i < numColumns; i++) {
            int spaces = columnStart[i] - cursorAt;
            whitespace(spaces);
            cursorAt += spaces;
            System.out.print(columnName[i]);
            cursorAt += columnName[i].length();
        }
        System.out.println();
        cursorAt = 0;

        // Underline column headings.
        for (int i = 0; i < numColumns; i++) {
            int spaces = columnStart[i] - cursorAt;
            whitespace(spaces);
            cursorAt += spaces;
            for (int j = 0; j < columnName[i].length(); j++) {
                System.out.print("-");
                cursorAt++;
            }
        }
        System.out.println();
        cursorAt = 0;


        /*
         * Attempt to open car list CSV file. If file not found, access denied
         * or other exception, quit the program with appropriate error message.
         * If file successfully opened, read line-by-line, splitting with the
         * comma delimiter and display in tabular format. "cursorAt" value is
         * used to ensure text begins at the correct column start position, set
         * by array "columnStart".
         */
        if (!CarList.isFile()) {
            System.out.printf("%sError: file not found.%s%n",
                    ANSI_RED, ANSI_RESET);
            System.exit(1);
        } else {
            try {
                Scanner fileText = new Scanner(CarList);
                while (fileText.hasNextLine()) {
                    String line = fileText.nextLine();
                    String[] columnText = line.split(",");
                    for (int i = 0; i < numColumns; i++) {
                        int spaces = columnStart[i] - cursorAt;
                        whitespace(spaces);
                        cursorAt += spaces;
                        String text = columnText[i];
                        System.out.print(text);
                        cursorAt += text.length();
                    }
                    System.out.println();
                    carCount++;
                }
                fileText.close();
            } catch (FileNotFoundException e) {
                System.out.printf("%sError: file not found.%s%n",
                        ANSI_RED, ANSI_RESET);
                System.exit(1);
            } catch (SecurityException e) {
                System.out.printf("%sError: file access denied.%s%n",
                        ANSI_RED, ANSI_RESET);
                System.out.println(e.getMessage());
                System.exit(2);
            } catch (Exception e) {
                System.out.printf("%sError: an unknown error occurred.%s%n",
                        ANSI_RED, ANSI_RESET);
                System.out.println(e.getMessage());
                System.exit(3);
            }
        }

        // Display footer, number of cars available and insurance message.
        displayBanner('-');
        System.out.printf("There are %d cars available.%n", carCount);
        System.out.println("\nPlease note that for premium cars, an " +
                "additional 5% insurance premium surcharge\napplies on " +
                "top of the advertised rate, payable at booking confirmation.");
        System.out.println();
        displayBanner('~');

        return carCount;
    }

    // Helper method repeats specified character for width of screen
    private void displayBanner(char decoration) {
        for (int i = 0; i < SCREEN_WIDTH; i++) {
            System.out.print(decoration);
        }
        System.out.println();
    }

    // Helper method prints required number of spaces
    private void whitespace(int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            System.out.print(" ");
        }
    }

    /**
     * Prompt user to either make a booking or exit the system.
     * @return integer 1 to make a booking or 2 to exit.
     */
    int getSelection() {
        // Helper object
        Scanner keyboard = new Scanner(System.in);

        boolean validInput;
        int selection = 0;

        System.out.print("""
                Please select from the following options
                    
                        1. Proceed with booking
                        2. Exit
                         
                Enter your selection:\s""");

        do {
            String input = keyboard.nextLine();
            if (input.length() != 1) {
                System.out.printf("%n%sInvalid input: too many characters." +
                                "%s%n", ANSI_RED, ANSI_RESET);
                System.out.print("Enter a single character and press enter: ");
                validInput = false;
            } else {
                switch (input.charAt(0)) {
                    case '1':
                        validInput = true;
                        selection = 1;
                        break;
                    case '2':
                        validInput = true;
                        selection = 2;
                        break;
                    default:
                        System.out.printf("%n%sInvalid menu option, try again" +
                                        ".%s%n",ANSI_RED, ANSI_RESET);
                        System.out.print("Enter 1 to proceed or 2 to exit: ");
                        validInput = false;
                }
            }
        } while (!validInput);
        return selection;
    }
}
