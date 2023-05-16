import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MenuDisplay {

    private static final int SCREEN_WIDTH = 80;
    private static final String filePath = "src/res/CarList.csv";

    public static int displayCarList() {

        int carCount = 0;
        final int[] columnStart = {0, 10, 33, 43, 60, 77};
        File CarList = new File(filePath);


        displayBanner('*');
        whitespace(23);
        System.out.print("Welcome to Carrington Car Rentals\n");
        displayBanner('*');

        if (!CarList.isFile()) {
            System.out.println("Error: file not found.");
            System.exit(1);
        } else {
            try {
                Scanner fileText = new Scanner(CarList);
                while (fileText.hasNextLine()) {
                    int cursorAt = 0;
                    String line = fileText.nextLine();
                    String[] columnText = line.split(",");
                    for (int i = 0; i < 6; i++) {
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
                System.out.println("Error: file not found.");
                System.exit(1);
            } catch (SecurityException e) {
                System.out.println("Error: file access denied.");
                System.out.println(e.getMessage());
                System.exit(2);
            } catch (Exception e) {
                System.out.println("Error: an unknown error occurred.");
                System.exit(3);
            }
        }
        return carCount;
    }






    public static void displayBanner(char decoration) {
        for (int i = 0; i < SCREEN_WIDTH; i++) {
            System.out.print(decoration);
        }
        System.out.println();
    }

    public static void whitespace(int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            System.out.print(" ");
        }
    }
}
