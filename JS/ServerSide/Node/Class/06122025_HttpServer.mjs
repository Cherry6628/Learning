import http from 'http';
try{
    const{log}=console;
    let server = http.createServer((req,res)=>{
        // log(req);
        // log(res);
        res.write("<p>Hello</p>")
        res.end();
    });

    const port = process.argv[2];
    server.listen(port,(err,data)=>{
        if(err){
            log(err);
        } else{
            log("Listening on port "+port);
            log("http://localhost:"+port)
        }
    })

} catch(Exception){
    log(e);

}