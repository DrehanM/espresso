import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Relation {
    /** A new Table whose columns are given by COLUMNNAMES which may
     *  not contain duplicate names. */
    public Relation(ArrayList<String> columnNames, ArrayList<Class> columnTypes) {
        _colNames = columnNames;
        _primary = columnNames.get(0);
        _numColumns = columnNames.size();
        _rows = new ArrayList<>();
        _columns = new HashMap<>();
        _colTypes = new HashMap<>();
        for (int i = 0; i < _numColumns; i++) {
            String colName = columnNames.get(i);
            Class colType = columnTypes.get(i);
            _columns.put(colName, new Column(colName, colType));
            _colTypes.put(colName, colType);
        }
    }

    public void put(Object[] row)
        throws IllegalArgumentException {
        if (row.length != _numColumns) {
            throw new IllegalArgumentException("Invalid add: # of columns mismatched.");
        }

        for (String colName : _colNames) {
            int indexOfCol = _colNames.indexOf(colName);
            Column col = _columns.get(colName);
            col.put(row[indexOfCol]);
            _columns.put(colName, col);
        }

        _rows.add(row);
    }

    /** Return a hashmap mapping queried column names to the values in those columns. */

    public Map<String, Column> get(String... queriedColumns)
        throws IllegalArgumentException {
        HashMap<String, Column> queryResult = new HashMap<>();
        for (String colName : queriedColumns) {
            if (!_colNames.contains(colName)) {
                throw new IllegalArgumentException("No such column name in schema.");
            }
            Column col = _columns.get(colName);
            queryResult.put(colName, col);
        }
        return queryResult;
    }

    private ArrayList<Object[]> _rows;
    private ArrayList<String> _colNames;
    private int _numColumns;
    private HashMap<String, Column> _columns;
    private HashMap<String, Class> _colTypes;
    private String _primary;

}