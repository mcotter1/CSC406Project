import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Checking account class creates regular checking and gold/diamond checking accounts
 */
public class Checking extends Account {
    protected int ssn;
    protected double balance;
    protected double golddiamondrate;
    protected boolean golddiamondcheck;
    protected boolean backupsavingscheck;
    protected Savings backupsavingsaccount;
    protected String savingsaccountid;
    protected LocalDate dateopened;
    protected String accounttype;
    protected int ATMwithdrawalfrequency;
    protected boolean ATMcard;
    protected int overdraftsthismonth;
    ArrayList<Check> newchecks = new ArrayList<Check>();
    ArrayList<Check> usedchecks = new ArrayList<Check>();
    public Checking(int ssn, String accounttype,double balance,String savingsaccountid,int overdraftsthismonth, LocalDate dateopeend,boolean ATMcard){
        this.ssn = ssn;
        this.savingsaccountid = savingsaccountid;
        this.overdraftsthismonth = overdraftsthismonth;
        this.accounttype = accounttype;
        this.balance = balance;
        this.dateopened = dateopeend;
        this.ATMcard = ATMcard;
        if(accounttype.equalsIgnoreCase("tmb")){
            golddiamondcheck = false;
        }
        else golddiamondcheck = true;
        if(savingsaccountid==null){
            backupsavingscheck = false;
        }
        else backupsavingscheck = true;
        //for loop within constructor populates newchecks with blank checks
        for(int i = 1;i<=100;i++){
            newchecks.add(new Check(i));
        }
    }
    //this is the checking account ATM withdrawal function
    public void WithdrawFromATM(double amount){
        if(ATMwithdrawalfrequency>2){
            System.out.println("Cannot withdraw more than twice per day");
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
    //Withdraws money from account with a check, savings account must be added to checking account, updated, then pushed
    //back to the user account. Could also change to have savings account be input for this function
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
        }
        paybackamount = usedchecks.get(i).getPaymentamount();
        balance = balance+paybackamount;
        balance = balance - 35;
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
    public String getAccounttype() {
        return this.accounttype;
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

    public LocalDate getDateopened() {
        return dateopened;
    }

    public void setDateopened(LocalDate dateopened) {
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
    @Override
    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getSavingsaccountid() {
        return savingsaccountid;
    }

    public void setOverdraftbackupnumber(String savingsaccountid) {
        this.savingsaccountid = savingsaccountid;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public int getATMwithdrawalfrequency() {
        return ATMwithdrawalfrequency;
    }

    public void setATMwithdrawalfrequency(int ATMwithdrawalfrequency) {
        this.ATMwithdrawalfrequency = ATMwithdrawalfrequency;
    }

    public boolean isATMcard() {
        return ATMcard;
    }

    public void setATMcard(boolean ATMcard) {
        this.ATMcard = ATMcard;
    }
    @Override
    public String toString(){
        String s = String.format("Account type: %s Balance: %f",accounttype,balance);
        return s;
    }
}
