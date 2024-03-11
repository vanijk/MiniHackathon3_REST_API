package com.glab.minihackathon3_rest_api.repository;

import com.glab.minihackathon3_rest_api.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepositoryI  extends JpaRepository<Movie, Long> {
    @Query("FROM Movie  WHERE title like :title ")
    Movie getReferenceByTitle(String title);
}
