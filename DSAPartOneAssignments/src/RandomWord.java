import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        String readString;
        int i = 1;
        double prob;
        do {
            prob = 1.0/i;
            readString = StdIn.readString();
            if (StdRandom.bernoulli(prob)) {
                champion = readString;
            }
            i++;
        }
        while (!StdIn.isEmpty());
        StdOut.println(champion);
    }
}