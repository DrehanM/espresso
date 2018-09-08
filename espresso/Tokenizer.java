import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Scanner;

class Tokenizer {

    private static final String
            LITERAL_TEXT = "'(?:[^,'\n\r]*)'?",
            IDENTIFIER_TEXT = "[\\p{Alpha}_]\\w*",
            COMMENT_TEXT = "(?:/\\*.*?\\*/|/\\*.*)";

    private static final Pattern
            TOKEN_PATN = mkPatn("(?s)[<>!]?=|%s|%s|%s|\r?\n|\\S",
            LITERAL_TEXT, IDENTIFIER_TEXT, COMMENT_TEXT);

    static final Pattern
            IDENTIFIER = mkPatn(IDENTIFIER_TEXT),
            LITERAL = mkPatn("'.*"),
            RELATION = mkPatn("[<>!]?=|[<>]");

    Tokenizer(Scanner s, PrintStream prompter) {
        _input = s;
        _buffer = new ArrayList<>();
        _prompter = prompter;
        _continued = false;
        _shouldPrompt = true;
        _k = 0;
        _mat = Pattern.compile(".").matcher("");
    }

    private void readToken() {
        while (true) {
            prompt();
            String token = _input.findWithinHorizon(TOKEN_PATN, 0);
            if (token == null) {
                token = "*EOF*";
            } else if (token.startsWith("'")) {
                if (token.length() == 1 || !token.endsWith("'")) {
                    throw new Error("unterminated literal constant");
                }
            } else if (token.startsWith("/*")) {
                if (token.length() < 4 || !token.endsWith("*/")) {
                    throw new Error("unterminated comment");
                }
                continue;
            } else if (token.endsWith("\n")) {
                _shouldPrompt = true;
                continue;
            }
            _buffer.add(token);
            _continued = !token.equals(";");
            return;
        }
    }

    private void prompt() {
        if (_shouldPrompt && _prompter != null) {
            if (_continued) {
                _prompter.print("...");
            } else {
                _prompter.print("> ");
            }
            _prompter.flush();
            _shouldPrompt = false;
        }
    }

    void newCommand() {
        _continued = true;
    }


    String next(Pattern p) {
        if (!nextIs(p)) {
            if (nextIs("*EOF*")) {
                throw new Error("unexpected end of input");
            } else {
                throw new Error("unexpected token: '%s'", peek());
            }
        }
        return next();
    }

    String next(String p) {
        if (!nextIs(p)) {
            if (nextIs("*EOF*")) {
                throw new Error("unexpected end of input");
            } else {
                throw new Error("unexpected token: '%s'");
            }
        }
        return next();
    }

    boolean nextIf(Pattern p) {
        if (nextIs(p)) {
            next();
            return true;
        }
        return false;
    }

    boolean nextIf(String p) {
        if (nextIs(p)) {
            next();
            return true;
        }
        return false;
    }

    boolean nextIs(Pattern p) {
        String token = peek();
        return _mat.usePattern(p).reset(token).matches();
    }

    boolean nextIs(String p) {
        String token = peek();
        return token.equals(p);
    }

    String next() {
        if (_k == _buffer.size()) {
            readToken();
        }
        _k += 1;
        return _buffer.get(_k - 1);
    }

    String peek() {
        while (_k >= _buffer.size()) {
            readToken();
        }
        return _buffer.get(_k);
    }

    void rewind() {
        _k = 0;
    }

    void flush() {
        _buffer.subList(0, _k).clear();
        _k = 0;
    }

    void flushToSemi() {
        rewind();
        while (true) {
            try {
                newCommand();
                String token = next();
                if (token == null || token.equals(";")) {
                    break;
                }
            } catch (Exception e) {
                /* Ignore Exception */
            }
        }
        flush();
        newCommand();
    }


    /** Matcher used for pattern matching. */
    private Matcher _mat;
    /** The character input source. */
    private Scanner _input;
    /** All tokens read since the last flush or beginning of input. */
    private ArrayList<String> _buffer;
    /** Output for prompts.  Null if prompts not used. */
    private PrintStream _prompter;
    /** False iff the next token is expected to start a command. */
    private boolean _continued;
    /** True iff prompt is needed for the next token. */
    private boolean _shouldPrompt;
    /** Current position in the token stream as an offset within _buffer. */
    private int _k;
}