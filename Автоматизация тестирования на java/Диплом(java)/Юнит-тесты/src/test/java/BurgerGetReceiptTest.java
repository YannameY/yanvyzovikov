import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

@RunWith(Parameterized.class)
public class BurgerGetReceiptTest {
    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final String expectedReceipt;

    public BurgerGetReceiptTest(Bun bun, List<Ingredient> ingredients, String expectedReceipt) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        // Создаем и настраиваем моки
        Bun whiteBun = Mockito.mock(Bun.class);
        Mockito.when(whiteBun.getName()).thenReturn("white bun");
        Mockito.when(whiteBun.getPrice()).thenReturn(1f); // Важно: цена 1

        Ingredient cutlet = Mockito.mock(Ingredient.class);
        Mockito.when(cutlet.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(cutlet.getName()).thenReturn("cutlet");
        Mockito.when(cutlet.getPrice()).thenReturn(0.5f);

        Ingredient chiliSauce = Mockito.mock(Ingredient.class);
        Mockito.when(chiliSauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(chiliSauce.getName()).thenReturn("chili sauce");
        Mockito.when(chiliSauce.getPrice()).thenReturn(4f);

        Ingredient sausage = Mockito.mock(Ingredient.class);
        Mockito.when(sausage.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(sausage.getName()).thenReturn("sausage");
        Mockito.when(sausage.getPrice()).thenReturn(1f);

        return new Object[][]{
                {
                        whiteBun,
                        List.of(cutlet, chiliSauce, sausage),
                        "(==== white bun ====)\n" +
                                "= filling cutlet =\n" +
                                "= sauce chili sauce =\n" +
                                "= filling sausage =\n" +
                                "(==== white bun ====)\n" +
                                "\n" +
                                "Price: 7,500000\n"
                }
        };
    }

    @Test
    public void testGetReceipt() {
        // Проверяем цены перед тестом
        System.out.println("Bun price: " + bun.getPrice());
        System.out.println("Ingredients prices:");
        ingredients.forEach(i -> System.out.println(i.getName() + ": " + i.getPrice()));

        Burger burger = new Burger();
        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);

        // Проверяем промежуточную цену
        float calculatedPrice = burger.getPrice();
        System.out.println("Calculated price: " + calculatedPrice);

        String actualReceipt = burger.getReceipt();
        Assert.assertEquals(expectedReceipt, actualReceipt);
    }
}