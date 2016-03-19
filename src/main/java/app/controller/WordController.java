package app.controller;

import app.model.Collocation;
import app.model.Word;
import app.model.WordType;
import app.repository.CollocationRepository;
import app.repository.WordRepository;
import app.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * Created by ka on 06/03/16.
 */
@RestController
public class WordController {

    @Autowired
    protected WordRepository wordRepository;

    @Autowired
    protected CollocationRepository collocationRepository;

    @Autowired
    protected WordService wordService;

    @RequestMapping(value = "/word/type")
    public WordType[] wordTypeList() {
        return WordType.values();
    }

    @RequestMapping(value = "/words")
    public Iterable<Word> list() {
        Sort sort = new Sort(Sort.Direction.ASC, "value");
        return wordRepository.findAll(sort);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/words")
    public Word add(@RequestBody Word word) {
        return wordService.create(word.value, word.type);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/words/{id}")
    public Word update(@PathVariable("id") Long id, @RequestBody Word word) {
        Word localWord = wordRepository.findOne(id);
        localWord.value = word.value;
        localWord.type = word.type;
        return wordRepository.save(localWord);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/words/{id}")
    public void delete(@PathVariable("id") Long id) {
        Word word = wordRepository.findOne(id);
        List<Collocation> relatedCollocationList = collocationRepository.findByWords(word);
        for (Collocation c: relatedCollocationList) {
            c.words.remove(word);
            collocationRepository.save(c);
        }
        wordRepository.delete(id);
    }
}
