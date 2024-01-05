package datastructures.lists;

import java.util.Collection;

public interface List<E> {

    E get(int index);

    int indexOf(Object o);

    int lastIndexOf(E e);

    E set(int index, E e);

    int size();

    E remove(int index);

    default boolean contains(E e) {
        if (this.indexOf(e) >= 0)
            return true;
        return false;
    }

    void clear();

    default boolean isEmpty() {
        return this.size() == 0;
    }

    default boolean add(E e) {
        set(this.size(), e);
        return true;
    }

    default boolean remove(E o) {
        int index = this.indexOf(o);
        if (index >= 0) {
            this.remove(index);
            return true;
        } else {
            return false;
        }
    }
}
