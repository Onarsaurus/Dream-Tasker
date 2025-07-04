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
public class SingleToDoList extends ToDoList {

    public SingleToDoList() {
    }

    public SingleToDoList(String name, ArrayList items) {
        super(name, items);
    }

    public SingleToDoList(int listID, String name, ArrayList<ToDoItem> items) {
        super(listID, name, items);
    }

    public SingleToDoList(int listID, String name, ArrayList<ToDoItem> items, LocalDate completedAt) {
        super(listID, name, items, completedAt);
    }

}
