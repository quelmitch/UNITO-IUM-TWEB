const form = document.getElementById("filter-form")

form.addEventListener("submit", () => {
    Array.from(form.elements).forEach(control => {
        control.disabled = control.value === '';
    });
})


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
    const rangeStart = container.querySelector('.range-start');
    const rangeEnd = container.querySelector('.range-end');

    // Evento per aggiornare il conteggio quando cambia uno dei checkbox
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', () => updateCount(countElement, checkboxes));
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
        if (fieldSearchBar) fieldSearchBar.value = "";
        checkboxes.forEach(checkbox => {
            if (checkbox.parentElement.classList.contains('selected-chips-elements-wrapper')) {
                checkbox.parentElement.remove()
            }
            const label = checkbox.parentNode;
            label.style.display = "block";
        });

        if (rangeStart && rangeEnd) {
            rangeStart.value = "";
            rangeEnd.value = "";
        }
        updateCount(countElement, checkboxes);
    });

    // Evento per chiudere il dropdown quando si clicca al di fuori
    window.addEventListener('click', (event) => {
        if (!event.target.closest('.filter-container')) {
            dropdown.classList.remove('show');
        }
    });

    // Apply Button
    applyButton.addEventListener('click', () => {
        if (fieldSearchBar)
            addChipElementInContainer(fieldSearchBar, selectedChipElementsContainer)

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

    rangeStart?.addEventListener('change', () => {
        rangeEnd.min = parseInt(rangeStart.value, 10) + 1;
    })

    rangeEnd?.addEventListener('change', () => {
        rangeStart.max = parseInt(rangeEnd.value, 10) - 1;
    })

    if (!rangeStart && !rangeEnd)
        updateCount(countElement, checkboxes);
});

// Funzione per aggiornare il conteggio
function updateCount(countElement, checkboxes) {
    const count = Array.from(checkboxes).filter(checkbox => checkbox.checked).length;
    if (count) countElement.textContent = count;
    else countElement.style.display = "none";
}

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
        const chipContainer = document.createElement('div');
        chipContainer.className = 'selected-chips-elements-wrapper';
        chipContainer.textContent = searchTerm;

        const inputChip = document.createElement('input');
        inputChip.className = 'selected-chips-elements';
        inputChip.type = 'checkbox';
        inputChip.value = searchTerm;
        inputChip.name = 'actor';
        inputChip.checked = true;

        const closeButton = document.createElement('span');
        closeButton.className = 'close-button';
        closeButton.textContent = '×';

        chipContainer.appendChild(inputChip);
        chipContainer.appendChild(closeButton);
        selectedChipElementsContainer.appendChild(chipContainer);
        fieldSearchBar.value = "";
    } else {
        alert("Attore già aggiunto");
    }
}

document.querySelectorAll('.close-button').forEach(button => {
    button.addEventListener('click', (event) => {
        event.preventDefault();
        event.stopPropagation();
        button.parentNode.remove();
        const name = button.dataset.name
        updateCount(document.getElementById(name+"-count"), button.parentNode.querySelectorAll('input[type="checkbox"]'))
    });
})
