// Query selector shorthand utility functions
const qs = (selector, scope = document) => scope.querySelector(selector);
const qsa = (selector, scope = document) => Array.from(scope.querySelectorAll(selector));

document.addEventListener('DOMContentLoaded', () => {
    qsa(`.filter-container`).forEach(initFilterContainer);

    qsa('.close-button').forEach(button => {
        manageChipCloseButton(button)
    })

    initForm();
});

function initForm() {
    const form = document.getElementById("filter-form")
    form.addEventListener("submit", () => {
        Array.from(form.elements).forEach(control => {
            control.disabled = control.value === '';
        });
    })
}

function initFilterContainer(container) {
    const chip = qs('.filter-chip', container);
    const dropdown = qs(`.filter-dropdown`, container);
    const countElement = qs('.count', chip);

    const clearButton = qs('.clear-all', container);
    const applyButton = qs('#apply-button', container);

    const checkboxes = qsa('input[type="checkbox"], input[type="radio"]', dropdown);
    const checkboxSearchBar = qs('.checkbox-search-bar', container);

    const fieldSearchBar = qs('.field-search-bar', container);
    const selectedChipElementsContainer = qs('.selected-chips-elements-container', container);

    const rangeStart = qs('.range-start', container);
    const rangeEnd = qs('.range-end', container);

    toggleFilterDropdownVisibility(chip, dropdown);

    if (rangeStart && rangeEnd)
        handleRangeFilter(rangeStart, rangeEnd);

    if (fieldSearchBar)
        handleSearchFilter(fieldSearchBar, selectedChipElementsContainer);

    if (checkboxes)
        handleCheckboxFilter(checkboxes, checkboxSearchBar, countElement)

    // Handle clearing selected options for each type of filter
    clearButton.addEventListener('click', (event) => {
        event.preventDefault();

        checkboxes.forEach(checkbox => checkbox.checked = false);

        if (checkboxSearchBar) {
            checkboxSearchBar.value = "";

            // Clean checkbox research
            checkboxes.forEach(checkbox => {
                checkbox.parentNode.style.display = "block";
            });
        }

        if (fieldSearchBar) {
            fieldSearchBar.value = "";
            selectedChipElementsContainer.innerHTML = ""
        }

        if (rangeStart && rangeEnd) rangeStart.value = rangeEnd.value = "";

        updateCount(countElement, checkboxes);
    });

    // Apply filters button
    applyButton.addEventListener('click', () => {
        dropdown.classList.remove('show');

        if (fieldSearchBar)
            addChipInSearchFilter(fieldSearchBar, selectedChipElementsContainer)
    });

    if (!rangeStart && !rangeEnd)
        updateCount(countElement, checkboxes);
}


/* Generic Filter Functions */
// Toggle the filter dropdown visibility
function toggleFilterDropdownVisibility(chip, dropdown) {
    chip.addEventListener('click', () => {
        qsa('.filter-dropdown').forEach(otherDropdown => {
            if (otherDropdown !== dropdown)
                otherDropdown.classList.remove('show');
        });
        dropdown.classList.toggle('show');
    });

    // Hide the dropdown if the click is outside the filter container
    window.addEventListener('click', (event) => {
        if (!event.target.closest('.filter-container'))
            dropdown.classList.remove('show');
    });
}

// Update the counter of the filter
function updateCount(countElement, checkboxes) {
    const count = Array.from(checkboxes).filter(checkbox => checkbox.checked).length;
    if (count > 0) {
        countElement.textContent = count;
        countElement.style.display = "block";
    } else {
        countElement.style.display = "none";
    }
}


/* Filter Range */
function handleRangeFilter(rangeStart, rangeEnd) {
    rangeStart.addEventListener('change', () => {
        rangeEnd.min = parseFloat(rangeStart.value) + parseFloat(rangeStart.step);
    })

    rangeEnd.addEventListener('change', () => {
        rangeStart.max = parseFloat(rangeEnd.value) - parseFloat(rangeEnd.step);
    })
}


/* Filter Checkbox */
function handleCheckboxFilter(checkboxes, checkboxSearchBar, counterLabel) {
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', () => updateCount(counterLabel, checkboxes));
    });

    // Search for labels in a checkbox filter
    checkboxSearchBar?.addEventListener('input', () => {
        const searchTerm = checkboxSearchBar.value.toLowerCase();
        checkboxes.forEach(checkbox => {
            const label = checkbox.parentNode;
            const value = checkbox.value.toLowerCase();
            label.style.display = value.includes(searchTerm) ? "block" : "none";
        });
    });

    checkboxSearchBar?.addEventListener('keydown', (event) => {
        if (event.key === 'Enter') event.preventDefault();
    });
}


/* Filter Search */
function handleSearchFilter(searchBar, selectedChipElementsContainer) {
    searchBar.addEventListener('keydown', (event) => {
        if (event.key === 'Enter') {
            event.preventDefault();
            addChipInSearchFilter(searchBar, selectedChipElementsContainer)
        }
    });
}

function addChipInSearchFilter(searchBar, chipsContainer) {
    const searchTerm = searchBar.value.trim();

    if (!searchTerm)
        return

    let alreadyAdded = false;
    const existingChips = qsa('.selected-chips-elements', chipsContainer);
    existingChips.forEach(existingChip => {
        if (existingChip.value.trim().toLowerCase() === searchTerm.toLowerCase())
            alreadyAdded = true;
    });

    if (alreadyAdded)
        return;

    const chipContainer = document.createElement('div');
    chipContainer.className = 'selected-chips-elements-wrapper';
    chipContainer.textContent = searchTerm;

    const inputChip = document.createElement('input');
    inputChip.className = 'selected-chips-elements';
    inputChip.type = 'checkbox';
    inputChip.value = searchTerm;
    inputChip.name = chipsContainer.dataset.name;
    inputChip.checked = true;

    const closeButton = document.createElement('span');
    closeButton.className = 'close-button';
    closeButton.textContent = '×';
    manageChipCloseButton(closeButton)

    chipContainer.appendChild(inputChip);
    chipContainer.appendChild(closeButton);
    chipsContainer.appendChild(chipContainer);
    searchBar.value = "";
}

function manageChipCloseButton(button) {
    button.addEventListener('click', (event) => {
        event.preventDefault();
        event.stopPropagation();
        button.parentNode.remove();
        const name = button.dataset.name
        updateCount(document.getElementById(name + "-count"), qsa('input[type="checkbox"]', button.parentNode))
    });
}
