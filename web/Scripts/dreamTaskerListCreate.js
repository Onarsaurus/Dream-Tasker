/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

"use strict";

const $ = selector => document.querySelector(selector);


//const addItemAI = () => {
//    const container = document.getElementById("item_container");
//    const newItem = document.createElement("div"); 
//    newItem.classList.add("item");
//
//    newItem.innerHTML = `
//      <label>Item:</label>
//      <input type="text" name="itemName">
//      <label>Description:</label>
//      <input type="text" name="itemDescription">
//      <button id="removeItem" name="removeItem"> X </button>
//    `;
//
//    container.appendChild(newItem);
//};

const addItem = () => {
    const container = $("#item_container");
    const div = document.createElement("div");
    const itemNameLbl = document.createElement("label");
    const labelTextName = document.createTextNode("Item Name");
    const itemNameInp = document.createElement("input");
    const itemDescriptionLbl= document.createElement("label");
    const labelTextDescription = document.createTextNode("Description");
    const itemDescriptionInp = document.createElement("input");
    const cancelItem = document.createElement("button");
    const buttonText = document.createTextNode("X");
    
    itemNameInp.setAttribute("type", "text");
    itemNameInp.setAttribute("name", "itemName");
    itemDescriptionInp.setAttribute("type", "text");
    itemDescriptionInp.setAttribute("name", "itemDescription");
    cancelItem.setAttribute("class", "cancelItem");
    cancelItem.setAttribute("name", "cancelItem");
    
    itemNameLbl.appendChild(labelTextName);
    itemDescriptionLbl.appendChild(labelTextDescription);
    cancelItem.appendChild(buttonText);
    
    div.append(itemNameLbl, itemNameInp, itemDescriptionLbl, itemDescriptionInp, cancelItem);
//    div.appendChild(itemNameLbl);
//    div.appendChild(itemNameInp);
//    div.appendChild(itemDescriptionLbl);
//    div.appendChild(itemDescriptionInp);
//    div.appendChild(cancelItem);
    container.appendChild(div);
};

const cancelItem = evt => {
    const clicked = evt.target;
     if (clicked.classList.contains("cancelItem")) {
        clicked.parentElement.remove(); // remove the parent div, removing the item
    }
    evt.preventDefault();
};

document.addEventListener("DOMContentLoaded", () => {
    $("#itemAdd").addEventListener("click", addItem);
    $("#item_container").addEventListener("click", cancelItem);
});
