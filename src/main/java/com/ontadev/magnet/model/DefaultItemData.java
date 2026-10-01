// OntaDev_Magnet Plugin
// Авторские права (c) 2025 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.model;

import com.google.gson.annotations.SerializedName;
import com.ontadev.libs.message.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

@Getter
@Setter
@AllArgsConstructor
public class DefaultItemData {
    private Message name;
    private Message lore;
    private Material type;
    @SerializedName("head-value")
    private String headValue;
}
