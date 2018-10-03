package esp;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConditionTest {
    @Test
    public void testConditionAllComponents() {
        Table table1 = new Table(new String[] {"alpha", "bravo", "charlie"});
        Table table2 = new Table(new String[] {"alpha", "beta", "gamma"});
        Table table3 = new Table(new String[] {"one", "two", "three"});


        table1.add(new String[] {"1", "2", "3"});
        table1.add(new String[] {"4", "5", "6"});
        table2.add(new String[] {"1", "2", "3"});
        table2.add(new String[] {"4", "5", "6"});

        Column col1 = new Column("alpha", table1, table2);
        Column col2 = new Column("alpha", table1, table2);

        Condition cond1 = new Condition(col1, "=", "1");
        Condition cond2 = new Condition(col1, "=", col2);

        assertTrue(cond1.test(0));
        assertTrue(cond2.test(0));
    }
}
