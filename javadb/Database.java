package javadb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import Relation;

class Database implements Serializable {
    /** Constructor for Database. */
    public Database(String name) {
        _name = name;
        _relations = new HashMap<String, Relation>();
    }

    /**  */
    public void put(String name, Relation rel) {
        if (_relations.containsKey(name)) {
            throw new Exception("This database contains a relation with that name.");
        }
    }

    public void drop(String name) {
        if (_relations.containsKey(name)) {
            throw new Exception("No such relation with that name exists in this database.");
        }
    }

    

    private HashMap<String, Relation> _relations;
    private String _name;
}