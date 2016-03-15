package app.controller;

import app.model.Word;
import app.model.WordType;
import app.repository.WordRepository;
import app.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by ka on 06/03/16.
 */
@RestController
public class WordController {

    @Autowired
    protected WordRepository wordRepository;

    @Autowired
    protected WordService wordService;

    @RequestMapping(value = "/word/type")
    public WordType[] wordTypeList() {
        return WordType.values();
    }

    @RequestMapping(value = "/word/page/{page}")
    public Page<Word> page(@PathVariable("page") Integer page) {
        if (page==null || page<1) {
            page=1;
        }
        Pageable req = new PageRequest(page-1, 20, Sort.Direction.ASC, "value");
        return wordRepository.findAll(req);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/word/{type}/{value}")
    public Word page(@PathVariable("type") String type, @PathVariable("value") String value) {
        return wordService.create(value, WordType.valueOf(type));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/word/{value}")
    public void page(@PathVariable("value") String value) {
        wordRepository.delete(wordService.findByValue(value));
    }
}
