// OntaDev_Magnet Plugin
// Авторские права (c) 2026 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class MagnetDataTest {

    @Test
    void compareReturnsThisWhenStrengthIsGreater() {
        MagnetData stronger = new MagnetData(5, 10.0, 1);
        MagnetData weaker = new MagnetData(5, 2.0, 1);

        assertSame(stronger, stronger.compare(weaker));
    }

    @Test
    void compareReturnsOtherWhenOtherStrengthIsGreater() {
        MagnetData weaker = new MagnetData(5, 2.0, 1);
        MagnetData stronger = new MagnetData(5, 10.0, 1);

        assertSame(stronger, weaker.compare(stronger));
    }

    @Test
    void compareReturnsOtherWhenStrengthIsEqual() {
        MagnetData first = new MagnetData(5, 5.0, 1);
        MagnetData second = new MagnetData(3, 5.0, 2);

        assertSame(second, first.compare(second));
    }
}
