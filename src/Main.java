import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Customer customer1 = new Customer(1010101,"Tommy","Hutton","MO","St. Joseph",64506,"Whatever");
        Customer customer2 = new Customer(101010,"Dakotah","Profitt","MO","St. Joseph",64506,"Whatever");
        Savings simplesavings = new Savings(1010101,false,10101.0,0.1,new Date(2023,Calendar.AUGUST,12),new Date(2023,Calendar.NOVEMBER,14),56706);
        Checking golddiamond = new Checking(10101010,2358394,true,5601.1,new Date(2022,Calendar.DECEMBER,12));
        customer1.AddAccount(simplesavings);
        customer1.AddAccount(golddiamond);
        customer2.AddAccount(simplesavings);
        customer2.AddAccount(golddiamond);
        ArrayList<User> users1 = new ArrayList<>();
        users1.add(customer1);
        users1.add(customer2);
        FileOutputStream fos = new FileOutputStream("t.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(users1);
        FileInputStream fis = new FileInputStream("t.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<User> users2 = new ArrayList<User>();
        users2 = (ArrayList<User>) ois.readObject();
        ois.close();
        System.out.println(users2);
        ArrayList<Account> accounts = new ArrayList<>();
        Account account1 = users2.get(0).getAccounts().get(0);
        Account account2 = users2.get(0).getAccounts().get(1);
        Account account3 = users2.get(1).getAccounts().get(0);
        Account account4 = users2.get(1).getAccounts().get(1);
        System.out.println(account1.getAccounttype());
        System.out.println(account2.getAccounttype());
        System.out.println(account3.getAccounttype());
        System.out.println(account4.getAccounttype());


    }
}