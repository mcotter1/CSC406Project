
public class Main {
    public static void main(String[] args){
        Savings simplesavings = new Savings(false,50000,0.02,"12-14-2019","12-30-2021",01);
        Customer user1 = new Customer(140910110,"Thomas","Hutton","MO","St. Joseph",64506,"2626 Lovers Lane");
        user1.AddAccount(simplesavings);
        System.out.println(user1.getAccounts().get(0).getBalance());
        user1.getAccounts().get(0).setBalance(60000);
        System.out.println(user1.getAccounts().get(0).getBalance());


    }
}