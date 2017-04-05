import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by 49738 on 2017/4/5.
 */


public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randQueue = new RandomizedQueue<>();

        int k = Integer.parseInt(args[1]);
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            randQueue.enqueue(line);
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(randQueue.dequeue());
        }

    }  // unit testing (optional)
}
