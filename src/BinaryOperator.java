public class BinaryOperator implements AST {

    AST left;
    Token token;
    AST right;

    BinaryOperator(AST left, Token token, AST right) {
        this.left = left;
        this.token = token;
        this.right = right;
    }
}
