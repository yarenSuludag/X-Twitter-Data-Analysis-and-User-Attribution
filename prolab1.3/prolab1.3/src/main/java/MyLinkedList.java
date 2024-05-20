import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyLinkedList<T> implements Iterable<T>  {
    private Node<T> head;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T data) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.data.equals(data)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    public void removeUser(MyLinkedList<User> users, User userToRemove) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User currentUser = iterator.next();
            if (currentUser.equals(userToRemove)) {
                iterator.remove();
                break; // Eğer sadece bir tane kaldırmak istiyorsanız, bulduktan sonra döngüyü sonlandırabilirsiniz.
            }
        }
    }


    public void clear() {
        head = null;
        size = 0;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    public void removeFirst() {
        if (head != null) {
            head = head.next;
            size--;
        }
    }

    public void removeLast() {
        if (head == null || head.next == null) {
            head = null;
            size = 0;
            return;
        }
        Node<T> temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        size--;
    }
    public void addAll(MyLinkedList<T> otherList) {
        Node<T> currentNode = otherList.head;
        while (currentNode != null) {
            add(currentNode.data);
            currentNode = currentNode.next;
        }
    }
}