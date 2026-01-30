document.addEventListener("DOMContentLoaded", () => {
    if (typeof data === "undefined") {
        console.error("data.js not loaded. Cannot initialize application.");
        return;
    }

    const planetNav = document.getElementById("planet-nav");
    const tabButtons = document.querySelectorAll(".tab-button");

    const planetNameEl = document.getElementById("planet-name");
    const planetDescEl = document.getElementById("planet-description");
    const mainPlanetImgEl = document.getElementById("main-planet-img");
    const geologyImgEl = document.getElementById("geology-img");

    const statRotationEl = document.getElementById("stat-rotation");
    const statRevolutionEl = document.getElementById("stat-revolution");
    const statRadiusEl = document.getElementById("stat-radius");
    const statTempEl = document.getElementById("stat-temp");

    let currentPlanet = "mercury";
    let currentView = "overview";

    const formatStat = (stat) => {
        if (!stat) return "";
        if (stat.unit === "y") {
            return stat.n.toFixed(2) + " YEARS";
        }
        if (stat.unit === "d") {
            return stat.n % 1 === 0
                ? `${stat.n} DAYS`
                : `${stat.n.toFixed(2)} DAYS`;
        }
        if (stat.unit === "h") {
            return stat.n.toFixed(2) + " HOURS";
        }
        return stat.n.toLocaleString();
    };

    const updateContent = () => {
        const planetData = data[currentPlanet];

        const themeColor = getComputedStyle(document.documentElement).getPropertyValue(`--color-${currentPlanet}`);

        tabButtons.forEach((btn) => {
            btn.style.backgroundColor = "transparent";
            btn.style.borderColor = themeColor;
            btn.classList.remove("active-tab");

            if (btn.dataset.view === currentView) {
                btn.classList.add("active-tab");
                btn.style.backgroundColor = themeColor;
                btn.style.borderColor = themeColor;
            }
        });

        planetNameEl.textContent = currentPlanet.toUpperCase();

        mainPlanetImgEl.style.display = "block";
        geologyImgEl.style.display = "none";

        let mainImgSrc = "";
        if (currentView === "overview" || currentView === "geology") {
            mainImgSrc = `img/planet-${currentPlanet}.svg`;
        } else if (currentView === "internal") {
            mainImgSrc = `img/planet-${currentPlanet}-internal.svg`;
        }
        mainPlanetImgEl.src = mainImgSrc;
        mainPlanetImgEl.alt = `${currentPlanet} ${currentView} image`;

        if (currentView === "geology") {
            geologyImgEl.src = `img/geology-${currentPlanet}.png`;
            geologyImgEl.style.display = "block";
        }

        // planetDescEl.textContent = planetData.about;
        planetDescEl.innerHTML = planetData[currentView];

        statRotationEl.textContent = formatStat(planetData.rotationTime);
        statRevolutionEl.textContent = formatStat(planetData.revolutionTime);
        statRadiusEl.textContent = `${planetData.radius.toLocaleString()} KM`;
        statTempEl.textContent = `${planetData.temperature.toLocaleString()}°C`;
    };

    planetNav.addEventListener("click", (e) => {
        const target = e.target.closest("h4");
        if (!target) return;

        document
            .querySelector(".active-planet")
            .classList.remove("active-planet");
        target.classList.add("active-planet");

        currentPlanet = target.dataset.planet;
        currentView = "overview";

        updateContent();
    });

    document.getElementById("tabs").addEventListener("click", (e) => {
        const target = e.target.closest(".tab-button");
        if (!target) return;

        currentView = target.dataset.view;

        updateContent();
    });

    updateContent();
});
