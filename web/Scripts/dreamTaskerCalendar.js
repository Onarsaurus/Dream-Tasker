/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
"use strict";

const $ = selector => document.querySelector(selector);

document.addEventListener('DOMContentLoaded', function () {
    //Gets the calendar container and object
    var calendarEl = $("#calendar");
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        events: '/dreamTasker/Private?action=getevents', // fetches JSON from servlet
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

    //Sets modal closing handler to use on any modal
    var modal = $('#eventModal');
    const closeModal = $(".closeModal");
    closeModal.addEventListener("click", () => {
        modal.style.display = "none";
    });

    //Opens the model to create an event
    function openEventModal(info) {
        $('#event-start').value = info.startStr;
        $("#event-end").value = info.endStr || info.startStr;
        modal.style.display = "block";
    }

    //Gets the data entered in the model and sends to servlet
    $("#eventForm").addEventListener("submit", function (e) {
//    document.getElementById('eventForm').addEventListener('submit', function (e) {
        e.preventDefault();

        const name = $("#event-name").value;
        const start = $("#event-start").value;
        const end = $("#event-end").value;
        const allDay = $("#event-all-day").checked;
        const recurring = $("#event-recurring").checked;

        //Include validation
        
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
                    return response.json();
                })
                .then(data => {
                    console.log("Saved:", data);
                    //adds event to the calendar
                    calendar.addEvent({
                        id: data.id,
                        title: data.name,
                        start: data.start,
                        end: data.end,
                        allDay: data.allDay,
                        extendedProps: {
                            recurring: data.recurring
                        }
                    });
                    // Optionally show success message
                })
                .catch(error => {
                    console.error("Error saving event:", error);
                });

        $("#eventModal").style.display = "none";
        this.reset();
    });

    calendar.render();

});
