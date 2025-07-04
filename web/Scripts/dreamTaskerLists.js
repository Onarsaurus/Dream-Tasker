/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


function addItem() {
    const container = document.getElementById("item_container");
    const newItem = document.createElement("div");
    newItem.classList.add("item");

    newItem.innerHTML = `
      <label>Item:</label>
      <input type="text" name="itemName">
      <label>Description:</label>
      <input type="text" name="itemDescription">
    `;

    container.appendChild(newItem);
}