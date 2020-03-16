package kz.attractorschool.moviereviewrr.controller;

import kz.attractorschool.moviereviewrr.model.Movie;
import kz.attractorschool.moviereviewrr.model.Review;
import kz.attractorschool.moviereviewrr.model.User;
import kz.attractorschool.moviereviewrr.repository.MovieRepository;
import kz.attractorschool.moviereviewrr.repository.ReviewRepository;
import kz.attractorschool.moviereviewrr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    MovieRepository mr ;
    @Autowired
    ReviewRepository rr;
    @Autowired
    UserRepository ur;

    @GetMapping("/movie")
    public Iterable<Movie> getMovie() {
        Sort s = Sort.by(Sort.Order.asc("title"));
        return mr.findAll(s);
    }
    @GetMapping("/movienew/{year}")
    public Iterable<Movie> getMovie(@PathVariable("year") int year) {
        Sort s = Sort.by(Sort.Order.asc("title"));
        return mr.findAllByReleaseYearGreaterThanEqual(year, s);
    }

    @GetMapping("/movienew/{year1}/{year2}")
    public Iterable<Movie> getMovie(@PathVariable("year1") int year1,
                                   @PathVariable("year2") int year2){
        Sort s = Sort.by(Sort.Order.asc("title"));
        return mr.findAllByReleaseYearBetween(year1, year2, s);
    }

    @GetMapping("/moviebetween/{year1}/{year2}")
    public Iterable<Movie> getMovieBetween(@PathVariable("year1") int year1,
                                           @PathVariable("year2") int year2){
        Sort s = Sort.by(Sort.Order.asc("title"));
        return mr.getMoviesBetween(year1, year2, s);
    }
    @GetMapping("/movietitle/{title}")
    public Iterable<Movie> getMovie(@PathVariable("title") String title){
        return mr.findByTitleContains(title);
    }

    @GetMapping("/movieselect/{title}")
    public Iterable<Movie> selectMovies(@PathVariable("title") String titleSelect){
        Sort s = Sort.by(Sort.Order.asc("title"));
        return mr.selectMovies(titleSelect, s);
    }

    @GetMapping("/movieselectn/{title}")
    public Iterable<Movie> selectMovie(@PathVariable("title") String titleSelect){
            return mr.selectMovie(titleSelect);
    }
    @GetMapping("/movieselectdirector/{directors}")
    public Iterable<Movie> selectDirector(@PathVariable("directors") String directors){
            return mr.selectDirector(directors);
    }
    @GetMapping("/movieselectactors/{actors}")
    public Iterable<Movie> selectActors(@PathVariable("actors") String actors){
        return mr.selectActor(actors);
    }
    @GetMapping("/movieselectrating/{rating}")
    public Iterable<Movie> selectRatings(@PathVariable("rating") double ratings){
        return mr.selectRatings(ratings);
    }

     @GetMapping("/rev/{title}")
     public List<Review> getReview(@PathVariable("title") String title) {
        var movie = mr.findByTitle(title);
    return rr.findByMovie_Id(movie.getId());
}

    @GetMapping("/reviewbydir/{directors}")
    public List<Review> getMovieD(@PathVariable("directors") String directors) {
        return rr.findByMovie_Directors(mr.findByDirectors(directors).getId());
    }
    @GetMapping("/moviepageable")
    public Page<Movie> getMoviePage() {
        Sort sortBy = Sort.by(Sort.Order.asc("title"),
                   Sort.Order.asc("rating"));
        int page = 1;
        int count = 10;
        Pageable pageable = PageRequest.of(page, count,sortBy);
        return mr.findAllBy(pageable);
    }


    @GetMapping("/users")
    public Iterable<User> getUsers() {
        Sort sort = Sort.by(Sort.Order.asc("name"));
        return ur.findAll(sort);
    }

    @GetMapping("/username/{name}")
    public User getUserName(@PathVariable("name") String name) {
        return ur.findByName(name);
    }
    @GetMapping("/email/{email}")
    public boolean isExist( String email) {
        return ur.existsByEmail(email);
    }

    }










