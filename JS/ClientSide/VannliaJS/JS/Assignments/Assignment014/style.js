function computePentagonLayout(d, g) {
  const sin36 = Math.sin(Math.PI / 5);
  const r = d / 2;
  const s = d + g;
  const R = s / (2 * sin36);
  const S = 2 * R + d;

  const anglesDeg = [-90, -18, 54, 126, 198];

  const circles = anglesDeg.map(thetaDeg => {
    const theta = thetaDeg * Math.PI / 180;
    const cx = R * Math.cos(theta);
    const cy = R * Math.sin(theta);
    const left = (S / 2) + cx - r;
    const top  = (S / 2) + cy - r;
    return { left, top };
  });

  return { squareSize: S, circles };
}
function toPixels(val) {
  const temp = document.createElement("div");
  temp.style.position = "absolute";
  temp.style.visibility = "hidden";
  temp.style.width = val;
  document.body.appendChild(temp);
  const pixels = temp.offsetWidth;
  temp.remove();
  return pixels;
}

const container = document.getElementById("options");
const options = document.getElementsByClassName("opt");
let firstCircle = document.querySelector(".option");
function getStyles(){
    let dRaw=getComputedStyle(firstCircle).getPropertyValue("--choice-size").trim();
    let gRaw=getComputedStyle(firstCircle).getPropertyValue("--choice-gap").trim();

    let d = toPixels(dRaw);
    let g = toPixels(gRaw);

    const { squareSize, circles } = computePentagonLayout(d, g);

    container.style.width = Math.ceil(squareSize) + "px";
    container.style.height = Math.ceil(squareSize) + "px";
    for (let ele of options){
      ele.style.height = d+"px";
      ele.style.width = d+"px";
    }

    document.querySelectorAll(".option").forEach((el, i) => {
    el.style.left = Math.round(circles[i].left) + "px";
    el.style.top  = Math.round(circles[i].top)  + "px";
    el.style.width = d + "px";
    el.style.height = d + "px";
    el.style.borderRadius = "50%";
    });
}
getStyles();
window.addEventListener('resize', getStyles)