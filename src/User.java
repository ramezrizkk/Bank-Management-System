public class User {
    private String id;
    private String username;
    private String password;
    private double balance;

    public User(String id, String username, String password, double balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public String get_id() { return id; }
    public String get_username() { return username; }
    public String get_password() { return password; }
    public double get_balance() { return balance; }

    public void set_username(String username) { this.username = username; }
    public void set_password(String password) { this.password = password; }
    public void set_balance(double balance) { this.balance = balance; }
}