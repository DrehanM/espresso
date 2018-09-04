package javadb;

import java.util.ArrayList;
import java.util.HashMap;
import Column;
import Row;

class Relation {
    /** A new Table whose columns are given by COLUMNTITLES, which may
     *  not contain duplicate names. */
    public Relation(String[] columnNames, String primaryKey, String[][] columnAttrs) {
        _columns = columnNames;
        _rows = new ArrayList<Row>();
        _primary = primaryKey;
        _schema = new HashMap<String, String[]>();
        for (int i = 0; i < columnNames.length; i++) {
            _schema.put(columnNames[i], columnAttrs[i]);
    }

    public void put(Row row) {
        if 

    }

    private HashMap<String, T[]> _rows;
    private String[] _columns;
    private HashMap<String, String[]> _schema;
    private String _primary;

    



}