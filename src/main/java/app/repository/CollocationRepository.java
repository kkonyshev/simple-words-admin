package app.repository;

import app.model.Collocation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * Created by ka on 06/03/16.
 */
@Repository
public interface CollocationRepository extends PagingAndSortingRepository<Collocation, Long> {
    Collocation findByValue(String value);
}
