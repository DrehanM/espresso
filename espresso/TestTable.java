import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Drehan on 9/5/18.
 */
public class TestTable {

    @Test
    public void testColumnGet() {
        Column intColumn = new Column("Integers", Integer.class);
        intColumn.put(1);
        intColumn.put(2);
        intColumn.put(1);

        Column strColumn = new Column("Strings", String.class);
        strColumn.put("foo");
        strColumn.put("barrr");
        strColumn.put("element3");
        strColumn.put("124");


    }

    @Test(expected = IllegalArgumentException.class)
    public void testIndexOutOfBoundsException() {

    }
}
