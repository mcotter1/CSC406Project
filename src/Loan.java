import java.time.LocalDate;

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
    @Override
    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
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
}
