const{log}=console;

const localVideo = document.getElementById("localVideo");
const stopStartBtn = document.getElementById("stopstart");
const option = document.getElementById("toShare");
let localStream;
function getStream(){
    let prom = option.value=="screen"?navigator.mediaDevices.getDisplayMedia({
        video:true,audio:true
    }):navigator.mediaDevices.getUserMedia({
        video:true,audio:true
    })
    prom.then(stream=>{
        log(stream);
        localStream=stream;
        localVideo.srcObject=stream;
    })
    
    .catch(err=>log(err));
}


function stopStream(){
    if(localStream){
        // log(localStream.getTracks());
        localStream.getTracks().forEach(track=>{
            log(track);
            track.stop()
        });
        localStream=undefined;
        localVideo.srcObject=null;
    }
}

stopStartBtn.addEventListener('click',()=>{
    if(localStream){
        stopStream()
    } else getStream()
    // streaming=!streaming;


})



let peerConnection;
const configuration = {
    iceServers:[
        {urls: "stun:stun.l.google.com:19302"}
    ]
}
const clientSocket = io();

function createPeerConnection(){
    peerConnection=new RTCPeerConnection(configuration);

    if(localStream){
        localStream.getTracks().forEach(track=>{
         peerConnection.addTrack(track,localStream);
        });
    }
    
    peerConnection.ontrack=(event)=>{
        // Assuming only one stream is sent by remote
        const remoteVideo=document.getElementById("remoteVideo");
        remoteVideo.srcObject = event.streams[0];
    };

    peerConnection.onicecandidate=(event)=>{
        if(event.candidate){
            clientSocket.emit("ice-candidate",event.candidate);
        }
    };
}

clientSocket.on("offer",async({offer})=>{
    console.log("Offer received ... Creating answer. To: ");
    createPeerConnection();
    await peerConnection.setRemoteDescription(offer);
    const answer=await peerConnection.createAnswer();
    await peerConnection.setLocalDescription(answer);
    clientSocket.emit("answer",answer);
    console.log("Local of p2 and remote of p1 description set ... Answer sent.");
});

clientSocket.on("answer",async({answer})=>{
    await peerConnection.setRemoteDescription(answer);
    console.log("Remote description set with answer from p2");
});

clientSocket.on("ice-candidate",async({candidate})=>{
    await peerConnection.addIceCandidate(candidate);
    console.log("ICE candidate added from remote peer");
    //ICE candidate means network information to connect peers
});

document.getElementById("start").addEventListener("click",async()=>{
    createPeerConnection();
    const offer=await peerConnection.createOffer();
    await peerConnection.setLocalDescription(offer);
    clientSocket.emit("offer",offer);
    console.log("Local description set ... Offer sent.");
});
// stopVideo.addEventListener("click",()=>{
//     if(localStream){
//         // console.log("Stopping local video stream.",localStream.getTracks());
//         localStream.getTracks().forEach(track=>{
//             track.stop();
//         });
//         localVideo.srcObject=null;
//     }
// });


document.getElementById("stop").addEventListener("click", () => {
    if (peerConnection) {
        peerConnection.close();
        peerConnection = null;
    }
    if (localStream) {
        localStream.getTracks().forEach(track => track.stop());
        localStream = null;
    }
    localVideo.srcObject = null;
});
