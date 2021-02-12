'use strict';

//---------- FOR DISPLAYING TASKS------------------------------
const checkDone = document.querySelector("#checkerDone");
const subtasks = document.querySelector("#subtasks");
const effortOutput = document.querySelector("#eLevelDisplay");
const deleteButton = document.querySelector("#deleteButton");
let display = document.querySelector("table");
// const check = 
const taskListOutput = document.querySelector("#subtaskLists");

const testDeleteButton = document.querySelector("#testDelete");
//--------------------------------------------------------------

//---------- FOR CREATE FORM------------------------------------
const subtaskDesc = document.querySelector("#subtaskDescription");
const eLevel = document.querySelector("#eLevel");
const alert = document.querySelector("#onsuccess");
//--------------------------------------------------------------
//---------- FOR UPDATE FORM------------------------------------
const updateButtonModal = document.querySelector("#updateButtonModal");
const subtaskDescPUT = document.querySelector("#subtaskDescriptionPUT");
const eLevelPUT = document.querySelector("#eLevelPUT");
const alertPUT = document.querySelector("#onsuccessPUT");
//--------------------------------------------------------------

const updateSubtaskModal = (id) => {
    updateButtonModal.setAttribute("onclick", `updateSubtask(${id})`);

}

const printTaskToScreen = (done, stasks, effort, taskId) => {
    let taskrow = document.createElement("div");
    taskrow.className = "row justify-content-around row-wireframe";
    let taskrowinside = document.createElement("div");
    taskrowinside.className = "alert alert-primary";

    let editColumn = document.createElement("div");
    editColumn.className = "col-2 wireframe";
    let edit = document.createElement("BUTTON");
    edit.innerHTML = "edit";
    edit.setAttribute("class", "btn btn-primary");
    edit.setAttribute("task_id", `${taskId}`);
    edit.setAttribute("data-bs-toggle", "modal");
    edit.setAttribute("data-bs-target", "#updateModalTarget");
    // let id = edit.getAttribute("task_id");
    edit.setAttribute("onclick", `updateSubtaskModal(${taskId})`);
    editColumn.appendChild(edit);

    let doneColumn = document.createElement("div");
    doneColumn.className = "col-2 wireframe";
    let text = document.createTextNode(`${done}`);
    doneColumn.appendChild(text);

    let taskColumn = document.createElement("div");
    taskColumn.className = "col-4 wireframe";
    let text2 = document.createTextNode(`${stasks}`);
    taskColumn.appendChild(text2);

    let effortColumn = document.createElement("div");
    effortColumn.className = "col-2 wireframe";
    let text3 = document.createTextNode(`${effort}`);
    effortColumn.appendChild(text3);

    let deleteColumn = document.createElement("div");
    deleteColumn.className = "col-2 wireframe";
    let del = document.createElement("BUTTON");
    del.innerHTML = "x";
    del.setAttribute("class", "btn btn-danger");
    del.setAttribute("task_id", `${taskId}`);
    // let id = del.getAttribute("task_id");
    // del.onclick = deleteSubtask(id);
    // del.addEventListener("click", deleteSubtask(id));
    del.setAttribute("onclick", `deleteSubtask(${taskId})`);
    deleteColumn.appendChild(del);

    taskrow.appendChild(editColumn);
    taskrow.appendChild(doneColumn);
    taskrow.appendChild(taskColumn);
    taskrow.appendChild(effortColumn);
    taskrow.appendChild(deleteColumn);
    taskrowinside.appendChild(taskrow);

    taskListOutput.appendChild(taskrowinside);

}


function generateTableHead(table, data) {
    let thead = table.createTHead();
    let row = thead.insertRow();
    for (let key of data) {
        let th = document.createElement("th");
        let text = document.createTextNode(key);
        th.appendChild(text);
        row.appendChild(th);
    }
}

const getSubtask = () => {
    fetch("http://localhost:8080/subtask/readAll")
        .then((response) => {
            // check that the response is OK (i.e. 200)
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`);
                //json-ify it (which returns a promise)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    console.log(infofromserver); // key - return array(6)
                    // generateTableHead(display,Object.keys(infofromserver[0]));
                    for (let tasks of infofromserver) {               
                            console.log(tasks.subtaskDescription);
                            printTaskToScreen(tasks.done, tasks.subtaskDescription, tasks.effortLevel, tasks.id);
                            // printNameToScreen(tasks.effortLevel);
                            // printTaskToScreen(tasks.done,tasks.subtaskDescription);                     
                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

// const getTask = () => {
//     fetch("http://localhost:8080/task/readAll")
//         .then((response) => {
//             // check that the response is OK (i.e. 200)
//             if (response.status !== 200) {
//                 throw new Error("I don't have a status of 200");
//             } else {
//                 console.log(response);
//                 console.log(`response is OK (200)`);
//                 //json-ify it (which returns a promise)
//                 response.json().then((infofromserver) => {
//                     console.log(infofromserver);
//                     console.log(infofromserver); // key - return array(6)
//                     // generateTableHead(display,Object.keys(infofromserver[0]));
//                     for (let tasks of infofromserver) {
//                         for (let subtasks of tasks.subtaskList) {
//                             console.log(subtasks.subtaskDescription);
//                             printTaskToScreen(subtasks.done, subtasks.subtaskDescription, subtasks.effortLevel, subtasks.id);
//                             // printNameToScreen(tasks.effortLevel);
//                             // printTaskToScreen(tasks.done,tasks.subtaskDescription);
//                         }
//                     }
//                 })
//             }
//         }).catch((err) => {
//             console.error(err);
//         })
// }

const createSubtask = () => {
    const subtaskDescriptionValue = subtaskDesc.value;
    const effortLevelValue = eLevel.value;

    let data = {
        subtaskDescription: subtaskDescriptionValue,
        effortLevel: effortLevelValue
    }

    fetch("http://localhost:8080/subtask/create", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
            alert.setAttribute("class", "alert alert-success");
            alert.innerHTML = "Subtask has been successfully created!";
            //    getSubtask(); 
            setTimeout(() => {
                alert.removeAttribute("class");
                alert.innerHTML = "";

            }, 2000);
        })
        .catch(err => console.error(`Stopppppp! ${err}`));
}

const updateSubtask = (id) => {
    const subtaskDescriptionValue = subtaskDescPUT.value;
    const effortLevelValue = eLevelPUT.value;

    let data = {
        subtaskDescription: subtaskDescriptionValue,
        effortLevel: effortLevelValue
    }

    fetch("http://localhost:8080/subtask/update/" + id, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
            alertPUT.setAttribute("class", "alert alert-success");
            alertPUT.innerHTML = "Subtask has been successfully updated!";
            setTimeout(() => {
                alertPUT.removeAttribute("class");
                alertPUT.innerHTML = "";
            }, 2000);
        })
        .catch(err => console.error(`Stopppppp! ${err}`));
}

const deleteSubtask = (id) => {


    fetch("http://localhost:8080/subtask/delete/" + id, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    })
        // .then(response => response.json())
        .then(response => {
            console.log(response);
            alert.setAttribute("class", "alert alert-success");
            alert.innerHTML = "Subtask has been successfully deleted!";
            setTimeout(() => {
                alert.removeAttribute("class");
                alert.innerHTML = "";
            }, 2000);
        })
        .catch(err => console.error(`Stopppppp! ${err}`));
}





getSubtask();
// getTaskID();

