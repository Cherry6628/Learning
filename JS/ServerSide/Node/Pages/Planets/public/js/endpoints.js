document.querySelectorAll("header [data-planet]").forEach(b=>{
    b.addEventListener("click", async (e)=>{
        let prom = await fetch("/"+e.currentTarget.dataset.planet);
        let result = await prom.json();
        data = prom;
    })
})