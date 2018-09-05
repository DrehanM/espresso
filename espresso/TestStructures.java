import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

import Column.*;


/**
 * Created by Drehan on 10/11/17.
 */
public class TestStructures {


    /** Column Class Tests */
    @Test
    public void testColumn() {
        Column col1 = new Column("column 1", Integer);
        col1.put(1);
        col1.put(2);
        col1.put(1);

        assertEquals(col1.length(), 3);
    }
}