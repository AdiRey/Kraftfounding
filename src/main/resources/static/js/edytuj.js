
let userData = {
	"id": null,
	"name": null,
	"surname": null,
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

//wszystkie fetche byly w funkcji test() ale ona nigdzie nie byla wywolywana wiec nie wiem. moze w onload byla, a mialem stary html.

fetch("/api/students/1", {
	headers: {
		"Authorization" : localStorage.getItem("Authorization")
	}
})
	.then(response => response.json())
	.then(response => {
		userData = JSON.parse(JSON.stringify(response));
		console.log(userData);
		var skills;
		document.getElementById("naz").value = userData.surname;
		document.getElementById("im").value = userData.name;
		CKEDITOR.document.getById('op').setText(userData.interests);
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

/*async function postData(url='', data={}) {
	const response = await fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	});
	return response.json();
}*/

fetch("/api/subjects?type=unique")
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

fetch("/api/abilities")
	.then(response => response.json())
	.then(response => {
		skills = JSON.parse(JSON.stringify(response));
		var umki = "<legend>Umiejętności</legend>";
		var id = 1;
		var umek;
		for (skill in skills) {
			for (user_skill in userData.abilities) {
				console.log(userData.abilities[user_skill])
				console.log(skills[skill])
				if (userData.abilities[user_skill].ability === skills[skill].ability) {
					console.log("XD")
					umek = "<input type=\"checkbox\" name=\"um" + id + "\" value=\"" + id + "\" checked>" + "<label for=\"um" + id + "\">" + skills[skill].ability + "</label><br>";
					break;
				}
				else {
					umek = "<input type=\"checkbox\" name=\"um" + id + "\" value=\"" + id + "\">" + "<label for=\"um" + id + "\">" + skills[skill].ability + "</label><br>";
				}
			}
			umki = umki + umek;
			id++;
		}
		document.getElementById("umy").innerHTML = umki;
	})
	.catch(error => console.error(error))



async function putData(url='', data={}) {
	const response = await fetch(url, {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	});
	return response.json();
}

function validateForm() {
	var imie = document.forms["edycja-profilu"]["imie"].value;
	var nazwisko = document.forms["edycja-profilu"]["nazwisko"].value;
	//var opis = CKEDITOR.document.getById('op').val;
	var opis = CKEDITOR.instances.op.getData();
	var kierunek = document.forms["edycja-profilu"]["op"].value;

	if (/\d/.test(imie)) {
		alert("Imię nie może zawierać cyfr");
		return false;
	}
	else if (/\d/.test(nazwisko)) {
		alert("Nazwisko nie może zawierać cyfr");
		return false;
	}
	else if (opis.length > 250) {
		alert("Opis jest za długi (max 250 znaków)!");
		return false;
	}
	userData.name = imie;
	userData.surname = nazwisko;
	userData.interests = opis;
	userData.subject.name = kierunek;
	alert(userData.interests);
	return putData('/api/students/1', userData)
}

function validateFormHasla() {
	var stare_haslo = document.forms["edycja-hasla"]["stare_haslo"].value;
	var nowe_haslo1 = document.forms["edycja-hasla"]["nowe_hasloa"].value;
	var nowe_haslo2 = document.forms["edycja-hasla"]["nowe_haslob"].value;
	const paswd = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/;
	if (stare_haslo == nowe_haslo1) {
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
	//return postData('/api/students/1', userData2)
}

