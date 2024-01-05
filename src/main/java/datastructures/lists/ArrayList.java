package datastructures.lists;

import java.util.Arrays;
import java.util.SplittableRandom;

public class ArrayList<E> implements List<E> {
    public static final int INITIAL_CAPACITY = 16;

    private E[] data = (E[]) new Object[INITIAL_CAPACITY];

    private int size = 0;

    public ArrayList() {

    }

    public ArrayList(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            this.add(objects[i]);
        }
    }

    private void verifyCapacity() {
        if (this.size >= data.length) {
            this.data = Arrays.copyOf(this.data, this.size * 2 + 1);
        }
    }

    private void verifyIndex (int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, size: %d", index, size));
        }
    }

    @Override
    public E get(int index) {
        verifyIndex(index);
        return this.data[index];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (o.equals(this.data[i]))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--) {
            if (e.equals(this.data[i]))
                return i;
        }
        return -1;
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, size: %d", index, size));
        }
        verifyCapacity();

        // move elements from index position 'index + 1' from the end
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        this.size++;
        return e;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E remove(int index) {
        verifyIndex(index);
        E e = this.data[index];

        for (int i = index; i < size - 1; i++) {
            this.data[i] = this.data[i + 1];
        }
        data[size - 1] = null;
        this.size--;
        return e;
    }

//    @Override
//    public boolean contains(E e) {
//        int index = this.indexOf(e);
//        if (index >= 0 && index < this.size)
//            return true;
//        return false;
//    }

    @Override
    public void clear() {
        this.data = (E[]) (new Object[INITIAL_CAPACITY]);
        this.size = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.size() == 0)
            return "[]";
        sb.append("[ ");
        for (int i = 0; i < this.size - 1; i++) {
            sb.append(this.data[i].toString());
            sb.append(" -> ");
        }
        sb.append(this.data[this.size - 1]);
        sb.append(" ]");
        return sb.toString();
    }
}
