public class Atom implements AST {

    Token token;

    Atom(Token token) {
        this.token = token;
    }

    public String toString() {
        return String.format("%s", this.token.value);
    }
}
