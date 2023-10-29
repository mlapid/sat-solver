public class Lexer {

    String text;
    private int index;
    private Character currentChar;

    Lexer(String text) {
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
        while (Character.isAlphabetic(this.currentChar)) {
            result = result.concat(this.currentChar.toString());
        }
        return result;
    }

    Token getNextToken() throws InvalidCharacterException {
        while (this.index < this.text.length()) {
            System.out.println(Character.getName(this.currentChar));

            this.skipWhitespace(); // Loops zero or more times

            if (Character.isAlphabetic(this.currentChar)) {
                return new Token(TokenType.ATOM, this.atom());
            }

            if (this.currentChar.equals('¬')) {
                return new Token(TokenType.NOT, this.currentChar.toString());
            }

            if (this.currentChar.equals('∧')) {
                return new Token(TokenType.AND, this.currentChar.toString());
            }

            if (this.currentChar.equals('∨')) {
                return new Token(TokenType.OR, this.currentChar.toString());
            }

//            if (this.currentChar.equals('⇒') || this.currentChar.equals('→')) {
//                return new Token(TokenType.IF, this.currentChar.toString());
//            }

            this.error();
        }
        return new Token(TokenType.NAN, "");
    }
}
