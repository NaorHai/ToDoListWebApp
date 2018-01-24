package com.todolist.pojo;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * Created by Papushe on 14/12/2017.
 */
@Entity
@Table(name="Users")
public class User implements Serializable {

    private String userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date creationDate;

    public User(){
        this.userId = UUID.randomUUID().toString();
        this.creationDate = Date.valueOf(LocalDate.now());
    }

    public User(String email, String password, String firstName, String lastName) {
        this.userId = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = Date.valueOf(LocalDate.now());
    }

    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) { this.userId = userId; }

    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + userId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getUserId().equals(user.getUserId())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (!getFirstName().equals(user.getFirstName())) return false;
        if (!getLastName().equals(user.getLastName())) return false;

        return getCreationDate().equals(user.getCreationDate());
    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getCreationDate().hashCode();
        return result;
    }
}
