const extensionId = "mynotestakingapp_xR3GA6XUMH2DGCeWdmho1chJv3isXuf5";
const save = document.getElementById("save");
const text = document.getElementById("text");

async function init() {
    const data = await getData();
    if (data && data.value) {
        text.value = data.value;
    }
}

async function getData() {
    let results = await browser.storage.local.get(extensionId);
    return results[extensionId] || {};
}

function setData(data) {
    browser.storage.local.set({ [extensionId]: data });
}

async function updateKey(keyName, data) {
    const currentData = await getData();
    const newData = { ...currentData, [keyName]: data };
    setData(newData);
}

init();

save.addEventListener("click", () => {
    updateKey("value", text.value);
});
text.addEventListener("update", () => {
    updateKey("value", text.value);
});