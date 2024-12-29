document.getElementById('itemsPerPage').addEventListener('change', function() {
    const selectedValue = this.value;
    const urlParams = new URLSearchParams(window.location.search);
    urlParams.set('limit', selectedValue);
    window.location.search = urlParams.toString();
});