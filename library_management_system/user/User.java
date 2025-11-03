package library_management_system.user;

public class User {
    private String id;
    private String name;
    private String email;
    private String phone;

    private static final String ID_PATTERN = "^U\\d{3}$";
    private static final String EMAIL_PATTERN = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
    private static final String PHONE_PATTERN = "^0\\d{9}$";

    public User() {};

    public User(String name, String email, String phone) {

    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public static String getEmailPattern() {
        return EMAIL_PATTERN;
    }

    public static String getIdPattern() {
        return ID_PATTERN;
    }

    public static String getPhonePattern() {
        return PHONE_PATTERN;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }else {
            throw new IllegalArgumentException("Name cannot be null");
        }
    };

    public void setEmail(String email) {
        if (!email.isEmpty()) {
            if (email.matches(EMAIL_PATTERN)) {
                this.email = email;
            }else {
                throw new IllegalArgumentException("Email pattern is invalid. Try again.");
            }
        }else {
            throw new IllegalArgumentException("Email cannot be null");
        }
    };

    public void setPhone(String phone) {
        if (!phone.isEmpty()) {
            if (phone.matches(PHONE_PATTERN)) {
                this.phone = phone;
            }else {
                throw new IllegalArgumentException("Phone pattern is invalid. Try again");
            }
        }else {
            throw new IllegalArgumentException("Phone cannot be null");
        }
    }

    @Override
    public String toString() {
        System.out.println("--------------------");
        return "User: " + id + "\n"
        + "Name: " + name + "\n"
        + "Email: " + email + "\n"
        + "Phone: " + phone;
    }
}
