package com.todolist.pojo;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by Papushe on 14/12/2017.
 */
public class Item {
    private UUID uuid;
    private UUID assignedUser;
    private String title;
    private String content;
    private LocalDate creationDate;

    public Item(UUID uuid, UUID assignedUser, String title, String content, LocalDate creationDate) {
        this.uuid = uuid;
        this.assignedUser = assignedUser;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(UUID assignedUser) {
        this.assignedUser = assignedUser;
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
                "uuid=" + uuid +
                ", assignedUser=" + assignedUser +
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

        if (!getUuid().equals(item.getUuid())) return false;
        if (!getAssignedUser().equals(item.getAssignedUser())) return false;
        if (!getTitle().equals(item.getTitle())) return false;
        if (!getContent().equals(item.getContent())) return false;
        return getCreationDate().equals(item.getCreationDate());
    }

    @Override
    public int hashCode() {
        int result = getUuid().hashCode();
        result = 31 * result + getAssignedUser().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getContent().hashCode();
        result = 31 * result + getCreationDate().hashCode();
        return result;
    }
}
