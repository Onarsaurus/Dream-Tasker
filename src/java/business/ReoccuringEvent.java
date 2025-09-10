/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author turtl
 */
public class ReoccuringEvent extends Event {

    private LocalDate createdAt;

    public ReoccuringEvent() {
    }

    public ReoccuringEvent(String name, String description, LocalDate startDay, LocalDate endDay, LocalDateTime startTime, LocalDateTime endTime, boolean allDay, boolean recurring) {
        super(name, description, startDay, endDay, startTime, endTime, allDay, recurring);
    }

//    public ReoccuringEvent(String name, LocalDate startDay, LocalDate endDay, boolean allDay, boolean recurring) {
//        super(name, startDay, endDay, allDay, recurring);
//    }
//
//    public ReoccuringEvent(String name, LocalDate startDay, LocalDate endDay) {
//        super(name, startDay, endDay);
//    }

}
