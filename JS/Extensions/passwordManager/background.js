async function getData(key) {
    const results = await browser.storage.local.get(key);
    return results[key] || {};
}

async function setData(key, data) {
    return await browser.storage.local.set({ [key]: data });
}

browser.runtime.onMessage.addListener((obj, context, sendResponse) => {
    console.log(obj, context, sendResponse);
    if (obj?.action=="SAVE_PASSWORD"){
        (async () => {
            const old = await getData("pwd");

            if (!old[obj.data.app]) {
                old[obj.data.app] = {};
            }

            old[obj.data.app][obj.data.mail] = obj.data.pwd;

            await setData("pwd", old);
            sendResponse("saved");
        })();

        return true;
    }
    if (obj?.action === "GET_PASSWORD") {
        (async () => {
            const pwd = await getData("pwd");
            sendResponse(pwd);
        })();

        return true;
    }
});
