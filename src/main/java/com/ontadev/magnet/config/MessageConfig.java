// OntaDev_Magnet Plugin
// Авторские права (c) 2025 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.config;

import com.google.gson.annotations.SerializedName;
import com.ontadev.libs.config.YamlConfig;
import com.ontadev.libs.ioc.annotation.lifecycle.Shutdown;
import com.ontadev.libs.ioc.annotation.stereotype.Config;
import com.ontadev.libs.message.Message;
import lombok.Getter;

@Config
@Getter
public class MessageConfig extends YamlConfig {

    @SerializedName("magnet-activated")
    Message magnetActivated = new Message("<#4B5563>[<#34D399>✔<#4B5563>] <#CBD5E1>Магнит <#34D399>активирован");

    @SerializedName("magnet-deactivated")
    Message magnetDeactivated = new Message("<#4B5563>[<#F87171>✘<#4B5563>] <#CBD5E1>Магнит <#F87171>деактивирован");

    @SerializedName("no-permission")
    Message noPermission = new Message("<#4B5563>[<#F87171>✘<#4B5563>] <#CBD5E1>Недостаточно прав для этого действия");

    @SerializedName("player-not-found")
    Message playerNotFound = new Message("<#4B5563>[<#F87171>✘<#4B5563>] <#CBD5E1>Игрок <#FBBF24><player> <#CBD5E1>не найден");

    @SerializedName("not-number")
    Message notNumber = new Message("<#4B5563>[<#F87171>✘<#4B5563>] <#CBD5E1>Нужно ввести число");

    @SerializedName("not-enough-space")
    Message notEnoughSpace = new Message("<#4B5563>[<#F87171>✘<#4B5563>] <#CBD5E1>Недостаточно места в инвентаре");

    @SerializedName("magnet-given")
    Message magnetGiven = new Message("<#4B5563>[<#34D399>✔<#4B5563>] <#CBD5E1>Магнит успешно <#34D399>выдан");

    @SerializedName("no-item-in-hand")
    Message noItemInHand = new Message("<#4B5563>[<#F87171>✘<#4B5563>] <#CBD5E1>В основной руке нет предмета");

    @SerializedName("not-a-magnet")
    Message notAMagnet = new Message("<#4B5563>[<#F87171>✘<#4B5563>] <#CBD5E1>Удерживаемый предмет — не магнит");

    @SerializedName("magnet-enchanted")
    Message magnetEnchanted = new Message("<#4B5563>[<#34D399>✔<#4B5563>] <#CBD5E1>Магнит успешно <#34D399>зачарован");

    @SerializedName("plugin-reloaded")
    Message pluginReloaded = new Message("<#4B5563>[<#34D399>✔<#4B5563>] <#CBD5E1>Конфигурация плагина <#34D399>перезагружена");

    @SerializedName("plugin-reload-usage")
    Message pluginReloadUsage = new Message("<#6B7280>» <#F59E0B>/magnet reload <#4B5563>- <#CBD5E1>перезагружает конфигурацию");

    @SerializedName("filter-usage")
    Message filterUsage = new Message(
            "<#4B5563>[<#F59E0B>Magnet<#4B5563>] <#CBD5E1>Управление фильтром предметов:",
            "<#6B7280>» <#F59E0B>/magnet filter enable|disable|status <#4B5563>- <#CBD5E1>управление фильтром",
            "<#6B7280>» <#F59E0B>/magnet filter add [Материал] <#4B5563>- <#CBD5E1>добавить предмет в фильтр",
            "<#6B7280>» <#F59E0B>/magnet filter remove <Материал> <#4B5563>- <#CBD5E1>удалить предмет из фильтра",
            "<#6B7280>» <#F59E0B>/magnet filter change <whitelist|blacklist> <#4B5563>- <#CBD5E1>сменить режим фильтра"
    );

    @SerializedName("filter-invalid-material")
    Message filterInvalidMaterial = new Message("<#4B5563>[<#F87171>✘<#4B5563>] <#CBD5E1>Материал <#FBBF24><material> <#CBD5E1>не найден");

    @SerializedName("filter-invalid-type")
    Message filterInvalidType = new Message("<#4B5563>[<#F87171>✘<#4B5563>] <#CBD5E1>Неверный тип фильтра: <#FBBF24><type>");

    @SerializedName("filter-enabled")
    Message filterEnabled = new Message("<#4B5563>[<#34D399>✔<#4B5563>] <#CBD5E1>Фильтр предметов <#34D399>включён");

    @SerializedName("filter-disabled")
    Message filterDisabled = new Message("<#4B5563>[<#F87171>✘<#4B5563>] <#CBD5E1>Фильтр предметов <#F87171>выключен");

    @SerializedName("filter-type-changed")
    Message filterTypeChanged = new Message("<#4B5563>[<#34D399>✔<#4B5563>] <#CBD5E1>Тип фильтра изменён на <#FBBF24><type>");

    @SerializedName("filter-item-added")
    Message filterItemAdded = new Message("<#4B5563>[<#34D399>✔<#4B5563>] <#CBD5E1>Предмет <#FBBF24><material> <#CBD5E1>добавлен в фильтр");

    @SerializedName("filter-item-removed")
    Message filterItemRemoved = new Message("<#4B5563>[<#34D399>✔<#4B5563>] <#CBD5E1>Предмет <#FBBF24><material> <#CBD5E1>удалён из фильтра");

    @SerializedName("filter-item-exists")
    Message filterItemExists = new Message("<#4B5563>[<#F87171>✘<#4B5563>] <#CBD5E1>Предмет <#FBBF24><material> <#CBD5E1>уже в фильтре");

    @SerializedName("filter-item-not-found")
    Message filterItemNotFound = new Message("<#4B5563>[<#F87171>✘<#4B5563>] <#CBD5E1>Предмет <#FBBF24><material> <#CBD5E1>не найден в фильтре");

    @SerializedName("filter-status")
    Message filterStatus = new Message(
            "<#4B5563>▬▬▬▬▬▬▬ <#F59E0B>Фильтр предметов <#4B5563>▬▬▬▬▬▬▬",
            "<#CBD5E1>Статус: <enabled>",
            "<#CBD5E1>Режим: <#FBBF24><type>",
            "<#CBD5E1>Предметов в списке: <#FBBF24><count>",
            "<#CBD5E1>Список: <#FBBF24><items>",
            "<#4B5563>▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
    );

    @Override
    public String getFileName() {
        return "messages";
    }

    @Shutdown
    @Override
    public void save() {
        super.save();
    }
}
