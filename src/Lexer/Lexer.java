package Lexer;

public class Lexer {

    private final String text;
    private int index;
    private Character currentChar;

    public Lexer(String text) {
        this.text = text;
        this.index = 0;
        this.currentChar = this.text.charAt(this.index);
    }

    void error() throws InvalidCharacterException {
        throw new InvalidCharacterException("Invalid character");
    }

    void advance() {
        this.index += 1;
        if (this.index > this.text.length() - 1) {
            this.currentChar = null; // Indicates end of input
            return;
        }
        this.currentChar = this.text.charAt(this.index);
    }

    void skipWhitespace() {
        while (Character.getName(this.currentChar).equals("SPACE")) {
            this.advance();
        }
    }

    String atom() {
        String result = "";
        while (this.currentChar != null && Character.isAlphabetic(this.currentChar)) {
            result = result.concat(this.currentChar.toString());
            this.advance();
        }
        return result;
    }

    public Token getNextToken() throws InvalidCharacterException {
        while (this.currentChar != null) {
            //System.out.println(Character.getName(this.currentChar));

            this.skipWhitespace(); // Loops zero or more times

            if (Character.isAlphabetic(this.currentChar)) {
                return new Token(TokenType.ATOM, this.atom());
            }

            if (this.currentChar.equals('¬')) {
                this.advance();
                return new Token(TokenType.NOT, "¬");
            }

            if (this.currentChar.equals('∧')) {
                this.advance();
                return new Token(TokenType.AND, "∧");
            }

            if (this.currentChar.equals('∨')) {
                this.advance();
                return new Token(TokenType.OR, "∨");
            }

            if (this.currentChar.equals('⟹')) {
                this.advance();
                return new Token(TokenType.IF, "⟹");
            }

            if (this.currentChar.equals('⟺')) {
                this.advance();
                return new Token(TokenType.IFF, "⟺");
            }

            if (this.currentChar.equals('(')) {
                this.advance();
                return new Token(TokenType.LPAREN, "(");
            }

            if (this.currentChar.equals(')')) {
                this.advance();
                return new Token(TokenType.RPAREN, ")");
            }

            this.error();
        }
        return new Token(TokenType.EOF, null);
    }
}
