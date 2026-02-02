const nameinp = document.getElementById("appName");
const pwdinp = document.getElementById("password");
const submit = document.getElementById("save");
const ul = document.getElementById("list");

submit.addEventListener("click", () => {
    browser.runtime.sendMessage({
        action: "SAVE_PASSWORD",
        data:{
            app: nameinp.value,
            pwd: pwdinp.value
        }
    });
});
