
import java.util.ArrayList;
import java.util.HashMap;
import Column.*;

class Relation {
    /** A new Table whose columns are given by COLUMNTITLES, which may
     *  not contain duplicate names. */
    public Relation(ArrayList<String> columnNames, String primaryKey, String[][] columnAttrs) {
        _columns = columnNames;
        _numColumns = _columns.length;
        _rows = new ArrayList<T[]>();
        _primary = primaryKey;
        _schema = new HashMap<>();
        for (int i = 0; i < columnNames.length; i++) {
            _schema.put(columnNames.get(i), columnAttrs[i]);
        }
    }

    public void put(T[] row) {
        if (row.length != numColumns) {
            throw new Exception("Invalid add: # of columns mismatched.");
        }
        _rows.add(row);
    }

    /** Return a hashmap mapping queried column names to the values in those columns. */
    public HashMap<String, T[]> get(String[] columnNames) {

        HashMap<String, T[]> result = new HashMap<>();

        for (String col : columnNames) {
            if (!_columns.contains(col)) {
                throw new Exception("column not in schema");
            }
            ArrayList colvalues = new ArrayList<T>();
            for (T[] row: _rows) {
                colvalues.add(row[_columns.indexOf(col)]);
            }

            result.put(col, colvalues);
        }
        return result;
    }

    private ArrayList _rows;
    private ArrayList _columns;
    private int _numColumns;
    private HashMap<String, String[]> _schema;
    private String _primary;

    



}