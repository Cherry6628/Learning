import validator from "validator";
import { v4 as uuidv4 } from "uuid";
import moment from "moment";
import chalk from "chalk";

const red = chalk.red;
const green = chalk.green;
const yellow = chalk.yellow;
const cyan = chalk.cyan;

function validateUser(user) {
    let errors = [];

    if (validator.isEmpty(user.name || "")) errors.push("Name cannot be empty");
    if (!validator.isEmail(user.email || "")) errors.push("Invalid email format");
    if (!validator.isStrongPassword(user.password || "", { minLength: 8, minNumbers: 1, minSymbols: 1 })) 
        errors.push("Weak password");
    if (!validator.isMobilePhone(user.mobile || "", "en-IN")) errors.push("Invalid Indian mobile number");
    if (user.website && !validator.isURL(user.website)) errors.push("Invalid website URL");

    if (errors.length) {
        errors.forEach(e => console.log(red(e)));
    } else {
        user.email = user.email.toLowerCase();
        console.log(green("Valid User Data"));
    }
}

let user = {
    name: "Aravind",
    email: "Aravind@Example.com",
    password: "Abcd@1234",
    mobile: "9876543210",
    website: "https://example.com"
};

console.log(cyan("\nUser Validation"));
validateUser(user);









console.log(cyan("\nMovie Ticket Booking System"));

let bookings = [];

function createBooking(customerName, movie, seats) {
    let id = uuidv4();
    let booking = { bookingId: id, customerName, movie, seats };
    console.log(JSON.stringify(booking));
    bookings.push(booking);
    return booking;
}

createBooking("Aravind", "Leo", 2);
createBooking("Karan", "Vikram", 3);
createBooking("Aisha", "Jailer", 1);

bookings.forEach(b => {
    console.log(green("Booking ID: ") + b.bookingId);
    console.log(yellow("Customer: ") + b.customerName);
    console.log(cyan("Movie: ") + b.movie);
    console.log("Seats: " + b.seats + "\n");
});

let ids = new Set(bookings.map(b => b.bookingId));
console.log(ids.size === bookings.length ? green("All UUIDs are unique") : red("Duplicate UUID found"));


















console.log(cyan("\nTask Management"));

let tasks = [];

function addTask(name, dueDate) {
    tasks.push({ name, dueDate: moment(dueDate), overdue: false });
}

function daysRemaining(task) {
    return task.dueDate.diff(moment(), "days");
}

function displayTasks() {
    tasks.forEach(t => {
        let rem = daysRemaining(t);
        let msg;

        if (rem < 0) msg = red("OVERDUE");
        else if (rem === 0) msg = yellow("Due today");
        else msg = green(rem + " days remaining");

        let human = t.dueDate.fromNow();
        console.log(cyan(t.name) + " → " + human + " (" + msg + ")");
    });
}

addTask("Finish assignment", "2025-02-01");
addTask("Submit project", "2025-01-15");
addTask("Pay bills", "2025-01-10");

tasks.forEach(t => { if (t.dueDate.isBefore(moment())) t.overdue = true; });

displayTasks();
