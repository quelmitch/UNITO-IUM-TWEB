package org.unito.postgreserver.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.utils.SpecificationUtil;

import java.util.List;

@Service
public class MovieService {
    // TODO: Business Logic, calculations, data transformation go here

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public List<Movie> getMovieByTitle(String title) {
        Specification<Movie> spec = Specification
                .where(SpecificationUtil.equals("title", title));
        return movieRepository.findAll(spec);
    }

//    // GT -> Greater Than; LT -> Less Than
//    public List<Movie> getFilteredMovies(Integer releaseYearGT, Integer releaseYearLT, Integer rating, Integer durationGT, Integer durationLT) {
//        Specification<Movie> spec = Specification.where(null);
//
//        if (releaseYearGT != null) {
//            spec = spec.and(MovieSpecification.hasReleaseYearGreaterThan(releaseYearGT));
//        }
//        if (releaseYearLT != null) {
//            spec = spec.and(MovieSpecification.hasReleaseYearLessThan(releaseYearLT));
//        }
//        if (durationGT != null) {
//            spec = spec.and(MovieSpecification.hasDurationGreaterThan(durationGT));
//        }
//        if (durationLT != null) {
//            spec = spec.and(MovieSpecification.hasDurationLessThan(durationLT));
//        }
//        if (rating != null) {
//            spec = spec.and(MovieSpecification.hasRating(rating));
//        }
//
//        return movieRepository.findAll(spec);
//    }
}
