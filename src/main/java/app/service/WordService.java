package app.service;

import app.model.Word;
import app.model.WordType;
import app.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by ka on 06/03/16.
 */
@Service
public class WordService {

    @Autowired
    protected WordRepository wordRepository;

    public Iterable<Word> findAll() {
        return wordRepository.findAll();
    }

    public Word findByValue(String value) {
        return wordRepository.findByValue(value);
    }

    public Word create(String value, WordType type) {
        Word word = new Word();
        word.type = type;
        word.value = value;
        return wordRepository.save(word);
    }
}
