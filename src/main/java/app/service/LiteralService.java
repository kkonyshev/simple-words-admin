package app.service;

import app.model.Literal;
import app.model.LiteralType;
import app.repository.LiteralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ka on 06/03/16.
 */
@Service
public class LiteralService {

    @Autowired
    protected LiteralRepository literalRepository;

    public Iterable<Literal> findAll() {
        return literalRepository.findAll();
    }

    public Literal fineByValue(String value) {
        return literalRepository.findByValue(value);
    }

    public Literal create(String value, LiteralType type) {
        Literal literal = new Literal();
        literal.type = type;
        literal.value = value;
        return literalRepository.save(literal);
    }
}
