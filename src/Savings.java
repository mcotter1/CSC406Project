
public class Savings extends Account{
    protected int accountnumber;
    private boolean cdcheck;
    private double balance;
    protected double interestrate;
    private String dateopened;
    private String CDdue;
    public Savings(boolean cdcheck, double balance,double interestrate,String dateopened,String CDdue,int accountnumber){
        this.cdcheck = cdcheck;
        this.balance = balance;
        this.interestrate = interestrate;
        this.dateopened = dateopened;
        this.CDdue = CDdue;
        this.accountnumber = accountnumber;

    }
    @Override
    public double getBalance(){
        return this.balance;
    }
    @Override
    public void setBalance(double newbalance){
        this.balance = newbalance;
    }
    @Override
    public void getAccountNumber(){

    }
    public double getInterestrate(){
        return this.interestrate;
    }



}
