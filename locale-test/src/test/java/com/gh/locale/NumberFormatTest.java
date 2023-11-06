package com.gh.locale;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Java 11 uses <a href="http://cldr.unicode.org/index/downloads/cldr-33">http://cldr.unicode.org/index/downloads/cldr-33</a>
 * as its base information for formatting numbers. See
 * <a href="https://www.oracle.com/technetwork/java/javase/11-relnote-issues-5012449.html">https://www.oracle.com/technetwork/java/javase/11-relnote-issues-5012449.html</a>:
 *
 * <pre>
 * core-libs/java.util:i18n
 * ➜ Updated Locale Data to Unicode CLDR v33
 * </pre>
 *
 * <p>It appears that this implementation is not correct as the test below fails.</p>
 */
public class NumberFormatTest {

    @Test
    public void test() {
        final Locale locale = new Locale("de", "AT");

        // since https://www.unicode.org/cldr/charts/28/by_type/numbers.symbols.html#Symbols_ the grouping separator for
        // the austrian locale ("de-AT") is '\u00a0' (non-breaking space).
        // this is implemented correctly in Java 11 ...

        final NumberFormat numberInstance = NumberFormat.getNumberInstance(locale);
        assertEquals("10\u00a0000,03", numberInstance.format(10000.03));

        // ... but since https://www.unicode.org/cldr/charts/30/by_type/numbers.symbols.html#Symbols_ Austria has
        // its own grouping separator that is used for currency only. See Symbols>currencyGroup. the separator is
        // '.'.
        // this is not implemented correctly b/c the currency instance of a NumberFormat still returns
        // "\u20ac\u00a010\u00a0000,03" instead of "\u20ac\u00a010.0000,03"

        final NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(locale);
        assertEquals("\u20ac\u00a010.000,03", currencyInstance.format(10000.03));
    }
}

