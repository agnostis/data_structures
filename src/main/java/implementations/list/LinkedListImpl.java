package implementations.list;

import interfaces.list.LinkedList;

import java.util.Iterator;

public class LinkedListImpl implements LinkedList {
    private Node head;
    private Node tail;
    private int size;

    public LinkedListImpl() {
        head = null;
        size = 0;
    }

    static class Node {
        private Object currentElem;
        private Node nextElem;

        public Node(Object currentElem, Node nextElemm) {
            this.currentElem = currentElem;
            this.nextElem = nextElemm;
        }

        public Object getCurrent() {
            return currentElem;
        }

        public Node getNextElem() {
            return nextElem;
        }
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object data = current.currentElem;
            current = current.nextElem;
            return data;
        }

    }

    @Override
    public void addFirst(Object element) {
        head = new Node(element, head);
        size++;
    }

    @Override
    public void addLast(Object element) {
        if (head == null) {
            head = new Node(element, null);
            size++;
        } else {
            Node temp = head;
            while (temp.nextElem != null) {
                temp = temp.nextElem;
            }
            temp.nextElem = new Node(element, null);
            size++;
        }
    }

    @Override
    public void removeFirst() {
        Node temp = head;
        head = temp.nextElem;
        temp.nextElem = null;
        size--;
    }

    @Override
    public void removeLast() {
        Node node = head;
        while (node.nextElem.nextElem != null)
            node = node.nextElem;
        node.nextElem = null;
        size--;
    }

    @Override
    public Object getFirst() {
        return head == null ? null : head.currentElem;
    }

    @Override
    public Object getLast() {
        if (head == null) {
            return null;
        }
        Node temp = head;
        while (temp.nextElem != null) {
            temp = temp.nextElem;
        }
        return temp.currentElem;
    }

    @Override
    public Object search(Object element) {
        Node temp = head;
        if (head == null) {
            return null;
        }
        if (head.currentElem.equals(element)) {
            return element;
        }
        while (temp.nextElem != null) {
            temp = temp.nextElem;
            if (temp.currentElem != null && temp.currentElem.equals(element)) {
                return element;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        Node previous = head;
        Node current = head.nextElem;
        if (head.currentElem.equals(element)) {
            head = head.nextElem;
            size--;
            return true;
        }

        while (current != null) {
            Object dataOld = current.currentElem;
            if ((dataOld == null && element == null) || (dataOld != null && dataOld.equals(element))) {
                Node afterRemoved = current.nextElem;
                previous.nextElem = afterRemoved;
                if (afterRemoved == null) { // i.e. removing last element
                    tail = previous;
                }
                size--;
                return true;
            } else {
                previous = current;
                current = current.nextElem;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        Iterator<Object> it = iterator();
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (it.hasNext()) {
            Object o = it.next();
            sb.append(o);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(", ");
        }
        return sb.toString();
    }
}
