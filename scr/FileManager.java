import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public void add_user(String ID, String name, String password, double balance){
        try {
            FileWriter fw = new FileWriter("users.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ID +"," + name +"," + password + "," + balance);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }

    public ArrayList<User> read_users() {
        ArrayList<User> users = new ArrayList<>();
        try {
            File file = new File("users.txt");
            if (!file.exists()) return users; // Return empty list if file doesn't exist yet

            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] data = line.split(",");

                User u = new User(data[0], data[1], data[2], Double.parseDouble(data[3]));
                users.add(u);
            }
            fileReader.close();
        } catch (Exception e) {
            System.err.println("Error reading users: " + e.getMessage());
        }
        return users;
    }

    public void update_all_users(ArrayList<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", false))) {
            for (User u : users) {
                bw.write(u.get_id() + "," + u.get_username() + "," + u.get_password() + "," + u.get_balance());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }
}
