import com.dyma.exeption.TicTacToeInvalideException;
import com.dyma.game.GuessGame;
import com.dyma.game.TicTacToe;
import com.dyma.ressources.Player;

import java.util.Random;
import java.util.Scanner;

/**
 * Class of the entrypoint of the Guess Game.
 */
public class Main {

    /**
     * Entry point of the Guess Game. Contains the main algorithm of the game.
     */
    public static void main(String[] args) {

        while (true){
            final var gamePlaying = scanNumber("Tu veux jouer à quoi ? GuessGame = 1 | TicTacToe = 2 | Quiter = 0");

            if (gamePlaying == 1){
                boolean guessGame = PlayGuessGame();
            }
            if (gamePlaying == 2){
                boolean ticTacToe = PlayTicTacToe();
            }
            if (gamePlaying == 0){
                break;
            }
        }
    }

    private static boolean PlayGuessGame() {

        var status = true;

        final var random = new Random();
        final var words = "abuser crottes fleches continental babiole etoile bougie coup coeur malade".split(" ");
        final var lifePoints = 10;
        var wordToGuess = words[random.nextInt(words.length)];
        var game = new GuessGame(wordToGuess, lifePoints);

        System.out.println("Début du jeu.");

        while(status) {
            System.out.println(game);
            final var letter = scanLetter("Entrez une lettre");

            game.guesseLetter(letter);
            if (game.isLost()){
                System.out.println(game);
                System.out.println("Perdu !");
            }
            if (game.isWon()){
                System.out.println(game);
                System.out.println("Gagné !");
            }

            if(game.isWon() || game.isLost()){
                System.out.println(game);
                var replayAnswer = scanLetter("Rejouer ? (y, Y, o, O)");
                if (replayAnswer == 'y' || replayAnswer == 'Y' || replayAnswer == 'o' || replayAnswer == 'O') {
                    wordToGuess = words[random.nextInt(words.length-1)];
                    game = new GuessGame(wordToGuess, lifePoints);
                } else {
                    status = false;
                }
            }
        }
        return status;
    }

    private static boolean PlayTicTacToe() {

        var status = true;

        final var game = new TicTacToe();

        var player = Player.FIRST;

        while (status) {
            try {
                System.out.println(game);
                System.out.println(player + " / Veuillez saisir un des chiffres [1-9] :");
                final var playerInput = TicTacToeInput();

                game.processInput(player, playerInput);
                if (game.checkWin()) {
                    System.out.println(game);
                    System.out.println("Le joueur " + player + " a gagné la partie ! :");
                    status = false;
                }
                if (game.checkDraw()) {
                    System.out.println(game);
                    System.out.println("Personne n'a gagné la partie :");
                    status = false;
                }

                player = nextPlayer(player);
            } catch (TicTacToeInvalideException e){
                System.out.println(e.getMessage());
            }
        }
        return status;
    }

    private static Player nextPlayer(Player player){
        if (player.equals(Player.FIRST)){
            return Player.SECOND;
        }
        else{
            return Player.FIRST;
        }
    }

    private static char scanLetter(String question) {
        var scanner = new Scanner(System.in);
            Character letter = null;
            do {
                System.out.println(question);
                var input = scanner.nextLine();
                if (input.length() == 1 && !isNumeric(input)) {
                    letter = input.charAt(0);
                }
            } while (letter == null);
            return letter;
    }

    private static double scanNumber(String question) {
        var scanner = new Scanner(System.in);
            Integer number = null;
            do{
                System.out.println(question);
                var input = scanner.nextLine();
                if (isInt(input)){
                    number = Integer.parseInt(String.valueOf(input.charAt(0)));
                }
            } while (number == null);
            return number;
    }

    private static int TicTacToeInput() throws TicTacToeInvalideException {
        final var scanner = new Scanner(System.in);
        var input =  scanner.nextInt();
        if(input < 1 || input > 9){
            throw new TicTacToeInvalideException("Le chiffre doit étre en 1 et 9 comprie");
        }
        return input;
    }

    /**
     * Check if the input is a number.
     * @param input The input the user gave us.
     * @return boolean true if the input is a number, false otherwise.
     */
    public static boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Check if the input is an integer.
     * @param input The input the user gave us.
     * @return boolean true if the input is an integer, false otherwise.
     */
    public static boolean isInt(String input) {
        if (input == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}