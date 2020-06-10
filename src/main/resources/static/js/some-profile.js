
function opcjeDropdown() {
    document.getElementById("opcje-dropdown").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function (event) {
    if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("dropdown-user-menu");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}

fetch("/api/students/1", {
    headers: {
        "Authorization" : localStorage.getItem("Authorization")
    }
})
    .then(response => response.json())
    .then(response => {
        userData = JSON.parse(JSON.stringify(response));
        var skills = "";
        document.getElementById("nazwisko").textContent = userData.name + " " + userData.surname;
        document.getElementById("kierunek").textContent = "Kierunek: " + userData.subject.name;
        document.getElementById("opis").textContent = userData.interests;
        for (skill of userData.abilities) {
            skills = skills + "<li>" + skill.ability + "</li>";
        }
        document.getElementById("um").innerHTML = skills;
    })
    .catch(error => console.error(error))
