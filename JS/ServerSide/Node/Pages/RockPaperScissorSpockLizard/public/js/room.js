const socket = io();

const status = document.getElementById("status");
const scoreEl = document.getElementById("score");
const winnerPopup = document.getElementById("winner-popup");
const p1img = document.getElementById("p1opt");
const p2img = document.getElementById("p2opt");

let canPlay = false;

const paths = {
  rock: "/images/icon-rock.svg",
  paper: "/images/icon-paper.svg",
  scissor: "/images/icon-scissors.svg",
  lizard: "/images/icon-lizard.svg",
  spock: "/images/icon-spock.svg"
};

socket.emit("join-room", ROOM_CODE);

socket.on("room-full", () => {
  alert("Room is full!");
  window.location.href = "/";
});

socket.on("player-count", c => {
  status.textContent = `Players: ${c}/2`;
});

socket.on("start-game", () => {
  status.textContent = "Opponent joined! Choose your move.";
  canPlay = true;
});

document.querySelectorAll(".option").forEach(opt => {
  opt.onclick = () => {
    if (!canPlay) return;
    canPlay = false;
    status.textContent = "Waiting for opponent...";
    socket.emit("choice", opt.dataset.choice);
  };
});

socket.on("round-result", data => {
  scoreEl.textContent = `You: ${data.scores.you} | Opponent: ${data.scores.opponent}`;
  p1img.src = paths[data.myChoice];
  p2img.src = paths[data.opponentChoice];
  p1img.parentElement.style.visibility = "visible";
  p2img.parentElement.style.visibility = "visible";

  if (data.winner === "you") {
    status.textContent = "YOU WON THE ROUND!";
  } else if (data.winner === "opponent") {
    status.textContent = "OPPONENT WON THE ROUND!";
  } else {
    status.textContent = "IT'S A DRAW!";
  }
  canPlay = true;
});

socket.on("game-over", data => {
  canPlay = false;
  const isWinner = data.winner === socket.id;
  showWinnerPopup(isWinner ? "YOU WON THE GAME!" : "YOU LOST THE GAME!");
});

socket.on("player-left", () => {
  status.textContent = "Opponent disconnected.";
  canPlay = false;
});

function showWinnerPopup(text) {
  winnerPopup.textContent = text;
  winnerPopup.style.display = "block";
  setTimeout(() => {
    winnerPopup.style.display = "none";
    status.textContent = "Game Over. Refresh to play again.";
  }, 4000);
}

const instructionsBtn = document.getElementById("instructions-btn");
const instructionsPopup = document.getElementById("instructions-popup");

if(instructionsBtn) {
    instructionsBtn.onclick = () => {
      instructionsPopup.style.display = "flex";
    };
}
if(instructionsPopup) {
    instructionsPopup.onclick = () => {
      instructionsPopup.style.display = "none";
    };
}