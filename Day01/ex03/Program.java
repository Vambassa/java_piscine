import java.util.UUID;

public class Program {

    public static void main(String[] args) {

        User hero1 = new User("John", 20_000);
        User hero2 = new User("Mike", 1_000);

        TransactionsLinkedList list = new TransactionsLinkedList();

        Transaction transaction1 = Transaction.makeTransaction(hero2, hero1, TransferType.CREDIT, -1_000);
        Transaction transaction2 = Transaction.makeTransaction(hero2, hero1, TransferType.CREDIT, -1_000);
        hero1.setBalance(hero1.getBalance() - 1_000);
        hero1.setBalance(hero1.getBalance() - 1_000);

        list.addTransaction(transaction1);
        list.addTransaction(transaction2);

        TransNode tmp = list.getFirst();

        while (tmp != null) {
            System.out.println(tmp.value.getIdentifier());
            tmp = tmp.next;
        }
        System.out.println();

        System.out.println(transaction1.getIdentifier());
        System.out.println(transaction2.getIdentifier());
        System.out.println();

        Transaction[] transArr = list.listToArray();
        int length = list.getNumber();
        for (int i = 0; i < length; ++i) {
            System.out.println(transArr[i].getIdentifier());
        }
        System.out.println();

        // list.removeTransaction("534d4f0e-355f-4d3c-b26e-47600f098ae9");
        // System.out.println();

        list.removeTransaction(transArr[0].getIdentifier().toString());

        TransNode tmp1 = list.getFirst();

        while (tmp1 != null) {
            System.out.println(tmp1.value.getIdentifier());
            tmp1 = tmp1.next;
        }
        System.out.println();

        list.removeTransaction(transArr[1].getIdentifier().toString());
        while (tmp1 != null) {
            System.out.println(tmp1.value.getIdentifier());
            tmp1 = tmp1.next;
        }
    }
}
