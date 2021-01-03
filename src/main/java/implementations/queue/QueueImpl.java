package implementations.queue;

import interfaces.queue.Queue;

import java.util.Iterator;

public class QueueImpl implements Queue {

    private ObjectBox head = null;
    private ObjectBox tail = null;
    private int size = 0;

    public QueueImpl() {

    }

    @Override
    public void clear() {
        head.object = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        ObjectBox next = head;

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Object next() {
            Object data = next.object;
            next = next.next;
            return data;
        }

    }

    @Override
    public void enqueue(Object element) {
        ObjectBox ob = new ObjectBox();
        ob.setObject(element);
        if (head == null) {
            head = ob;
        } else {
            tail.setNext(ob);
        }
        tail = ob;
        size++;
    }

    @Override
    public Object dequeue() {
        if (size == 0) {
            return null;
        }
        Object obj = head.getObject();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return obj;
    }

    @Override
    public Object top() {
        return head.object;
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

    private class ObjectBox {
        private Object object;
        private ObjectBox next;

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public ObjectBox getNext() {
            return next;
        }

        public void setNext(ObjectBox next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {

    }

}
