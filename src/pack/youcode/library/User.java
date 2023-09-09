package pack.youcode.library;

public class User {
    private int id;
    private String name;
    private String phone;
    private String cin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String name, String phone, String cin) {
        this.name = name;
        this.phone = phone;
        this.cin = cin;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
}
