import org.junit.Test;

import static org.junit.Assert.*;



/**
 * Created by Drehan on 10/11/17.
 */
public class TestColumn {

    /** Column Class Tests */
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

        assertEquals(intColumn.length(), 3);
        assertEquals(strColumn.length(), 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIndexOutOfBoundsException() {
        Column intColumn = new Column("Integers", Integer.class);
        intColumn.put(1);
        intColumn.put(2);
        intColumn.put(1);

        intColumn.put("error string");

    }
}