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
import com.ontadev.magnet.config.MessageConfig;
import com.ontadev.magnet.model.MagnetData;
import com.ontadev.magnet.service.MagnetService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Menu
public class MagnetItemMenu extends AbstractMenu {

    private final MagnetService magnetService;
    private final ChatInputService chatInputService;
    private final MessageConfig messageConfig;

    public MagnetItemMenu(MagnetService magnetService, ChatInputService chatInputService, MessageConfig messageConfig) {
        this.magnetService = magnetService;
        this.chatInputService = chatInputService;
        this.messageConfig = messageConfig;
    }

    @Override
    public String id() {
        return "magnet-item";
    }

    @Override
    public Message title(PlayerSnapshot snapshot) {
        return new Message("<#4B5563>⚙ <#F59E0B>Настройки магнита");
    }

    @Override
    public int size() {
        return 27;
    }

    @Override
    public Map<Integer, ItemModel> dynamicItems(PlayerSnapshot snapshot) {
        Player player = Bukkit.getPlayer(snapshot.uuid());
        Map<Integer, ItemModel> items = new HashMap<>();
        if (player == null) return items;

        MagnetData data = magnetService.getMagnetData(player.getInventory().getItemInMainHand());
        if (data == null) return items;

        items.put(11, numberItem(
                Material.REDSTONE,
                "<#F59E0B>Радиус",
                String.valueOf(data.radius()),
                "<#FBBF24>Введи новый радиус магнита в блоках:",
                (p, value) -> updateData(p, current -> new MagnetData(value.intValue(), current.strength(), current.limit()))
        ));

        items.put(13, numberItem(
                Material.SUGAR,
                "<#F59E0B>Сила",
                String.valueOf(data.strength()),
                "<#FBBF24>Введи новую силу притяжения магнита:",
                (p, value) -> updateData(p, current -> new MagnetData(current.radius(), value, current.limit()))
        ));

        items.put(15, numberItem(
                Material.HOPPER,
                "<#F59E0B>Лимит",
                String.valueOf(data.limit()),
                "<#FBBF24>Введи новый лимит предметов (0 = без лимита):",
                (p, value) -> updateData(p, current -> new MagnetData(current.radius(), current.strength(), value.intValue()))
        ));

        return items;
    }

    private void updateData(Player player, Function<MagnetData, MagnetData> updater) {
        ItemStack item = player.getInventory().getItemInMainHand();
        MagnetData current = magnetService.getMagnetData(item);
        if (current == null) {
            messageConfig.getNotAMagnet().send(player);
            return;
        }

        magnetService.setupMagnetData(item, updater.apply(current));
        new Message("<#4B5563>[<#34D399>✔<#4B5563>] <#CBD5E1>Параметр магнита <#34D399>обновлён").send(player);
        open(player);
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
}
