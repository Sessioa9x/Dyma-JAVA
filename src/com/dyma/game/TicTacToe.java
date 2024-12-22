package com.dyma.game;

import com.dyma.exeption.TicTacToeInvalideException;
import com.dyma.ressources.Player;

import static com.dyma.ressources.StringConstant.LINE_SEPARATOR;
import static com.dyma.ressources.StringConstant.SPACE;

public class TicTacToe {
    private char[][] grid = new char[][]{
            {'.', '.', '.'},
            {'.', '.', '.'},
            {'.', '.', '.'}
    };

    public void processInput(Player player, int playerInput) throws TicTacToeInvalideException {
        final var row = (playerInput - 1) /3 ;
        final var column = (playerInput - 1) % 3;
        if (grid[row][column] == '.'){
            if (player.equals(Player.FIRST)){
                grid[row][column] = 'X';
            }
            else{
                grid[row][column] = 'O';
            }
        } else {
            throw new TicTacToeInvalideException("La case est déjà remplie");
        }
    }

    public boolean checkWin(){
        for(int i = 0; i < 3; i++){
            var checkWinLine = grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][2] != '.';
            var checkWinColumn = grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] && grid[2][i] != '.';
            if (checkWinLine || checkWinColumn){
                return true;
            }
        }
        var checkWinDiagonal1 = grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[2][2] != '.';
        var checkWinDiagonal2 = grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[2][0] != '.';
        return checkWinDiagonal1 || checkWinDiagonal2;
    }

    public boolean checkDraw(){
        for(char[] row : grid){
            for (char cell : row){
                if (cell == '.'){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        final var builder = new StringBuilder();
        builder.append("Grille du Morpion : ").append(LINE_SEPARATOR);
        for (char[] line : grid){
            for (char cell : line){
                builder.append(SPACE).append(cell).append(SPACE);
            }
            builder.append(LINE_SEPARATOR);
        }
        return builder.toString();
    }
}
