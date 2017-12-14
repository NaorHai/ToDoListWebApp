package com.todoList.pojo;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by Papushe on 14/12/2017.
 */
public class User {

    private UUID userId;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate creationDate;

    public User(UUID uuid, String email, String firstName, String lastName, LocalDate creationDate) {
        this.userId = uuid;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = creationDate;
    }
    public User(){

    }

    public UUID getUuid() {
        return userId;
    }

    public void setUuid(UUID uuid) {
        this.userId = uuid;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

        if (!getUuid().equals(user.getUuid())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (!getFirstName().equals(user.getFirstName())) return false;
        if (!getLastName().equals(user.getLastName())) return false;

        return getCreationDate().equals(user.getCreationDate());
    }

    @Override
    public int hashCode() {
        int result = getUuid().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getCreationDate().hashCode();
        return result;
    }

    public UUID createUUID(){
        return UUID.randomUUID();
    }

}
