const ceremonyDropdown = document.getElementById('ceremony-dropdown');
const categoriesContainer = document.getElementById('categories-container');

// Add event listener for ceremony dropdown change
ceremonyDropdown.addEventListener('change', function(event) {
    const selectedCeremony = event.target.value;
    window.location.href = `http://localhost:3000/oscars?numberCeremony=${selectedCeremony}`;
});