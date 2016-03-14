package app.controller;

import app.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by ka on 06/03/16.
 */
@RestController
public class WordController {

    @Autowired
    protected WordRepository wordRepository;


}
