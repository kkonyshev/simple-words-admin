package app.repository;

import app.model.Collocation;
import app.model.Word;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * Created by ka on 06/03/16.
 */
@Repository
public interface CollocationRepository extends PagingAndSortingRepository<Collocation, Long> {
    Collocation findByValue(String value);
    List<Collocation> findByWords(Word words);
}
