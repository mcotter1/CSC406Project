import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        Savings simplesavings = new Savings(true,50000,0.02,new Date(2020,Calendar.NOVEMBER,12), new Date(2024, Calendar.AUGUST,1),01);
        Customer user1 = new Customer(140910110,"Thomas","Hutton","MO","St. Joseph",64506,"2626 Lovers Lane");
        user1.AddAccount(simplesavings);
        System.out.println(user1.getAccounts().get(0).getBalance());
        user1.getAccounts().get(0).setBalance(60000);
        System.out.println(user1.getAccounts().get(0).getBalance());
        simplesavings = (Savings) user1.getAccounts().get(0);
        System.out.println(simplesavings.getInterestrate());
        user1.getAccounts().add(0,simplesavings);


    }
}