package Parser;

import Lexer.Token;

public class Atom extends AST {

    Token token;

    Atom(Token token) {
        this.token = token;
    }

    public String toString() {
        return String.format("%s", this.token.value);
    }
}
