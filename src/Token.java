public class Token {

    public TokenType type;
    public String value;

    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public String toString() {
        return String.format("Token(%s, %s)", this.type, this.value);
    }
}
