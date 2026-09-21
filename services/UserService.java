package services;

import models.User;
import java.util.*;

/**
 * Service class for managing user operations
 */
public class UserService {
    private HashMap<String, User> userHashMap;
    private int userCounter;

    public UserService() {
        this.userHashMap = new HashMap<>();
        this.userCounter = 1;
    }

    /**
     * Add a new user to the system
     */
    public String addUser(String name, String email, String phone) {
        String userId = "U" + String.format("%04d", userCounter++);
        User user = new User(userId, name, email, phone);
        userHashMap.put(userId, user);
        return userId;
    }

    /**
     * Get a user by ID
     */
    public User getUser(String userId) {
        return userHashMap.get(userId);
    }

    /**
     * Update user details
     */
    public boolean updateUser(String userId, String field, String newValue) {
        User user = userHashMap.get(userId);
        if (user == null) return false;

        switch (field.toLowerCase()) {
            case "name":
                user.setName(newValue);
                break;
            case "email":
                user.setEmail(newValue);
                break;
            case "phone":
                user.setPhone(newValue);
                break;
            default:
                return false;
        }
        return true;
    }

    /**
     * Delete a user from the system
     */
    public boolean deleteUser(String userId) {
        if (!userHashMap.containsKey(userId)) return false;
        userHashMap.remove(userId);
        return true;
    }

    /**
     * Get all users sorted by name
     */
    public List<User> getAllUsersSorted() {
        List<User> users = new ArrayList<>(userHashMap.values());
        users.sort(Comparator.comparing(User::getName));
        return users;
    }

    /**
     * Get all users (unsorted)
     */
    public Collection<User> getAllUsers() {
        return userHashMap.values();
    }

    /**
     * Get total number of users
     */
    public int getTotalUsers() {
        return userHashMap.size();
    }

    /**
     * Check if a user exists
     */
    public boolean userExists(String userId) {
        return userHashMap.containsKey(userId);
    }

    /**
     * Search users by name
     */
    public List<User> searchUsersByName(String name) {
        List<User> results = new ArrayList<>();
        for (User user : userHashMap.values()) {
            if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(user);
            }
        }
        results.sort(Comparator.comparing(User::getName));
        return results;
    }
}
