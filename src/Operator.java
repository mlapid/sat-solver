public class Operator implements AST {

    int precedenceLevel;

    Operator(int precedenceLevel) {
        this.precedenceLevel = precedenceLevel;
    }
}
