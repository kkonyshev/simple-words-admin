package app.repository;

import app.model.Stock;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ka on 06/03/16.
 */
@Repository
public interface StockRepository extends PagingAndSortingRepository<Stock, String> {
}
