let res = document.getElementById("result");
const mail = document.querySelector("input[placeholder=username]");
const pwd = document.querySelector("input[placeholder=password]");
const button = document.querySelector("input[type=submit]");

function showMessage(msg,t=5000){
    let message = document.createElement("p");
    message.innerText = msg;
    message.style.border = "1px solid black";
    res.appendChild(message);
    if(t>=0)setTimeout(()=>{
        message.remove()
    }, t)
}

button.addEventListener('click', async () => {
    if (!mail.value || !pwd.value) {
        return showMessage("Please enter both email/username and password.");
    }

    try {
        let response = await fetch("/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ 
                "mail": mail.value, 
                "pwd": pwd.value 
            })
        });

        let json_ = await response.json();
        
        if (response.ok) {
            showMessage(json_.message);
            mail.value = '';
            pwd.value = '';
        } else {
            showMessage(json_.message || `Login failed. Status: ${response.status}`,-1);
        }
    } catch (error) {
        console.error("Fetch error:", error);
        showMessage("An unexpected network error occurred.");
    }
});