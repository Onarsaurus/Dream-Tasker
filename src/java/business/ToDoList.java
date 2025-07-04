/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author turtl
 */
public class ToDoList implements Serializable {

    private int listID;
    private String name;
    private ArrayList<ToDoItem> items;
    private LocalDate completedAt;

    public ToDoList() {
    }

    public ToDoList(String name, ArrayList items) {
        this.name = name;
        this.items = items;
    }

    public ToDoList(int listID, String name, ArrayList<ToDoItem> items) {
        this.listID = listID;
        this.name = name;
        this.items = items;
    }

    public ToDoList(int listID, String name, ArrayList<ToDoItem> items, LocalDate completedAt) {
        this.listID = listID;
        this.name = name;
        this.items = items;
        this.completedAt = completedAt;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ToDoItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ToDoItem> items) {
        this.items = items;
    }

    public LocalDate getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDate completedAt) {
        this.completedAt = completedAt;
    }



}
