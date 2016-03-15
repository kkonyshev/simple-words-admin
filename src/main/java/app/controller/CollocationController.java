package app.controller;

import app.model.Collocation;
import app.model.CollocationType;
import app.model.Word;
import app.model.WordType;
import app.repository.CollocationRepository;
import app.repository.WordRepository;
import app.service.CollocationService;
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
public class CollocationController {

    @Autowired
    protected CollocationRepository collocationRepository;

    @Autowired
    protected CollocationService collocationService;

    @RequestMapping(value = "/collocation/type")
    public CollocationType[] collocationTypeList() {
        return CollocationType.values();
    }

    @RequestMapping(value = "/collocation/page/{page}")
    public Page<Collocation> page(@PathVariable("page") Integer page) {
        if (page==null || page<1) {
            page=1;
        }
        Pageable req = new PageRequest(page-1, 20, Sort.Direction.ASC, "value");
        return collocationRepository.findAll(req);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/collocation/{type}/{value}")
    public Collocation page(@PathVariable("type") String type, @PathVariable("value") String value) {
        return collocationService.create(value, CollocationType.valueOf(type));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/collocation/{value}")
    public void page(@PathVariable("value") String value) {
        collocationRepository.delete(collocationRepository.findByValue(value));
    }
}
