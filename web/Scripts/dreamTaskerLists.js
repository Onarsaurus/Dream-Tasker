/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

"use strict";

const $ = selector => document.querySelector(selector);

const toggleComplete = evt => {
    const spans = document.querySelector("#yes, #no");
    const span = evt.currentTarget;
    alert("clicked");
    span.classList.toggle("green");
    // Lets just Make yes or no mark the item as complete or not, then the item can dissapear. when No tems are left list can go away and be marked completed at. 
    //Yes or no will just be buttons for server side recoeding amnd processing. Then we can also work on editing a list and or list items. 
    //also in the create list you should be able to cancel a current item if you decide you dont want to add another item. 
};

function trashItem(){
    //actually, this should probably be a server side action not javascript
}
function trashList() {
    //actually, this should probably be a server side action not javascript
}

document.addEventListener("DOMContentLoaded", () => {
    $("#yes").addEventListener("click", toggleComplete);
    $("#no").addEventListener("click", toggleComplete);
});
