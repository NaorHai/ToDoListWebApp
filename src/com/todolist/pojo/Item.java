package com.todolist.pojo;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by Papushe on 14/12/2017.
 * Item Entity
 */
@Entity
@Table(name="Items")
public class Item implements Serializable {

    private String itemId;
    private String email;
    private String title;
    private String content;
    private Date creationDate;

    public Item(){
        this.itemId = UUID.randomUUID().toString();
        this.creationDate = Date.valueOf(LocalDate.now());
    }

    public Item(String email, String title, String content) {
        this.itemId = UUID.randomUUID().toString();
        this.email = email;
        this.title = title;
        this.content = content;
        this.creationDate = Date.valueOf(LocalDate.now());
    }

    @Id
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", userId=" + email +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (!getItemId().equals(item.getItemId())) return false;
        if (!getEmail().equals(item.getEmail())) return false;
        if (!getTitle().equals(item.getTitle())) return false;
        if (!getContent().equals(item.getContent())) return false;
        return getCreationDate().equals(item.getCreationDate());
    }

    @Override
    public int hashCode() {
        int result = getItemId().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getContent().hashCode();
        result = 31 * result + getCreationDate().hashCode();
        return result;
    }
}
