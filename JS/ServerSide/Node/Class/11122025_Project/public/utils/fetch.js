const wordInp = document.getElementById("word");
const button = document.getElementById("submit");
const result = document.getElementById("result");

const loglevel = Object.freeze({
    INFO: 1,
    WARN: 2,
    ERROR: 3,
});

async function fetchMeaning(word) {
    if (!word) return;
    const url = `https://api.dictionaryapi.dev/api/v2/entries/en/${encodeURIComponent(
        word
    )}`;
    const res = await fetch(url);
    if (!res.ok) throw new Error("Word not found");
    let r = await res.json();
    console.log(JSON.stringify(r));
    return r;
}

function showMessage(msg, level = loglevel.INFO) {
    result.innerHTML = "";

    const div = document.createElement("div");
    div.classList.add("message");

    if (level === loglevel.WARN) div.classList.add("warn");
    if (level === loglevel.ERROR) div.classList.add("error");

    div.textContent = msg;
    result.appendChild(div);
}

function renderMeanings(meanings) {
    return meanings
        .map((meaning) => {
            return `
            <section class="meaning">
                <h3 class="pos">${meaning.partOfSpeech}</h3>
                <ol>
                    ${meaning.definitions
                        .map(
                            (def) => `
                        <li>
                            <span class="def-text">${def.definition}</span>
                            ${
                                def.example
                                    ? `<div class="example">“${def.example}”</div>`
                                    : ""
                            }
                        </li>
                    `
                        )
                        .join("")}
                </ol>
            </section>
        `;
        })
        .join("");
}
async function handleSearch() {
    const word = wordInp.value.trim();
    if (!word) return showMessage("Type a word.", loglevel.WARN);

    showMessage("Loading...");

    try {
        const data = await fetchMeaning(word);
        const entry = data[0];

        result.innerHTML = `
            <div class="definition">
                <h2>${entry.word}</h2>
                ${renderMeanings(entry.meanings)}
            </div>
        `;
    } catch (err) {
        showMessage("Word not found.", loglevel.WARN);
    }
}

button.addEventListener("click", handleSearch);
wordInp.addEventListener("keydown", (e) => {
    if (e.key === "Enter") handleSearch();
});
