package com.todoList.pojo;

import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * Created by Papushe on 14/12/2017.
 */
@Entity
@Table(name = "Item")
public class Item {

    private UUID itemId;
    private UUID userId;
    private String title;
    private String content;
    private LocalDate creationDate;

    public Item(){

    }
    public Item(UUID itemId, UUID userId, String title, String content, LocalDate creationDate) {
        this.itemId = itemId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
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
