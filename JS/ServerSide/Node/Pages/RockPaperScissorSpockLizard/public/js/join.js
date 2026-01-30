document.getElementById("joinBtn").onclick = () => {
  const code = document.getElementById("room").value.trim();
  if (!code) return;
  window.location.href = `/room/${code}`;
};