public class MySet<E> {
    private Object[] elements;
    private int size;

    // create the set
    public MySet() {
        elements = new Object[10];
        size = 0;
    }

    // add an element to the set if its already no added
    public void add(E element) {
        if (contains(element)) {
            return;
        }
        if (size >= elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        elements[size] = element;
        size++;
    }

    // remove an given element from the set
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                elements[i] = elements[size - 1];
                elements[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    // check if a given element exists in the set
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    // get the length of the set
    public int size() {
        return size;
    }

    // remove all the elements from the set
    public void clear() {
        elements = new Object[10];
        size = 0;
    }

    // check whether the set is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // convert the string to string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public MySet<E> copy() {
        MySet<E> newSet = new MySet<E>();
        for (int i = 0; i < size; i++) {
            newSet.add((E)elements[i]);
        }
        return newSet;
    }
}
