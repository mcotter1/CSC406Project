
public class Savings extends Account{
    private boolean cdcheck;
    private double balance;
    private double interestrate;
    private String dateopened;
    private String CDdue;
    public Savings(boolean cdcheck, double balance,double interestrate,String dateopened,String CDdue){
        this.cdcheck = cdcheck;
        this.balance = balance;
        this.interestrate = interestrate;
        this.dateopened = dateopened;
        this.CDdue = CDdue;
    }

}
