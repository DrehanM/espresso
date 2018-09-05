import java.util.ArrayList;
import java.util.HashMap;

class Column<T> {

    public Column(String name, Class<T> type) {
        _name = name;
        _type = type;
        _length = 0;
        _elements = new ArrayList<T>();
    }

    public void put(Object element) {
        _elements.add(element);
        _length += 1;
    }

    public Object get(int index) {
        return _elements.get(index);
    }

    public void del(int index) {
        _elements.remove(index);
    }

    public int del (T target) {
        int numRemoved;
        _elements.remove(target);
        numRemoved = _length - _elements.size();
        _length = _elements.size();
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