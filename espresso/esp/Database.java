package esp;

import java.util.ArrayList;

/** A collection of Tables, indexed by name. */
class Database {
    /** An empty database. */
    public Database() {
        _tables = new TableList();
        _names = new NameList();
    }

    /** Return the Table whose name is NAME stored in this database, or null
     *  if there is no such table. */
    public Table get(String name) {
        int indexToReturn = _names.indexOf(name);
        if (indexToReturn != -1) {
            return _tables.get(indexToReturn);
        } else {
            return null;
        }
    }

    /** Set or replace the table named NAME in THIS to TABLE.  TABLE and
     *  NAME must not be null, and NAME must be a valid name for a table. */
    public void put(String name, Table table) {
        if (name == null || table == null) {
            throw new IllegalArgumentException("null argument");
        }
        if (_names.contains(name)) {
            int indexToChange = _names.indexOf(name);
            _tables.set(indexToChange, table);
        } else {
            _names.add(name);
            _tables.add(table);
        }
    }

    /** Lists to store the tables in the database and the corresponding names
     * of each table. */
    private TableList _tables;

    /** List to store the names of the above tables stored in this
     * Database object. */
    private NameList _names;

    /** Redefined the List types to have more readable data types. */
    private static class TableList extends ArrayList<Table> {
    }

    /** See above comment. */
    private static class NameList extends ArrayList<String> {
    }
}
