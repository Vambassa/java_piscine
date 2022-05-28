public class Program {

    public static void main(String[] args) {

        User hero1 = new User("John", 1_000);
        User hero2 = new User("Mike", 1_000);
        hero1.setIdentifier(100);
        hero2.setIdentifier(101);

        Transaction transaction1 = Transaction.makeTransaction(hero2, hero1, TransferType.CREDIT, -500);
        Transaction transaction2 = Transaction.makeTransaction(hero1, hero2, TransferType.DEBIT, 500);
        System.out.println(transaction1);
        System.out.println(transaction2);

        hero1.setBalance(hero1.getBalance() - 500);
        hero2.setBalance(hero2.getBalance() + 500);
        System.out.printf("%s %d\n", hero1.getName(), hero1.getBalance());
        System.out.printf("%s %d\n", hero2.getName(), hero2.getBalance());

        transaction1 = Transaction.makeTransaction(hero1, hero2, TransferType.CREDIT, -10_000);
        transaction1 = Transaction.makeTransaction(hero1, hero2, TransferType.DEBIT, 10_000);
    }
}
