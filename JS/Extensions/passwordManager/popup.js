const nameinp = document.getElementById("appName");
const pwdinp = document.getElementById("password");
const mailinp = document.getElementById("mail");
const submit = document.getElementById("save");
const ul = document.getElementById("list");

submit.addEventListener("click", () => {
    savePassword(nameinp.value, mailinp.value, pwdinp.value);
    loadPwds();
});

function savePassword(app, mail, pwd) {
    browser.runtime.sendMessage({
        action: "SAVE_PASSWORD",
        data: { app, mail, pwd },
    }).then(r=>{
        console.log(r);
    })
}

function loadPwds() {
    browser.runtime.sendMessage({
        action: "GET_PASSWORD",
    }).then(r=>{
        const results = document.getElementById("list");
        for(let app in r){
            const appDiv = document.createElement("div");
            appDiv.innerHTML = "<h1>"+app+"</h1><ul>";

            for (let u in r[app]){
                appDiv.innerHTML += "<li>"+u+"<br>"+r[app][u]+"</li><br>";
            }
            appDiv.innerHTML+="</ul><hr>";
            results.appendChild(appDiv);
        }
    })
}
loadPwds();