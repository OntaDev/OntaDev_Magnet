// OntaDev_Magnet Plugin
// Авторские права (c) 2025 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.config;


import com.google.gson.annotations.SerializedName;
import com.ontadev.libs.config.YamlConfig;
import com.ontadev.libs.ioc.annotation.lifecycle.Shutdown;
import com.ontadev.libs.message.Message;
import com.ontadev.magnet.model.DefaultItemData;
import com.ontadev.magnet.model.FilterData;
import com.ontadev.magnet.model.ParticleData;
import com.ontadev.magnet.model.TasksSettings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.Particle;

@com.ontadev.libs.ioc.annotation.stereotype.Config
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Config extends YamlConfig {

    @SerializedName("permission-required")
    boolean permissionRequired = false;


    @SerializedName("particles")
    ParticleData particleData = new  ParticleData(
            true,
            Particle.END_ROD
    );


    @SerializedName("tasks")
    TasksSettings tasksSettings =  new TasksSettings(
            10, 1200, 90, 90
    );


    @SerializedName("default-item")
    DefaultItemData defaultItemData = new DefaultItemData(
            new Message("<#EF4444>Маг<#3B82F6>нит"),
            new Message(
                    "<#A78BFA>Притягивает предметы поблизости",
                    "<#4B5563>▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                    "<#CBD5E1>Радиус: <#FBBF24><radius> <#6B7280>бл.",
                    "<#CBD5E1>Сила: <#FBBF24><strength>",
                    "<#CBD5E1>Лимит: <#FBBF24><limit>",
                    "<#4B5563>▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                    "<#6B7280>© OntaDev"
            ),
            Material.PLAYER_HEAD,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJhOGViYzRjNmE4MTczMDk0NzQ5OWJmN2UxZDVlNzNmZWQ2YzFiYjJjMDUxZTk2ZDM1ZWIxNmQyNDYxMGU3In19fQ=="

    );

    @SerializedName("filter-items")
    FilterData filterData = new FilterData();

    @Override
    public String getFileName() {
        return "config";
    }

    @Shutdown
    @Override
    public void save() {
        super.save();
    }
}
