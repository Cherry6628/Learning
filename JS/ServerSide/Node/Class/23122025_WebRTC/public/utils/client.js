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

// getStream();

function stopStream(){
    if(localStream){
        // log(localStream.getTracks());
        localStream.getTracks().forEach(track=>{
            log(track);
            track.stop()
        });

        localVideo.srcObject=null;
    }
}
let streaming=false;
stopStartBtn.addEventListener('click',()=>{
    if(streaming){
        stopStream()
    } else getStream()
    streaming=!streaming;
})