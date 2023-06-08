import java.util.HashMap;

public class Bank
{
    private final int routingNumber;
    private static long lastTxnId;
    private static int lastCID = 0;
    private static int lastAccID = 0;
    private static HashMap<Integer, Customer> customerList;

    //Bank constructor
    public Bank(int newRoutingNumber)
    {
        routingNumber = newRoutingNumber;
        customerList = new HashMap<>();
    }

    //Gets a customer from the big customer list, returns a copy without exposing the customer object
    public Customer getCustomer(int id)
    {
        if(customerList.get(id) == null)
        {
            System.out.println("No customer with that ID");
            return null;
        }
        String copyName = customerList.get(id).getName();
        int copyID = customerList.get(id).getCustomerID();
        int copySSN = customerList.get(id).getSsn();
        HashMap<AccountType, BankAccount> copyList = new HashMap<>(customerList.get(id).getAllAccounts());
        Customer copy = new Customer(copyName, copyID, copySSN);
        for(AccountType t : copyList.keySet())
        {
            copy.addAccount(copyList.get(t));
        }
        return copy;
    }

    //Simplifying, initialize both checking/savings accs, although in reality this is not required
    public Customer addCustomer(String name, int SSN, double checkingDepo, double savingsDepo)
    {
        lastCID++;
        Customer customer = new Customer(name, SSN, lastCID);

        lastAccID++;
        BankAccount chk = new BankAccount(AccountType.c, lastAccID, checkingDepo);

        lastAccID++;
        BankAccount sv = new BankAccount(AccountType.s, lastAccID, savingsDepo);
        customer.addAccount(chk);
        customer.addAccount(sv);
        System.out.println("New customer added " + name);
        return customer;
    }

    //Method to increment the transaction ID, static because there is only 1 bank
    public static void incrementTxnID()
    {
        lastTxnId++;
    }

    //Setter for the transaction ID, used for debugging purposes
    public static void setTxnID(long longMan)
    {
        //for debugging purposes
        lastTxnId = longMan;
    }

    //Getters
    public int getRoutingNumber()
    { return routingNumber; }

    public static long getLastTxnId()
    { return lastTxnId; }

    public static long getLastCID()
    { return lastCID; }

    public static long getAccID()
    { return lastAccID; }
}
