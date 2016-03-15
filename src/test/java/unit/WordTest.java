package unit;

import app.WordsAdmin;
import app.model.Word;
import app.model.WordType;
import app.repository.WordRepository;
import app.service.WordService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * Created by ka on 14/03/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(WordsAdmin.class)
public class WordTest {

    @Autowired
    protected WordService wordService;

    @Autowired
    protected WordRepository wordRepository;

    @Before
    public void clearRepos() {
        wordRepository.deleteAll();
    }

    @Test
    public void createAndFindNewWordNoun() {
        String word = "table";

        Word createdWord = wordService.create(word, WordType.NOUN);
        Word foundWord = wordService.findByValue(word);

        Assert.assertEquals(word, createdWord.value);
        Assert.assertEquals(word, foundWord.value);

        Assert.assertEquals(WordType.NOUN, createdWord.type);
        Assert.assertEquals(WordType.NOUN, foundWord.type);
    }

    @Test
    public void createAndFindNewWordAdj() {
        String word = "quick";

        Word createdWord = wordService.create(word, WordType.ADJECTIVE);
        Word foundWord = wordService.findByValue(word);

        Assert.assertEquals(word, createdWord.value);
        Assert.assertEquals(word, foundWord.value);

        Assert.assertEquals(WordType.ADJECTIVE, createdWord.type);
        Assert.assertEquals(WordType.ADJECTIVE, foundWord.type);
    }

    @Test
    public void createAndFindNewWordVerb() {
        String word = "lead";

        Word createdWord = wordService.create(word, WordType.VERB);
        Word foundWord = wordService.findByValue(word);

        Assert.assertEquals(word, createdWord.value);
        Assert.assertEquals(word, foundWord.value);

        Assert.assertEquals(WordType.VERB, createdWord.type);
        Assert.assertEquals(WordType.VERB, foundWord.type);
    }

    @Test
    public void checkAddWord() {
        Iterable<Word> resListBefore = wordService.findAll();
        long sizeBefore = resListBefore.spliterator().getExactSizeIfKnown();
        wordService.create("chair", WordType.NOUN);

        Iterable<Word> resListAfter = wordService.findAll();
        long sizeAfter = resListAfter.spliterator().getExactSizeIfKnown();
        Assert.assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void createWordCount() {
        String wordValue = "chair";
        wordService.create(wordValue, WordType.NOUN);
        Word foundWord = wordService.findByValue(wordValue);
        Assert.assertEquals((long)0, (long)wordService.findByValue(wordValue).viewCount);

        wordService.view(foundWord);
        Assert.assertEquals((long)1, (long)wordService.findByValue(wordValue).viewCount);

        wordService.view(wordValue);
        Assert.assertEquals((long)2, (long)wordService.findByValue(wordValue).viewCount);
    }
}
