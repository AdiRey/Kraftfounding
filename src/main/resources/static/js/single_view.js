const params = new URLSearchParams(window.location.search);
userIcon = '../img/icon.png';

let fileData = [{
    "id" : null,
    "name" : null
}];

function wypisz(item) {
    el = document.createElement("li");
    el.style.float = "left";
    el.style.width = "100%";
    el.style.marginLeft = '5%';
    el.style.listStyleType = 'none';
    el2 = document.createElement("img");
    el2.style.float = "left";
    el2.setAttribute("src", userIcon);
    el2.style.width = '20px';
    el2.style.height = '20px';
    el3 = document.createElement("span");
    el3.style.marginLeft = '5%';
    el3.style.float = "left";
    el3.innerText = item.name + item.surname;
    el.appendChild(el2);
    el.appendChild(el3);
    document.getElementById("members").appendChild(el);
}
function wypiszKompetencje(item) {
    ela = document.createElement("li");
    ela.style.float = "left";
    ela.style.width = "100%";
    ela.style.marginLeft = '5%';
    ela.style.listStyleType = 'circle';
    elb = document.createElement("span");
    elb.style.marginLeft = '5%';
    elb.innerText = item.ability;
    document.getElementById("competences").appendChild(ela);
    ela.appendChild(elb);
}
function wypiszPliki(item) {
    elc = document.createElement("li");
    elc.style.float = "left";
    elc.style.width = "100%";
    elc.style.marginLeft = '5%';
    elc.style.listStyleType = 'circle';
    eld = document.createElement("span");
    elz = document.createElement("a");
    elz.href = '/api/file/downloadFile/' + item.name;
    console.log(item.name);
    elz.target = '_blank';
    eld.appendChild(elz);
    eld.style.marginLeft = '5%';
    elz.innerText = item.name;
    document.getElementById("files").appendChild(elc);
    elc.appendChild(eld);
}

function checkLoginX() {
    let authenticationX = localStorage.getItem("Authorization");
    if (authenticationX === null) {
        document.getElementById("user-logout").style.display = "block";
        document.getElementById("user-login").style.display = "none";
    } else {
        let decoded = jwt_decode(authenticationX.substr(7));
        user = decoded.sub;
        document.getElementById("user-login-menu").textContent = user.replace(/![0-9]+/,"");
        document.getElementById("user-logout").style.display = "none";
        document.getElementById("user-login").style.display = "block";
    }
}

fetch("/api/projects/" + params.get("id") + "/files", {
    headers: {
        "Authorization": localStorage.getItem("Authorization")
    }
})
    .then(response => response.json())
    .then(response => {
        fileData = JSON.parse(JSON.stringify(response));
        fileData.forEach(u => wypiszPliki(u));
    })
    .catch(error => {
        console.error(error);
        // alert("Nie masz dost�pu! Zaloguj si�!");
        // window.location.replace("/sign-in");
    })

adres_projektu = "/api/projects/" + params.get("id") + "/students";
fetch(adres_projektu, {
    headers: {
        "Authorization": localStorage.getItem("Authorization")
    }
})
    .then(response => response.json())
    .then(response => {
        userData3 = JSON.parse(JSON.stringify(response));
        userData3.forEach(u => wypisz(u));
    })
    .catch(error => {
        console.error(error);
        // alert("Nie masz dost�pu! Zaloguj si�!");
        // window.location.replace("/sign-in");
    })

fetch("/api/projects/"+params.get("id"), {
    headers: {
        "Authorization": localStorage.getItem("Authorization")
    }
})
    .then(response => response.json())
    .then(response => {
        userData2 = JSON.parse(JSON.stringify(response));
        let myHeading = document.querySelectorAll('.author-project');
        myHeading[0].textContent = userData2.studentDto.name + ' ' + userData2.studentDto.surname;
    })
    .catch(error => {
        console.error(error);
        // alert("Nie masz dost�pu! Zaloguj si�!");
        // window.location.replace("/sign-in");
    })


fetch("/api/projects/" + params.get("id"), {
    headers: {
        "Authorization": localStorage.getItem("Authorization")
    }
})
    .then(response => response.json())
    .then(response => {
        projectData = JSON.parse(JSON.stringify(response));
        document.querySelectorAll('.opis')[0].textContent = projectData.description;
        document.querySelectorAll('#name-project')[0].textContent = projectData.title;
        document.querySelectorAll('.date-project')[0].textContent = projectData.date.substr(0,10);
    })
    .catch(error => {
        console.error(error);
    })
fetch("/api/projects/" + params.get("id") + "/abilities", {
    headers: {
        "Authorization": localStorage.getItem("Authorization")
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
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return response.json();
}

function zglos() {
    dane = { userId: null, projectId: null };
    alert("U�ytkownik zg�oszony!");
    return postData('/api/...', dane);
}

function checkUserLog() {
    let aut = localStorage.getItem("Authorization");
    let user = [];
    let decoded = jwt_decode(aut.substr(7));
    user = decoded.authorities;
    FirstButton = document.querySelector('.zglos');
    SecondButton = document.querySelector('.pomoc');
    ThirdButton = document.querySelector('.uwagaDiv');
    for (let i = 0; i < user.length; i++) {
        if (user[i].authority === 'student') {
            SecondButton.style.display = 'none';
            ThirdButton.style.display = 'none';
        }
        if (user[i].authority === 'worker') {
            FirstButton.style.display = 'none';
        }
    }
}

function helpMe() {
    alert("Ch�� pomocy zosta�a zg�oszona");
    dane2 = { workerId: null, opis: null };
    dane2.opis = CKEDITOR.instances.op.getData();
    dane2.workerId = 1;
    return postData('/api/...', dane2);
}

function ahtung() {
    alert("Uwaga zosta�a zg�oszona");
    dane3 = { workerId: null, opis: null };
    dane3.opis = CKEDITOR.instances.op.getData();
    dane3.workerId = 1;
    return postData('/api/...', dane3);
}