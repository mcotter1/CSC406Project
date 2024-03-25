import java.util.ArrayList;
import java.util.Date;

/**
 * Checking account class creates regular checking and gold/diamond checking accounts
 */
public class Checking extends Account {
    protected int ssn;
    protected int accountnumber;
    protected double balance;
    protected double golddiamondrate;
    protected boolean golddiamondcheck;
    protected boolean backupsavingscheck;
    protected Savings backupsavingsaccount;
    protected Date dateopened;
    ArrayList<Check> newchecks = new ArrayList<Check>();
    ArrayList<Check> usedchecks = new ArrayList<Check>();
    public Checking(int ssn, int accountnumber,boolean golddiamondcheck,double balance, Date dateopeend){
        this.ssn = ssn;
        this.accountnumber = accountnumber;
        this.golddiamondcheck = golddiamondcheck;
        this.balance = balance;
        this.dateopened = dateopeend;
        //for loop within constructor populates newchecks with blank checks
        for(int i = 1;i<=100;i++){
            newchecks.add(new Check(i));
        }
    }
    //Withdraws money from account with a check, savings account must be added to checking account, updated, then pushed
    //back to the user account
    public Check WriteCheck(double paymentamount) {
        Check check;
        check = newchecks.getFirst();
        newchecks.removeFirst();
        check.setPaymentamount(paymentamount);
        double addbalance;
        //not gold diamond
        if(!golddiamondcheck) {
            //insufficient funds and no backup savings account
            if (check.getPaymentamount() > balance && !backupsavingscheck) {
                check.setPaid(false);
                balance = balance - 25;
                usedchecks.add(check);
                System.out.println("Check not honored and overdraft of $25 taken");
                //insufficient funds and backup savings account
            } else if (check.getPaymentamount() > balance && backupsavingscheck) {
                check.setPaid(true);
                addbalance = backupsavingsaccount.setBalance(backupsavingsaccount.getBalance() - paymentamount - 0.75);
                balance = balance+addbalance;
                usedchecks.add(check);
            }
            //sufficient funds
            else {
                check.setPaid(true);
                balance = balance - paymentamount - 0.75;
                usedchecks.add(check);
            }
        }
        //gold diamond account
        else{
            //sufficient funds and maintaining minimum balance
            if((balance-check.getPaymentamount())>=5000.0){
                check.setPaid(true);
                balance = balance-paymentamount;
                usedchecks.add(check);
            }
            //sufficient funds but going below minimum balance
            else if((balance-check.getPaymentamount())<5000.0 && (balance-check.getPaymentamount())>0.75) {
                System.out.println("You have gone below minimum balance, service charge of $0.75 incurred");
                check.setPaid(true);
                balance = balance - paymentamount - 0.75;
                usedchecks.add(check);

            } else if (check.getPaymentamount() > balance && backupsavingscheck) {
                check.setPaid(true);
                addbalance = backupsavingsaccount.setBalance(backupsavingsaccount.getBalance() - paymentamount - 0.75);
                balance = balance+addbalance;
                usedchecks.add(check);
            }
            else {
                check.setPaid(false);
                balance = balance - 25;
                usedchecks.add(check);
                System.out.println("Check not honored and overdraft of $25 taken");
            }
        }
        return check;
    }
    public void StopPayment(int checknumber){
        int i = 0;
        double paybackamount;
        for(i=0;i<=usedchecks.size()-1;i++){
            if(usedchecks.get(i).getChecknumber()==checknumber){
                usedchecks.get(i).setPaid(false);
                break;
            }
            else System.out.println("Could not find check");
            paybackamount = usedchecks.get(i).getPaymentamount();
            balance = balance+paybackamount;
        }
    }
    @Override
    public double getBalance() {
        return 0;
    }
    @Override
    public double setBalance(double newbalance) {
        this.balance = newbalance;
        return balance;
    }
    @Override
    public int getAccountNumber() {
        return this.accountnumber;
    }

    public int getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(int accountnumber) {
        this.accountnumber = accountnumber;
    }

    public double getGolddiamondrate() {
        return golddiamondrate;
    }

    public void setGolddiamondrate(double golddiamondrate) {
        this.golddiamondrate = golddiamondrate;
    }

    public boolean isGolddiamondcheck() {
        return golddiamondcheck;
    }

    public void setGolddiamondcheck(boolean golddiamondcheck) {
        this.golddiamondcheck = golddiamondcheck;
    }

    public boolean isBackupsavingscheck() {
        return backupsavingscheck;
    }

    public void setBackupsavingscheck(boolean backupsavingscheck) {
        this.backupsavingscheck = backupsavingscheck;
    }

    public Savings getBackupsavingsaccount() {
        return backupsavingsaccount;
    }

    public void setBackupsavingsaccount(Savings backupsavingsaccount) {
        this.backupsavingsaccount = backupsavingsaccount;
    }

    public Date getDateopened() {
        return dateopened;
    }

    public void setDateopened(Date dateopened) {
        this.dateopened = dateopened;
    }

    public ArrayList<Check> getNewchecks() {
        return newchecks;
    }

    public void setNewchecks(ArrayList<Check> newchecks) {
        this.newchecks = newchecks;
    }

    public ArrayList<Check> getUsedchecks() {
        return usedchecks;
    }

    public void setUsedchecks(ArrayList<Check> usedchecks) {
        this.usedchecks = usedchecks;
    }
}
