/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author turtl
 */
public class Event implements Serializable {

    private String name, description;
    private LocalDate startDay, endDay;
    private LocalDateTime startTime, endTime;
    private boolean allDay, recurring;

    public Event() {
    }

    public Event(String name, String description, LocalDate startDay, LocalDate endDay, LocalDateTime startTime, LocalDateTime endTime, boolean allDay, boolean recurring) {
        this.name = name;
        this.description = description;
        this.startDay = startDay;
        this.endDay = endDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.allDay = allDay;
        this.recurring = recurring;
    }

    public Event(String name, LocalDate startDay, LocalDate endDay, boolean allDay, boolean recurring) {
        this.name = name;
        this.startDay = startDay;
        this.endDay = endDay;
        this.allDay = allDay;
        this.recurring = recurring;
    }

    public Event(String name, LocalDate startDay, LocalDate endDay) {
        this.name = name;
        this.startDay = startDay;
        this.endDay = endDay;
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

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setReccuring(boolean recurring) {
        this.recurring = recurring;
    }

}
