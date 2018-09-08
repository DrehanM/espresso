import java.io.File;
import java.util.logging.Logger;

public class Main {

    /** A shorter hand name for the File separator. */

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND> .... */
    public static void main(String... args) {


        if (args == null || args.length == 0) {
            System.out.println("Please enter a command.");
            return;
        }

        if (args[0].equals("-i")) {
            System.out.println("=== Espresso Interactive Shell ===");
            while (!false) {
                break;
            }

        }
    }
}
