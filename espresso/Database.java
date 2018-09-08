
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

class Database implements Serializable {
    /** Constructor for Database. */
    public Database(String name) {
        _name = name;
        _relations = new HashMap<>();
    }

    /**  */
    public void create(String name, Relation rel)
        throws IllegalAccessException {
        if (_relations.containsKey(name)) {
            throw new IllegalAccessException("This database already contains a relation with that name.");
        }

        _relations.put(name, rel);
    }

    public Relation retrieve(String name)
        throws IllegalAccessException {
        if (_relations.containsKey(name)) {
            throw new IllegalAccessException("No such relation with that name exists in this database.");
        }

        return _relations.get(name);
    }

    public boolean update(String name, String command) {
        return false;
    }


    public void drop(String name)
        throws IllegalAccessException {
        if (_relations.containsKey(name)) {
            throw new IllegalAccessException("No such relation with that name exists in this database.");
        }

        _relations.remove(name);
    }

    public String name() {
        return _name;
    }

    public void commit() {
        File dbFile = new File(_name);
        Utils.writeObject(dbFile, this);
    }

    private HashMap<String, Relation> _relations;
    private String _name;
}