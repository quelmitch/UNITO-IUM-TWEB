const express = require('express');
const router = express.Router();

router.get('/', (req, res) => {
    // placeholder data
    const reviewsData = [
        {
            movie_title: "Oppenheimer",
            critic_name: "Mario Rossi",
            is_top_critic: true,
            publisher_name: "CineBlog",
            type: "Online",
            score: "9.5/10",
            date: "2023-07-20",
            content: "Un'opera monumentale che esplora la complessità di Oppenheimer e le conseguenze della creazione della bomba atomica. Nolan al suo meglio."
        },
        {
            movie_title: "Barbie",
            critic_name: "Luisa Verdi",
            is_top_critic: false,
            publisher_name: "Film.it",
            type: "Web",
            score: "8/10",
            date: "2023-07-21",
            content: "Divertente e sorprendentemente profondo, Barbie offre una satira intelligente sulla società e i ruoli di genere. Ottime interpretazioni di Robbie e Gosling."
        },
        {
            movie_title: "Mission: Impossible - Dead Reckoning Part One",
            critic_name: "Giovanni Bianchi",
            is_top_critic: true,
            publisher_name: "Everyeye Cinema",
            type: "Online",
            score: "9/10",
            date: "2023-07-12",
            content: "Spettacolare e adrenalinico, l'ultimo Mission: Impossible non delude le aspettative. Cruise si conferma una garanzia nel genere action."
        },
        {
            movie_title: "Spider-Man: Across the Spider-Verse",
            critic_name: "Anna Neri",
            is_top_critic: false,
            publisher_name: "IGN Italia",
            type: "Web",
            score: "8.8/10",
            date: "2023-06-01",
            content: "Un capolavoro visivo e narrativo che espande il multiverso di Spider-Man in modi sorprendenti. Animazione innovativa e storia coinvolgente."
        },
        {
            movie_title: "Guardiani della Galassia Vol. 3",
            critic_name: "Paolo Gialli",
            is_top_critic: false,
            publisher_name: "BadTaste.it",
            type: "Online",
            score: "7.5/10",
            date: "2023-05-03",
            content: "Un addio emozionante ai Guardiani che conclude la trilogia in modo soddisfacente, anche se con qualche calo di ritmo nella parte centrale."
        }
    ];

    res.render('pages/reviews', { title: 'Reviews', reviews: reviewsData });
});

module.exports = router;