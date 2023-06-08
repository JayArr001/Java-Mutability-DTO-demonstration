public class Transaction
{
    //This class is a DTO and could be a record
    private final int routingNumber, customerId, accId;
    private final Long txnId;
    private final double amount;

    public Transaction(int inputRoutingNumber, int inputCID, int inputAccID,
                       Long inputTxnID, double inputDouble)
    {
        routingNumber = inputRoutingNumber; customerId = inputCID; accId = inputAccID;
        txnId = inputTxnID; amount = inputDouble;
    }

    public int getAccId() {
        return accId;
    }

    public int getRoutingNumber() {
        return routingNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Long getTxnId() {
        return txnId;
    }

    public double getAmount() {
        return amount;
    }
}
