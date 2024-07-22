import java.util.HashMap;

//This is a 5-file application that simulates basic banking operations
//adding/storing customer information plus handling transactions
//it is meant to be a demonstration of the author's skill and familiarity in the following topics:
//object instantiation, object mutability and protection, with some Collections usage.
public class Main
{
    public static void main(String[] args)
    {
        HashMap<Integer, Customer> bigCustomerList = new HashMap<>();
        Bank bank = new Bank(1337);
        Customer bob = bank.addCustomer("Bob", 1234, 100.00, 100.00);
        int bobID = bob.getCustomerID();
        if(bigCustomerList.get(bobID) == null)
        {
            bigCustomerList.put(bobID, bob);
        }
        BankAccount bobchk = bob.getMutableAccount(AccountType.c);
        bobchk.doTxn(bank.getRoutingNumber(), bob.getCustomerID(), -50.00);
        bobchk.doTxn(bank.getRoutingNumber(), bob.getCustomerID(), 120.00);
        Bank.setTxnID(0);
        bobchk.doTxn(bank.getRoutingNumber(), bob.getCustomerID(), -5.00);
    }
}
