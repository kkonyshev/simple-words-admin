package app.repository;

import app.model.Literal;
import app.model.Stock;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ka on 06/03/16.
 */
@Repository
public interface LiteralRepository extends PagingAndSortingRepository<Literal, Long> {
    Literal findByValue(String value);
}
