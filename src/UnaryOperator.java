public class UnaryOperator implements AST {

    Token token;
    AST formula;
    UnaryOperator(Token token, AST formula) {
        this.token = token;
        this.formula = formula;
    }
}
