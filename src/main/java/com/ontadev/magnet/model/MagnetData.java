// OntaDev_Magnet Plugin
// Авторские права (c) 2025 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.model;

import java.util.Objects;

public final class MagnetData {
    private final int radius;
    private final double strength;
    private final int limit;

    public MagnetData(int radius, double strength, int limit) {
        this.radius = radius;
        this.strength = strength;
        this.limit = limit;
    }

    public int radius() {
        return radius;
    }

    public double strength() {
        return strength;
    }

    public int limit() {
        return limit;
    }

    public MagnetData compare(MagnetData magnetData) {
        return strength > magnetData.strength ? this : magnetData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MagnetData)) return false;
        MagnetData that = (MagnetData) o;
        return radius == that.radius && Double.compare(that.strength, strength) == 0 && limit == that.limit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, strength, limit);
    }

    @Override
    public String toString() {
        return "MagnetData[radius=" + radius + ", strength=" + strength + ", limit=" + limit + "]";
    }
}
