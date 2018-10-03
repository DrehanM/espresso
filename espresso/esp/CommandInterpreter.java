package esp;

import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Scanner;

import static esp.Utils.*;

/** An object that reads and interprets a sequence of commands from an
 *  input source. */
class CommandInterpreter {

    /** A new CommandInterpreter executing commands read from INP, writing
     *  prompts on PROMPTER, if it is non-null. */
    CommandInterpreter(Scanner inp, PrintStream prompter) {
        _input = new Tokenizer(inp, prompter);
        _database = new Database();
    }

    /** Parse and execute one statement from the token stream.  Return true
     *  iff the command is something other than quit or exit. */
    boolean statement() {
        switch (_input.peek()) {
        case "create":
            createStatement();
            break;
        case "load":
            loadStatement();
            break;
        case "exit": case "quit":
            exitStatement();
            return false;
        case "*EOF*":
            return false;
        case "insert":
            insertStatement();
            break;
        case "print":
            printStatement();
            break;
        case "select":
            selectStatement();
            break;
        case "store":
            storeStatement();
            break;
        default:
            throw error("unrecognizable command");
        }
        return true;
    }

    /** Parse and execute a create statement from the token stream. */
    void createStatement() {
        _input.next("create");
        _input.next("table");
        String name = name();
        Table table = tableDefinition();
        _database.put(name, table);
        _input.next(";");
    }

    /** Parse and execute an exit or quit statement. Actually does nothing
     *  except check syntax, since statement() handles the actual exiting. */
    void exitStatement() {
        if (!_input.nextIf("quit")) {
            _input.next("exit");
        }
        _input.next(";");
    }

    /** Parse and execute an insert statement from the token stream. */
    void insertStatement() {
        _input.next("insert");
        _input.next("into");
        Table table = tableName();
        _input.next("values");
        int cols = table.columns();

        while (true) {
            String[] values = new String[cols];
            int k = 1;
            _input.next("(");
            values[0] = literal();
            while (_input.nextIf(",")) {
                if (k >= cols) {
                    throw error("inserted literals exceeds row capacity");
                } else {
                    values[k] = literal();
                    k += 1;
                }
            }
            _input.next(")");
            table.add(values);
            if (!_input.nextIf(",")) {
                break;
            }
        }
        _input.next(";");
    }

    /** Parse and execute a load statement from the token stream. */
    void loadStatement() {
        _input.next("load");
        String name = name();
        Table table = Table.readTable(name);
        _database.put(name, table);
        _input.next(";");
        System.out.printf("Loaded %s.db%n", name);
    }

    /** Parse and execute a store statement from the token stream. */
    void storeStatement() {
        _input.next("store");
        String name = _input.peek();
        Table table = tableName();
        table.writeTable(name);
        System.out.printf("Stored %s.db%n", name);
        _input.next(";");
    }

    /** Parse and execute a print statement from the token stream. */
    void printStatement() {
        _input.next("print");
        String tableName = _input.peek();
        Table table = tableName();
        System.out.printf("Contents of %s:%n", tableName);
        table.print();
        _input.next(";");
    }

    /** Parse and execute a select statement from the token stream. */
    void selectStatement() {
        Table table = selectClause();
        System.out.println("Search results:");
        table.print();
        _input.next(";");
    }

    /** Parse and execute a table definition, returning the specified
     *  table. */
    Table tableDefinition() {
        Table table;
        ArrayList<String> colNames = new ArrayList<String>();
        if (_input.nextIf("(")) {
            colNames.add(columnName());
            while (_input.nextIf(",")) {
                colNames.add(columnName());
            }
            table = new Table(colNames);
            _input.next(")");
        } else {
            _input.next("as");
            table = selectClause();
        }
        return table;
    }

    /** Parse and execute a select clause from the token stream, returning the
     *  resulting table. */
    Table selectClause() {
        _input.next("select");
        ArrayList<String> colNames = new ArrayList<>();
        ArrayList<Condition> conds;
        Table table;
        colNames.add(columnName());
        while (_input.nextIf(",")) {
            colNames.add(columnName());
        }

        _input.next("from");
        Table fromTable1 = tableName();
        if (_input.nextIf(",")) {
            Table fromTable2 = tableName();
            conds = conditionClause(fromTable1, fromTable2);
            table = fromTable1.select(fromTable2, colNames, conds);
        } else {
            conds = conditionClause(fromTable1);
            table = fromTable1.select(colNames, conds);
        }

        return table;
    }

    /** Parse and return a valid name (identifier) from the token stream. */
    String name() {
        return _input.next(Tokenizer.IDENTIFIER);
    }

    /** Parse and return a valid column name from the token stream. Column
     *  names are simply names; we use a different method name to clarify
     *  the intent of the code. */
    String columnName() {
        return name();
    }

    /** Parse a valid table name from the token stream, and return the Table
     *  that it designates, which must be loaded. */
    Table tableName() {
        String name = name();
        Table table = _database.get(name);
        if (table == null) {
            throw error("unknown table: %s", name);
        }
        return table;
    }

    /** Parse a literal and return the string it represents (i.e., without
     *  single quotes). */
    String literal() {
        String lit = _input.next(Tokenizer.LITERAL);
        return lit.substring(1, lit.length() - 1).trim();
    }

    /** Parse and return a list of Conditions that apply to TABLES from the
     *  token stream.  This denotes the conjunction (`and') of zero
     *  or more Conditions. */
    ArrayList<Condition> conditionClause(Table... tables) {
        ArrayList<Condition> conds = new ArrayList<Condition>();
        Condition cond;
        if (_input.nextIf("where")) {
            cond = condition(tables);
            conds.add(cond);
            while (_input.nextIf("and")) {
                cond = condition(tables);
                conds.add(cond);
            }
        }
        return conds;
    }

    /** Parse and return a Condition that applies to TABLES from the
     *  token stream. */
    Condition condition(Table... tables) {
        Column col1 = new Column(columnName(), tables);
        String relation = _input.next();
        Condition cond;
        if (_input.nextIs(Tokenizer.LITERAL)) {
            cond = new Condition(col1, relation, literal());
        } else {
            Column col2 = new Column(columnName(), tables);
            cond = new Condition(col1, relation, col2);
        }
        return cond;
    }

    /** Advance the input past the next semicolon. */
    void skipCommand() {
        while (true) {
            try {
                while (!_input.nextIf(";") && !_input.nextIf("*EOF*")) {
                    _input.next();
                }
                return;
            } catch (DBException excp) {
                /* No action */
            }
        }
    }

    /** The command input source. */
    private Tokenizer _input;
    /** Database containing all tables. */
    private Database _database;
}
