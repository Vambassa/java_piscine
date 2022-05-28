public class TransNode {

    Transaction value;
    TransNode   next;
    TransNode   prev;

    public TransNode(Transaction value, TransNode prev, TransNode next){

        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}
