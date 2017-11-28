package ru.lymar.lsp.food;

import org.junit.Test;

import java.text.DecimalFormat;
import java.time.LocalDate;

import static junit.framework.TestCase.assertNotNull;

public class FoodTest {

    @Test
    public void freshnessTest() {
        Food apple = new Apple("Sezonnoe", LocalDate.now().plusMonths(12),
                LocalDate.now().minusMonths(2), 50.0, "50");
        String result = new DecimalFormat("#0.00").format(apple.getCorruptionPercent());
        assertNotNull(result);
    }

}