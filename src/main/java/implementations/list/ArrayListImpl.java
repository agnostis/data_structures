package implementations.list;

import interfaces.list.ArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListImpl implements ArrayList {
    private Object[] array;
    private int lenght = 0;
    private int size;

    public ArrayListImpl(int size) {
        this.size = size;
        array = new Object[size];
    }

    public ArrayListImpl() {
        array = new Object[0];
    }

    @Override
    public void clear() {
        lenght = 0;
        array = new Object[0];
    }

    @Override
    public int size() {
        return lenght;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return array[index++];
            }
            throw new NoSuchElementException();
        }

    }

    @Override
    public void add(Object element) {
        Object[] temp = array;
        if (lenght >= size) {
            array = new Object[array.length + 1];
            System.arraycopy(temp, 0, array, 0, temp.length);
            array[array.length - 1] = element;
            lenght++;
        } else {
            array[lenght] = element;
            ++lenght;
        }
    }

    @Override
    public void set(int index, Object element) {
        array[index] = element;
    }

    @Override
    public Object get(int index) {
        return index < lenght ? array[index] : -1;
    }

    @Override
    public int indexOf(Object element) {
        int index = -1;
        for (int i = 0; i < lenght; i++) {
            if (array[i] == element) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void remove(int index) {
        Object[] temp = array;
        array = new Object[array.length - 1];
        System.arraycopy(temp, 0, array, 0, index);
        int amountElemFromIndex = array.length - index;
        System.arraycopy(temp, index + 1, array, index, amountElemFromIndex);
        --lenght;
    }

    @Override
    public String toString() {
        if (array == null)
            return "null";
        int iMax = lenght - 1;
        if (iMax == -1)
            return "[]";
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(array[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}
