function opcjeDropdown() {
    document.getElementById("opcje-dropdown").classList.toggle("show");
}
let myToken;
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

function decode_token() {
    myToken = localStorage.getItem("Authorization");
    myToken = jwt_decode(myToken.substr(7));
    myToken = myToken.sub;
    myToken = myToken.match(/![0-9]+/g);
    return myToken[0].substr(1);
}

fetch("/api/students/" + decode_token(), {
    headers: {
        "Authorization": localStorage.getItem("Authorization")
    }
})
    .then(response => response.json())
    .then(response => {
        userData = JSON.parse(JSON.stringify(response));
        var skills = "";
        document.getElementById("nazwisko").textContent = userData.name + " " + userData.surname;
        document.getElementById("kierunek").textContent = "Kierunek: " + userData.subject.name;
        document.getElementById("opis").textContent = userData.interests;
        document.getElementById("avatar").src = "..\\img\\"+userData.imageUrl;
        for (skill of userData.abilities) {
            skills = skills + "<li>" + skill.ability + "</li>";
        }
        document.getElementById("um").innerHTML = skills;
    })
    .catch(error => {
		console.error(error);
	})
