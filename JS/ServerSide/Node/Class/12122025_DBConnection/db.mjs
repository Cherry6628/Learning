let password, database;
{
    password = "root";
    database = "dummy";
}
import mysql from "mysql2";
const db = mysql.createConnection({
    host:"localhost",
    user:"root",
    password,
    database

})


// db.end();

export default db;