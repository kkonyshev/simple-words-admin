package unit;

import app.WordsAdmin;
import app.model.Collocation;
import app.model.CollocationType;
import app.model.Word;
import app.model.WordType;
import app.repository.CollocationRepository;
import app.repository.WordRepository;
import app.service.CollocationService;
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
public class CollocationTest {

    @Autowired
    protected CollocationService collocationService;

    @Autowired
    protected WordService wordService;

    @Autowired
    protected WordRepository wordRepository;

    @Autowired
    protected CollocationRepository collocationRepository;

    @Before
    public void clearRepos() {
        collocationRepository.deleteAll();
        wordRepository.deleteAll();
    }

    @Test
    public void createAndFindNewCollocationSentence() {
        String sentence = "The life is beautiful!";

        Collocation createdCollocation = collocationService.create(sentence, CollocationType.SENTENCE);

        Assert.assertEquals(sentence, createdCollocation.value);
        Assert.assertEquals(CollocationType.SENTENCE, createdCollocation.type);
    }

    @Test
    public void createAndFindNewCollocation() {
        String collocation = "Beautiful life";

        Collocation createdCollocation = collocationService.create(collocation, CollocationType.COLLOCATION);

        Assert.assertEquals(collocation, createdCollocation.value);
        Assert.assertEquals(CollocationType.COLLOCATION, createdCollocation.type);
    }

    @Test
    public void checkAddCollocation() {
        Iterable<Collocation> resListBefore = collocationService.findAll();
        long sizeBefore = resListBefore.spliterator().getExactSizeIfKnown();
        collocationService.create("The life is beautiful!", CollocationType.SENTENCE);

        Iterable<Collocation> resListAfter = collocationService.findAll();
        long sizeAfter = resListAfter.spliterator().getExactSizeIfKnown();
        Assert.assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void checkAddCollocationWord() {
        Collocation collocation = collocationService.create("The life is beautiful!", CollocationType.SENTENCE);
        Assert.assertEquals(0, collocation.words.size());

        Word word = wordService.create("life", WordType.VERB);
        collocation = collocationService.addWord(collocation, word);
        Assert.assertEquals(1, collocation.words.size());

        Iterable<Collocation> all = collocationService.findAll();
        Collocation foundCollocation = all.iterator().next();
        Assert.assertEquals(1, foundCollocation.words.size());

        Word foundWord = foundCollocation.words.iterator().next();
        Assert.assertEquals(word.value, foundWord.value);
        Assert.assertEquals(word.type, foundWord.type);
    }

    @Test
    public void checkRemoveCollocationWordAndClear() {
        Collocation collocation = collocationService.create("The life is beautiful!", CollocationType.SENTENCE);
        Word word1 = wordService.create("life", WordType.VERB);
        collocation = collocationService.addWord(collocation, word1);
        Word word2 = wordService.create("beautiful", WordType.ADJECTIVE);
        collocation = collocationService.addWord(collocation, word2);

        Iterable<Collocation> all = collocationService.findAll();
        Collocation foundCollocation1 = all.iterator().next();
        Assert.assertEquals(2, foundCollocation1.words.size());

        collocationService.removeWord(collocation, word1);
        Iterable<Collocation> all1 = collocationService.findAll();
        Collocation foundCollocation2 = all1.iterator().next();
        Assert.assertEquals(1, foundCollocation2.words.size());

        collocationService.clearWords(foundCollocation2);
        Iterable<Collocation> all2 = collocationService.findAll();
        Collocation foundCollocation3 = all2.iterator().next();
        Assert.assertEquals(0, foundCollocation3.words.size());
    }

    @Test
    public void createCollocationCount() {
        collocationService.create("The life is beautiful!", CollocationType.SENTENCE);
        Assert.assertEquals((long)0, (long)collocationService.findAll().iterator().next().viewCount);

        collocationService.view(collocationService.findAll().iterator().next());
        Assert.assertEquals((long)1, (long)collocationService.findAll().iterator().next().viewCount);
    }


    @Test
    public void testSplitCollocation() {
        Collocation cl = collocationService.create("собака сидит", CollocationType.COLLOCATION);
        Assert.assertEquals(2, cl.words.size());

        for (Word w: collocationService.findAll().iterator().next().words) {
            Assert.assertEquals(WordType.UNDEF, w.type);
        }

        Word dog = wordService.findByValue("собака");
        Assert.assertEquals(WordType.UNDEF, dog.type);

        Word sit = wordService.findByValue("сидит");
        Assert.assertEquals(WordType.UNDEF, sit.type);

        sit.type = WordType.NOUN;
        wordRepository.save(sit);

        Assert.assertEquals(WordType.NOUN, wordService.findByValue("сидит").type);
    }
}
