package app.service;

import app.model.Collocation;
import app.model.CollocationType;
import app.model.Word;
import app.model.WordType;
import app.repository.CollocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by ka on 06/03/16.
 */
@Service
public class CollocationService {

    @Autowired
    protected CollocationRepository collocationRepository;

    public Iterable<Collocation> findAll() {
        return collocationRepository.findAll();
    }

    public Collocation create(String value, CollocationType type) {
        Collocation collocation = new Collocation();
        collocation.type = type;
        collocation.value = value;
        return collocationRepository.save(collocation);
    }
}
