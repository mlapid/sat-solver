import java.util.Scanner;
import java.io.PrintStream;

public class Solver {

    final Scanner in;
    final PrintStream out;

    Solver() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }

    void start() throws InvalidCharacterException, InvalidSyntaxException {

        while (true) {
            out.print("solve> ");
            String text = in.nextLine();

            if (text.equals("exit")) {
                break;
            }

            Lexer lexer = new Lexer(text);
            Parser parser = new Parser(lexer);
            AST ast = parser.parse();
            out.println("Parser returning " + ast.toString());
        }
    }

    public static void main(String[] args) throws InvalidCharacterException, InvalidSyntaxException {
        new Solver().start();
    }

}