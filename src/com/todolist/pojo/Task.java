package com.todolist.pojo;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by Papushe on 14/12/2017.
 */
public class Task {
    private UUID uuid;
    private UUID assignedUser;
    private String title;
    private String content;
    private LocalDate creationDate;
}
