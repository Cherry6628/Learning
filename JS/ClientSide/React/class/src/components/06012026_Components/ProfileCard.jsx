import "./ProfileCard.css";

function ProfileCard(props) {

    return (
        <div class="profile-card">
    <div class="image-container">
        <img
            src={props.url}
            id="img"
        />
        <h3 id="name">{props.name}</h3>
        {/* <button id="hide-info">Hide Info</button> */}
    </div>
    <div class="info">
        <div>
            <div>
                <p>Age: <span id="age">{props.age}</span></p>
            </div>
            <div>
                <p>Email: <span id="email">{props.email}</span></p>
            </div>
            <div>
                <p>Phone: <span id="phone">{props.phone}</span></p>
            </div>
        </div>
    </div>
</div>
    );
}

export default ProfileCard;