import java.time.LocalDate;
import java.util.ArrayList;

public class Loan extends Account{
    protected int ssn;
    protected double balance;
    protected double interestrate;
    protected LocalDate datepaymentdue;
    protected LocalDate notifiedofpayment;
    protected double paymentamountdue;
    protected LocalDate lastpaymentdate;
    protected boolean missedpayment;
    protected String collateral;
    protected String accounttype;
    protected double termlength;
    protected String repaymentplantype;
    protected String loantype;
    protected double creditcardlimit;
    ArrayList<Transaction> transactions = new ArrayList<>();
    public Loan(int ssn, double balance, double interestrate, LocalDate datepaymentdue, LocalDate notifiedofpayment, double paymentamountdue, LocalDate lastpaymentdate, boolean missedpayment,double termlength, String collateral, String repaymentplantype,String loantype,double creditcardlimit) {
        this.ssn = ssn;
        this.balance = balance;
        this.interestrate = interestrate;
        this.datepaymentdue = datepaymentdue;
        this.notifiedofpayment = notifiedofpayment;
        this.paymentamountdue = paymentamountdue;
        this.lastpaymentdate = lastpaymentdate;
        this.missedpayment = missedpayment;
        this.termlength = termlength;
        this.collateral = collateral;
        this.repaymentplantype = repaymentplantype;
        this.loantype = loantype;
        this.creditcardlimit = creditcardlimit;
        if(this.loantype.equalsIgnoreCase("long term")){
            this.accounttype = "Long Term Loan";
        }
        if(this.loantype.equalsIgnoreCase("Short Term")){
            this.accounttype = "Short Term Loan";
        }
        if(this.creditcardlimit!=0){
            this.accounttype = "Credit Card";
        }
    }
    //this function checks all the credit card transactions for the current month and returns the total amount owed
    public double IssueBillCreditCard(){
        LocalDate currentdate = LocalDate.now();
        double amountowed = 0;
        for(int i=0;i<transactions.size();i++){
            if(transactions.get(i).getDateoccurred().getMonth().equals(currentdate.getMonth())){
                amountowed+=transactions.get(i).getAmount();
            }
        }
        return amountowed;
    }
    //this function allows a user to make a credit card payment
    public void PayWithCreditCard(double amount){
        LocalDate today = LocalDate.now();
        if(accounttype.equalsIgnoreCase("credit card")){
            if(amount+getBalance()>creditcardlimit){
                System.out.println("Exceeds credit limit");
            }
            else{
                Transaction currenttransaction = new Transaction("Purchase","Credit Card",amount,today);
                balance = balance+amount;
            }
        } else {
            System.out.println("This is not an open line of credit");
        }
    }
    public void AddTransaction(Transaction transaction){
        transactions.add(transaction);
    }
    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }
    @Override
    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public double getTermlength() {
        return termlength;
    }

    public void setTermlength(double termlength) {
        this.termlength = termlength;
    }

    public String getRepaymentplantype() {
        return repaymentplantype;
    }

    public void setRepaymentplantype(String repaymentplantype) {
        this.repaymentplantype = repaymentplantype;
    }

    public String getLoantype() {
        return loantype;
    }

    public void setLoantype(String loantype) {
        this.loantype = loantype;
    }

    public double getCreditcardlimit() {
        return creditcardlimit;
    }

    public void setCreditcardlimit(double creditcardlimit) {
        this.creditcardlimit = creditcardlimit;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getBalance() {
        return balance;
    }
    @Override
    public double setBalance(double balance) {
        this.balance = balance;
        return balance;
    }
    public double getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(double interestrate) {
        this.interestrate = interestrate;
    }

    public LocalDate getDatepaymentdue() {
        return datepaymentdue;
    }

    public void setDatepaymentdue(LocalDate datepaymentdue) {
        this.datepaymentdue = datepaymentdue;
    }

    public LocalDate getNotifiedofpayment() {
        return notifiedofpayment;
    }

    public void setNotifiedofpayment(LocalDate notifiedofpayment) {
        this.notifiedofpayment = notifiedofpayment;
    }

    public double getPaymentamountdue() {
        return paymentamountdue;
    }

    public void setPaymentamountdue(double paymentamountdue) {
        this.paymentamountdue = paymentamountdue;
    }

    public LocalDate getLastpaymentdate() {
        return lastpaymentdate;
    }

    public void setLastpaymentdate(LocalDate lastpaymentdate) {
        this.lastpaymentdate = lastpaymentdate;
    }

    public boolean isMissedpayment() {
        return missedpayment;
    }

    public void setMissedpayment(boolean missedpayment) {
        this.missedpayment = missedpayment;
    }

    public String getCollateral() {
        return collateral;
    }

    public void setCollateral(String collateral) {
        this.collateral = collateral;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }
    @Override
    public String toString(){
        String s = String.format("Account type: %s    Balance: %.2f",accounttype,balance);
        return s;
    }

}
