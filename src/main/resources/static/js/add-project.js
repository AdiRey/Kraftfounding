//obiekt
let project_Data = {
    "date": null,
    "description": "",
    "limit": null,
    "student": {
        "email": "",
    },
    "title": null,
    "abilities": []
}
let userData = {
    "login": null,
    "password": null,
    "email": null,
    "name": null,
    "surname": null,
    "availability": null,
    "subject": null,
    "abilities": [],
    "interests": null
}
let member = {
    "content": [
        {
            "name": null,
            "surname": null
        }
    ]
}
//stałe i zmienne
let project_DataString;
let fileProject;
let fileProjectString;
const url = "https://jsonplaceholder.typicode.com/posts";
//zamiana obiektu na JSON
function JSONFromForm() {
    const now = new Date();
    project_Data.date = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + now.getDate() + 'T' + now.getHours() + ':' + now.getMinutes() + ':' + now.getSeconds() + '.' + now.getMilliseconds() + 'Z';
    project_Data.description = document.getElementById('description').value;
    project_Data.limit = 0;
    project_Data.student.email = "XYZ@PLP.PL";
    project_Data.title = document.getElementById('name').value;
    let poleSelect = document.forms['abilities'].abilities;
    for (let i = 0; i < poleSelect.length; i++) {
        if (poleSelect.options[i].checked) {
            project_Data.abilities.push({"ability": poleSelect.options[i].value}) //+= poleSelect.options[i].value;
        }
    }
    project_DataString = JSON.stringify(project_Data);
    console.log(project_DataString);
}

function file() {
    fileProject = document.getElementById('file').value;
    fileProjectString = JSON.stringify(fileProject);
    console.log(fileProjectString)
}

//wysyłanie danych na serwer
function send() {
    fetch("/api/projects", {
        method: "post",
        headers: {
            "Authorization" : token
        },
        body: project_DataString
    })
        .then(res => res.json())
        .then(res => {
            alert("Wysłano")
            console.log("Dodałem projekt:");
            console.log(res);
        })
        .catch(error => console.log("Błąd: ", error));
    fetch("/api/files", {
        method: "post",
        headers: {
            "Authorization" : token
        },
        body: fileProjectString
    })
        .then(res => res.json())
        .then(res => {
            alert("Wysłano")
            console.log("Dodałem plik:");
            console.log(res);
        })
        .catch(error => console.log("Błąd: ", error));
}

function add_project() {
    var name = document.getElementById('name').value;
    var description = document.getElementById('description').value;
    var added = document.forms['list'].elements['added'];
    var members = new Array();
    for (var i = 0; i < added.options.length; i++) {
        members[i] = added.options[i].value;
    }
    var abilities = new Array();
    for (var i = 0; i < 10; i++) {
        var id = "c" + (i + 1);
        abilities[i] = document.getElementById(id);
    }
    var file = document.getElementById('file').value;
    var project = "Nazwa: " + name + "\nOpis: " + description + "\nCzłonkowie:\n";
    for (var i = 0; i < members.length; i++) {
        project = project + members[i] + "\n";
    }
    project = project + "Kompetencje:\n";
    for (var i = 0; i < abilities.length; i++) {
        if (abilities[i].checked == true)
            project = project + abilities[i].value + "\n";
    }
    project = project + "Plik: " + file;
    // alert(project);

    JSONFromForm();
    alert("Chyba dodałem");
    file();

    send();
}

function add() {
    var added = document.forms['list'].elements['added'];
    var to_add = document.forms['list'].elements['to_add'];
    var ListItems = new Array();
    for (var i = (to_add.options.length - 1); i >= 0; i--)
        if (to_add.options[i].selected) {
            ListItems[ListItems.length] = new Option(to_add.options[i].text);
            to_add.options[i] = null;
        }
    for (var i = ListItems.length - 1; i >= 0; i--)
        added.options[added.options.length] = ListItems[i];
}

function remove() {
    var added = document.forms['list'].elements['added'];
    var to_add = document.forms['list'].elements['to_add'];
    var ListItems = new Array();
    for (var i = (added.options.length - 1); i >= 0; i--)
        if (added.options[i].selected) {
            ListItems[ListItems.length] = new Option(added.options[i].text);
            added.options[i] = null;
        }
    for (var i = ListItems.length - 1; i >= 0; i--)
        to_add.options[to_add.options.length] = ListItems[i];
}

/*ładowanie danych do formularza*/
function funLoadMembers() {
    let optionForm = document.querySelector("select[name='to_add']");


    fetch("/api/students", {
        headers: {
            "Authorization" : token
        }
    })
        .then(response => response.json())
        .then(response => {
            member = JSON.parse(JSON.stringify(response));
            for (let i in member.content) {
                optionForm.options[optionForm.options.length] = new Option(member.content[i].name + " " + member.content[i].surname);
            }
        })
}

function funLoadAbilities_() {

    let ability = {
        "ability": ""
    }
    fetch("/api/abilities", {
        headers: {
            "Authorization" : token
        }
    })
        .then(response => response.json())
        .then(response => {
            ability = JSON.parse(JSON.stringify(response));
            let j = 1;
            let ch1 = "";
            let ch2 = "";
            for (let i in ability) {
                if (j <= 5) {
                    ch1 += "<input type=\"checkbox\" id=\"c" + j + "\" value=\"" + ability[i].ability + "\"/> " + ability[i].ability + "<br />";
                } else {
                    ch2 += "<input type=\"checkbox\" id=\"c" + j + "\" value=\"" + ability[i].ability + "\"/> " + ability[i].ability + "<br />";
                }
                j++;
            }
            document.getElementById("ch1").innerHTML = ch1;
            document.getElementById("ch2").innerHTML = ch2;
        })
}



