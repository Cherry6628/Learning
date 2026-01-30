
let dateOutputElement = document.querySelector("#date-output");
console.log(dateOutputElement);
let today=new Date();
dateOutputElement.innerText=today;
console.log(today);

let date=Date.now();
console.log(date);
console.log(date/(1000*60*60*24))

let epoch=new Date(0);
console.log(epoch);


let todayDate=new Date("2025-08-25");
let birthDate=new Date("2025-07-25");
console.log(todayDate);
console.log((todayDate - birthDate)/(1000*60*60*24));


Date.now();
const d= Date.now();
console.log("current Time -->",d);

const year2000= new Date(2000,0,1);
console.log("Year2000 -->",year2000);

const universal = Date.UTC(2025);
console.log("universal Time --->",universal);

console.log("difference -->",(universal-epoch)/(1000*60*60*24));

let parsedDate = Date.parse("Aug 25, 2025 12:00:00");
console.log("parsedDate --->", parsedDate);



console.log("getDate() -->", today.getDate());


console.log("getDay() -->", today.getDay());


console.log("getFullYear() -->", today.getFullYear());


console.log("getHours() -->", today.getHours());

console.log("getSeconds() -->", today.getSeconds());

console.log("getMilliseconds() -->", today.getMilliseconds());

console.log("getTime() -->", today.getTime());

console.log("setTime() -->", today.setTime(946684800000)); // Jan 1 2000
console.log("today -->",today);



const date_=new Date();
console.log("date -->",date_);
const universalTime=date_.toUTCString();

console.log("Universal Time -->",universalTime);
console.log("timeZoneOffSet()-->",date_.getTimezoneOffset()); // difference in minutes


console.log(date_.setTime(0));
console.log(date_);

console.log("getUTCDate() -->",date_.getUTCDate());

console.log("getUTCDay() -->",date_.getUTCDay());

console.log("getUTCFullYear() -->",date_.getUTCFullYear());

console.log("getUTCMonth() -->",date_.getUTCMonth());

console.log("getUTCHours() -->",date_.getUTCHours());



console.log("getUTCMinutes() -->",date_.getUTCMinutes());

console.log("getUTCseconds() -->",date_.getUTCSeconds());

console.log("getUTCMilliSeconds() -->",date_.getUTCMilliseconds());


console.log("toString() --->", date_.toString());
console.log("toDateString() --->", date_.toDateString());

console.log("toISOString() --->", date_.toISOString());


console.log("toJSON() --->", date_.toJSON());

console.log("toLocaleDateString() --->", date_.toLocaleDateString("ar-EG"));

console.log("toLocaleString() --->", date_.toLocaleString());

console.log("toLocaleTimeString() --->", date_.toLocaleTimeString("ko-KR"));






















