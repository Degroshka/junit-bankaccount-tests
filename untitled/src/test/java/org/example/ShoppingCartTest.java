package org.example;

import org.junit.jupiter.api.BeforeEach;
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

}
