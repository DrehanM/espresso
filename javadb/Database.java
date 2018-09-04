package javadb;

import java.util.ArrayList;
import java.util.HashSet;

import Relation;

class Database {
    private HashSet<String, Relation> _relations;
    private String _name;
   
    /** Constructor for Database. */
    public Database(String name) {
        _name = name;
        _relations = new HashSet<String, Relation>();
    }

    /**  */
    public void put(String name, Relation rel) {
        assert False;
    }

    public void drop(String name) {
        assert False;
    }
}