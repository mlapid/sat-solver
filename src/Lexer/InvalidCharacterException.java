package Lexer;

public class InvalidCharacterException extends Exception {
    public InvalidCharacterException(String errorMessage) {
        super(errorMessage);
    }
}
