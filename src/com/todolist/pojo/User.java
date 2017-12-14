package com.todolist.pojo;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by Papushe on 14/12/2017.
 */
public class User {

    private UUID uuid;
    private String email;
    private String firstName;
    private String lasttName;
    private String password;
    private LocalDate creationDate;

    public User(UUID uuid, String email, String firstName, String lasttName, String password, LocalDate creationDate) {
        this.uuid = uuid;
        this.email = email;
        this.firstName = firstName;
        this.lasttName = lasttName;
        this.password = password;
        this.creationDate = creationDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasttName() {
        return lasttName;
    }

    public void setLasttName(String lasttName) {
        this.lasttName = lasttName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lasttName='" + lasttName + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getUuid().equals(user.getUuid())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (!getFirstName().equals(user.getFirstName())) return false;
        if (!getLasttName().equals(user.getLasttName())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        return getCreationDate().equals(user.getCreationDate());
    }

    @Override
    public int hashCode() {
        int result = getUuid().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLasttName().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getCreationDate().hashCode();
        return result;
    }
}
