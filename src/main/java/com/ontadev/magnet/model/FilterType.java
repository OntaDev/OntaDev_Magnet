// OntaDev_Magnet Plugin
// Авторские права (c) 2026 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.model;

public enum FilterType {
    BLACKLIST,
    WHITELIST;

    public static FilterType of(String string){
        if (string == null || string.isBlank()) {
            return null;
        }

        try {
            return FilterType.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public static FilterType of(String string, FilterType default_value){
        FilterType type = of(string);
        return type == null? default_value : type;
    }
}
