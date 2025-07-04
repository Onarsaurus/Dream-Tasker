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
public class Expense implements Serializable {

    private String name, category;
    private boolean recurring;

    public Expense() {
    }

    public Expense(String name, String category, boolean recurring) {
        this.name = name;
        this.category = category;
        this.recurring = recurring;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isReoccuring() {
        return recurring;
    }

    public void setReoccuring(boolean recurring) {
        this.recurring = recurring;
    }

}
