package kz.attractorschool.moviereviewrr.repository;

import kz.attractorschool.moviereviewrr.model.Movie;
import kz.attractorschool.moviereviewrr.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.DirectoryStream;
import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {
    public Iterable<Movie> findAll(Sort s);
    public Iterable<Movie> findAllByReleaseYearGreaterThanEqual(int year, Sort s);
    public Iterable<Movie> findAllByReleaseYearBetween(int year1, int year2, Sort s);

    @Query("{'releaseYear': {'$gte' : ?0, '$lte' : ?1}}")
    public Iterable<Movie> getMoviesBetween(int year1, int year2, Sort s);
    public Iterable<Movie> findByTitleContains(String title);

    @Query("{'title': {'$regex' : '?0', '$options' : 'i'}}")
       public Iterable<Movie> selectMovies(String title, Sort s);

    @Query("{'title': {'$regex' : '?0' , '$options' : 'm'}}")
    public Iterable<Movie> selectMovie(String title);

    @Query("{'directors': {'$regex' : '?0' , '$options' : 'm'}}")
    public Iterable<Movie> selectDirector(String director);

    @Query("{'actors': {'$regex' : '?0' , '$options' : 'i'}}")
    public Iterable<Movie> selectActor(String actor);

    @Query("{'rating':  ?0}")
    public Iterable<Movie> selectRatings(double ratings);

    public Movie findByTitle(String title);

    public Movie findByDirectors(String directors);
    Page<Movie>findAllBy(Pageable pageable);








}
