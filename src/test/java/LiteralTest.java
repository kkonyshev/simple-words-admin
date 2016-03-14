import app.WordsAdmin;
import app.model.Literal;
import app.model.LiteralType;
import app.service.LiteralService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ka on 14/03/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(WordsAdmin.class)
public class LiteralTest {

    @Autowired
    protected LiteralService literalService;

    @Test
    public void createNew() {
        Literal createdTableLiteral = literalService.create("table", LiteralType.NOUN);
        Literal foundTableLiteral = literalService.fineByValue("table");

        Assert.assertEquals("table", createdTableLiteral.value);
        Assert.assertEquals("table", foundTableLiteral.value);

        Assert.assertEquals(LiteralType.NOUN, createdTableLiteral.type);
        Assert.assertEquals(LiteralType.NOUN, foundTableLiteral.type);
    }

    @Test
    public void testAdd() {
        Iterable<Literal> resListBefore = literalService.findAll();
        long sizeBefore = resListBefore.spliterator().getExactSizeIfKnown();
        literalService.create("chair", LiteralType.NOUN);

        Iterable<Literal> resListAfter = literalService.findAll();
        long sizeAfter = resListAfter.spliterator().getExactSizeIfKnown();
        Assert.assertEquals(sizeBefore+1, sizeAfter);
    }
}
