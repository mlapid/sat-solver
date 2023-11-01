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

        Token token = this.currentToken;
        AST node;

        if (token.type.equals(TokenType.NOT)) {
            this.eat(TokenType.NOT);
            node = new UnaryOperator(token, this.literal());
        } else if (token.type.equals(TokenType.LPAREN)) {
            this.eat(TokenType.LPAREN);
            node = this.formula();
            this.eat(TokenType.RPAREN);
        } else {
            this.eat(TokenType.ATOM);
            node = new Atom(token);
        }
        System.out.println("Literal returning " + node.toString());
        return node;
    }

    AST clause() throws InvalidCharacterException, InvalidSyntaxException {
        /*clause : literal ((AND) literal)*"""*/

        AST node = this.literal();

        while (this.currentToken.type.equals(TokenType.AND)) {
            Token token = this.currentToken;
            this.eat(TokenType.AND);
            node = new BinaryOperator(node, token, this.literal());
        }
        System.out.println("Clause returning " + node.toString());
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
            Token token = this.currentToken;
            this.eat(TokenType.OR);
            node = new BinaryOperator(node, token, this.clause());
        }
        System.out.println("Formula returning " + node.toString());
        return node;
    }

    AST parse() throws InvalidCharacterException, InvalidSyntaxException {
        return this.formula();
    }
}
