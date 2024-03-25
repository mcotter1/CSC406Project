import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("t.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<User> users2 = new ArrayList<User>();
        users2 = (ArrayList<User>) ois.readObject();
        ois.close();
        System.out.println(users2);


    }
}