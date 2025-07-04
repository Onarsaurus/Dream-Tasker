/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author turtl
 */
public class ReoccuringToDoList extends ToDoList {

    private LocalDate createdAt;

    public ReoccuringToDoList(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public ReoccuringToDoList(LocalDate createdAt, String name, ArrayList items) {
        super(name, items);
        this.createdAt = createdAt;
    }

    public ReoccuringToDoList(LocalDate createdAt, int listID, String name, ArrayList<ToDoItem> items) {
        super(listID, name, items);
        this.createdAt = createdAt;
    }

    public ReoccuringToDoList(LocalDate createdAt, int listID, String name, ArrayList<ToDoItem> items, LocalDate completedAt) {
        super(listID, name, items, completedAt);
        this.createdAt = createdAt;
    }

}
