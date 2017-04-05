import edu.princeton.cs.algs4.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by 49738 on 2017/4/3.
 */
public class Deque<Item> implements Iterable<Item> {

    //Inner class
    private class Node {
        private Node prev;
        private Node next;
        private Item item;

        Node() {
            this.prev = null;
            this.next = null;
            this.item = null;
        }

        void setItem(Item i) {
            this.item = i;
        }

        void connectNextTo(Node obj) {
            this.next = obj;
            obj.prev = this;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        DequeIterator() {
            this.current = First.next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (size == 0 || !hasNext())
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public boolean hasNext() {
            return current != First;
        }

    }

    //property
    private Node First;
    private Integer size;


    //functions
    public Deque() {
        this.First = new Node();
        this.First.connectNextTo(this.First);
        this.size = 0;
    }                           // construct an empty deque

    public boolean isEmpty() {
        return this.size == 0;
    }              // is the deque empty?

    public int size() {
        return this.size;
    }                     // return the number of items on the deque

    public void addFirst(Item i) {
        if (i == null) {
            throw new NullPointerException();
        }

        Node new_one = new Node();
        new_one.setItem(i);
        Node old_one = this.First.next;

        this.First.connectNextTo(new_one);
        new_one.connectNextTo(old_one);

        this.size += 1;
    }       // add the item to the front

    public void addLast(Item i) {
        if (i == null) {
            throw new NullPointerException();
        }

        Node new_one = new Node();
        new_one.setItem(i);
        Node old_one = this.First.prev;

        old_one.connectNextTo(new_one);
        new_one.connectNextTo(this.First);

        this.size += 1;
    }          // add the item to the end

    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            size -= 1;
            Node tmp = this.First.next;
            this.First.connectNextTo(tmp.next);
            return tmp.item;
        }
    }              // remove and return the item from the front

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException();
        } else {
            size -= 1;
            Node tmp = this.First.prev;
            tmp.prev.connectNextTo(this.First);
            return tmp.item;
        }
    }              // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }      // return an iterator over items in order from front to end

    public static void main(String[] args) {
        Deque<Integer> one = new Deque<Integer>();
        one.addLast(1);
        one.addLast(2);
        one.addLast(3);
//        for (Integer i : one) {
//            StdOut.println(i);
//        }
        one.removeFirst();
        one.removeFirst();
        one.removeFirst();
        one.removeFirst();

    }  // unit testing (optional)
}