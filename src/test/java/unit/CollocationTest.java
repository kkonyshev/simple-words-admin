package unit;

import app.WordsAdmin;
import app.model.Collocation;
import app.model.CollocationType;
import app.service.CollocationService;
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
public class CollocationTest {

    @Autowired
    protected CollocationService collocationService;

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
    public void checkAddWord() {
        Iterable<Collocation> resListBefore = collocationService.findAll();
        long sizeBefore = resListBefore.spliterator().getExactSizeIfKnown();
        collocationService.create("The life is beautiful!", CollocationType.SENTENCE);

        Iterable<Collocation> resListAfter = collocationService.findAll();
        long sizeAfter = resListAfter.spliterator().getExactSizeIfKnown();
        Assert.assertEquals(sizeBefore+1, sizeAfter);
    }
}
