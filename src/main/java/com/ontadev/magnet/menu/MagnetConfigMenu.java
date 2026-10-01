// OntaDev_Magnet Plugin
// Авторские права (c) 2026 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.menu;

import com.ontadev.libs.ioc.annotation.menu.Menu;
import com.ontadev.libs.item.ItemModel;
import com.ontadev.libs.menu.AbstractMenu;
import com.ontadev.libs.message.Message;
import com.ontadev.libs.player.PlayerSnapshot;
import com.ontadev.libs.service.ChatInputService;
import com.ontadev.magnet.config.Config;
import com.ontadev.magnet.model.TasksSettings;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Menu
public class MagnetConfigMenu extends AbstractMenu {

    private final Config config;
    private final ChatInputService chatInputService;

    public MagnetConfigMenu(Config config, ChatInputService chatInputService) {
        this.config = config;
        this.chatInputService = chatInputService;
    }

    @Override
    public String id() {
        return "magnet-config";
    }

    @Override
    public Message title(PlayerSnapshot snapshot) {
        return new Message("<#4B5563>⚙ <#F59E0B>Настройки Magnet");
    }

    @Override
    public int size() {
        return 27;
    }

    @Override
    public Map<Integer, ItemModel> dynamicItems(PlayerSnapshot snapshot) {
        Map<Integer, ItemModel> items = new HashMap<>();
        TasksSettings tasks = config.getTasksSettings();

        items.put(10, numberItem(
                Material.CLOCK,
                "<#F59E0B>Период работы магнита",
                "<#CBD5E1>" + tasks.getMagnetPeriod() + " тиков",
                "<#FBBF24>Введи новый период магнита в тиках (20 = 1 сек):",
                (player, value) -> {
                    tasks.setMagnetPeriod(value.intValue());
                    persistAndReopen(player, "Период магнита обновлён");
                }
        ));

        items.put(11, numberItem(
                Material.REPEATER,
                "<#F59E0B>Период проверки производительности",
                "<#CBD5E1>" + tasks.getPerformancePeriod() + " тиков",
                "<#FBBF24>Введи новый период проверки производительности в тиках:",
                (player, value) -> {
                    tasks.setPerformancePeriod(value.intValue());
                    persistAndReopen(player, "Период проверки обновлён");
                }
        ));

        items.put(12, numberItem(
                Material.REDSTONE,
                "<#F59E0B>Лимит CPU",
                "<#CBD5E1>" + tasks.getCpuLimit() + "%",
                "<#FBBF24>Введи новый лимит CPU в процентах:",
                (player, value) -> {
                    tasks.setCpuLimit(value);
                    persistAndReopen(player, "Лимит CPU обновлён");
                }
        ));

        items.put(13, numberItem(
                Material.CHEST_MINECART,
                "<#F59E0B>Лимит RAM",
                "<#CBD5E1>" + tasks.getRamLimit() + "%",
                "<#FBBF24>Введи новый лимит RAM в процентах:",
                (player, value) -> {
                    tasks.setRamLimit(value);
                    persistAndReopen(player, "Лимит RAM обновлён");
                }
        ));

        items.put(15, toggleItem(
                "<#F59E0B>Требовать permission",
                config.isPermissionRequired(),
                player -> {
                    config.setPermissionRequired(!config.isPermissionRequired());
                    persistAndReopen(player, null);
                }
        ));

        items.put(16, toggleItem(
                "<#F59E0B>Частицы",
                config.getParticleData().isEnabled(),
                player -> {
                    config.getParticleData().setEnabled(!config.getParticleData().isEnabled());
                    persistAndReopen(player, null);
                }
        ));

        items.put(17, toggleItem(
                "<#F59E0B>Фильтр предметов",
                config.getFilterData().isEnabled(),
                player -> {
                    config.getFilterData().setEnabled(!config.getFilterData().isEnabled());
                    persistAndReopen(player, null);
                }
        ));

        return items;
    }

    private ItemModel numberItem(Material material, String name, String currentValue, String prompt, BiConsumer<Player, Double> onValue) {
        return ItemModel.builder()
                .material(material)
                .name(new Message(name))
                .lore(new Message("<#6B7280>Текущее: " + currentValue, "", "<#34D399>ЛКМ <#6B7280>- изменить"))
                .build()
                .onAnyInteraction((player, event) -> {
                    player.closeInventory();
                    chatInputService.requestNumber(player, new Message(prompt), value -> onValue.accept(player, value));
                });
    }

    private ItemModel toggleItem(String name, boolean state, java.util.function.Consumer<Player> onToggle) {
        return ItemModel.builder()
                .material(state ? Material.LIME_DYE : Material.GRAY_DYE)
                .name(new Message(name))
                .lore(new Message(
                        "<#6B7280>Статус: " + (state ? "<#34D399>включено" : "<#F87171>выключено"),
                        "",
                        "<#34D399>ЛКМ <#6B7280>- переключить"
                ))
                .build()
                .onAnyInteraction((player, event) -> onToggle.accept(player));
    }

    private void persistAndReopen(Player player, String successMessage) {
        config.save();
        if (successMessage != null) {
            new Message("<#4B5563>[<#34D399>✔<#4B5563>] <#CBD5E1>" + successMessage).send(player);
        }
        open(player);
    }
}
