package app.repository;

import app.model.Word;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * Created by ka on 06/03/16.
 */
@Repository
public interface WordRepository extends PagingAndSortingRepository<Word, Long> {
    Word findByValue(String value);
}
