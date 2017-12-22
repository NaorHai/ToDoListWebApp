package com.todoList.pojo;

import org.hibernate.annotations.Entity;

import java.sql.Date;
import java.util.UUID;
import javax.persistence.*;

/**
 * Created by Papushe on 14/12/2017.
 */
@Entity
@Table(name = "Items")
public class Item {

    private String itemId;
    private String userId;
    private String title;
    private String content;
    private Date creationDate;

    public Item(){ }
    public Item(String userId, String title, String content, Date creationDate) {
        this.itemId = UUID.randomUUID().toString();
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }

    @Id
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
                ", userId=" + userId +
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
        if (!getUserId().equals(item.getUserId())) return false;
        if (!getTitle().equals(item.getTitle())) return false;
        if (!getContent().equals(item.getContent())) return false;
        return getCreationDate().equals(item.getCreationDate());
    }

    @Override
    public int hashCode() {
        int result = getItemId().hashCode();
        result = 31 * result + getUserId().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getContent().hashCode();
        result = 31 * result + getCreationDate().hashCode();
        return result;
    }

    @PrePersist
    private void generateCodeIdentifier(){
        createUUID();
    }



    public UUID createUUID(){
        return UUID.randomUUID();
    }
}
