Routes:

***"a number of": need to specify the number of result (DEFAULT = 100???)
***"data": is the keyword to fetch all elaborated data (including visualization)

/movie
>> fetch a number of movies (with few info)
/movie/{movie_id}
>> fetch all info about a movie
/movie/{movie_id}/poster
>> fetch the movie poster link
/movie/{movie_id}/reviews
>> fetch a number of reviews from given movie (with few info)
/movie/{movie_id}/credits
>> fetch actors, crew and studios
/movie/{movie_id}/releases
>> fetch all release dates (and ratings???) from different countries

/movie/{movie_id}/data???
>> fetch elaborated data from given movie (related movies???, etc)


/oscar
>> fetch a number of oscar ceremony (with few info)
/oscar/{oscar_ceremony_number}
>> fetch all info about an oscar ceremony
/oscars/winners
>> fetch a number of oscar winners


/reviews
>> fetch a number of the latest reviews
/reviews/{review_id}
>> fetch all info about a review


/data
>> data visualizations facts