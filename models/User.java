package models;

import java.time.LocalDate;

/**
 * User class representing a library member
 */
public class User {
    private String userId;
    private String name;
    private String email;
    private String phone;
    private LocalDate memberSince;

    public User(String userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.memberSince = LocalDate.now();
    }

    // Getters
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public LocalDate getMemberSince() { return memberSince; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return String.format("ID: %-8s | Name: %-25s | Email: %-30s | Phone: %-15s | Member Since: %s",
                userId, name, email, phone, memberSince);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
