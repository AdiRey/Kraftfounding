let projectData = {
    "date":null,
    "description":"",
    "studentDto": {
        "name":null,
        "surname":null
    },
    "title":null,
}

let user = {
    "name":null
};

request = new XMLHttpRequest();
request.open("GET", "/", true);
/*"https://jsonplaceholder.typicode.com/users"*/
fetch("/api/projects/threeAdded/projects", {
    headers: {
        "Authorization" : localStorage.getItem("Authorization")
    }
})
    .then(response => response.json())
    .then(response => {
        projectData = JSON.parse(JSON.stringify(response));

        for (let i=0; i<3; i++){
            document.querySelectorAll('.date-project')[i].textContent = projectData[i].date.substring(0,10);
            document.querySelectorAll('.body-project')[i].textContent = projectData[i].description;
            document.querySelectorAll('.author-project')[i].textContent = projectData[i].studentDto.name + " " + projectData[i].studentDto.surname;
            document.querySelectorAll('.name-project')[i].textContent = projectData[i].title;
        }
    });



function usermenu() {
    document.getElementById("userDropdown").classList.toggle("show");
}

window.onclick = function(e) {
    if (!e.target.matches('.dropbtn')) {
        let myDropdown = document.getElementById("userDropdown");
        if (myDropdown.classList.contains('show')) {
            myDropdown.classList.remove('show');
        }
    }
}

function logout() {
    localStorage.removeItem("Authorization")
}