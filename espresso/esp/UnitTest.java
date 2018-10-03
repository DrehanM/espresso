package esp;

import ucb.junit.textui;

public class UnitTest {
    /** Run the JUnit tests in this package.  */
    public static void main(String[] ignored) {
        textui.runClasses(TableTest.class, DatabaseTest.class,
                ConditionTest.class);
    }
}

