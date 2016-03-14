import app.WordsAdmin;
import app.model.Word;
import app.model.WordType;
import app.service.WordService;
import org.junit.Assert;
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
    protected WordService literalService;

    @Test
    public void createAndFindNewWordNoun() {
        String word = "table";

        Word createdWord = literalService.create(word, WordType.NOUN);
        Word foundWord = literalService.fineByValue(word);

        Assert.assertEquals(word, createdWord.value);
        Assert.assertEquals(word, foundWord.value);

        Assert.assertEquals(WordType.NOUN, createdWord.type);
        Assert.assertEquals(WordType.NOUN, foundWord.type);
    }

    @Test
    public void createAndFindNewWordAdj() {
        String word = "quick";

        Word createdWord = literalService.create(word, WordType.ADJECTIVE);
        Word foundWord = literalService.fineByValue(word);

        Assert.assertEquals(word, createdWord.value);
        Assert.assertEquals(word, foundWord.value);

        Assert.assertEquals(WordType.ADJECTIVE, createdWord.type);
        Assert.assertEquals(WordType.ADJECTIVE, foundWord.type);
    }

    @Test
    public void createAndFindNewWordVerb() {
        String word = "lead";

        Word createdWord = literalService.create(word, WordType.VERB);
        Word foundWord = literalService.fineByValue(word);

        Assert.assertEquals(word, createdWord.value);
        Assert.assertEquals(word, foundWord.value);

        Assert.assertEquals(WordType.VERB, createdWord.type);
        Assert.assertEquals(WordType.VERB, foundWord.type);
    }

    @Test
    public void checkAddWord() {
        Iterable<Word> resListBefore = literalService.findAll();
        long sizeBefore = resListBefore.spliterator().getExactSizeIfKnown();
        literalService.create("chair", WordType.NOUN);

        Iterable<Word> resListAfter = literalService.findAll();
        long sizeAfter = resListAfter.spliterator().getExactSizeIfKnown();
        Assert.assertEquals(sizeBefore+1, sizeAfter);
    }
}
