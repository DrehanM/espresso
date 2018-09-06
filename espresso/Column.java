import java.util.ArrayList;
import java.util.List;

class Column<T> {

    public Column(String name, Class<T> typeClass) {
        _name = name;
        _length = 0;
        _type = typeClass;
        _elements = new ArrayList<>();
    }

    public void put(T element)
        throws IllegalArgumentException {
        if (element.getClass() != _type) {
            throw new IllegalArgumentException("Incompatible types");
        }
        _elements.add(element);
        _length += 1;
    }

    public Object get(int index) {
        return _elements.get(index);
    }

    public ArrayList<T> slice(int fromIndex, int toIndex)
        throws IndexOutOfBoundsException {
        if (fromIndex >= _length || toIndex >= _length) {
            throw new IndexOutOfBoundsException("Given indices are out of bounds for the Column.");
        }
        ArrayList<T> slice = new ArrayList<>(_elements.subList(fromIndex, toIndex));
        return slice;
    }

    public void del(int index) {
        _elements.remove(index);
    }

    public int del(T target) {
        int numRemoved;
        _elements.remove(target);
        numRemoved = _length - _elements.size();
        _length = _elements.size();
        return numRemoved;
    }

    public String name() {
        return _name;
    }

    public int length() {
        return _length;
    }

    public Class<T> type() {
        return _type;
    }

    private String _name;
    private Class<T> _type;
    private ArrayList<T> _elements;
    private int _length;
}