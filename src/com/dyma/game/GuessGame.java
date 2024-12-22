package com.dyma.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class responsible of representing the Guess Game. Provides methods to :
 * - validate if the game is won or lost
 * - validate if a given letter is considered discovered or not in the secret word
 */
public class GuessGame {

    /**
     * Stores the secret word that the player wants to discover
     */
    private final List<Character> secretWord = new ArrayList<>();

    /**
     * Stores the remaining number of life points.
     */
    private int lifePoints;

    /**
     * Stores letters discovered by the player. '_' stored for not discovered letters.
     */
    private final List<Character> guessWord = new ArrayList<>();

    /**
     * Stores letters that the player has used to try to discover the secret word.
     */
    private final Set<Character> guessedLetters = new HashSet<>();

    /**
     * Build a Guess Game object.
     * @param secretWord the secret word the player has to discover.
     * @param lifePoints the number of retries allowed to discover the secret word.
     */
    public GuessGame(String secretWord, int lifePoints) {
        for (char c : secretWord.toCharArray()) {
            this.secretWord.add(c);
        }
        this.lifePoints = lifePoints;
        for (int index = 0; index < secretWord.length(); index++) {
            this.guessWord.add('_');
        }
    }

    /**
     * Algorithm which verifies if a char given by the player is discovered in the secret word.
     * @param letter The letter to validate in `secretWord` and `guessWord`.
     */
    public void guesseLetter(char letter){
        var isGoodLetter = secretWord.contains(letter) && !guessWord.contains(letter);
        guessedLetters.add(letter);
        if (isGoodLetter) {
            var index = 0;
            for (char c : secretWord){
                if (c == letter) {
                    guessWord.set(index, c);
                }
                index++;
            }
        }
        else{
            lifePoints -= 1;
        }
    }

    /**
     * Check if the game is lost.
     * @return boolean true if the game is lost, false otherwise.
     */
    public boolean isLost() {
        return lifePoints <= 0;
    }

    /**
     * Check if the game is won.
     * @return boolean true is the game is won, false otherwise.
     */
    public boolean isWon(){
        return !guessWord.contains('_');
    }

    @Override
    public String toString() {
        return "mot à deviner : " + guessWord +
                " | points de vie : " + lifePoints +
                " | lettres essayées : " + guessedLetters;
    }
}
