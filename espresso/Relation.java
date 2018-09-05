
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Relation {
    /** A new Table whose columns are given by COLUMNTITLES, which may
     *  not contain duplicate names. */
    public Relation(ArrayList<String> columnNames) {
        _colNames = columnNames;
        _primary = columnNames.get(0);
        _numColumns = columnNames.size();
        _rows = new ArrayList<>();
        _columns = new HashMap<>();
    }

    public void put(Object[] row)
        throws Exception {
        if (row.length != _numColumns) {
            throw new Exception("Invalid add: # of columns mismatched.");
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
    public Map<String, Object[]> get(String[] columnNames, boolean ya)
        throws Exception {

        HashMap<String, Object[]> result = new HashMap<>();

        for (String colName : columnNames) {
            if (!_columns.containsKey(colName)) {
                throw new Exception("column not in schema");
            }
            ArrayList colvalues = new ArrayList<T>();
            for (Object[] row: _rows) {
                colvalues.add(row[_colNames.indexOf(colName)]);
            }

            result.put(colName, colvalues);
        }
        return result;
    }

    public Map<String, Column> get(String... queriedColumns)
        throws IllegalAccessException {
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
    private String _primary;

    



}