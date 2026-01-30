import express from "express";
import http from "http";
import { Server } from "socket.io";
import path from "path";
import { fileURLToPath } from "url";

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();
const server = http.createServer(app);
const io = new Server(server);

const MAX_POINTS = 3;

app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "public/views"));
app.use(express.static(path.join(__dirname, "public")));

app.get("/", (req, res) => {
    res.render("join");
});

app.get("/room/:code", (req, res) => {
    res.render("room", { roomCode: req.params.code });
});

const canKill = {
    rock: ["scissor", "lizard"],
    paper: ["rock", "spock"],
    scissor: ["paper", "lizard"],
    lizard: ["paper", "spock"],
    spock: ["scissor", "rock"],
};

const rooms = {};

io.on("connection", (socket) => {
    socket.on("join-room", (roomId) => {
        if (!rooms[roomId]) {
            rooms[roomId] = {
                players: [],
                choices: {},
                scores: {},
            };
        }
        const room = rooms[roomId];

        if (room.players.length >= 2) {
            socket.emit("room-full");
            return;
        }

        room.scores[socket.id] = 0;
        room.players.push(socket.id);
        socket.join(roomId);
        socket.roomId = roomId;

        io.to(roomId).emit("player-count", room.players.length);

        if (room.players.length === 2) {
            io.to(roomId).emit("start-game");
        }
    });

    socket.on("choice", (choice) => {
        const room = rooms[socket.roomId];
        if (!room || !room.players.includes(socket.id)) return;

        room.choices[socket.id] = choice;

        if (Object.keys(room.choices).length === 2) {
            const playerIds = room.players;
            const p1 = playerIds[0];
            const p2 = playerIds[1];
            
            const c1 = room.choices[p1];
            const c2 = room.choices[p2];

            let winnerId = "draw";
            if (c1 !== c2) {
                winnerId = canKill[c1].includes(c2) ? p1 : p2;
                room.scores[winnerId]++;
            }

            playerIds.forEach(id => {
                const opponentId = playerIds.find(pid => pid !== id);
                io.to(id).emit("round-result", {
                    myChoice: room.choices[id],
                    opponentChoice: room.choices[opponentId],
                    scores: {
                        you: room.scores[id],
                        opponent: room.scores[opponentId]
                    },
                    winner: winnerId === "draw" ? "draw" : (winnerId === id ? "you" : "opponent")
                });
            });

            room.choices = {};

            if (room.scores[p1] >= MAX_POINTS || room.scores[p2] >= MAX_POINTS) {
                const gameWinner = room.scores[p1] >= MAX_POINTS ? p1 : p2;
                io.to(socket.roomId).emit("game-over", { winner: gameWinner });
                delete rooms[socket.roomId];
            }
        }
    });

    socket.on("disconnect", () => {
        const roomId = socket.roomId;
        if (!roomId || !rooms[roomId]) return;
        rooms[roomId].players = rooms[roomId].players.filter(id => id !== socket.id);
        if (rooms[roomId].players.length === 0) {
            delete rooms[roomId];
        } else {
            io.to(roomId).emit("player-left");
        }
    });
});

server.listen(1234, () => console.log("http://localhost:1234"));