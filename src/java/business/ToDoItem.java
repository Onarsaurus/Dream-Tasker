/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.io.Serializable;

/**
 *
 * @author turtl
 */
public class ToDoItem implements Serializable {

    private String name, description;
    private boolean complete;

    public ToDoItem() {
    }

    public ToDoItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ToDoItem(String name, String description, boolean complete) {
        this.name = name;
        this.description = description;
        this.complete = complete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

}
