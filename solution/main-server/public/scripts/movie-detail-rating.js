const rating = parseFloat(document.getElementById('rating-value').textContent);

const numStars = 5;
const starContainer = document.getElementById('star-container');

// Generate stars based on rating
for (let i = 0; i < numStars; i++) {
    const svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    svg.setAttribute("viewBox", "0 0 24 24");
    svg.setAttribute("xmlns", "http://www.w3.org/2000/svg");

    const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
    path.setAttribute("d", "M12 2l2.39 7.26h7.61l-6.16 4.49 2.39 7.26-6.16-4.49-6.16 4.49 2.39-7.26-6.16-4.49h7.61z");
    svg.appendChild(path);

    const decimalPart = rating - Math.floor(rating);
    const clipPercentage = decimalPart * 100;

    if (rating >= i + 1) {
        svg.classList.add('filled');
    } else if (rating > i) {
        svg.classList.add('half');
        svg.style.clipPath = `inset(0 ${100 - clipPercentage}% 0 0)`;
    } else {
        svg.style.clipPath = `inset(0 100% 0 0)`;
        svg.classList.add('star');
    }

    starContainer.appendChild(svg);
}
