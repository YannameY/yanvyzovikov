import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerGetPriceTest {

    private final Bun bun;
    private final Ingredient[] ingredients;
    private final float expectedPrice;
    private static final float DELTA = 0.01f;

    public BurgerGetPriceTest(Bun bun, Ingredient[] ingredients, float expectedPrice) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(1.0f);

        Ingredient ingredient1 = mock(Ingredient.class);
        when(ingredient1.getPrice()).thenReturn(0.5f);
        Ingredient ingredient2 = mock(Ingredient.class);
        when(ingredient2.getPrice()).thenReturn(0.8f);

        return Arrays.asList(new Object[][] {
                {bun, new Ingredient[]{}, 2.0f},
                {bun, new Ingredient[]{ingredient1}, 2.5f},
                {bun, new Ingredient[]{ingredient1, ingredient2}, 3.3f}
        });
    }


    @Test
    public void testGetPrice() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }
}