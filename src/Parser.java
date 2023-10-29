public class Parser {

    Lexer lexer;
    Token currentToken;

    Parser(Lexer lexer) throws InvalidCharacterException {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
    }

    void error() throws InvalidSyntaxException {
        throw new InvalidSyntaxException("Invalid syntax");
    }

    void eat(TokenType tokenType) throws InvalidCharacterException, InvalidSyntaxException {
        if (this.currentToken.type.equals(tokenType)) {
            this.currentToken = this.lexer.getNextToken();
        } else {
            this.error();
        }
    }

    AST literal() throws InvalidCharacterException, InvalidSyntaxException {
        /*literal : (¬) literal | ATOM | LPAREN formula RPAREN*/

        AST node;

        if (this.currentToken.type.equals(TokenType.NOT)) {
            this.eat(TokenType.NOT);
            node = new UnaryOperator(this.currentToken, this.literal());
        } else {
            this.eat(TokenType.ATOM);
            node = new Atom(this.currentToken);
        }
        return node;
    }

    AST clause() throws InvalidCharacterException, InvalidSyntaxException {
        /*clause : literal ((AND) literal)*"""*/

        AST node = this.literal();

        while (this.currentToken.type.equals(TokenType.AND)) {
            this.eat(TokenType.AND);
            node = new BinaryOperator(node, this.currentToken, this.literal());
        }
        return node;
    }

    AST formula() throws InvalidCharacterException, InvalidSyntaxException {
        /*
        formula: clause ((OR) clause)*
        clause : literal ((AND) literal)*
        literal: (¬) literal | ATOM | LPAREN formula RPAREN
         */

        AST node = this.clause();

        while (this.currentToken.type.equals(TokenType.OR)) {
            this.eat(TokenType.OR);
            node = new BinaryOperator(node, this.currentToken, this.clause());
        }
        return node;
    }

    AST parse() throws InvalidCharacterException, InvalidSyntaxException {
        return this.formula();
    }
}
