import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int input_valid_int(Scanner sc) {
        try{
            return sc.nextInt();
        } catch (Exception e) {
            return -1;
        }
    }

    public static void show_login_menu(){
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
        System.out.println("1. Login to your account");
        System.out.println("2. Create an account");
        System.out.println("3. Exit");
        System.out.print("Please enter your choice: ");
    }

    public static void show_banking_menu(){
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
        System.out.println("1. Show balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer Money");
        System.out.println("5. Change account's name");
        System.out.println("6. Change Password");
        System.out.println("7. Delete account");
        System.out.println("8. Exit");
        System.out.print("Please enter your choice: ");
    }

    public static void update_users(User modified_user, FileManager manager){
        ArrayList<User> current_users = manager.read_users();
        for (User u : current_users){
            if (u.get_id().equals(modified_user.get_id())){
                u.set_balance(modified_user.get_balance());
                u.set_password(modified_user.get_password());
                u.set_username(modified_user.get_username());
                manager.update_all_users(current_users);
                break;
            }
        }
    }

    public static void deposit(Scanner sc, User current_user){
        try {
            System.out.println("Enter the amount you want to deposit: ");
            double deposited_amount = sc.nextDouble();
            sc.nextLine();

            if (deposited_amount<= 0){
                System.out.println("The amount you enter must be a positive number.");
                return;
            }

            double balance_after_deposit = current_user.get_balance() + deposited_amount;
            current_user.set_balance(balance_after_deposit);
            System.out.println("$" + deposited_amount + " has been deposited into your account successfully.");
        }catch (Exception e) {
            System.err.println("Invalid input, you must enter a number.");
        }
    }

    public static void withdraw(Scanner sc, User current_user){
        System.out.println("Enter the amount you want to withdraw: ");
        try {
            double withdrawn_amount = sc.nextDouble();
            sc.nextLine();

            if (withdrawn_amount > current_user.get_balance()) {
                System.out.println("The amount you entered exceeds your current balance.");
                return;
            }

            if (withdrawn_amount<= 0){
                System.out.println("The amount you enter must be a positive number");
                return;
            }

            double balance_after_withdrawal = current_user.get_balance() - withdrawn_amount;
            current_user.set_balance(balance_after_withdrawal);
            System.out.println("$" + withdrawn_amount + " has been withdrawn from your account successfully.");
        } catch (Exception e) {
            System.err.println("Invalid input, you must enter a number.");
        }
    }

    public static void change_username(Scanner sc, User current_user){
        System.out.print("Please enter your password to proceed: ");
        String entered_password = sc.nextLine();
        if (entered_password.equals(current_user.get_password())){
            System.out.print("Please enter the new name: ");
            String new_name = sc.nextLine();
            current_user.set_username(new_name);
            System.out.println("The account's name has been changed successfully to: " + new_name);
        }
        else{
            System.out.println("Wrong password!");
        }
    }

    public static void change_password(Scanner sc, User current_user){
        System.out.print("Please enter your password to proceed: ");
        String entered_password = sc.nextLine();
        if (entered_password.equals(current_user.get_password())){
            System.out.print("Please enter the new password: ");
            String new_password = sc.nextLine();
            current_user.set_password(new_password);
            System.out.println("The account's password has been changed successfully.");
        }
        else{
            System.out.println("Wrong password!");
        }
    }

    public static void transfer_money(Scanner sc, User sender, FileManager manager){
        try {
            ArrayList<User> users = manager.read_users();
            System.out.print("Enter recipient ID: ");
            String rid = sc.nextLine();

            User recipient = null;
            for (User u : users) {
                if (u.get_id().equals(rid)) { recipient = u; break; }
            }

            if (recipient == null) {
                System.out.println("Recipient not found.");
                return;
            }

            System.out.print("Amount to transfer: ");
            double amount = sc.nextDouble(); sc.nextLine();

            if (amount > sender.get_balance() || amount <= 0) {
                System.out.println("Invalid input, the amount you enter must be positive and less than your balance.");
            } else {
                for (User u : users) {
                    if (u.get_id().equals(sender.get_id())) u.set_balance(u.get_balance() - amount);
                    if (u.get_id().equals(recipient.get_id())) u.set_balance(u.get_balance() + amount);
                }
                manager.update_all_users(users);
                sender.set_balance(sender.get_balance() - amount);
                System.out.println("$" + amount + " has been transferred successful!");
            }
        } catch (Exception e) {
            System.err.println("You didn't enter a number, try again.");
        }
    }

    public static boolean delete_account(Scanner sc, User current_user, FileManager manager){
        try {
            System.out.print("Are you sure you want to delete your account? (yes/no): ");
            String confirm = sc.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                ArrayList<User> allUsers = manager.read_users();

                for (int i = 0; i < allUsers.size(); i++) {
                    if (allUsers.get(i).get_id().equals(current_user.get_id())) {
                        allUsers.remove(i);
                        break;
                    }
                }
                manager.update_all_users(allUsers);
                System.out.println("Account deleted successfully. Logging out...");
                return true;
            }
        } catch (Exception e) {
            System.err.println("Invalid input, you must enter \"Yes\" or \"No\".");
        }
        return false;
    }

    public static boolean banking_menu(int option, Scanner sc, User current_user, FileManager manager){
        if (option == -1){
            System.out.println("Invalid input, you must enter an integer.");
            return false;
        }
        if (!(option >=1 && option <=8)){
            System.out.println("Please enter a number from the available choices.");
            return false;
        }

        //Show balance
        if (option == 1){
            System.out.println("Your balance = $" + current_user.get_balance());
        }

        //Deposit
        else if (option == 2){
            deposit(sc, current_user);
            update_users(current_user, manager);
        }

        //Withdraw
        else if (option == 3){
            withdraw(sc, current_user);
            update_users(current_user, manager);
        }

        //Transfer Money
        else if (option == 4){
            transfer_money(sc, current_user, manager);
            update_users(current_user, manager);
        }

        //Change account's name
        else if (option == 5) {
            change_username(sc, current_user);
            update_users(current_user, manager);
        }

        //Change Password
        else if (option == 6) {
            change_password(sc, current_user);
            update_users(current_user, manager);
        }

        //Delete account
        else if (option == 7){
            return delete_account(sc, current_user, manager);
        }

        //Exit
        else if (option == 8) {
            return true;
        }

        return false;
    }

    public static void login(Scanner sc, FileManager manager){
        System.out.print("Please enter your ID: ");
        String id = sc.nextLine();
        System.out.print("Please enter your password: ");
        String password = sc.nextLine();
        ArrayList<User> allUsers = manager.read_users();
        User loggedIn_user = null;
        for (User u : allUsers){
            if(u.get_id().equals(id) && u.get_password().equals(password)){
                loggedIn_user = u;
                break;
            }
        }
        if (loggedIn_user != null){
            System.out.println("Welcome, " + loggedIn_user.get_username() + ".");
            boolean logout = false;
            while (!logout) {
                show_banking_menu();
                int option = input_valid_int(sc); sc.nextLine();
                logout = banking_menu(option, sc, loggedIn_user,manager);
            }
            System.out.println("Goodbye, " + loggedIn_user.get_username() + ".");
        }
        else{
            System.out.println("Invalid ID or Password.");
        }
    }

    public static void create_account(Scanner sc, FileManager manager, int count){
        System.out.print("Please enter your name: ");
        String name = sc.nextLine();
        System.out.print("Please enter your password: ");
        String password = sc.nextLine();
        String id = String.valueOf(count);;

        User newUser = new User(id, name, password, 0.0);

        manager.add_user(newUser.get_id(), newUser.get_username(), newUser.get_password(), newUser.get_balance());

        System.out.println("Your account was created successfully, your ID is: " + id + ".");

        boolean logout = false;
        while (!logout) {
            show_banking_menu();
            int option = input_valid_int(sc); sc.nextLine();
            logout = banking_menu(option, sc, newUser, manager);
        }
        System.out.println("Goodbye, " + newUser.get_username() + ".");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        FileManager file_manager = new FileManager();

        ArrayList<User> existingUsers = file_manager.read_users();
        int counter = 0;
        if (!existingUsers.isEmpty()) {
            counter = Integer.parseInt(existingUsers.get(existingUsers.size() - 1).get_id()) + 1;
        }

        while (true){
            show_login_menu();
            int choice = input_valid_int(input);
            input.nextLine();

            //error with the input
            if (choice == -1){
                System.out.println("Invalid input, Please enter an integer");
                continue;
            }

            //Login
            else if (choice == 1){
                login(input, file_manager);
            }

            //Create a new account
            else if(choice == 2){
                create_account(input, file_manager, counter++);
            }

            //Exit
            else if(choice == 3){
                System.out.println("Goodbye");
                break;
            }

            //Another number
            else{
                System.out.println("Please enter a number from the available choices");
            }
        }
        input.close();
    }
}