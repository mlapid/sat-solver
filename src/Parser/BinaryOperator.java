package Parser;

import Lexer.Token;

public class BinaryOperator extends AST {

    AST left;
    Token token;
    AST right;

    BinaryOperator(AST left, Token token, AST right) {
        this.left = left;
        this.token = token;
        this.right = right;
    }

    public String toString() {
        return String.format("%s %s %s", this.left, this.token.value, this.right);
    }
}
