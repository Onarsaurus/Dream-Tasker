/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
"use strict";

const $ = selector => document.querySelector(selector);

document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = $("#calendar");
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,

        //selectHelper: true,
        select: function (info) {
            alert('selected ' + info.startStr + ' to ' + info.endStr);
            openEventModal(info);
        },
        dateClick: function (info) {
            alert('clicked ' + info.dateStr);
            //openEventModal(info.dateStr, null);
        }

    });

    //Opens the model to create an event
    function openEventModal(info) {
        $('#event-start').value = info.startStr;
        document.getElementById('event-end').value = info.endStr || info.startStr;
        var modal = document.getElementById('eventModal');
        modal.style.display = "block";
        const closeModal = $(".closeModal");
        closeModal.addEventListener("click", () => {
            modal.style.display = "none";
        });
        //new bootstrap.Modal(document.getElementById('eventModal')).show();
    }

    //Gets the data entered in the model and sends to servlet
    document.getElementById('eventForm').addEventListener('submit', function (e) {
        e.preventDefault();
        const name = document.getElementById('event-name').value;
        const start = document.getElementById('event-start').value;
        const end = document.getElementById('event-end').value;
        const allDay = document.getElementById('event-all-day').checked;
        const recurring = document.getElementById('event-recurring').checked;

        //call to servlet
        fetch("Private?action=saveevent", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=UTF-8"
            },
            body: JSON.stringify({name, start, end, allDay, recurring})
        })
                .then(response => {
                    if (!response.ok)
                        throw new Error("Failed to save");
                    return response.json(); // Optional
                })
                .then(data => {
                    console.log("Saved:", data);
                    // Optionally show success message
                })
                .catch(error => {
                    console.error("Error saving event:", error);
                });

        //adds event to the calendar
        calendar.addEvent({
            title: name,
            start: start,
            end: end,
            allDay: allDay,
            extendedProps: {
                recurring: recurring
            }
        });
        //bootstrap.Modal.getInstance(document.getElementById('eventModal')).hide();
        this.reset();
    });

    calendar.render();

});
