(function () {
    function savePassword(app, mail, pwd) {
        browser.runtime
            .sendMessage({ action: "SAVE_PASSWORD", data: { app, mail, pwd } })
            .then((r) => console.log(r));
    }
    let cachemail = "", cachepwd = "";
    function updateCache() {
        cachemail = mailinp.value;
        cachepwd = pwdinp.value;
    }
    const appinp = location.hostname;
    const mailinp = document.querySelector("[type=email],[name~=username]");
    const pwdinp = document.querySelector("[type=password]");

    mailinp?.addEventListener("change",updateCache);
    pwdinp?.addEventListener("change",updateCache);
    document.addEventListener("click", (e) => {
        let target =
            e.target.closest("[type=submit]") || e.target.closest("button");
        if (!target) return;
        if (!cachemail || !cachepwd) return;

        confirm("DO YOU WANT TO SAVE THE PASSWORD ? ") &&
            savePassword(appinp, cachemail, cachepwd);
    });
})();
