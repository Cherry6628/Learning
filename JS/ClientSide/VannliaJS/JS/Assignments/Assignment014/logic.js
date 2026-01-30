let rock, paper, scissor, lizard, spock, selections, draw, playerVsComputer, playerVsPlayer, player1, player2, computerName, diff4, diff3, diff2, diff1, diff0, debug, defaultMode, defaultDifficulty, maxPoints, option1, option2, points1, points2, rockElement, paperElement, scissorElement, lizardElement, spockElement, canKill;

function flashOption(imgElement, type) {
    const parent = imgElement.closest(".opt");

    let className;
    if(type === "correct") className = "correct";
    else if(type === "wrong") className = "wrong shake";
    else if(type === "draw") className = "draw";

    parent.classList.add(...className.split(' '));

    setTimeout(() => {
        parent.classList.remove(...className.split(' '));
    }, 600);
}



function showWinnerPopup(winnerName){
    const popup = document.getElementById("winner-popup");
    popup.textContent = winnerName + " WON!";
    popup.style.display = "block";
    popup.style.opacity = 0;
    let opacity = 0;
    const fadeIn = setInterval(()=>{
        opacity += 0.05;
        popup.style.opacity = opacity;
        if(opacity >= 1) clearInterval(fadeIn);
    }, 20);
    setTimeout(()=>{
        const fadeOut = setInterval(()=>{
            opacity -= 0.05;
            popup.style.opacity = opacity;
            if(opacity <= 0){
                clearInterval(fadeOut);
                popup.style.display = "none";
            }
        }, 20);
    }, 2000);
}


function whoWonAmong(opt1, opt2){
    if(opt1==opt2){option1=null; option2=null; return draw;}
    else if(canKill[opt1].includes(opt2)){option1=null; option2=null; return player1;}
    option1=null; option2=null; return player2;
}

function log(...x){if (debug) console.log(...x);};

function selectRandom(arr){return arr[Math.floor(Math.random()*arr.length)];}
function selectRandomWithExcludance(arr, ...exclusion){return selectRandom(arr.filter(x=>!(exclusion.includes(x))));}

function computerLogic(userOption,level=diff1){
    if (Math.random() >= level){
        return selectRandomWithExcludance(selections, ...selections.filter(x=>x!=userOption && x!=canKill[userOption][0] && x!=canKill[userOption][1]));
    } else {
        return selectRandomWithExcludance(selections, userOption, ...canKill[userOption]);
    }
}
function handler(currentOption, mode=defaultMode, difficultyLevel=defaultDifficulty){
    if (mode==playerVsPlayer){
        if (option1==null){
            option1=currentOption;
        } else if (option2==null){
            option2=currentOption;
        }
    } else if (mode==playerVsComputer){
        if (option1==null){
            option1=currentOption;
        }
        option2 = computerLogic(option1, difficultyLevel)
    }
    if (option1 !=null && option2!=null){
        opt1.setAttribute("src", paths[option1]);
        opt2.setAttribute("src", paths[option2]);
        
        log(player1+" chose "+option1+"\n"+player2+" chose "+option2)
        let result = whoWonAmong(option1, option2)
        if (result==player1){
            points1++;
            flashOption(opt1, "correct");
            flashOption(opt2, "wrong");
            log("%c"+player1+" won", "color: black; background-color: white;")
            
        } else if (result==player2){
            points2++;
            flashOption(opt1, "wrong");
            flashOption(opt2, "correct");
            
            log("%c"+player2+" won.", "color: black; background-color: white;")
        } else{
            flashOption(opt1, "draw");
            flashOption(opt2, "draw");
            log("%cdraw...", "color: black; background-color: white;")
        }
        if (points1>=maxPoints || points2>=maxPoints){
            opt1.setAttribute("src", "");
            opt2.setAttribute("src", "");
            removeEventListeners();
            let w = points1==maxPoints?player1:player2;
            userWon(w);
            showWinnerPopup(w); 
        }
        option1=null; option2=null;
    }

}
function rockHandler(){handler(rock)}
function paperHandler(){handler(paper)}
function scissorHandler(){handler(scissor)}
function lizardHandler(){handler(lizard)}
function spockHandler(){handler(spock)}

function userWon(user){
    log("%c"+user+" Won", "color: green; background-color: black;");
}

function startListening(){
    //temp
    option1=null, option2=null;
    points1=0, points2=0;
    console.clear();

    rockElement.addEventListener('click', rockHandler);
    paperElement.addEventListener('click', paperHandler);
    scissorElement.addEventListener('click', scissorHandler)
    lizardElement.addEventListener('click', lizardHandler);
    spockElement.addEventListener('click', spockHandler);
}

function removeEventListeners(){
    rockElement.removeEventListener('click', rockHandler);
    paperElement.removeEventListener('click', paperHandler);
    scissorElement.removeEventListener('click', scissorHandler)
    lizardElement.removeEventListener('click', lizardHandler);
    spockElement.removeEventListener('click', spockHandler);
}
function initializeVariables(){
    rock = "rock"; paper = "paper"; scissor = "scissor"; lizard = "lizard"; spock = "spock";
    selections = [rock, paper, scissor, lizard, spock]

    draw = "DRAW";     // winning chance = 100%

    playerVsComputer = "PLAYERVSCOMPUTER"; playerVsPlayer = "PLAYERVSPLAYER";
    diff4 = 1;     // impossible
    diff3 = 0.75;  // winning chance = 25%
    diff2 = 0.5;   // winning chance = 50%
    diff1 = 0.25;  // winning chance = 75%
    diff0 = 0;

    // Options
    debug=true;
    maxPoints = 5;



    rockElement = document.getElementById('rock');
    paperElement = document.getElementById("paper");
    scissorElement = document.getElementById("scissor");
    lizardElement = document.getElementById("lizard");
    spockElement = document.getElementById("spock");

    canKill = {
        rock: [scissor, lizard],
        paper: [rock, spock],
        scissor: [paper, lizard],
        lizard: [paper, spock],
        spock: [scissor, rock]
    }
    paths = {
        null: "",
        rock: "images/icon-rock.svg",
        paper: "images/icon-paper.svg",
        scissor: "images/icon-scissors.svg",
        lizard: "images/icon-lizard.svg",
        spock: "images/icon-spock.svg"
    }
    document.querySelector("[type=\"submit\"]").addEventListener('click', (e)=>{
        e.preventDefault()
    })

}

initializeVariables();

let pvpInput = document.getElementById("pvp");
let pvcInput = document.getElementById("pvc");
let selectedDiff = document.getElementById("selection-option");
let opt1 = document.getElementById("p1opt");
let opt2 = document.getElementById("p2opt");
let opt1Parent = opt1.closest(".opt");
let opt2Parent = opt2.closest(".opt");


function doProcess(){

    opt1.setAttribute("src", "");
    opt2.setAttribute("src", "");

    removeEventListeners();
    player1 = "Player1";
    if (pvpInput.checked){
        
        player2 = "Player 2"
        defaultMode=playerVsPlayer;
        // defaultDifficulty=diff0;
    } else if (pvcInput.checked){
        player2 = "Bot"
        defaultMode=playerVsComputer;
        defaultDifficulty=Number(selectedDiff.value);

    }
    startListening();
}

const instructionsBtn = document.getElementById("instructions-btn");
const instructionsPopup = document.getElementById("instructions-popup");

instructionsBtn.addEventListener("click", () => {
    instructionsPopup.style.display = "flex";
});

instructionsPopup.addEventListener("click", () => {
    instructionsPopup.style.display = "none";
});
