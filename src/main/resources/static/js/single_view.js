userIcon = '<svg class="bi bi-person-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">< path fill - rule="evenodd" d = "M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 100-6 3 3 0 000 6z" clip - rule="evenodd" /></svg >';

let token = localStorage.getItem("Authorization");

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
    el3.innerText = item.members;
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
    el3.innerText = item.competences;
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
fetch("/api/projects", {
    headers: {
        "Authorization" : token
    }
})
    .then(response => response.json())
    .then(response => {
        userData = JSON.parse(JSON.stringify(response));
        let myHeading = document.querySelectorAll('.author-project');
        myHeading[0].textContent = userData[0].name;
        document.querySelectorAll('.body-project')[0].textContent = userData[1].description;
        document.querySelectorAll('#name-project')[0].textContent = userData[1].name;
        document.querySelectorAll('.date-project')[0].textContent = userData[1].date;
        userData.forEach(wypisz);
        userData.forEach(wypiszKompetencje);
        userData.forEach(wypiszPliki);
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