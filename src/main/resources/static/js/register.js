//obiekt
let userData = {
    "login":null,
    "password":null,
    "email":null,
    "name":null,
    "surname":null,
    "availability":null,
    "subject":{
        "name":null,
        "term":null
    },
    "abilities":[],
    "interests":null
}

let userDataLogin = {
    "username":null,
    "password":null
}
//stałe i zmienne

let request = new XMLHttpRequest();

let userDataString;
let userDataStringLogin;
const url = "https://jsonplaceholder.typicode.com/posts";

//przełączanie okienek w formularzu (rejestracja)
function HideContent(d)
{
    switch (d) {
        case 'part-1':
            if(test1()){
                document.getElementById('part-1').style.display = "none";
                document.getElementById('part-2').style.display = "block";
                funLoadSubject();
                funLoadAbilities();
            }
            break;
        case 'part-2':
            if (test2()){
                document.getElementById('part-2').style.display = "none";
                document.getElementById('part-3').style.display = "block";
                //fun();
            }
            break;
        case 'part-3':
            document.getElementById('part-3').style.display = "none";
            document.getElementById('part-4').style.display = "block";
            break;
        case 'part-4':
            JSONFromForm();
            send();
            break;
        default:
            break;
    }
}

//formularz logowanie
function loginAndSend() {
    JSONFromFormLogin();
    sendLogin();
}

//zamiana obiektu na JSON
function JSONFromForm(){
    userData.login = document.forms['register'].login.value;
    userData.password = document.forms['register'].password.value;
    userData.email = document.forms['register'].email.value;
    userData.name = document.forms['register'].firstname.value;
    userData.surname = document.forms['register'].lastname.value;
    userData.availability = document.forms['register'].availability.value;
    userData.subject = {
        "name" : document.forms['register'].subject.value,
        "term" : document.forms['register'].term.value
    };
    let poleSelect = document.forms['register'].abilities;
    for (let i=0; i<poleSelect.length; i++){
        if (poleSelect.options[i].selected){
            userData.abilities.push({"ability":poleSelect.options[i].value})
        }
    }
    userData.interests = document.forms['register'].description.value;

    userDataString = JSON.stringify(userData);
    console.log(userDataString);
}

function JSONFromFormLogin(){
    userDataLogin.username = document.forms['login'].username.value;
    userDataLogin.password = document.forms['login'].password.value;
    userDataStringLogin = JSON.stringify(userDataLogin);
    console.log(userDataStringLogin);
}

//wysyłanie danych na serwer (rejestracja)
function send() {
    fetch("/api/students", {
        method: "post",
        headers: {
            'Content-Type': 'application/json'
        },
        body: userDataString
    })
        .then(res => res.json())
        .then(res => {
            alert("Zarejestrowano")
            console.log("Dodałem użytkownika:");
            console.log(res);
        })
        .catch(error => console.log("Błąd: ", error));
}

//logowanie (wysyłanie danych na serwer)
let token = null;
function sendLogin() {
    fetch("/login", {
        method: "post",
        headers: {
            'Content-Type': 'application/json'
        },
        body: userDataStringLogin,
    })
        .then(res => res.headers.get("Authorization"))
        .then(res => {
            token = res;
            console.log(token);
            localStorage.setItem("Authorization", token);
            window.location.href='/';
        })
        .catch(error => console.log("Błąd: ", error));
}

//walidacja formularza
function test1() {
    if(document.forms['register'].login.value === "")
    {alert("Nie podałeś loginu"); return false;}
    else if(document.forms['register'].password.value === "")
    {alert("Nie podałeś hasła");return false;}
    else if(document.forms['register'].password.value.length <8)
    {alert("Hasło za któtkie (min 8 liter)");return false;}
    else if(document.forms['register'].email.value === "")
    {alert("Nie podałeś e-maila");return false;}
    else if(document.forms['register'].email.value !== document.forms['register'].email2.value)
    {alert("Podane adresy e-mail nie są takie same");return false;}
    else
        return true;
}
function test2(){
    if(document.forms['register'].firstname.value === "")
    {alert("Nie podałeś imienia"); return false;}
    else if(document.forms['register'].lastname.value === "")
    {alert("Nie podałeś nazwiska"); return false;}
    else if(document.forms['register'].availability.value === "")
    {alert("Błędny wiek"); return false;}
    else if(document.forms['register'].subject.value === "disabled")
    {alert("Nie podałeś kierunku");return false;}
    else
        return true;
}

/*ładowanie danych do formularza*/
function funLoadSubject(){
    let optionForm = document.querySelector("select[name='subject']");
    let optionFormTerm = document.querySelector("select[name='term']");

    let subject = {
        "name":"",
        "term":""
    }
    fetch("/api/subjects?type=all")
        .then(response => response.json())
        .then(response => {
            console.log(response);
            subject = JSON.parse(JSON.stringify(response));
            for (let i in subject){
                optionForm.options[optionForm.options.length] = new Option(subject[i].name);
                optionFormTerm.options[optionFormTerm.options.length] = new Option(subject[i].term);
            }
        })
}

function funLoadAbilities(){
    let optionForm = document.querySelector("select[name='abilities']");

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
