let userData = {
	"id": null,
	"name": null,
	"surname": null,
	"email":null,
	"imageUrl":null,
	"interests": null,
	"subject": {
		"name": null,
		"term": null
	},
	"abilities": [
		{
			"ability":null
		}
	]
};

fetch("/api/abilities")
	.then(response => response.json())
	.then(response => {
		skills = JSON.parse(JSON.stringify(response));
		var umki = "<legend>Umiejętności</legend>";
		var id = 1;
		var umek;
		for (skill in skills) {
			for (user_skill in userData.abilities) {
				if (userData.abilities[user_skill].ability === skills[skill].ability) {
					umek = "<input type=\"checkbox\" name=\"um" + id + "\" value=\"" + id + "\" checked>" + "<label for=\"um" + id + "\">" + skills[skill].ability + "</label><br>";
					break;
				}
				else {
					umek = "<input type=\"checkbox\" name=\"um" + id + "\" value=\"" + id + "\">" + "<label for=\"um" + id + "\">" + skills[skill].ability + "</label><br>";
				}
			}
			umki = umki + umek;
			document.getElementById("umy").innerHTML = umki;
			id++;
		}
	})
	.catch(error => console.error(error));

function decode_token2() {
	let myToken;
	myToken = localStorage.getItem("Authorization");
	myToken = jwt_decode(myToken.substr(7));
	myToken = myToken.sub;
	myToken = myToken.match(/![0-9]+/g);
	return myToken[0].substr(1);
}

//wszystkie fetche byly w funkcji test() ale ona nigdzie nie byla wywolywana wiec nie wiem. moze w onload byla, a mialem stary html.

CKEDITOR.config.allowedContent = {
	$1: {
		elements: CKEDITOR.dtd,
		attributes: true,
		styles: true,
		classes: true
	}
};
CKEDITOR.config.disallowedContent = 'script; *[on*];'; //mozna dodac wiecej, nie wiem czy dziala XD

fetch("/api/students/" + decode_token2(), {
	headers: {
		"Authorization": localStorage.getItem("Authorization")
	}
})
	.then(response => response.json())
	.then(response => {
		userData = JSON.parse(JSON.stringify(response));
		console.log(userData);
		var skills;
		document.getElementById("naz").value = userData.surname;
		document.getElementById("im").value = userData.name;
		CKEDITOR.document.getById("op").setText(userData.interests);
		document.getElementById("sem").value = userData.subject.term;
		document.getElementById("avatar-edycja").src = "..\\img\\"+userData.imageUrl;
		//document.getElementById("op").textContent = userData.interests;
		//document.getElementById(userData.subject.name).selected = true;
		/*for (skill of userData.abilities) {
			document.getElementById(skill).checked = true;
		}*/

	})
	.catch(error => {
		/*console.error(error);
		alert("Nie masz dostępu! Zaloguj się!");
		window.location.replace("/sign-in");*/
	})

async function postData(url='', data={}) {
	const response = await fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	});
	return response.json();
}

fetch("/api/subjects?type=unique",{
	headers: {
		"Authorization": localStorage.getItem("Authorization")
	}
})
	.then(response => response.json())
	.then(response => {
		terms = JSON.parse(JSON.stringify(response));
		var id = 1;
		var termy = "";
		var termXD;
		for (term in terms) {
			if(userData.subject.name === terms[term].name) {
				termXD = "<option selected value=\"t" + id + "\">" + terms[term].name + "</option>";
			} else {
				termXD = "<option value=\"t" + id + "\">" + terms[term].name + "</option>";
			}
			termy = termy + termXD;
			id++;
			termXD = null;
		}
		document.getElementById("kier").innerHTML = termy;
	})
	.catch(error => console.error(error))


async function putData(url='', data={}) {
	const response = await fetch(url, {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json',
			'Authorization' : localStorage.getItem('Authorization')
		},
		body: JSON.stringify(data)
	});
	return response.json();
}

async function validateForm() {
	var imie = document.forms["edycja-profilu"]["imie"].value;
	var nazwisko = document.forms["edycja-profilu"]["nazwisko"].value;
	//var opis = CKEDITOR.document.getById('op').val;
	var opis = CKEDITOR.instances.op.getData();
	var plain_opis = CKEDITOR.instances.op.document.getBody().getText()
	var kierunek = document.forms["edycja-profilu"]["kier"].value;
	var semestr = document.forms["edycja-profilu"]["sem"].value;

	if (/\d/.test(imie)) {
		alert("Imię nie może zawierać cyfr");
		return false;
	}
	else if (/\d/.test(nazwisko)) {
		alert("Nazwisko nie może zawierać cyfr");
		return false;
	}
	else if (plain_opis.length > 250) {
		alert("Opis jest za długi (max 250 znaków)!");
		return false;
	}
	var userData2 = userData;
	userData2.name = imie;
	userData2.surname = nazwisko;
	userData2.interests = opis;
	userData2.subject.name = kierunek;
	userData2.subject.term = semestr;
	console.log(userData2);
	await sleep(2000000000);
	return putData('/api/students/' + decode_token2(), userData2)
}

function sleep(ms) {
	return new Promise(resolve => setTimeout(resolve, ms));
}

function validateFormHasla() {
	var stare_haslo = document.forms["edycja-hasla"]["stare_haslo"].value;
	var nowe_haslo1 = document.forms["edycja-hasla"]["nowe_hasloa"].value;
	var nowe_haslo2 = document.forms["edycja-hasla"]["nowe_haslob"].value;
	const paswd = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/;
	if (stare_haslo === nowe_haslo1) {
		alert("Stare i nowe hasła są takie same!");
		return false;
	}
	else if (nowe_haslo1 !== nowe_haslo2) {
		alert("Błędnie wpisane nowe hasło");
		return false;
	}
	else if(!nowe_haslo1.match(paswd))  {
		alert("Hasło musi zawierać od 7 do 15 znaków, conajmniej jedną cyfrę i conajmniej jeden znak specjalny");
		return false;
	}
	var userData2 = userData;
	userData2.password = nowe_haslo1;
	return putData('/api/students/' + decode_token2(), userData2)
}

function validateFormAvatar() {
	postData('/api/file?email='+userData.email+'&surname='+userData.surname+'&profile=true');
}