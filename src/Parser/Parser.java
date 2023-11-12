package Parser;

import BinaryTreeDiagram.ChildrenExceededException;
import BinaryTreeDiagram.Node;
import Lexer.Lexer;
import Lexer.Token;
import Lexer.TokenType;
import Lexer.InvalidCharacterException;

public class Parser {

    Lexer lexer;
    Token currentToken;
    AST ast;

    public Parser(Lexer lexer) throws InvalidCharacterException {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
        this.ast = new AST();
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

    Node literal() throws InvalidCharacterException, InvalidSyntaxException {
        /*literal : (¬) literal | ATOM | LPAREN formula RPAREN*/

        Token token = this.currentToken;
        Node node;

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
        return node;
    }

    Node clause() throws InvalidCharacterException, InvalidSyntaxException {
        /*clause : literal ((AND) literal)*"""*/

        Node node = this.literal();

        while (this.currentToken.type.equals(TokenType.AND) || this.currentToken.type.equals(TokenType.OR)) {
            Token token = this.currentToken;
            if (token.type.equals(TokenType.AND)) {
                this.eat(TokenType.AND);
            } else {
                this.eat(TokenType.OR);
            }
            node = new BinaryOperator(node, token, this.literal());
        }
        return node;
    }

    Node formula() throws InvalidCharacterException, InvalidSyntaxException {
        /*
        formula: clause ((OR) clause)*
        clause : literal ((AND) literal)*
        literal: (¬) literal | ATOM | LPAREN formula RPAREN
         */

        Node node = this.clause();

        while (this.currentToken.type.equals(TokenType.IF) || this.currentToken.type.equals(TokenType.IFF)) {
            Token token = this.currentToken;
            if (token.type.equals(TokenType.IF)) {
                this.eat(TokenType.IF);
            } else {
                this.eat(TokenType.IFF);
            }
            node = new BinaryOperator(node, token, this.clause());
        }
        return node;
    }

    public AST parse() throws InvalidCharacterException, InvalidSyntaxException, ChildrenExceededException {
        ast.addNode(ast.root, this.formula());
        return this.ast;
    }
}
