package dev.kunal.productcatalogservice.UtilsTests;

import dev.kunal.productcatalogservice.util.ArrayUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayUtilTests {

    @Test
    @DisplayName("When middle index, then get valid value")
    void getElement_middleIndex_thenReturnElement() {
        int shelf[] = {10,20,30};
        int actual = ArrayUtils.getElementByIndex(shelf,1);
        assertEquals(20,actual);
    }

    @Test
    @DisplayName("When negative index, then throw exception")
    void getElement_NegativeIndex_thenThrowException() {
        int shelf[] = {10,20,30};
        assertThrows(IllegalArgumentException.class,()->ArrayUtils.getElementByIndex(shelf,-1));
    }

    @ParameterizedTest
    @DisplayName("When valid index, then get valid value")
    @CsvSource({"0,10", "1,20", "2,30"})
    void getElement_validIndex_thenReturnElement(int index, int expected) {
        int shelf[] = {10,20,30};
        int actual = ArrayUtils.getElementByIndex(shelf,index);
        assertEquals(expected,actual);
    }
}
