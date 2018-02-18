package hangman;

/**
 * @author Ainsley Clark
 */
import java.util.Arrays;
import java.util.Scanner;

public class HangMan {

    static int guessesRemaining;
    static String stringWord;
    static char[] charWord;

    public static void playHangman() {

        System.out.print("Welcome to Hangman! ");
        System.out.println("Player 1: Enter a word");
        Scanner scan = new Scanner(System.in);
        stringWord = scan.nextLine();

        //Loop to get user to enter in letters only
        while (stringWord.matches(".*[^a-z].*")) {
            System.out.println("Please enter letters only, try again:");
            stringWord = scan.nextLine();
        }

        System.out.println("The length of the word is " + stringWord.length()); //Print out length of word.
        stringWord = stringWord.toLowerCase();

        int lettersRemaining = stringWord.length(); //Letters to be gussed is equal to the word length.

        //Create a new char array equal to the word length, add * to each index.
        charWord = new char[stringWord.length()];
        for (int i = 0; i < stringWord.length(); i++) {
            charWord[i] = ('\u002A');
        }
        int livesRemaining = 7; // 7 lives remaining!
        MainLoop:
        /*
        Main loop, gets user input using Scanner, ensures it is only a letter (not a number or special character)
        Converts string to char, uses the guess method which compares the secret word to the character entered and
        returns true if it found it, false if it didn't. 
        If found, letters remaining - 1, if the char array contains any asterisks, envoke the gameWon() method.
        If not found, lives remaining - 1, if no more lives, envoke the gameLost() method.
        */
        while (livesRemaining > 0) {
            System.out.println("Player 2: Guess a Letter:!");
            char guessLetter;
            String b = scan.next();
            while (b.matches(".*[^a-z].*")) {
                System.out.println("Please enter letters only, try again:");
                b = scan.next();
            }
            guessLetter = b.toLowerCase().charAt(0);
            String a = new Hangman2().print(guessLetter);
            if (guess(guessLetter, stringWord)) {
                lettersRemaining--;
                if (!a.contains("*")) {
                    gameWon();
                    break;
                }
                System.out.println("Well done, you guessed correctly! You have " + lettersRemaining + " letters remaining");
            } else {
                livesRemaining--;
                if (livesRemaining == 0) {
                    gameLost();
                }
                System.out.println("Too bad, you guessed incorrectly, you have " + livesRemaining + " lives remaining");
            }
        }
    }

    public String print(char c) {
        //Prints asterisks and replaces characters that have been guessed.
        String misses;
        char[] guess = stringWord.toCharArray();
        for (int i = 0; i < charWord.length; i++) {
            if (guess[i] == c) {
                charWord[i] = c;
            }
        }
        misses = Arrays.toString(charWord);
        System.out.print(misses + "  ");
        return misses;
    }

    public static boolean guess(char c, String s) {
        //Evaluates the chracter entered and compares it to the word, returns a boolean (correct or incorrect).
        boolean result = false;
        char[] a = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (a[i] == c) {
                result = true;
            }
        }
        return result;
    }

    public static void gameWon() {
        char yesno;
        boolean enter;
        System.out.print("Congratulations, you won!");
        Scanner input = new Scanner(System.in);
        System.out.println(" Would you like to play again? Y / N");
        do {
            yesno = input.next().toUpperCase().charAt(0);
            switch (yesno) {
                case 'Y':
                    playHangman();
                case 'N':
                    System.exit(0);
                default:
                    System.out.println("Please enter Y / N");
                    enter = false;
                    break;
            }
        } while (!enter);
    }

    public static void gameLost() {
        char yesno;
        boolean enter;
        System.out.print("Too bad, you lost...");
        Scanner input = new Scanner(System.in);
        System.out.println(" Would you like to play again? Y / N");
        do {
            yesno = input.next().toUpperCase().charAt(0);
            switch (yesno) {
                case 'Y':
                    playHangman();
                case 'N':
                    System.exit(0);
                default:
                    System.out.println("Please enter Y / N");
                    enter = false;
                    break;
            }
        } while (!enter);
    }

    public static void main(String[] args) {
        playHangman();
    }

}
