while(True):
    with open("06122025_"+input()+".html","w")as f:
        f.write("""<!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Document</title>
        <style>
        span{
            margin: 20px;
            background-color: green;
        }

        </style>
    </head>
    <body>
        <div>
            <p><span>Hello</span><span>Hello</span><span>Hello</span><span>Hello</span><span>Hello</span></p>
            <p><span>Hello</span><span>Hello</span><span>Hello</span><span>Hello</span><span>Hello</span></p>
            <p><span>Hello</span><span>Hello</span><span>Hello</span><span>Hello</span><span>Hello</span></p>
            <p><span>Hello</span><span>Hello</span><span>Hello</span><span>Hello</span><span>Hello</span></p>
            <p><span>Hello</span><span>Hello</span><span>Hello</span><span>Hello</span><span>Hello</span></p>
        </div>
    </body>
    <script src="jquery-3.7.1.min.js"></script>
    <script>
        function sleep(s){
            ms = s*1000
            return new Promise((resolve)=>setTimeout(resolve, ms));
        }
        const{log}=console;
        async function a(){

        }
        a();
    </script>
    </html>""")