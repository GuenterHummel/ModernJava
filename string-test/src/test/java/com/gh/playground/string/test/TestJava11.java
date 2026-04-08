package com.gh.playground.string.test;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class TestJava11 {
    @Test
    void testMultiLineString () {
        String multilineString = "Baeldung helps \n \n developers \n explore Java.";
        List<String> lines = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());
        assertThat(lines).containsExactly("Baeldung helps", "developers", "explore Java.");
    }

    @Test // JUnit Jupiter markiert dies als ausführbaren Test
    void meinErsterAssertJTest() {
        List<String> lines = List.of("Hallo Welt", "JUnit 5", "AssertJ");

        // AssertJ übernimmt die Prüfung
        assertThat(lines)
                .hasSize(3)
                .contains("JUnit 5")
                .doesNotContain("Spam");
    }
}
