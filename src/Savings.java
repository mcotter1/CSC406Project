
public class Savings extends Account{
    protected int ID;
    private boolean cdcheck;
    private double balance;
    protected double interestrate;
    private String dateopened;
    private String CDdue;
    public Savings(boolean cdcheck, double balance,double interestrate,String dateopened,String CDdue,int ID){
        this.cdcheck = cdcheck;
        this.balance = balance;
        this.interestrate = interestrate;
        this.dateopened = dateopened;
        this.CDdue = CDdue;
        this.ID = ID;

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
    public void getID(){

    }
    public double getInterestrate(){
        return this.interestrate;
    }



}
