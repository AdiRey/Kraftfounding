function checkLogin(){
    let authentication = localStorage.getItem("Authorization");
    if (authentication === null){
        document.getElementById("user-logout").style.display = "block";
        document.getElementById("user-login").style.display = "none";
    }else{
        let decoded = jwt_decode(authentication.substr(7));
        user.name = decoded.sub;
        document.getElementById("user-login-menu").textContent = user.name.replace(/![0-9]+/,"");
        document.getElementById("user-logout").style.display = "none";
        document.getElementById("user-login").style.display = "block";
    }
}