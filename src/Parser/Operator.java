package Parser;

public class Operator extends AST {

    int precedenceLevel;

    Operator(int precedenceLevel) {
        this.precedenceLevel = precedenceLevel;
    }
}
