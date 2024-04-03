import java.util.Date;

/**
 * Savings account class creates simple savings and CD account types
 */
public class Savings extends Account{
    protected int ssn;
    protected String savingsaccountid;
    protected String passbook;
    protected boolean cdcheck;
    protected double balance;
    protected double interestrate;
    protected Date dateopened;
    protected String accounttype;
    protected Date CDdue;
    protected int ATMwithdrawalfrequency;
    //constructor, CDdue is null for simple savings
    public Savings(String savingsaccountid,int ssn,double balance, double interestrate,Date dateopened,String passbook,Date CDdue){
        this.ssn = ssn;
        this.savingsaccountid = savingsaccountid;
        this.passbook = passbook;
        this.balance = balance;
        this.interestrate = interestrate;
        this.dateopened = dateopened;
        if(passbook == null){
            this.cdcheck = false;
        }
        else cdcheck = true;
        if(cdcheck){
            this.accounttype = "CD";
        }
        else this.accounttype = "Simple Savings";
    }
    //this is the simple savings account ATM withdrawal function
    public void WithdrawFromATM(double amount){
        if(ATMwithdrawalfrequency>2){
            System.out.println("Cannot withdraw more than twice per day");
        }
        else if(cdcheck) {
            System.out.println("ATM card cannot withdraw from CD");
        }
        else{
            if(amount>balance){
                System.out.println("Insufficent balance to withdraw "+amount);
            }
            else{
                balance = balance-amount;
            }
        }
    }
    //returns the current balance
    @Override
    public double getBalance(){
        return this.balance;
    }
    //sets the current balance
    @Override
    public double setBalance(double newbalance){
        this.balance = newbalance;
        return balance;
    }
    //returns the interest rate
    public double getInterestrate(){
        return this.interestrate;
    }
    //sets the current interest rate
    public void setInterestrate(double interestrate){
        this.interestrate = interestrate;
    }

    public boolean isCdcheck() {
        return cdcheck;
    }

    public void setCdcheck(boolean cdcheck) {
        this.cdcheck = cdcheck;
    }

    public Date getDateopened() {
        return dateopened;
    }

    public void setDateopened(Date dateopened) {
        this.dateopened = dateopened;
    }
    @Override
    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public Date getCDdue() {
        return CDdue;
    }

    public void setCDdue(Date CDdue) {
        this.CDdue = CDdue;
    }
}
