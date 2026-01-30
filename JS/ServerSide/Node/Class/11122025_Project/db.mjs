import mysql2 from "mysql2/promise";

class Connection {
    static #instances = new Map();
    #pool = null;

    constructor(dbConfig) {
        const key = dbConfig.database;

        if (Connection.#instances.has(key)) {
            return Connection.#instances.get(key);
        }

        this.#pool = mysql2.createPool({
            host: dbConfig.host || "localhost",
            user: dbConfig.user || "root",
            password: dbConfig.password,
            database: dbConfig.database,
            waitForConnections: true,
            connectionLimit: 10,
        });

        Connection.#instances.set(key, this);
    }

    async query(sql, params = []) {
        if (!this.#pool) {
            throw new Error("Database pool is closed.");
        }

        try {
            return await this.#pool.query(sql, params);
        } catch (err) {
            err.message = `[DB QUERY FAILED] ${err.message}`;
            throw err;
        }
    }

    async close() {
        if (this.#pool) {
            await this.#pool.end();
            this.#pool = null;
        }
    }
}

const db = new Connection({
    database: "codeAcc",
    password: "p@ssw0rd@123",
    user: "dicUser",
});

async function init(){
    db.query(```
        create table if not exists Users(
            id mediumint unsigned primary key auto_increment not null, 
            mailId varchar(320) not null unique key
        );
        ```);
}