document.querySelectorAll('.filter-container').forEach(container => {
    const chip = container.querySelector('.filter-chip');
    const dropdown = container.querySelector('.filter-dropdown');
    const clearAllButton = container.querySelector('.clear-all');
    const applyButton = container.querySelector('#apply-button');
    const checkboxes = dropdown.querySelectorAll('input[type="checkbox"]');
    const countElement = chip.querySelector('.count');
    const checkboxSearchBar = container.querySelector('.checkbox-search-bar');
    const fieldSearchBar = container.querySelector('.field-search-bar');
    const selectedChipElementsContainer = container.querySelector('.selected-chips-elements-container');

    // Funzione per aggiornare il conteggio
    function updateCount() {
        countElement.textContent = Array.from(checkboxes).filter(checkbox => checkbox.checked).length;
    }

    // Evento per aggiornare il conteggio quando cambia uno dei checkbox
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', updateCount);
    });

    // Mostra/nasconde il dropdown quando si clicca sul chip
    chip.addEventListener('click', () => {
        document.querySelectorAll('.filter-dropdown').forEach(otherDropdown => {
            if (otherDropdown !== dropdown) {
                otherDropdown.classList.remove('show');
            }
        });
        dropdown.classList.toggle('show');
    });

    // Rimuove tutte le selezioni quando si clicca su "Clear All"
    clearAllButton.addEventListener('click', (event) => {
        event.preventDefault();
        checkboxes.forEach(checkbox => {
            checkbox.checked = false;
        });
        if (checkboxSearchBar) checkboxSearchBar.value = "";
        checkboxes.forEach(checkbox => {
            const label = checkbox.parentNode;
            label.style.display = "block";
        });
        updateCount();
    });

    // Evento per chiudere il dropdown quando si clicca al di fuori
    window.addEventListener('click', (event) => {
        if (!event.target.closest('.filter-container')) {
            dropdown.classList.remove('show');
        }
    });

    // Apply Button
    applyButton.addEventListener('click', () => {
        if(checkboxes) {
            const activeFilters = Array.from(checkboxes)
                .filter(checkbox => checkbox.checked)
                .map(checkbox => checkbox.value);
            console.log(`Filters applied for ${chip.textContent.trim()}:`, activeFilters);
        }
        else if (fieldSearchBar) {
            addChipElementInContainer(fieldSearchBar, selectedChipElementsContainer)
        }
        dropdown.classList.remove('show');
    });

    checkboxSearchBar?.addEventListener('input', () => {
        const searchTerm = checkboxSearchBar.value.toLowerCase();
        checkboxes.forEach(checkbox => {
            const label = checkbox.parentNode;
            const languageName = checkbox.value.toLowerCase();
            label.style.display = languageName.includes(searchTerm) ? "block" : "none";
        });
    });

    checkboxSearchBar?.addEventListener('keydown', (event) => {
        if (event.key === 'Enter') {
            event.preventDefault();
        }
    });

    fieldSearchBar?.addEventListener('keydown', (event) => {
        if (event.key === 'Enter') {
            event.preventDefault();
            addChipElementInContainer(fieldSearchBar, selectedChipElementsContainer)
        }
    });

    updateCount();
});


function addChipElementInContainer(fieldSearchBar, selectedChipElementsContainer) {
    const searchTerm = fieldSearchBar.value.trim();
    let alreadyAdded = false;
    const existingChips = selectedChipElementsContainer.querySelectorAll('.selected-chips-elements');
    existingChips.forEach(existingChip => {
        if(existingChip.textContent.slice(0,-1).trim().toLowerCase() === searchTerm.toLowerCase()){
            alreadyAdded = true;
        }
    });

    if(!alreadyAdded){
        const newChip = document.createElement('div');
        newChip.className = 'selected-chips-elements';
        newChip.textContent = searchTerm;

        const closeButton = document.createElement('span');
        closeButton.className = 'close-button';
        closeButton.textContent = '×';
        closeButton.addEventListener('click', (event) => {
            event.preventDefault()
            event.stopPropagation()
            newChip.remove();
        });
        newChip.appendChild(closeButton);
        selectedChipElementsContainer.appendChild(newChip);
        fieldSearchBar.value = "";
    } else {
        alert("Attore già aggiunto");
    }
}