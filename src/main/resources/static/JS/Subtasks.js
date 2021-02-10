'use strict';

const subtasks = document.querySelector("#subtasks");
const subtaskDesc = document.querySelector("#subtaskDescription");
const eLevel = document.querySelector("#eLevel"); 
const alert = document.querySelector("#onsuccess");   

const printNameToScreen = (stasks) => {
    let task = document.createElement("p"); // <p> </p>
    let text = document.createTextNode(`${stasks}`); // username
    task.appendChild(text); // <p> username </p>
    subtasks.appendChild(task);
}

const getSubtask = () => {
    fetch("http://localhost:8080/subtask/readAll")
    .then((response) => {
        // check that the response is OK (i.e. 200)
        if(response.status !== 200){
            throw new Error("I don't have a status of 200");
        }else{
            console.log(response);
            console.log(`response is OK (200)`);
            //json-ify it (which returns a promise)
            response.json().then((infofromserver) =>{
                console.log(infofromserver);
                console.log(infofromserver); // key - return array(6)
                for(let tasks of infofromserver){
                    console.log(tasks.subtaskDescription);
                    printNameToScreen(tasks.subtaskDescription);
                    // printNameToScreen(tasks.effortLevel);
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

    let data = {
        subtaskDescription: subtaskDescriptionValue, 
        effortLevel: effortLevelValue
    }

    fetch("http://localhost:8080/subtask/create",{
        method: "POST", 
        body: JSON.stringify(data),
        headers:{
            "Content-Type":"application/json"
        }
    })
    .then(response => response.json())
    .then(info => {
        console.log(info);
       alert.setAttribute("class", "alert alert-success"); 
       alert.innerHTML = "Subtask has been successfully created!"; 
        setTimeout( () => {
          alert.removeAttribute("class"); 
          alert.innerHTML = "";  
        },2000);
    })
    .catch(err => console.error(`Stopppppp! ${err}`));
}

getSubtask();