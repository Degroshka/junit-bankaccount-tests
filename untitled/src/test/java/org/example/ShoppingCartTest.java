package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    @DisplayName("Инициализация корзины")
    void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    @DisplayName("TC-3.1.1: Новая корзина пуста")
    void ShoppingCart_IsEmpty_returnsTrue() {
        boolean isEmpty = cart.isEmpty();
        int itemCount = cart.getItemCount();
        assertAll(
                ()->assertTrue(isEmpty),
                ()->assertEquals(0,itemCount)
        );
        }

    @Test
    @DisplayName("TC-3.1.2: Добавление двух товаров")
    void addItem_addTwo_returnTwo() {
        Product milk = new Product("milk", 100.0);
        Product bread = new Product("bread", 50.0);
        cart.addItem(milk);
        cart.addItem(bread);
        assertAll(
                ()->assertFalse(cart.isEmpty()),
                ()->assertEquals(2,cart.getItemCount())
        );

    }
    @Test
    @DisplayName("TC-3.1.3: Очистка корзины")
    void clear_isEmpty_returnTrue() {
        Product milk = new Product("milk", 100.0);
        Product bread = new Product("bread", 50.0);
        cart.addItem(milk);
        cart.addItem(bread);
        cart.clear();
        assertAll(
                ()->assertTrue(cart.isEmpty()),
                ()->assertEquals(0,cart.getItemCount())
        );

    }
    @Test
    @DisplayName("TC-3.2.1: Сумма двух товаров")
    void getTotalPrice_twoProducts_returnSumOfTwo() {
        Product milk = new Product("milk", 100.0);
        Product bread = new Product("bread", 50.0);
        cart.addItem(milk);
        cart.addItem(bread);
        assertEquals(150.0, cart.getTotalPrice(),0.001);
    }
    @Test
    @DisplayName("TC-3.2.2: Сумма пустой корзины")
    void getTotalPrice_emptyCart_returnZero() {
        Product milk = new Product("milk", 100.0);
        Product bread = new Product("bread", 50.0);
        cart.addItem(milk);
        cart.addItem(bread);
        cart.clear();
        assertEquals(0, cart.getTotalPrice(),0.001);
    }
    @Test
    @DisplayName("TC-3.2.3: Бесплатный товар")
    void getTotalPrice_priceZero_returnZero() {
        Product milk = new Product("milk", 0);
        cart.addItem(milk);
        assertEquals(0, cart.getTotalPrice(),0.001);
    }
    @Test
    @DisplayName("TC-3.3.1: Скидка 10%")
    void getTotalPrice_discountTenPercent_returnWithDiscount() {
        Product milk = new Product("milk", 150);
        Product bread = new Product("bread", 50.0);
        cart.addItem(milk);
        cart.addItem(bread);
        cart.setDiscount(10);
        assertEquals(180, cart.getTotalPrice(),0.001);
    }
    @Test
    @DisplayName("TC-3.3.2: Скидка 100%")
    void getTotalPrice_discountHundredPercent_returnZero() {
        Product milk = new Product("milk", 150);
        Product bread = new Product("bread", 50.0);
        cart.addItem(milk);
        cart.addItem(bread);
        cart.setDiscount(100);
        assertEquals(0, cart.getTotalPrice(),0.001);
    }
    @Test
    @DisplayName("TC-3.3.3: Скидка 50% на товар 99")
    void getTotalPrice_discountFiftyPercent_returnPriceInDouble() {
        Product tv = new Product("tv", 99);
        cart.addItem(tv);
        cart.setDiscount(50);
        assertEquals(49.5, cart.getTotalPrice(),0.001);
    }
    @Test
    @DisplayName("TC-3.3.4: Отрицательная скидка")
    void setDiscount_negativeDiscount_returnIllegalArgument() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> cart.setDiscount(-5));
        assertEquals("Discount must be between 0 and 100",exception.getMessage());
    }
    @Test
    @DisplayName("TC-3.3.5: Скидка больше 100%")
    void setDiscount_discountMoreThanHundredPercent_returnIllegalArgument() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> cart.setDiscount(101));
        assertEquals("Discount must be between 0 and 100",exception.getMessage());
    }
    @Test
    @DisplayName("TC-3.3.6: Граничные значения 0 и 100 допустимы")
    void getTotalPrice_boundaryValues_shouldNotThrow() {
        assertDoesNotThrow(() -> {
            cart.setDiscount(0);
            cart.setDiscount(100);
        });
    }
    @Test
    @Disabled("BUG-4: Скидка сохраняется после очистки корзины")
    @DisplayName("TC-3.3.7: Скидка не сбрасывается после clear()")
    void clear_whenCalled_doesNotResetDiscount() {
        Product milk = new Product("milk", 100);
        cart.addItem(milk);
        cart.setDiscount(10);
        cart.clear();
        cart.addItem(milk);
        assertEquals(90, cart.getTotalPrice(),0.001);
    }

}
