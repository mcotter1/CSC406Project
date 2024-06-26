import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This is the class for all loan accounts
 */
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

    /**
     * constructor for the loan class, contains logic for setting account type
     * @param ssn
     * @param balance
     * @param interestrate
     * @param datepaymentdue
     * @param notifiedofpayment
     * @param paymentamountdue
     * @param lastpaymentdate
     * @param missedpayment
     * @param termlength
     * @param collateral
     * @param repaymentplantype
     * @param loantype
     * @param creditcardlimit
     */
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

    /**
     * This function iterates through the credit card transactions and produces a bill
     * @return
     */
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

    /**
     * This function allows a user to pay with a credit card
     * @param amount
     */
    public void PayWithCreditCard(double amount){
        LocalDate today = LocalDate.now();
        if(accounttype.equalsIgnoreCase("credit card")){
            if(amount+getBalance()>creditcardlimit){
                System.out.println("Exceeds credit limit");
            }
            else{
                balance = balance+amount;
                Transaction currenttransaction = new Transaction("Purchase","Credit Card",amount,today,balance);
            }
        } else {
            System.out.println("This is not an open line of credit");
        }
    }

    /**
     * This function allows adding to transaction list
     * @param transaction
     */
    @Override
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

    public void AddBilledTransaction(BilledTransaction billedTransaction){
        transactions.add(billedTransaction);
    }

    public ArrayList<Transaction> getBilledTransactions(){
        ArrayList<Transaction> billedtransactions = new ArrayList<>();
        for(int i=0;i<transactions.size();i++){
            if(transactions.get(i).getTransactiontype().equalsIgnoreCase("payment")){
                billedtransactions.add(transactions.get(i));
            }
        }
        return billedtransactions;
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
        String s = String.format("Account type: %s    Balance: $%.2f",accounttype,balance);
        return s;
    }


}
