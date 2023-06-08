import java.util.HashMap;

public class Customer
{
    protected final String name;
    protected final int customerID;
    protected final int ssn;
    private HashMap<AccountType, BankAccount> accountList;

    //Constructor for the customer class object
    public Customer(String newName, int newID, int newSSN)
    {
        accountList = new HashMap<>();
        name = newName; customerID = newID; ssn = newSSN;
    }

    //Adds an account to the account list, taking a BankAccount object as an arg
    public void addAccount(BankAccount acc)
    {
        AccountType type = acc.getType();
        if(accountList.get(type) == null)
        {
            accountList.put(type, acc);
        }
        else
        {
            System.out.println("Account already has an account of that type: " + type.name());
        }
    }

    //Returns a copy of the account list without exposing the core list and subjecting it to mutation
    public HashMap getAllAccounts()
    {
        HashMap<AccountType, BankAccount> copy = new HashMap<>(accountList);
        return copy;
    }

    //Returns a copy of the account without exposing it
    public BankAccount getAccount(AccountType type)
    {
        if(accountList.get(type) == null)
        {
            System.out.println("No account for that type");
            return null;
        }
        BankAccount copy = new BankAccount(accountList.get(type));
        return copy;
    }

    //Returns a reference to the account that is mutable. Some care would need to be taken when using this
    //as the core account object is exposed
    public BankAccount getMutableAccount(AccountType type)
    {
        if(accountList.get(type) == null)
        {
            System.out.println("No account for that type");
            return null;
        }
        return accountList.get(type);
    }

    //getters
    public int getSsn() {return ssn;}
    public int getCustomerID() {return customerID;}
    public String getName() {return name;}

}
