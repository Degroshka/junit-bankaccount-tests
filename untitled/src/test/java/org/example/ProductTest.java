package org.example;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
public class ProductTest {

    @Test
    @DisplayName("TC-4.1: Создание корректного товара")
    void product_bookPriceFifty_returnBookPriceFifty() {
        Product book = new Product("Book", 50.0);
        assertAll(
                ()-> assertEquals("Book", book.getName()),
                ()->assertEquals(50, book.getPrice(),0.001)
        );
    }
    @Test
    @DisplayName("TC-4.2: Пустое имя")
    void product_emptyName_returnIllegalArgument() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                ()-> new Product("", 50.0));
        assertEquals("Product name cannot be blank", exception.getMessage());
    }
    @Test
    @DisplayName("TC-4.3: null имя")
    void product_nullName_returnIllegalArgument() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                ()-> new Product(null, 50.0));
        assertEquals("Product name cannot be blank", exception.getMessage());
    }
    @Test
    @DisplayName("TC-4.4: Отрицательная цена")
    void product_negativePrice_returnIllegalArgument() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                ()-> new Product("Book", -1));
        assertEquals("Price cannot be negative", exception.getMessage());
    }
    @Test
    @DisplayName("TC-4.5: Цена ноль допустима")
    void product_whenPriceIsZero_shouldNotThrow() {
        new Product("Free", 0.0);
    }
    @Test
    @DisplayName("TC-4.6: Формат toString()")
    void toString_convertInString_returnInString() {
        Product book = new Product("Book", 50.0);
        assertEquals("Book (50.0)", book.toString());
    }
}
