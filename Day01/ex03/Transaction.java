import java.util.UUID;

enum TransferType {CREDIT, DEBIT}

public class Transaction {

    private UUID            identifier;
    private User            recipient;
    private User            sender;
    private String          transferCategory;
    private Integer         transferAmount;

    public Transaction(User recipient, User sender, TransferType transferCategory, Integer transferAmount) {
        this.identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferAmount = transferAmount;

        switch (transferCategory) {
            case CREDIT:
                this.transferCategory = "OUTCOME";
                break;

            case DEBIT:
                this.transferCategory = "INCOME";
        }
    }

    public static Transaction makeTransaction(User recipient, User sender, TransferType transferCategory, Integer transferAmount) {

        if ((transferCategory == TransferType.CREDIT && transferAmount < 0 && sender.getBalance() >= -transferAmount)
            || (transferCategory == TransferType.DEBIT && transferAmount > 0 && sender.getBalance() >= transferAmount)) {
                return new Transaction(recipient, sender, transferCategory, transferAmount);
        } else {
            printError("Failed transaction");
            return null;
        }
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public String getTransferCategory() {
        return transferCategory;
    }

    @Override
    public String toString() {
        String sign;
        if (this.getTransferCategory().equals("OUTCOME")) {
            sign = "";
        } else {
            sign = "+";
        }
        return String.format("%s -> %s, %s%s, %s, %s", sender.getName(), recipient.getName(), sign, transferAmount, transferCategory, identifier);
    }

    private static void printError(String str) {
        System.err.println(str);
    }
}