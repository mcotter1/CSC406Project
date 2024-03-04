import java.util.ArrayList;

public class Customer extends User{
    private String lastname;
    private String firstname;
    private String state;
    private String city;
    private int zipcode;
    private String address;
    private int idnumber;
    ArrayList<Account> accounts = new ArrayList<>();
    public Customer(int idnumber, String firstname, String lastname, String state, String city, int zipcode, String address){
        this.idnumber = idnumber;
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

    public int getIdnumber() {
        return idnumber;
    }

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

    public void setIdnumber(int idnumber) {
        this.idnumber = idnumber;
    }
    @Override
    public void DebitAccount(double amount,Account account){
        double newbalance = account.getBalance()-amount;
        account.setBalance(newbalance);
    }


}
