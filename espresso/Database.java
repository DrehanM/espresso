
import java.io.Serializable;
import java.util.HashMap;

class Database implements Serializable {
    /** Constructor for Database. */
    public Database(String name) {
        _name = name;
        _relations = new HashMap<String, Relation>();
    }

    /**  */
    public void put(String name, Relation rel)
        throws IllegalAccessException {
        if (_relations.containsKey(name)) {
            throw new IllegalAccessException("This database contains a relation with that name.");
        }
    }

    public void drop(String name)
        throws IllegalAccessException {
        if (_relations.containsKey(name)) {
            throw new IllegalAccessException("No such relation with that name exists in this database.");
        }
    }



    private HashMap<String, Relation> _relations;
    private String _name;
}