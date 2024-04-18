import java.time.LocalDate;
import java.util.ArrayList;

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
    protected LocalDate dateopened;
    protected String accounttype;
    protected LocalDate CDdue;
    protected int ATMwithdrawalfrequency;
    protected boolean ATMcard;
    ArrayList<Transaction> transactions = new ArrayList<>();
    //constructor, CDdue is null for simple savings
    public Savings(String savingsaccountid,int ssn,double balance, double interestrate,LocalDate dateopened,String passbook,LocalDate CDdue,boolean ATMcard){
        this.ssn = ssn;
        this.savingsaccountid = savingsaccountid;
        this.passbook = passbook;
        this.balance = balance;
        this.interestrate = interestrate;
        this.dateopened = dateopened;
        this.CDdue = CDdue;
        if(CDdue==null){
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
    //this function issues a rollover notice for an account
    public String IssueRolloverNotice(){
        String rollovernotice;
        if(accounttype.equalsIgnoreCase("cd")){
            rollovernotice = String.format("This CD is due: "+CDdue);
        } else {
            rollovernotice = "This is not a CD";
        }
        return rollovernotice;
    }
    @Override
    public void AddTransaction(Transaction transaction){
        transactions.add(transaction);
    }
    public ArrayList<Transaction> getTransactions(){
        return transactions;
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

    public LocalDate getDateopened() {
        return dateopened;
    }

    public void setDateopened(LocalDate dateopened) {
        this.dateopened = dateopened;
    }
    @Override
    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public LocalDate getCDdue() {
        return this.CDdue;
    }

    public void setCDdue(LocalDate CDdue) {
        this.CDdue = CDdue;
    }
    public String getSavingsaccountid(){
        return savingsaccountid;
    }
    @Override
    public int getSsn(){return ssn;}
    @Override
    public String toString(){
        String s = String.format("Account type: %s    Balance: $%.2f",accounttype,balance);
        return s;
    }
}
