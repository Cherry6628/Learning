const{log}=console;
const res=document.getElementById("result");
const uname=document.querySelector("input[name=uname]");
const mail = document.querySelector("input[name=mail]");
const pwd = document.querySelector("input[name=pwd]");
const cpwd = document.querySelector("input[name=cpwd]");
const button = document.querySelector("input[type=submit]");
// const;
function showMessage(msg,t=5000){
    let message = document.createElement("p");
    message.innerText = msg;
    message.style.border = "1px solid black";
    res.appendChild(message);
    setTimeout(()=>message.remove(),t)
}


button.addEventListener('click',async ()=>{
    if (pwd.value!=cpwd.value || !pwd.value || !uname.value || !mail.value) return showMessage("Invalid ");
    let response = await fetch("/signup", {
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({"uname":uname.value,"mail":mail.value,"pwd":pwd.value,"cpwd":cpwd.value})
    })
    let json_ = await response.json();
    if(json_.redirect===true){
        location.href="/login";
    } else {
        showMessage(json_.message, 10000);
    }

});


