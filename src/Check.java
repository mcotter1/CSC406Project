import java.io.Serializable;

/**
 * Class used for making check objects
 */
public class Check implements Serializable {
    protected int checknumber;
    protected double paymentamount;
    //boolean for checking if payment went through or was overdrafted
    protected boolean paid;
    public Check(int checknumber){
        this.checknumber = checknumber;
    }
    public int getChecknumber(){
        return this.checknumber;
    }
    public double getPaymentamount(){
        return this.paymentamount;
    }

    public void setChecknumber(int checknumber) {
        this.checknumber = checknumber;
    }

    public void setPaymentamount(double paymentamount) {
        this.paymentamount = paymentamount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
