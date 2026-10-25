package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.example.StringUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParameterizedStringTests {

    @ParameterizedTest(name = "[{index}] \"{0}\" — палиндром")
    @DisplayName("TC-2.4.1: Палиндромы через @ValueSource")
    @ValueSource(strings = {"racecar", "madam", "level", "kayak"})
    void isPalindrome_palindromeWord_returnsTrue(String candidate) {
        assertTrue(isPalindrome(candidate));
    }
    @ParameterizedTest(name = "[{index}] \"{0}\" —не палиндром")
    @DisplayName("TC-2.4.2: Не-палиндромы через @ValueSource")
    @ValueSource(strings = {"hello", "world", "java"})
    void isPalindrome_palindromeWord_returnsFalse(String candidate) {
        assertFalse(isPalindrome(candidate));
    }
    @ParameterizedTest(name = "[{index}] \"{0}\" - {1} слов(а)")
    @DisplayName("TC-2.4.3: countWords через @CsvSource")
    @CsvSource({
            "'hello world'," + " 2",
            "'one two three', 3",
            "'',    0"})
    void countWords_variousInput_returnsExpected(String input, int expected) {
        int actual = countWords(input);
        assertEquals(expected, actual);
    }
    @ParameterizedTest(name = "[{index}] \"{0}\" и \"{1}\" - анаграмма")
    @DisplayName("TC-2.4.4: isAnagram через @CsvSource")
    @CsvSource({
            "'арфа'," + "фара",
            "'ротонда', торнадо",
            "'барокко',  коробка",
            "'адрес',  среда"    })
    void isAnagram_variousInput_returnsTrue(String fWord, String sWord) {
        assertTrue(isAnagram(fWord, sWord));
    }

}
