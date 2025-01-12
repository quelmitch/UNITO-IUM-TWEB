/* Convert a filter object into URI */
function fromObjectToUri(filters) {
    const params = new URLSearchParams()

    for (const key in filters) {
        const value = filters[key]

        if (Array.isArray(value))
            value.forEach(item => params.append(key, item))
        else
            params.append(key, value)
    }

    return params.toString()
}

module.exports = {fromObjectToUri}
