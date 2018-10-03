package esp;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void testDatabaseConstructor() {
        Database db1 = new Database();

        assertEquals(null, db1.get("not in database"));

        try {
            db1.put(null, null);
            fail("IllegalArgumentException should have been thrown. "
                    + "Arguments are null.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testPutMethod() {
        Database db1 = new Database();

        String[] columnTitles1 = new String[]
        {"SID", "Last Name", "First Name", "Major"};
        Table table1 = new Table(columnTitles1);
        String[] student1 = new String[]
        {"00001", "Mahaarachchi", "Dre", "EECS"};
        String[] student2 = new String[]
        {"99999", "Jobs", "Steve", "MET"};
        String[] student3 = new String[]
        {"54998", "Kh", "Tina", "ArtHistory"};
        String[] student4 = new String[]
        {"00002", "Mahaarachchi", "Shanara", "PreMed"};
        table1.add(student1);
        table1.add(student2);
        table1.add(student3);
        table1.add(student4);

        db1.put("Table 1", table1);
        assertEquals(table1, db1.get("Table 1"));

        Table table2 = new Table(new String[] {"a", "b", "c", "d"});
        Table table3 = new Table(new String[] {"e", "f", "g", "h", "i"});
        Table table4 = null;

        db1.put("Table 2", table2);
        db1.put("Table 3", table3);

        try {
            db1.put("Table 4", table4);
            fail("IllegalArgumentException should have been thrown. "
                    + "Table argument is null.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        assertEquals(table3, db1.get("Table 3"));
        assertEquals(table2, db1.get("Table 2"));

        Table table5 = new Table(new String[] {"j", "k", "l"});
        db1.put("Table 2", table5);
        assertEquals(table5, db1.get("Table 2"));
    }

    @Test
    public void testDatabaseGetMethod() {
        Database db1 = new Database();

        String[] columnTitles1 = new String[]
        {"SID", "Last Name", "First Name", "Major"};
        Table table1 = new Table(columnTitles1);
        String[] student1 = new String[]
        {"00001", "Mahaarachchi", "Dre", "EECS"};
        String[] student2 = new String[]
        {"99999", "Jobs", "Steve", "MET"};
        String[] student3 = new String[]
        {"54998", "Kh", "Tina", "ArtHistory"};
        String[] student4 = new String[]
        {"00002", "Mahaarachchi", "Shanara", "PreMed"};
        table1.add(student1);
        table1.add(student2);
        table1.add(student3);
        table1.add(student4);

        Table table2 = new Table(new String[] {"a", "b", "c", "d"});
        Table table3 = new Table(new String[] {"e", "f", "g", "h", "i"});

        db1.put("Table 1", table1);
        db1.put("Table 2", table2);
        db1.put("Table 3", table3);

        assertEquals(table1, db1.get("Table 1"));
        assertEquals(table2, db1.get("Table 2"));
        assertEquals(table3, db1.get("Table 3"));
        assertEquals(null, db1.get("Table 4"));
    }
}
