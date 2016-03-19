package app.controller;

import app.model.Collocation;
import app.model.CollocationType;
import app.model.Word;
import app.repository.CollocationRepository;
import app.service.CollocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/collocations")
    public Iterable<Collocation> page() {
        Sort sort = new Sort(Sort.Direction.ASC, "value");
        return collocationRepository.findAll(sort);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/collocations")
    public Collocation create(@RequestBody Collocation collocation) {
        return collocationService.create(collocation.value, collocation.type);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/collocations/{id}")
    public Collocation update(@PathVariable("id") Long id, @RequestBody Collocation collocation) {
        //TODO fair update!
        collocationRepository.delete(id);
        return collocationService.create(collocation.value, collocation.type);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/collocations/{id}")
    public void update(@PathVariable("id") Long id) {
        collocationRepository.delete(collocationRepository.findOne(id));
    }
}
