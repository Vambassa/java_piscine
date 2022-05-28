public interface TransactionsList {

    void addTransaction(Transaction transaction);
    void removeTransaction(String identifier);
    Transaction[] listToArray();
}
 