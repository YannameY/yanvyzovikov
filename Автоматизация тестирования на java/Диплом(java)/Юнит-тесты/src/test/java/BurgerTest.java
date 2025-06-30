import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import org.assertj.core.api.SoftAssertions;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        Bun bun = mock(Bun.class);
        burger.setBuns(bun);
        Assert.assertSame(bun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientContainsAddedIngredient() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        Assert.assertTrue(burger.ingredients.contains(ingredient));
    }

    @Test
    public void testRemoveIngredientDecreasesSize() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);

        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientRemovesCorrectIngredient() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);

        Assert.assertFalse(burger.ingredients.contains(ingredient1));
    }

    @Test
    public void testRemoveIngredientKeepsOtherIngredients() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);

        Assert.assertTrue(burger.ingredients.contains(ingredient2));
    }

    @Test
    public void testMoveIngredientMaintainsSize() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);
        burger.moveIngredient(0, 2);

        Assert.assertEquals(3, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientChangesPosition() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);
        burger.moveIngredient(0, 2);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.get(2)).isSameAs(ingredient1);
        softly.assertThat(burger.ingredients.get(0)).isSameAs(ingredient2);
        softly.assertThat(burger.ingredients.get(1)).isSameAs(ingredient3);
        softly.assertAll();
    }
}