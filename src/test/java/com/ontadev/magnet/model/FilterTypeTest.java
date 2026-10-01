// OntaDev_Magnet Plugin
// Авторские права (c) 2026 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FilterTypeTest {

    @Test
    void ofParsesExactName() {
        assertEquals(FilterType.WHITELIST, FilterType.of("WHITELIST"));
        assertEquals(FilterType.BLACKLIST, FilterType.of("BLACKLIST"));
    }

    @Test
    void ofIsCaseInsensitive() {
        assertEquals(FilterType.WHITELIST, FilterType.of("whitelist"));
        assertEquals(FilterType.WHITELIST, FilterType.of("WhiteList"));
    }

    @Test
    void ofReturnsNullForBlankOrNullInput() {
        assertNull(FilterType.of(null));
        assertNull(FilterType.of(""));
        assertNull(FilterType.of("   "));
    }

    @Test
    void ofReturnsNullForUnknownValue() {
        assertNull(FilterType.of("greylist"));
    }

    @Test
    void ofWithDefaultFallsBackWhenUnparseable() {
        assertEquals(FilterType.BLACKLIST, FilterType.of(null, FilterType.BLACKLIST));
        assertEquals(FilterType.BLACKLIST, FilterType.of("nonsense", FilterType.BLACKLIST));
    }

    @Test
    void ofWithDefaultPrefersParsedValue() {
        assertEquals(FilterType.WHITELIST, FilterType.of("whitelist", FilterType.BLACKLIST));
    }
}
