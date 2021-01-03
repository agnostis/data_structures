package implementations.stack;

import implementations.list.ArrayListImpl;
import interfaces.stack.Stack;

import java.util.Iterator;


public class StackImpl implements Stack {

    public ArrayListImpl arr = new ArrayListImpl();

    @Override
    public void clear() {
        arr.clear();
    }

    @Override
    public int size() {
        return arr.size();
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        int count = size();
        private int index;

        @Override
        public boolean hasNext() {
            index++;
            return index <= size();
        }

        @Override
        public Object next() {
            --count;
            return arr.get(count);
        }

    }

    @Override
    public void push(Object element) {
        arr.add(element);
    }

    @Override
    public Object pop() {
        if (size() <= 0) {
            return null;
        }
        Object o = arr.get(size() - 1);
        arr.remove(size() - 1);
        return o;
    }

    @Override
    public Object top() {
        if (size() <= 0) {
            return null;
        }
        return arr.get(size() - 1);
    }

    @Override
    public String toString() {
        return arr.toString();
    }

    public static void main(String[] args) {

    }
}
