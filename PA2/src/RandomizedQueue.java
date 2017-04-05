import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by 49738 on 2017/4/4.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    //property
    private Item[] data;
    private int capacity;
    private int current;

    //inner class
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int it_current;

        RandomizedQueueIterator() {
            this.it_current = 0;
        }

        @Override
        public boolean hasNext() {
            return this.it_current < current;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[it_current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    //functions
    public RandomizedQueue() {
        this.capacity = 100;
        this.current = 0;
        this.data = (Item[]) new Object[capacity];
    }              // construct an empty randomized queue

    public boolean isEmpty() {
        return current == 0;
    }            // is the queue empty?

    public int size() {
        return current;
    }                     // return the number of items on the queue

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (current == capacity) {
            this.resize(capacity * 2);
        }
        data[current++] = item;
    }          // add the item

    public Item dequeue() {
        if (current == 0) {
            throw new NoSuchElementException();
        }
        int rand_index = StdRandom.uniform(current);
        Item item = data[rand_index];
        if (rand_index != current - 1) {
            data[rand_index] = data[current];
        }
        current -= 1;
        if (current < capacity / 4) {
            this.resize(capacity / 2);
        }
        return item;
    }              // remove and return a random item

    public Item sample() {
        if (current == 0) {
            throw new NoSuchElementException();
        }
        int rand_index = StdRandom.uniform(current);
        Item item = data[rand_index];
        return item;
    }                  // return (but do not remove) a random item

    private void resize(int new_capacity) {
        Item[] new_data = (Item[]) new Object[new_capacity];
        if (new_capacity >= this.capacity) {
            System.arraycopy(this.data, 0, new_data, 0, this.capacity);
        } else {
            System.arraycopy(this.data, 0, new_data, 0, new_capacity);
        }
        this.capacity = new_capacity;
        this.data = new_data;
    }  //resize the old data array to new data array with a new capacity

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }      // return an independent iterator over items in random order

    public static void main(String[] args) {
        RandomizedQueue<Integer> rdqueque = new RandomizedQueue<>();
        rdqueque.enqueue(100);
        rdqueque.enqueue(20);
        rdqueque.enqueue(30);
        rdqueque.enqueue(40);
        StdOut.println(rdqueque.dequeue());
        StdOut.println(rdqueque.dequeue());
        StdOut.println(rdqueque.dequeue());
        StdOut.println(rdqueque.dequeue());
    }  // unit testing (optional)
}
