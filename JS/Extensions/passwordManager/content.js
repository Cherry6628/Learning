console.log("Hello From Password Manager");

let multipleTimes = false;
function savePassword(app, mail, pwd) {
    browser.runtime
        .sendMessage({
            action: "SAVE_PASSWORD",
            data: { app, mail, pwd },
        })
        .then((r) => {
            console.log(r);
        });
}
function t(a) {
    // debugger function
    console.log(a);
}

const observer = new MutationObserver(() => {
    t(0);
    t(1);
    if (multipleTimes) return;
    t(2);
    const appinp = location.hostname;
    const mailinp = document.querySelector("[type=email],[name~=username]");
    const pwdinp = document.querySelector("[type=password]");
    let cachedMail = "";
    let cachedPwd = "";

    console.log(pwdinp, pwdinp.value, mailinp, mailinp.value);
    if (!pwdinp || !pwdinp.value || !mailinp) return;
    // console.log()
    t(3);
    multipleTimes = true;
    t(4);
    observer.disconnect();
    t(5);
    mailinp.addEventListener("input", () => {
        cachedMail = mailinp.value;
    });

    pwdinp.addEventListener("input", () => {
        cachedPwd = pwdinp.value;
    });

    document.addEventListener("click", (e) => {
        t(6);
        console.log("clicked");
        let target = e.target;
        console.log(target);
        window.temp =
            target.closest("[type=submit]") || target.closest("button");

        target = window.temp;
        delete window.temp;
        console.log(target);
        if (!target) return;

        let mailv = cachedMail;
        let pwdv = cachedPwd;

        console.log(mailv, pwdv);
        if (!confirm("DO YOU WANT TO SAVE THE PASSWORD ? ")) return;
        savePassword(appinp, mailv, pwdv);
        // let uname = mailinp.value;
    });
});

observer.observe(document.body, {
    childList: true,
    subtree: true,
    attributes: true,
});
