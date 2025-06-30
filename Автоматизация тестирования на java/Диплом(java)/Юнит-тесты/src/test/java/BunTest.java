import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;
    private Bun bun;
    private static final float DELTA = 0.01f;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} - цена: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"black bun", 100f},
                {"white bun", 200f},
                {"red bun", 300f}
        });
    }

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Test
    public void testGetName() {
        Assert.assertEquals(name, bun.getName());
    }

    @Test
    public void testGetPrice() {
        Assert.assertEquals(price, bun.getPrice(), DELTA);
    }
}
