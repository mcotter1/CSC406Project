import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Customer customer1 = new Customer(423453245,"114 North 4th","Clarkesdale","MO",64493,"Ronald","Jones");
        Customer customer2 = new Customer(345653425,"1805 Jules","St. Joseph","MO",64503,"Mark","Ingrem");
        Savings simplesavings = new Savings("SA21",423148894,250.23,0.46,new Date(2017,Calendar.JUNE,26),null,null);
        Checking golddiamond = new Checking(423453245,"Gold",5502.06,"SA21",0,new Date(2024,Calendar.JANUARY,3));
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
        Savings tempsavings;
        int accountindex;
        tempsavings = (Savings) users2.get(0).getAccounts().get(0);
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