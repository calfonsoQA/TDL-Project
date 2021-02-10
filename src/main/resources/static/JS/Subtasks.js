'use strict';

const subtasks = document.querySelector("#subtasks"); 

const printNameToScreen = (stasks) => {
    let task = document.createElement("p"); // <p> </p>
    let text = document.createTextNode(`${stasks}`); // username
    task.appendChild(text); // <p> username </p>
    subtasks.appendChild(task);
}

const retrieveData = () => {
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
                console.log(infofromserver.data); // key - return array(6)
                for(let users of infofromserver.data){
                    console.log(users.subtaskescription);
                    printNameToScreen(users.subtaskDescription);
                }
            })
        }
    }).catch((err) => {
        console.error(err);
    })
}

retrieveData();