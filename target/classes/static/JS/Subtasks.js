'use strict';

//---------- FOR DISPLAYING TASKS------------------------------
const checkDone = document.querySelector("#checkerDone");
const subtasks = document.querySelector("#subtasks");
const effortOutput = document.querySelector("#eLevelDisplay");
const deleteButton = document.querySelector("#deleteButton");
const taskListOutput = document.querySelector("#subtaskLists");
const testDeleteButton = document.querySelector("#testDelete");
//--------------------------------------------------------------

//---------- FOR TASK CREATE FORM------------------------------------
const taskName = document.querySelector("#taskName");
//--------------------------------------------------------------
//---------- FOR SUBTASK CREATE FORM------------------------------------
const subtaskDesc = document.querySelector("#subtaskDescription");
const eLevel = document.querySelector("#eLevel");
const taskSelector = document.querySelector("#taskDropdown");
const alert = document.querySelector("#onsuccess");
//--------------------------------------------------------------
//---------- FOR SUBTASK UPDATE FORM------------------------------------
const updateButtonModal = document.querySelector("#updateButtonModal");
const subtaskDescPUT = document.querySelector("#subtaskDescriptionPUT");
const eLevelPUT = document.querySelector("#eLevelPUT");
const alertPUT = document.querySelector("#onsuccessPUT");
//--------------------------------------------------------------
//---------- FOR TASK UPDATE FORM------------------------------------
const updateTaskButtonModal = document.querySelector("#updateTaskButtonModal");
const taskNamePUT = document.querySelector("#taskNamePUT");
const taskAlertPUT = document.querySelector("#TaskOnSuccessPUT");
//--------------------------------------------------------------

const updateSubtaskModal = (id) => {
    updateButtonModal.setAttribute("onclick", `updateSubtask(${id})`);
}
const updateTaskModal = (id) => {
    updateTaskButtonModal.setAttribute("onclick", `updateTask(${id})`);
}

const printTaskToScreen = (taskTitle) => {
    let taskrow = document.createElement("div");
    let tasktext = document.createTextNode(`${taskTitle}`);
    taskrow.appendChild(tasktext);
    taskrowinside.appendChild(taskrow);
}

const taskToDropDown = (taskName, id) => {
    let taskOption = document.createElement("option");
    taskOption.value = id;
    let tasktext = document.createTextNode(`${taskName}`);
    taskOption.appendChild(tasktext);
    taskSelector.appendChild(taskOption);
}

const printSubtaskToScreen = (done, stasks, effort, subtaskId, i, taskName, taskid) => {
    let subtaskrow = document.createElement("div");
    subtaskrow.className = "row justify-content-around row-wireframe";
    let taskrowinside = document.createElement("div");
    taskrowinside.className = "alert alert-primary";

    if (i == 0) {
        let taskrow = document.createElement("div");
        let taskHead = document.createElement("h5");
        let tasktext = document.createTextNode(`${taskName}`);
        taskHead.appendChild(tasktext);
        taskrow.appendChild(taskHead);

        let editTaskButton = document.createElement("BUTTON");
        editTaskButton.appendChild(tasktext);
        editTaskButton.setAttribute("class", "btn btn-secondary");
        editTaskButton.setAttribute("task_id", `${taskid}`);
        editTaskButton.setAttribute("data-bs-toggle", "modal");
        editTaskButton.setAttribute("data-bs-target", "#updateModalTargetTask");
        editTaskButton.setAttribute("onclick", `updateTaskModal(${taskid})`);

        taskListOutput.appendChild(editTaskButton);
    }

    let editColumn = document.createElement("div");
    editColumn.className = "col-2 wireframe";
    let edit = document.createElement("BUTTON");
    edit.innerHTML = "edit";
    edit.setAttribute("class", "btn btn-primary");
    edit.setAttribute("task_id", `${subtaskId}`);
    edit.setAttribute("data-bs-toggle", "modal");
    edit.setAttribute("data-bs-target", "#updateModalTarget");
    edit.setAttribute("onclick", `updateSubtaskModal(${subtaskId})`);
    editColumn.appendChild(edit);

    let doneColumn = document.createElement("div");
    doneColumn.className = "col-2 wireframe";
    let doneButton = document.createElement("BUTTON");
    // doneButton.innerHTML = `${done}`;
    doneButton.setAttribute("onclick", `updateSubtaskDone(${subtaskId},${done})`);
    if (done) {
        doneButton.setAttribute("class", "btn btn-success");
        doneButton.innerHTML = `<img src="./Resources/checkbox.svg" width= "25" height="25" />`;
    }
    if (!done) {
        doneButton.setAttribute("class", "btn btn-danger");
        doneButton.innerHTML = `<img src="./Resources/square-with-round-corners.svg" width= "25" height="25" />`;
    }
    doneColumn.appendChild(doneButton);

    let taskColumn = document.createElement("div");
    taskColumn.className = "col-2 wireframe";
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
    del.setAttribute("task_id", `${subtaskId}`);
    del.setAttribute("onclick", `deleteSubtask(${subtaskId})`);
    deleteColumn.appendChild(del);

    subtaskrow.appendChild(editColumn);
    subtaskrow.appendChild(doneColumn);
    subtaskrow.appendChild(taskColumn);
    subtaskrow.appendChild(effortColumn);
    subtaskrow.appendChild(deleteColumn);
    taskrowinside.appendChild(subtaskrow);

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
    fetch("http://localhost:8080/task/readAll")
        .then((response) => {

            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`);
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    for (let tasks of infofromserver) {
                        let i = 0;
                        for (let subtasks of tasks.subtaskList) {
                            console.log(i);
                            console.log(subtasks.subtaskDescription);
                            printSubtaskToScreen(subtasks.done, subtasks.subtaskDescription, subtasks.effortLevel, subtasks.id, i, tasks.taskName, tasks.id);
                            i++;
                        }
                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

const getTask = () => {
    fetch("http://localhost:8080/task/readAll")
        .then((response) => {

            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`);
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    console.log(infofromserver);
                    for (let tasks of infofromserver) {
                        taskToDropDown(tasks.taskName, tasks.id);
                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}
const createSubtask = () => {
    const subtaskDescriptionValue = subtaskDesc.value;
    const effortLevelValue = eLevel.value;
    const taskSelectorValue = taskSelector.value;
    console.log(taskSelectorValue);

    let data = {
        subtaskDescription: subtaskDescriptionValue,
        effortLevel: effortLevelValue,
        done: false,
        myTask: { id: taskSelectorValue }
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
                location.reload();
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
                location.reload();
            }, 1000);
        })
        .catch(err => console.error(`Stopppppp! ${err}`));

}

const updateSubtaskDone = (id, isDone) => {

    let data = {
        done: !isDone
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
            alertPUT.innerHTML = "Done status has been successfully updated!";
            setTimeout(() => {
                alertPUT.removeAttribute("class");
                alertPUT.innerHTML = "";

            }, 1000);
        })
        .catch(err => console.error(`Stopppppp! ${err}`));

    location.reload();
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
    location.reload();
}

const createTask = () => {

    let data = {
        taskName: taskName.value,

    }

    fetch("http://localhost:8080/task/create", {
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
                location.reload();
            }, 2000);
        })
        .catch(err => console.error(`Stopppppp! ${err}`));

}
const updateTask = (id) => {
    const taskNameValue = taskNamePUT.value;

    let data = {
        taskName: taskNameValue
    }

    fetch("http://localhost:8080/task/update/" + id, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
            taskAlertPUT.setAttribute("class", "alert alert-success");
            taskAlertPUT.innerHTML = "Task has been successfully updated!";
            setTimeout(() => {
                taskAlertPUT.removeAttribute("class");
                taskAlertPUT.innerHTML = "";
                location.reload();
            }, 1000);
        })
        .catch(err => console.error(`Stopppppp! ${err}`));

}





getSubtask();
getTask();

// getTaskID();

