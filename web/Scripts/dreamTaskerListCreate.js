/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

"use strict";

const $ = selector => document.querySelector(selector);


const addItem = () => {
    const container = document.getElementById("item_container");
    const newItem = document.createElement("div"); 
    newItem.classList.add("item");

    newItem.innerHTML = `
      <label>Item:</label>
      <input type="text" name="itemName">
      <label>Description:</label>
      <input type="text" name="itemDescription">
      <button id="removeItem" name="removeItem"> X </button>
    `;

    container.appendChild(newItem);
};

document.addEventListener("DOMContentLoaded", () => {
    $("#itemAdd").addEventListener("click", addItem);
});
