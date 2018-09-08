import javax.xml.crypto.Data;
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

        try {
            File dbFile = new File(args[0]);
            if (!dbFile.exists() && !args[1].equals("init")) {
                System.out.println(args[0] + " does not exist.");
            } else if (args[1].equals("init")) {
                Database db = new Database();
            }


        }
    }
}
