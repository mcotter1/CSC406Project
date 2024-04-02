import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Customer extends User {
    private String lastname;
    private String firstname;
    private String state;
    private String city;
    private int zipcode;
    private String address;
    private int ssn;
    ArrayList<Account> accounts = new ArrayList<>();
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

    @Override
    public void DebitAccount(double amount,Account account){
        double newbalance = account.getBalance()-amount;
        account.setBalance(newbalance);
    }
    public String getLastname() {
        return lastname;
    }

    public SimpleStringProperty LastnameProperty(){
        return new SimpleStringProperty(lastname);
    }

    public String getFirstname() {
        return firstname;
    }

    public SimpleStringProperty FirstnameProperty(){
        return new SimpleStringProperty(firstname);
    }

    public String getState() {
        return state;
    }

    public SimpleStringProperty StateProperty(){
        return new SimpleStringProperty(state);
    }

    public String getCity() {
        return city;
    }

    public SimpleStringProperty CityProperty(){
        return new SimpleStringProperty(city);
    }

    public int getZipcode() {
        return zipcode;
    }

    public SimpleStringProperty ZipCodeProperty(){
        return new SimpleStringProperty(Integer.toString(zipcode));
    }

    public String getAddress() {
        return address;
    }

    public SimpleStringProperty AddressProperty(){
        return new SimpleStringProperty(address);
    }

    @Override
    public int getSSN() {
        return ssn;
    }

    public SimpleStringProperty SSNProperty(){
        return new SimpleStringProperty(Integer.toString(ssn));
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
