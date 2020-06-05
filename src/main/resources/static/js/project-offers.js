let project_Data = {
    "id":null,
    "date":null,
    "description":"",
    "studentDto": {
        "name":null,
        "surname":null
    },
    "title":null,
    "abilities":[]
};
function createSchema(index) {
    return "<div class=\"project\">\n" +
        "                    <div class=\"header-project\">\n" +
        "                        <div class=\"name-project-d-inline-block\" id=\"projectname"+ index +"\" >\n" +
        "A"+index+
        "                        </div>\n" +
        "                        <div class=\"info-project-d-inline-block\">\n" +
        "                            <div class=\"author-project-d-inline-block\" id=\"studentname"+index+"\">\n" +
        "B"+index+
        "                            </div>\n" +
        "                            <div class=\"date-project-d-inline-block\" id=\"projectdate"+index+"\">\n" +
        "C"+index+
        "                            </div>\n" +
        "                        </div>\n" +
        "                        <div style=\"clear: both\"></div>\n" +
        "                    </div>\n" +
        "                    <div class=\"body-project\" id=\"projectdes" + index + "\">\n" +
        "D"+index+
        "                    </div>\n" +
        "                    <div class=\"footer-project\">\n" +
        "                        <div class=\"button-check\">\n" +
        "                            <a href=\"/project?id=link" + index + "\">Sprawdź</a>\n" +
        "                        </div>\n" +
        "                        <div style=\"clear: both\"></div>\n" +
        "                    </div>\n" +
        "                </div>";
}

request = new XMLHttpRequest();
request.open("GET", "/", true);

function funLoadAbilities_(){
    let optionForm = document.querySelector("select[id='drop1']");
    let ability = {
        "ability":""
    };
    fetch("/api/abilities")
        .then(response => response.json())
        .then(response =>{
            ability = JSON.parse(JSON.stringify(response));
            console.log(ability.length);
            for (let i = 0; i < ability.length; i++){
                console.log(ability[i].ability);
                optionForm.options[optionForm.options.length] = new Option(ability[i].ability);
            }
        })
}

function find(){
    var string = document.getElementById('name').value;
    console.log(string);
    var abi =  document.getElementById("drop1").options[document.getElementById("drop1").selectedIndex].value;
    console.log(abi);
    fetch("/api/projects?page=0&size=20&sort=ASC&filter="+string+"&ability="+abi, {
        headers: {
            "Authorization" : localStorage.getItem("Authorization")
        }
    })
        .then(response => response.json())
        .then(response => {
            projectData = JSON.parse(JSON.stringify(response));
            let szablon ="";
            for (let i=0; i<projectData.content.length; i++) {
                szablon += createSchema(i);
                szablon = szablon.replace("A"+i, projectData.content[i].title);
                szablon = szablon.replace("B"+i, projectData.content[i].studentDto.name + " " +projectData.content[i].studentDto.surname);
                szablon = szablon.replace("C"+i, projectData.content[i].date.substring(0,10));
                szablon = szablon.replace("D"+i, projectData.content[i].description);
                szablon = szablon.replace("link"+i, projectData.content[i].id);
            }
            console.log(abi);
            document.getElementById("projects").innerHTML = szablon;
        });
}
