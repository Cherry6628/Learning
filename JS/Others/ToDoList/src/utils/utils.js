export default {
    dim: function setAlphaToHex(string, alpha) {
        string = string.slice(1);
        return `rgba(${
            Number.parseInt(string.slice(0, 2),16)},${
            Number.parseInt(string.slice(2, 4),16)},${
            Number.parseInt(string.slice(4, 6),16)},${
            alpha
        })`;
    },

};
