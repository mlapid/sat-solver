package Parser;

import BinaryTreeDiagram.Node;
import Lexer.Token;

public class Atom extends Node {

    Token token;

    Atom(Token token) {
        super(token.value);
        this.token = token;
    }

    public String toString() {
        return String.format("%s", this.token.value);
    }
}
