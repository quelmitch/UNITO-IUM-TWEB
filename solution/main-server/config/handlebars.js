const navLinks = [
    { path: '/', label: 'Home' },
    { path: '/movies', label: 'Movies' },
    { path: '/oscars', label: 'Oscars' },
    { path: '/reviews', label: 'Reviews' },
]

const helpers = {
    eq: (a, b) => a === b,
    sum: (a, b) => a + b,
    sub: (a, b) => a - b,
    startsWith: (a, b) => a.startsWith(b),
    contains: (array, value) => (array === value) || (Array.isArray(array) && array.includes(value)),
}

module.exports = {
    navLinks,
    helpers
}