/* Group by the result by ceremony, category, winners and nominee */
function groupByAndReduce(arr) {
    const groupedByCeremony = {};

    for (const { numberCeremony, yearCeremony, category, isWinner, nomineeName, nomineeMovie } of arr) {
        // Init ceremonyGroup if not already existing
        const ceremonyGroup = (groupedByCeremony[`${numberCeremony}_${yearCeremony}`] ??= {
            numberCeremony,
            yearCeremony,
            categories: {}
        });

        // Initialize the category group if not already existing
        const categoryGroup = (ceremonyGroup.categories[category] ??= {
            winners: [],
            nominees: []
        });

        // Add nominee to the correct group
        const nomineeData = { nomineeName, nomineeMovie };
        if (isWinner)
            categoryGroup.winners.push(nomineeData);
        else
            categoryGroup.nominees.push(nomineeData);
    }

    // Transform the grouped object into an array of ceremonies
    return Object.values(groupedByCeremony);
}

module.exports = {
    groupByAndReduce
};