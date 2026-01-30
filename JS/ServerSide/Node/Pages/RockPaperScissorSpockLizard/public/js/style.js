function computePentagonLayout(d, g) {
  const sin36 = Math.sin(Math.PI / 5);
  const r = d / 2;
  const s = d + g;
  const R = s / (2 * sin36);
  const S = 2 * R + d;
  const anglesDeg = [-90, -18, 54, 126, 198];
  return {
    squareSize: S,
    circles: anglesDeg.map(a => {
      const t = a * Math.PI / 180;
      return {
        left: S / 2 + R * Math.cos(t) - r,
        top:  S / 2 + R * Math.sin(t) - r
      };
    })
  };
}

function toPixels(val) {
  const d = document.createElement("div");
  d.style.width = val;
  d.style.position = "absolute";
  d.style.visibility = "hidden";
  document.body.appendChild(d);
  const px = d.offsetWidth;
  d.remove();
  return px;
}

const root = document.getElementById("rpsls-ui");
const container = root.querySelector("#options");
const options = root.querySelectorAll(".option");

function applyLayout() {
  const style = getComputedStyle(root);
  const d = toPixels(style.getPropertyValue("--choice-size"));
  const g = toPixels(style.getPropertyValue("--choice-gap"));
  const { squareSize, circles } = computePentagonLayout(d, g);
  container.style.width = squareSize + "px";
  container.style.height = squareSize + "px";
  options.forEach((el, i) => {
    el.style.width = d + "px";
    el.style.height = d + "px";
    el.style.left = circles[i].left + "px";
    el.style.top  = circles[i].top  + "px";
  });
}

applyLayout();
window.addEventListener("resize", applyLayout);