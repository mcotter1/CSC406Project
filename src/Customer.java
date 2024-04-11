import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends User {
    protected String lastname;
    protected String firstname;
    protected String state;
    protected String city;
    protected int zipcode;
    protected String address;
    protected int ssn;
    ArrayList<Account> accounts = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();
    public Customer(int ssn, String address, String city, String state, int zipcode, String firstname, String lastname){
        this.ssn = ssn;
        this.firstname = firstname;
        this.lastname = lastname;
        this.state = state;
        this.city = city;
        this.zipcode= zipcode;
        this.address = address;
    }
    public void AddAccount(Account x){
        accounts.add(x);
    }
    public void AddTransaction(Transaction x){
        transactions.add(x);
    }

    @Override
    public void DebitAccount(double amount,Account account){
        double newbalance = account.getBalance()-amount;
        account.setBalance(newbalance);
    }
    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getAddress() {
        return address;
    }
    @Override
    public int getSSN() {
        return ssn;
    }
    @Override
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSSN(int ssn) {
        this.ssn = ssn;
    }




}
