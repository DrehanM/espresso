package esp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class TableTest {

    @Test
    public void testTableConstructor() {
        String[] columnTitles1 = new String[] {"0", "1", "0", "3"};
        try {
            Table table1 = new Table(columnTitles1);
            fail("Exception should have been thrown! Duplicate column names.");
        } catch (DBException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testColumnsMethod() {
        String[] columnTitles1 = new String[] {"0", "1", "2", "3"};
        Table table1 = new Table(columnTitles1);
        int expectedColumns1 = 4;
        int actualColumns1 = table1.columns();
        assertEquals(expectedColumns1, actualColumns1);

        String[] columnTitles2 = new String[]
        {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        Table table2 = new Table(columnTitles2);
        int expectedColumns2 = 10;
        int actualColumns2 = table2.columns();
        assertEquals(expectedColumns2, actualColumns2);
    }

    @Test
    public void testGetTitleMethod() {
        String[] columnTitles1 = new String[] {"0", "1", "2", "3"};
        Table table1 = new Table(columnTitles1);
        String expectedTitle1 = "2";
        String actualTitle1 = table1.getTitle(2);
        assertTrue(expectedTitle1.equals(actualTitle1));

        try {
            String actualTitle2 = table1.getTitle(5);
            fail("IndexOutOfBoundsException should have been thrown!");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }

        try {
            String actualTitle3 = table1.getTitle(-1);
            fail("IndexOutOfBoundsException should have been thrown!");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testFindColumnMethod() {
        String[] columnTitles1 = new String[]
        {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        Table table1 = new Table(columnTitles1);
        int expectedIndex1 = 3;
        int actualIndex1 = table1.findColumn("d");
        assertEquals(expectedIndex1, actualIndex1);

        int expectedIndex2 = 9;
        int actualIndex2 = table1.findColumn("j");
        assertEquals(expectedIndex2, actualIndex2);

        int expectedIndex3 = -1;
        int actualIndex3 = table1.findColumn("not in columns");
        assertEquals(expectedIndex3, actualIndex3);
    }

    @Test
    public void testSizeMethod() {
        String[] columnTitles1 = new String[] {"0", "1", "2", "3"};
        Table table1 = new Table(columnTitles1);
        int expectedSize1 = 0;
        int actualSize1 = table1.size();
        assertEquals(expectedSize1, actualSize1);

        String[] student1 = new String[]
        {"00001", "Mahaarachchi", "Dre", "EECS"};
        String[] student2 = new String[]
        {"99999", "Jobs", "Steve", "MET"};
        String[] student3 = new String[]
        {"54998", "Kh", "Tina", "Art History"};
        String[] student4 = new String[]
        {"00002", "Mahaarachchi", "Shanara", "PreMed"};

        table1.add(student1);
        assertEquals(1, table1.size());

        table1.add(student2);
        assertEquals(2, table1.size());

        table1.add(student3);
        assertEquals(3, table1.size());

        table1.add(student4);
        assertEquals(4, table1.size());

        table1.add(student1);
        assertEquals(4, table1.size());
    }

    @Test
    public void testTableGetMethod() {
        String[] columnTitles1 = new String[]
        {"SID", "Last Name", "First Name", "Major"};
        Table table1 = new Table(columnTitles1);

        String[] student1 = new String[]
        {"00001", "Mahaarachchi", "Dre", "EECS"};
        String[] student2 = new String[]
        {"99999", "Jobs", "Steve", "MET"};
        String[] student3 = new String[]
        {"54998", "Kh", "Tina", "Art History"};
        String[] student4 = new String[]
        {"00002", "Mahaarachchi", "Shanara", "PreMed"};

        table1.add(student1);
        table1.add(student2);
        table1.add(student3);
        table1.add(student4);

        String expectedSID1 = "00001";
        String actualSID1 = table1.get(0, 0);
        assertTrue(expectedSID1.equals(actualSID1));

        String expectedFirstName1 = "Dre";
        String actualFirstName1 = table1.get(0, 2);
        assertTrue(expectedFirstName1.equals(actualFirstName1));

        String expectedMajor1 = "PreMed";
        String actualMajor1 = table1.get(1, 3);
        assertTrue(expectedMajor1.equals(actualMajor1));

        String expectedLastName1 = "Kh";
        String actualLastName1 = table1.get(2, 1);
        assertTrue(expectedLastName1.equals(actualLastName1));

        String expectedSID2 = "99999";
        String actualSID2 = table1.get(3, 0);
        assertTrue(expectedSID2.equals(actualSID2));
    }

    @Test
    public void testFirstAddMethod() {
        String[] columnTitles1 = new String[]
        {"SID", "Last Name", "First Name", "Major"};
        Table table1 = new Table(columnTitles1);

        String[] student1 = new String[]
        {"00001", "Mahaarachchi", "Dre", "EECS"};
        String[] student2 = new String[]
        {"99999", "Jobs", "Steve", "MET"};
        String[] student3 = new String[]
        {"54998", "Kh", "Tina", "Art History"};
        String[] student4 = new String[]
        {"00002", "Mahaarachchi", "Shanara", "PreMed"};
        String[] student5 = new String[]
        {"54971", "Mahaarachchi", "Melony", "MechE"};
        String[] student6 = new String[]
        {"51269", "Mahaarachchi", "Zen", "Haas"};
        String[] student7 = new String[]
        {"71998", "Rubel", "Camille", "Chemistry"};
        String[] student8 = new String[]
        {"81598", "Schugel", "Cain", "PreMed"};
        String[] student9 = new String[]
        {"51498", "Woolsoncroft", "Everett", "Anthropology"};
        String[] student10 = new String[]
        {"00958", "Bolt", "Usain", "Track&Field"};
        String[] student11 = new String[]
        {"00000", "Christ", "Carol", "Chancellor"};

        assertTrue(table1.add(student1));
        int expectedSize1 = 1;
        int actualSize1 = table1.size();
        assertEquals(expectedSize1, actualSize1);

        assertTrue(table1.add(student2));
        assertTrue(table1.add(student3));
        assertTrue(table1.add(student4));
        int expectedSize2 = 4;
        int actualSize2 = table1.size();
        assertEquals(expectedSize2, actualSize2);

        assertTrue(table1.add(student5));
        assertTrue(table1.add(student6));
        assertTrue(table1.add(student7));
        assertTrue(table1.add(student8));
        assertTrue(table1.add(student9));

        int expectedSize3 = 9;
        int actualSize3 = table1.size();
        assertEquals(expectedSize3, actualSize3);

        assertTrue(table1.add(student10));
        assertTrue(table1.add(student11));
        int expectedSize4 = 11;
        int actualSize4 = table1.size();
        assertEquals(expectedSize4, actualSize4);

        assertFalse(table1.add(student3));
        assertFalse(table1.add(new String[]
        {"51498", "Woolsoncroft", "Everett", "Anthropology"}));
    }

    @Test
    public void testSecondAddMethod() {
        Table table1 = new Table(new String[] {"alpha", "bravo", "charlie"});
        Table table2 = new Table(new String[] {"alpha", "beta", "gamma"});
        Table table3 = new Table(new String[] {"one", "two", "three"});


        table1.add(new String[] {"1", "2", "3"});
        table1.add(new String[] {"4", "5", "6"});
        table2.add(new String[] {"1", "2", "3"});
        table2.add(new String[] {"4", "5", "6"});

        Column col1 = new Column("alpha", table1, table2);
        Column col2 = new Column("beta", table1, table2);
        Column col3 = new Column("charlie", table1, table2);
        List<Column> columns1 = new ArrayList<Column>();
        columns1.add(col1);
        columns1.add(col2);
        columns1.add(col3);

        assertTrue(table3.add(columns1, 0, 1, 2));
        assertFalse(table3.add((columns1), 0, 1, 2));
        assertFalse(table3.add(new String[] {"1", "5", "3"}));

        assertEquals(1, table3.size());

        String str1 = "1";
        String str2 = "5";
        String str3 = "3";
        assertTrue(str1.equals(table3.get(0, 0)));
        assertTrue(str2.equals(table3.get(0, 1)));
        assertTrue(str3.equals(table3.get(0, 2)));
    }

    @Test
    public void testReadTableMethod() {
        Table readingTable = Table.readTable("../testing/students");
        Table expectedTable = new Table(new String[]
        {"SID", "Lastname", "FirstName", "SemEnter", "YearEnter", "Major"});
        String[] sid101 = new String[]
        {"101", "Knowles", "Jason", "F", "2003", "EECS"};
        String[] sid102 = new String[]
        {"102", "Chan", "Valerie", "S", "2003", "Math"};
        String[] sid103 = new String[]
        {"103", "Xavier", "Jonathan", "S", "2004", "LSUnd"};
        String[] sid104 = new String[]
        {"104", "Armstrong", "Thomas", "F", "2003", "EECS"};
        String[] sid105 = new String[]
        {"105", "Brown", "Shana", "S", "2004", "EECS"};
        String[] sid106 = new String[]
        {"106", "Chan", "Yangfan", "F", "2003", "LSUnd"};

        expectedTable.add(sid101);
        expectedTable.add(sid102);
        expectedTable.add(sid103);
        expectedTable.add(sid104);
        expectedTable.add(sid105);
        expectedTable.add(sid106);

        assertEquals(6, readingTable.columns());
        assertEquals(6, readingTable.size());

        assertTrue(expectedTable.get(0, 0)
                .equals(readingTable.get(0, 0)));
        assertTrue(expectedTable.get(1, 2)
                .equals(readingTable.get(1, 2)));
        assertTrue(expectedTable.get(2, 5)
                .equals(readingTable.get(2, 5)));
        assertTrue(expectedTable.get(4, 2)
                .equals(readingTable.get(4, 2)));
        assertTrue(expectedTable.get(5, 5)
                .equals(readingTable.get(5, 5)));

    }

    @Test
    public void testPrintMethod() {
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

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String expectedOutput = "  00001 Mahaarachchi Dre EECS\n"
                + "  00002 Mahaarachchi Shanara PreMed\n"
                + "  54998 Kh Tina ArtHistory\n"
                + "  99999 Jobs Steve MET\n";

        table1.print();

        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    public void testSelectMethod() {
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

        ArrayList<String> colNames = new ArrayList<>();
        ArrayList<Condition> conds = new ArrayList<>();
        Table testTable;
        colNames.add("SID");

        testTable = table1.select(colNames, conds);

        assertEquals(4, testTable.size());
        assertTrue("00002".equals(testTable.get(1, 0)));
    }

    @Test
    public void testSecondSelectMethod() {
        String[] columnTitles1 = new String[]
        {"INDEX", "col1", "col2", "col3"};
        Table table1 = new Table(columnTitles1);
        table1.add(new String[] {"1", "9", "9", "7"});
        table1.add(new String[] {"2", "0", "0", "5"});
        table1.add(new String[] {"3", "0", "1", "7"});


        String[] columnTitles2 = new String[]
        {"INDEX", "col4", "col5", "col6"};
        Table table2 = new Table(columnTitles2);
        table2.add(new String[] {"1", "arb", "itr", "ary"});
        table2.add(new String[] {"2", "va", "lu", "es"});
        table2.add(new String[] {"3", "I", "love", "Hilfinger"});

        Column col1 = new Column("col1", table1, table2);
        Column col3 = new Column("col3", table1, table2);

        List<Condition> conds = new ArrayList<>();
        conds.add(new Condition(col1, "=", "0"));
        conds.add(new Condition(col3, "=", "7"));

        List<String> colNames = new ArrayList<>();
        colNames.add("col4");
        colNames.add("col5");
        colNames.add("col6");

        Table result = table1.select(table2, colNames, conds);

        assertEquals(3, result.columns());
        assertEquals(1, result.size());
        assertTrue("I".equals(result.get(0, 0)));
        assertTrue("love".equals(result.get(0, 1)));
        assertTrue("Hilfinger".equals(result.get(0, 2)));
    }


    @Test
    public void testWriteTableMethod() {
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

        table1.writeTable("testingTable");

        Table table2 = Table.readTable("testingTable");

        assertEquals(4, table2.columns());
        assertEquals(4, table2.size());

        assertTrue(table1.get(0, 0)
                .equals(table2.get(0, 0)));
        assertTrue(table1.get(1, 2)
                .equals(table2.get(1, 2)));
        assertTrue(table1.get(2, 3)
                .equals(table2.get(2, 3)));
        assertTrue(table1.get(3, 1)
                .equals(table2.get(3, 1)));
        assertTrue(table1.get(3, 3)
                .equals(table2.get(3, 3)));
    }
}
