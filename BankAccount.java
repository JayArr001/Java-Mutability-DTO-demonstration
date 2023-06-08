import java.util.HashMap;

enum AccountType {c,s}
public class BankAccount
{
    private final AccountType type;
    private final int accId;
    private double balance;
    private HashMap<Long, Transaction> txnList;

    //Constructor for the BankAccount class, holds balance, account type and ID
    public BankAccount(AccountType newType, int newID, double bal)
    {
        type = newType; accId = newID; balance = bal;
        txnList = new HashMap<>();
    }

    //Overloaded constructor which allows passing of an existing bank account to create a new one
    //used to return defensive copies to prevent mutation
    public BankAccount(BankAccount acc)
    {
        this(acc.getType(), acc.getId(), acc.getBalance());
    }

    //Initiates a transaction for this account. Since the routing number is static we didn't need
    //to require it in the method arg but it is what it is
    public void doTxn(int routingNum, int customerID, double amount)
    {
        //if the txn is to remove some $ of balance and the operation puts acc into the red
        //not allowing overdraft for simplicity
        if((amount < 0) && ((balance + amount) < 0))
        {
            System.out.println("Insufficient funds for this transaction on account " + accId);
            System.out.println(amount + " with only a balance of " + balance);
            return;
        }
        long txnId = Bank.getLastTxnId() + 1;
        //Recursion failsafe, stopping at 10k because this is only a demonstration of Java skills
        //and not a real bank that needs to handle and store a large number of transactions
        if(txnId > 10000)
        {
            return;
        }
        Bank.incrementTxnID();
        if(txnList.get(txnId) != null)
        {
            System.out.println("A value was already mapped for " + txnId + " - attempting again for +1");
            doTxn(routingNum, customerID, amount);
            return;
        }
        Transaction transaction = new Transaction(routingNum, customerID, accId, txnId, amount);
        txnList.put(txnId, transaction);
        balance += amount;
        System.out.println("Account " + accId + " - " + txnId);
        System.out.println("Transacted " + amount + ". New balance: " + balance);
        //Bank.transact(txnId, type, amount);
    }

    //Returns a copy of the transaction list and the original is not exposed. The calling method
    //can do whatever it wants to the copy without changing the core list
    public HashMap<Long, Transaction> getTxns()
    {
        HashMap<Long, Transaction> copy = new HashMap<>(txnList);
        return copy;
    }

    //Getters for the immutable fields
    public Double getBalance()
    { return balance;}

    public int getId()
    { return accId;}

    public AccountType getType()
    { return type;}

    @Override
    public String toString() {
        return "BankAccount{" +
                "type=" + type +
                ", accId=" + accId +
                ", balance=" + balance +
                '}';
    }

    //Making the balance mutable
    public void setBalance(double d)
    {
        balance = d;
    }
}
