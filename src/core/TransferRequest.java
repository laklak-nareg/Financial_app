package core;

public class TransferRequest {
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private double amount;
    private boolean approved;

    public TransferRequest(String sourceAccountNumber, String destinationAccountNumber, double amount){
        this.sourceAccountNumber = sourceAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.amount = amount;
        this.approved = false;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved1) {
        approved = approved1;
    }
}
