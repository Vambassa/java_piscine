public class Summarizer extends Thread {

    private int arr[];
    private int first;
    private int last;
    private int id;
    private int arrSum;

    public Summarizer(int arr[], int first, int last, int id) {
        this.arr = arr;
        this.first = first;
        this.last = last;
        this.id = id + 1;
        this.arrSum = 0;
    }

    public void summarize() {

        for (int i = first; i <= last; ++i) {
            arrSum += arr[i];
        }

        System.out.println(toString());
    }

    public int getArrSum() {
        return arrSum;
    }

    @Override
    public void run() {
        summarize();
    }

    @Override
    public String toString() {
        return "Thread " + id + ": from " + first + " to " + last + " sum is " + arrSum;
    }
}
