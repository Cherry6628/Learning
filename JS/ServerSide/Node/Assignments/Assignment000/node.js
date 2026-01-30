let fs = require("fs");
let file = "students.txt";
let backupFolder = "backup";
let fileBackup = "students_backup.txt";
let renamedFile = "student_list.txt";

const { log } = console;

function write(file, data) {
    fs.writeFileSync(file, data.join("\n") + "\n");
}

function append(file, data) {
    fs.appendFileSync(file, data.join("\n") + "\n");
}

function read(file) {
    return fs.readFileSync(file, "utf-8").split("\n").filter(e => e);
}

function mkdir(name) {
    try { fs.mkdirSync(name); return true; } catch { return false; }
}

function rm(name) {
    try { fs.unlinkSync(name); return true; } catch { return false; }
}

write(file, ["Aravind", "Malathi", "Karim"]);

let data = read(file);
for (let i = 0; i < data.length; i++) log(`${i + 1}: ${data[i]}`);

log("");

append(file, ["Aisha", "Kavin"]);
data = read(file);
for (let i = 0; i < data.length; i++) log(`${i + 1}: ${data[i]}`);

log("");

data = data.map(name => name === "Karim" ? "Karan" : name);
write(file, data);

mkdir(backupFolder);
fs.copyFileSync(file, `${backupFolder}/${fileBackup}`);

fs.renameSync(file, renamedFile);

rm(`${backupFolder}/${fileBackup}`);

let jsonFile = "student_data.json";

if (!fs.existsSync(jsonFile)) {
    fs.writeFileSync(jsonFile, JSON.stringify([{ name: "Aravind", age: 20 }], null, 2));
}

let jsonData = JSON.parse(fs.readFileSync(jsonFile, "utf-8"));
jsonData.push({ name: "Karan", age: 22 });

fs.writeFileSync(jsonFile, JSON.stringify(jsonData, null, 2));

log("Done.");
