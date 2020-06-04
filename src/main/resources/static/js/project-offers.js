let project_Data = {
    "date":null,
    "description":"",
    "studentDto": {
        "name":null,
        "surname":null
    },
    "title":null,
    "abilities":[]
}
request = new XMLHttpRequest();
request.open("GET", "/", true);

function funLoadAbilities_(){
    let optionForm = document.querySelector("select[name='drop1']");
    let ability = {
        "ability":""
    }
    fetch("/api/abilities")
        .then(response => response.json())
        .then(response =>{
            ability = JSON.parse(JSON.stringify(response));
            for (let i in ability){
                optionForm.options[optionForm.options.length] = new Option(ability[i].ability);
            }
        })
}

function find(){
    var string = document.getElementById('name').value;
    var abi =  document.getElementById("drop1").options[document.getElementById("drop1").selectedIndex].value;
    fetch("/api/projects?page=0&size=3&sort=ASC&filter="+string, {
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
}
