function groupByCeremonyAndCategory (data) {
    return data.content.reduce((acc, nominee) => {
        const { numberCeremony, category } = nominee;

        // Initialize the group for the ceremony if it doesn't exist
        if (!acc[numberCeremony]) {
            acc[numberCeremony] = {};
        }

        // Initialize the group for the category within the ceremony if it doesn't exist
        if (!acc[numberCeremony][category]) {
            acc[numberCeremony][category] = [];
        }

        // Add the nominee to the appropriate category within the ceremony
        acc[numberCeremony][category].push(nominee);

        return acc;
    }, {});
}

module.exports = {
    groupByCeremonyAndCategory
};