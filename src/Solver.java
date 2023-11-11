import BinaryTreeDiagram.ChildrenExceededException;
import Lexer.Lexer;
import Lexer.InvalidCharacterException;
import Parser.Parser;
import Parser.InvalidSyntaxException;
import Parser.AST;
import BinaryTreeDiagram.BinaryTreePrinter;

import java.util.Scanner;
import java.io.PrintStream;

public class Solver {

    final Scanner in;
    final PrintStream out;

    Solver() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }

    void start() throws InvalidCharacterException, InvalidSyntaxException, ChildrenExceededException {

        while (true) {
            out.print("solve> ");
            String text = in.nextLine();

            if (text.equals("exit")) {
                break;
            }

            Lexer lexer = new Lexer(text);
            Parser parser = new Parser(lexer);
            AST ast = parser.parse();
            BinaryTreePrinter printTree = new BinaryTreePrinter(ast);
            printTree.print(this.out);
        }
    }

    public static void main(String[] args) throws InvalidCharacterException, InvalidSyntaxException, ChildrenExceededException {
        new Solver().start();
    }

}