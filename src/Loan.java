import java.util.Date;

public class Loan extends Account{
    protected int ssn;
    protected int accountnumber;
    protected double balance;
    protected double interestrate;
    protected Date datepaymentdue;
    protected Date notifiedofpayment;
    protected double paymentamountdue;
    protected Date lastpaymentdate;
    protected boolean missedpayment;
    protected String collateral;
    protected String accounttype;

    public Loan(int ssn,int accountnumber, double balance, double interestrate, Date datepaymentdue, Date notifiedofpayment, double paymentamountdue, Date lastpaymentdate, boolean missedpayment, String collateral, String accounttype) {
        this.ssn = ssn;
        this.accountnumber = accountnumber;
        this.balance = balance;
        this.interestrate = interestrate;
        this.datepaymentdue = datepaymentdue;
        this.notifiedofpayment = notifiedofpayment;
        this.paymentamountdue = paymentamountdue;
        this.lastpaymentdate = lastpaymentdate;
        this.missedpayment = missedpayment;
        this.collateral = collateral;
        this.accounttype = accounttype;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public int getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(int accountnumber) {
        this.accountnumber = accountnumber;
    }

    public double getBalance() {
        return balance;
    }
    @Override
    public double setBalance(double balance) {
        this.balance = balance;
        return balance;
    }

    @Override
    public int getAccountNumber() {
        return accountnumber;
    }

    public double getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(double interestrate) {
        this.interestrate = interestrate;
    }

    public Date getDatepaymentdue() {
        return datepaymentdue;
    }

    public void setDatepaymentdue(Date datepaymentdue) {
        this.datepaymentdue = datepaymentdue;
    }

    public Date getNotifiedofpayment() {
        return notifiedofpayment;
    }

    public void setNotifiedofpayment(Date notifiedofpayment) {
        this.notifiedofpayment = notifiedofpayment;
    }

    public double getPaymentamountdue() {
        return paymentamountdue;
    }

    public void setPaymentamountdue(double paymentamountdue) {
        this.paymentamountdue = paymentamountdue;
    }

    public Date getLastpaymentdate() {
        return lastpaymentdate;
    }

    public void setLastpaymentdate(Date lastpaymentdate) {
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
