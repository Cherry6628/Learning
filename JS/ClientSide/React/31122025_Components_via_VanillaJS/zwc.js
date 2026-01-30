const { log } = console;
log("Hello");

class ProfileCard extends HTMLElement {
    static #template = document.createElement("template");
    
    static {
        ProfileCard.#template.innerHTML=`
<style>
    .profile-card,
    .profile-card * {
        margin: 0;
        padding: 0;
        border: 0;
        box-sizing: border-box;
    }
    .profile-card {
        display: flex;
        padding: 20px;
    }
    .profile-card img {
        /* height:100%;
        width:100%; */
        height: min(200px, 40vw);
        /* width: attr(height); */
    }
    .profile-card .image-container * {
        margin: auto !important;
        /* display: inline-block; */
        width: fit-content;
    }
    .profile-card .image-container {
        display: flex;
        flex-direction: column;
        justify-content: center;
        gap: 10px;
    }
    .profile-card .image-container button {
        padding: 10px;
        border-radius: 15px;
        width: 100px !important;
    }
    .profile-card .info {
        padding: 20px;
    }
    .profile-card .info > div{
        height:100%;
        width:100%;
        display: flex;
        flex-direction: column;
        justify-content: space-evenly;
        padding: 20px;
    }
    .profile-card {}
</style>
<div class="profile-card">
    <div class="image-container">
        <img
            src=""
            alt=""
            id="img"
        />
        <h3 id="name">John Doe</h3>
        <button id="hide-info">Hide Info</button>
    </div>
    <div class="info">
        <div>
            <div>
                <p>Age: <span id="age"></span></p>
            </div>
            <div>
                <p>Email: <span id="email"></span></p>
            </div>
            <div>
                <p>Phone: <span id="phone"></span></p>
            </div>
        </div>
    </div>
</div>
        `;
    }
    #visible=true;
    #name;
    #age;
    #phone;
    #email;
    constructor() {
        super();
        this.attachShadow({mode:"open"});
        
        
        this.shadowRoot.appendChild(ProfileCard.#template.content.cloneNode(true));

        this.#age=this.getAttribute("age");
        this.#name=this.getAttribute("name");
        this.#phone=this.getAttribute("phone");
        this.#email=this.getAttribute("email");
        

        this.shadowRoot.querySelector("#name").innerText=this.#name;
        this.shadowRoot.querySelector("#age").innerText=this.#age;
        this.shadowRoot.querySelector("#email").innerText=this.#email;
        this.shadowRoot.querySelector("#phone").innerText=this.#phone;
        this.shadowRoot.querySelector("#img").setAttribute("src", this.getAttribute("image"));
        this.shadowRoot.querySelector("#hide-info").addEventListener('click',()=>{
            this.#visible=!this.#visible;
            if(this.#visible){
                this.shadowRoot.querySelector("#hide-info").innerText="Hide Info";
                this.shadowRoot.querySelector("#name").innerText=this.#name;
                this.shadowRoot.querySelector("#age").innerText=this.#age;
                this.shadowRoot.querySelector("#email").innerText=this.#email;
                this.shadowRoot.querySelector("#phone").innerText=this.#phone;
            }
            else{
                this.shadowRoot.querySelector("#hide-info").innerText="Show Info";
                this.shadowRoot.querySelector("#name").innerText="*".repeat(this.#name.length);
                this.shadowRoot.querySelector("#age").innerText="*".repeat(this.#age.length);
                this.shadowRoot.querySelector("#email").innerText="*".repeat(this.#email.length);
                this.shadowRoot.querySelector("#phone").innerText="*".repeat(this.#phone.length);
            }
            
        })
        
        

    }
    connectedCallback() {
        console.log("inserted");
    }
    disconnectedCallback() {
        console.log("removed");
    }
    attributeChangedCallback() {
        console.log("hadfadfs");
    }
}
// Element.prototype.attachShadow = null;
customElements.define("profile-card",ProfileCard);

