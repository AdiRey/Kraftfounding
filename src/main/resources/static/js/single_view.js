userIcon = '../ img / logo.png';
function wypisz(item) {
    el = document.createElement("li");
    el.style.float = "left";
    el.style.width = "100%";
    el.style.marginLeft = '5%';
    el.style.listStyleType = 'none';
    el2 = document.createElement("img");
    el2.style.float = "left";
    el2.setAttribute("src", "../img/abc.jpg");
    el2.style.width = '20px';
    el2.style.height = '20px';
    el3 = document.createElement("span");
    el3.style.marginLeft = '5%';
    el3.style.float = "left";
    el3.innerText = item.name + item.surname;
    el.appendChild(el2);
    el.appendChild(el3);
    document.querySelectorAll('.members')[0].appendChild(el);
}
function wypiszKompetencje(item) {
    el = document.createElement("li");
    el.style.float = "left";
    el.style.width = "100%";
    el.style.marginLeft = '5%';
    el.style.listStyleType = 'none';
    el3 = document.createElement("span");
    el3.style.marginLeft = '5%';
    el3.style.float = "left";
    el3.innerText = item.ability;
    el.appendChild(el3);
    document.querySelectorAll('.competences')[0].appendChild(el);
}
function wypiszPliki(item) {
    el = document.createElement("li");
    el.style.float = "left";
    el.style.width = "100%";
    el.style.marginLeft = '5%';
    el.style.listStyleType = 'none';
    el3 = document.createElement("span");
    el3.style.marginLeft = '5%';
    el3.style.float = "left";
    el3.innerText = item.files;
    el.appendChild(el3);
    document.querySelectorAll('.files')[0].appendChild(el);
}

function checkLoginX(){
    let authenticationX = localStorage.getItem("Authorization");
    if (authenticationX === null){
        document.getElementById("user-logout").style.display = "block";
        document.getElementById("user-login").style.display = "none";
    }else{
        let decoded = jwt_decode(authenticationX.substr(7));
        user = decoded.sub;
        document.getElementById("user-login-menu").textContent = user;
        document.getElementById("user-logout").style.display = "none";
        document.getElementById("user-login").style.display = "block";
    }
}

identyfikator = 1;
adres_projektu = "/api/projects/" + identyfikator + "/students";
fetch(adres_projektu,  {
    headers: {
        "Authorization" : localStorage.getItem("Authorization")
}
})
    .then(response => response.json())
    .then(response => {
        userData = JSON.parse(JSON.stringify(response));
        let myHeading = document.querySelectorAll('.author-project');
        myHeading[0].textContent = userData[0].name + ' ' + userData[0].surname;
        userData.forEach(wypisz);
        //userData.forEach(wypiszPliki);
    })
    .catch(error => {
        console.error(error);
        // alert("Nie masz dostêpu! Zaloguj siê!");
        // window.location.replace("/sign-in");
    })

fetch("/api/projects/" + identyfikator, {
    headers: {
        "Authorization": localStorage.getItem("Authorization")
        }
    })
    .then(response => response.json())
    .then(response => {
        userData = JSON.parse(JSON.stringify(response));
        document.querySelectorAll('.body-project')[0].textContent = userData[0].description;
        document.querySelectorAll('#name-project')[0].textContent = userData[0].title;
        document.querySelectorAll('.date-project')[0].textContent = userData[0].date;
    })
    .catch(error => {
        console.error(error);
    })
fetch("/api/projects/" + identyfikator + "/abilities", {
    headers: {
        "Authorization" : localStorage.getItem("Authorization")
    }
})
    .then(response => response.json())
    .then(response => {
        userData = JSON.parse(JSON.stringify(response));
        userData.forEach(wypiszKompetencje);
    })
    .catch(error => {
        console.error(error);
    })

function usermenu() {
    document.getElementById("userDropdown").classList.toggle("show");
}

window.onclick = function (e) {
    if (!e.target.matches('.dropbtn')) {
        let myDropdown = document.getElementById("userDropdown");
        if (myDropdown.classList.contains('show')) {
            myDropdown.classList.remove('show');
        }
    }
}

async function postData(url = '', data = {}) {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return response.json();
}

function zglos() {
    var id = 1;
    return postData('/api/...',id)
}