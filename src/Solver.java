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
            parser.parse();
            //out.println("Need to determine the validity of " + text);
        }
    }

    public static void main(String[] args) throws InvalidCharacterException, InvalidSyntaxException {
        new Solver().start();
    }

}