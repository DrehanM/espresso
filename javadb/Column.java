

import java.util.ArrayList;
import java.util.HashMap;

class Column {

    public Column(String name, Class<T> type) {
        _name = name;
        _type = type;
        _length = 0;
        _elements = new ArrayList<type>();
    }

    public void put(_type element) {
        _elements.add(element);
        _length += 1;
    }

    public _type get(int index) {
        return _elements.get(index);
    }

    public void del(int index) {
        _elements.remove(index);
    }

    public int del (_type target) {
        int numRemoved;
        _elements.removeIf(e -> e.equals(target));
        numRemoved = _length - _elements.length;
        _length = _elements.length;
        return numRemoved;
    }

    public int length() {
        return _length;
    }

    private String _name;
    private Class<T> _type;
    private ArrayList _elements;
    private int _length;
}