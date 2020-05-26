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
function checkLogin(){
    let authentication = localStorage.getItem("Authorization");
    if (authentication === null){
        document.getElementById("user-logout").style.display = "block";
        document.getElementById("user-login").style.display = "none";
    }else{
        let decoded = jwt_decode(authentication.substr(7));
        user.name = decoded.sub;
        document.getElementById("user-login-menu").textContent = user.name;
        document.getElementById("user-logout").style.display = "none";
        document.getElementById("user-login").style.display = "block";
    }
}


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