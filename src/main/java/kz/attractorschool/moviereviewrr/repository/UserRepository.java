package kz.attractorschool.moviereviewrr.repository;

import kz.attractorschool.moviereviewrr.model.Movie;
import kz.attractorschool.moviereviewrr.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByName(String name);
    public Iterable<User> findAll(Sort sort);
    boolean existsByEmail(String email);
}
