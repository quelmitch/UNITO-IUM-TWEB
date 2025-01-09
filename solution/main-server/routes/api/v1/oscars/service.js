function groupByAndReduce(data) {
    const { numberCeremony, yearCeremony } = data.content[0]; // Si assume che siano uguali per tutti i record.

    // Creare la struttura desiderata
    const result = {
        numberCeremony,
        yearCeremony,
        categories: {}
    };

    // Popolare le categorie
    data.content.forEach(nominee => {
        const { category, isWinner, nomineeName, nomineeMovie } = nominee;
        const nomineeData = { nomineeName, nomineeMovie };

        // Inizializzare la categoria se non esiste
        if (!result.categories[category])
            result.categories[category] = { winners: [], nominees: [] };

        // Aggiungere il nominato al giusto array
        if (isWinner)
            result.categories[category].winners.push(nomineeData);
        else
            result.categories[category].nominees.push(nomineeData);
    });

    data.content = result;
    return data;
}

module.exports = {
    groupByAndReduce
};