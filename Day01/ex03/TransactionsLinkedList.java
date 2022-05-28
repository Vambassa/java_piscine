import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    
    private TransNode first;
    private TransNode last;
    private Integer number;

    public TransactionsLinkedList() {
        first = null;
        last = null;
        number = 0;
    }

    @Override
    public void addTransaction(Transaction trans) {

        if (number == 0) {
            this.first = new TransNode(trans, null, null);
            this.last = first;
        } else {
            this.last.next = new TransNode(trans, this.last, null);
            this.last = this.last.next;
        }
        ++number;
    }

    @Override
    public void removeTransaction(String identifier) throws TransactionNotFoundException {
        UUID uuid = UUID.fromString(identifier);
        TransNode trans = first;
        while (trans != null) {
            if (trans.value.getIdentifier().equals(uuid)) {
                if (trans.next == null && trans.prev == null) {
                    first = null;
                    last = null;
                } else if (trans.next == null) {
                    trans.prev.next = null;
                    last =  trans.prev;
                } else if (trans.prev == null) {
                    first = trans.next;
                    trans.next.prev = null;
                } else {
                    trans.next.prev = trans.prev;
                    trans.prev.next = trans.next;
                }
                number--;
                return;
            }
            trans = trans.next;
        }
        if (trans == null) throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] listToArray() {

        Transaction[] arrOfTrans = new Transaction[number];
        TransNode tmp = this.first;

        for (int i = 0; i < number; ++i) {
            arrOfTrans[i] = tmp.value;
            tmp = tmp.next;
        }
        return (arrOfTrans);
    }

    public TransNode getFirst() {
        return first;
    }

    public int getNumber() {
        return number;
    }
}
