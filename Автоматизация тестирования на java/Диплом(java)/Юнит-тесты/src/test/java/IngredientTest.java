import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;
    private static final float DELTA = 0f;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Type: {0}, Name: {1}, Price: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100f},
                {IngredientType.SAUCE, "sour cream", 200f},
                {IngredientType.SAUCE, "chili sauce", 300f},
                {IngredientType.FILLING, "cutlet", 100f},
                {IngredientType.FILLING, "dinosaur", 200f},
                {IngredientType.FILLING, "sausage", 300f}
        });
    }

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Type should match", type, ingredient.getType());
    }

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Name should match", name, ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Price should match", price, ingredient.getPrice(), DELTA);
    }


    @Test
    public void testValuesCount() {
        Assert.assertEquals("Should have exactly 2 values", 2, IngredientType.values().length);
    }

    @Test
    public void testSauceValueExists() {
        Assert.assertEquals("SAUCE should exist", IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void testFillingValueExists() {
        Assert.assertEquals("FILLING should exist", IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    public void testContainsSauce() {
        Assert.assertTrue("Should contain SAUCE",
                Arrays.asList(IngredientType.values()).contains(IngredientType.SAUCE));
    }

    @Test
    public void testContainsFilling() {
        Assert.assertTrue("Should contain FILLING",
                Arrays.asList(IngredientType.values()).contains(IngredientType.FILLING));
    }
}
