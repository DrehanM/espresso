package javadb;

import java.util.ArrayList;
import java.util.HashMap;

import Relation;

class Database {
    /** Constructor for Database. */
    public Database(String name) {
        _name = name;
        _relations = new HashMap<String, Relation>();
    }

    /**  */
    public void put(String name, Relation rel) {
        assert False;
    }

    public void drop(String name) {
        assert False;
    }

    private HashMap<String, Relation> _relations;
    private String _name;
}