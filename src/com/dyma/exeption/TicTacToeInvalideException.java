package com.dyma.exeption;

public class TicTacToeInvalideException extends Throwable {
    public TicTacToeInvalideException() {
        super();
    }
    public TicTacToeInvalideException(String message) {
        super(message);
    }
}
