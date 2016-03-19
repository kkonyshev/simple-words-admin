package app.service;

import app.model.Collocation;
import app.model.CollocationType;
import app.model.Word;
import app.model.WordType;
import app.repository.CollocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Created by ka on 06/03/16.
 */
@Service
@Transactional
public class CollocationService {

    @Autowired
    protected CollocationRepository collocationRepository;

    @Autowired
    protected WordService wordService;

    public Iterable<Collocation> findAll() {
        return collocationRepository.findAll();
    }

    public Collocation create(String value, CollocationType type) {
        Collocation collocation = new Collocation();
        collocation.type = type;
        collocation.value = value;
        Collocation c = collocationRepository.save(collocation);
        for (String s : value.split("\\s+")) {
            Word w = wordService.findByValue(s);
            if (w==null || !w.value.equalsIgnoreCase(s)) {
                w = wordService.create(s, WordType.UNDEF);
            }
            c = addWord(c, w);
        }
        return c;
    }

    public Collocation addWord(Collocation collocation, Word word) {
        collocation.words.add(word);
        return collocationRepository.save(collocation);
    }

    public Collocation clearWords(Collocation collocation) {
        collocation.words.clear();
        return collocationRepository.save(collocation);
    }

    public Collocation removeWord(Collocation collocation, Word word) {
        collocation.words.remove(word);
        return collocationRepository.save(collocation);
    }

    public Collocation view(Collocation collocation) {
        collocation.viewCount++;
        return collocationRepository.save(collocation);
    }
}
