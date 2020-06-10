function pasteProfile(k) {
    return "<div class=\"col-xs-12 col-lg-6\">\n" +
        "        <div class=\"profile-text\">\n" +
        "            <div class=\"row\">\n" +
        "                <div class=\"col-xs-12 col-md-2 col-lg-2\">\n" +
        "                    <img class=\"avatar\" src=\""+"E"+k+"\">\n" +
        "                </div>\n" +
        "                <div class=\"col-sm-12 col-md-8 col-lg-6\">\n" +
        "                    <span style=\"font-size: 30px; font-weight: bold; display: block;\">\n" +
        "                        <div id=\"nazwisko"+k+"\"></div>\n" +
        "A" + k +
        "                    </span>\n" +
        "                    <span style=\"font-size: 22px; font-weight: normal\">\n" +
        "                        <div id=\"kierunek"+k+"\"></div>\n" +
        "B" + k +
        "                    </span>\n" +
        "                </div>\n" +
        "                <div class=\"col-sm-12\">\n" +
        "                    <div id=\"opis"+k+"\">\n" +
        "C" + k +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div id=\"umiejetnosci\">\n" +
        "                    Umiejętności:\n" +
        "                    <ul id=\"um"+k+"\">\n" +
        "D" + k +
        "                    </ul>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>";
}

fetch("/api/students?page=0&sort=asc&filter=", {
    headers: {
        "Authorization": localStorage.getItem("Authorization")
    }
})
    .then(response => response.json())
    .then(response => {
        userData = JSON.parse(JSON.stringify(response));
        var skills = "";
        var bodyKafelki = "";
        console.log(userData.content.length);
        for (let i = 0; i < userData.content.length; i++) {
            skills = "";
            bodyKafelki += pasteProfile(i);
            console.log(bodyKafelki);
            //document.getElementById("avatar-edycja").src = "../../../../../image-profile/"+userData.image_url;
            bodyKafelki = bodyKafelki.replace("E"+i, "..\\img\\"+userData.content[i].imageUrl)
            bodyKafelki = bodyKafelki.replace("A"+i, userData.content[i].name + " " + userData.content[i].surname);
            bodyKafelki = bodyKafelki.replace("B"+i, "Kierunek: " + userData.content[i].subject.name);
            bodyKafelki = bodyKafelki.replace("C"+i, userData.content[i].interests);

            for (skill of userData.content[i].abilities) {
                skills = skills + "<li>" + skill.ability + "</li>";
        }
            bodyKafelki = bodyKafelki.replace("D"+i, skills);
            console.log(skills);
        }
        document.getElementById("kafelki").innerHTML = bodyKafelki;
    })
    .catch(error => console.error(error))