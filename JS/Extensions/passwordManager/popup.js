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
        for(let app in r){
            const appDiv = document.createElement("div");
        }
    })
}
