package datastructures.lists;

class Node<E> {
    E elem;
    Node<E> next;

    public Node(E e) {
        this.elem = e;
    }
}
public class SinglyLinkedList<E> implements List<E> {
    private Node<E> head;
    private int size = 0;

    public SinglyLinkedList() {}

    public SinglyLinkedList(E[] objects) {
        for (E e: objects) {
            this.add(e);
        }
    }

    private void verifyIndex (int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, size: %d", index, size));
        }
    }

    @Override
    public E set(int index, E e) {
        verifyIndex(index);
        Node<E> current = this.head;
        for (int i = 0; i < index; i++) {
            if (current.next == null) {
                current.next = new Node<>(e);
                return e;
            }
            current = current.next;
        }
        Node<E> oldNext = current.next;
        current.next = new Node<>(e);
        current = current.next;
        current.next = oldNext;
        return e;
    }

    @Override
    public E get(int index) {
        verifyIndex(index);
        Node<E> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.next.elem;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> current = this.head;
        int index = 0;
        while (current != null) {
            if (current.elem == (E) o)
                return index;
            else {
                current = current.next;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        Node<E> current = this.head;
        int lastIndex = -1, index = 0;
        while (current != null) {
            if (current.elem == (E) e) {
                lastIndex = index;
            } else {
                current = current.next;
            }
            index++;
        }
        return lastIndex;
    }

    @Override
    public int size() {
        Node<E> current = this.head;
        while (current != null) {
            current = current.next;
            this.size++;
        }
        return this.size;
    }

    @Override
    public E remove(int index) {
        verifyIndex(index);
        Node<E> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E e = current.next.elem;
        current.next = current.next.next;
        this.size--;
        return e;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.size() == 0)
            return "[ ]";
        sb.append("[ ");
        Node<E> current = this.head;
        while (current.next != null) {
            sb.append(current.elem.toString());
            sb.append(" -> ");
        }
        sb.append(current.elem);
        sb.append(" ]");
        return sb.toString();
    }
}
