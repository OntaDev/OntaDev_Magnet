// OntaDev_Magnet Plugin
// Авторские права (c) 2025 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.command.magnet;

import com.ontadev.libs.command.SubCommand;
import com.ontadev.libs.message.Message;
import com.ontadev.libs.message.Placeholders;
import com.ontadev.magnet.config.MessageConfig;
import com.ontadev.magnet.model.MagnetData;
import com.ontadev.magnet.service.MagnetService;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public class SubGive extends SubCommand {
    private final Message USAGE = new Message(
            "<#6B7280>» <#F59E0B>/magnet give {Игрок} {Сила} {Радиус} {Лимит} <#4B5563>- <#CBD5E1>выдача магнита игроку",
            "<#6B7280>   * <#CBD5E1>{Лимит} <#4B5563>- <#CBD5E1>максимальное кол-во притягивающихся предметов"
    );
    private final MagnetService magnetService;
    private final MessageConfig messageConfig;

    public SubGive(MagnetService magnetService, MessageConfig messageConfig) {
        super("give");
        this.magnetService = magnetService;
        this.messageConfig = messageConfig;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String... args) {
        if (args.length < 4) {
            sendUsage(sender, command, label, args);
            return;
        }

        String name = args[0];
        Player player = Bukkit.getPlayer(name);

        if (player == null) {
            messageConfig.getPlayerNotFound().send(sender, Placeholders.of("player", name));
            return;
        }

        MagnetData magnetData;
        try{
            double strength = Double.parseDouble(args[1]);
            int radius = Integer.parseInt(args[2]);
            int limit = Integer.parseInt(args[3]);

            magnetData = new MagnetData(radius, strength, limit);
        }catch (NumberFormatException exception){
            messageConfig.getNotNumber().send(sender);
            return;
        }

        PlayerInventory inventory = player.getInventory();

        if (inventory.firstEmpty() == -1) {
            messageConfig.getNotEnoughSpace().send(sender);
            return;
        }

        inventory.addItem(magnetService.getDefaultMagnet(magnetData));
        messageConfig.getMagnetGiven().send(sender);
    }

    @Override
    public List<String> complete(CommandSender sender, String... args) {
        if (!sender.hasPermission(getPermission()) && !sender.isOp()) {
            return null;
        }

        switch (args.length) {
            case 1: return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(java.util.stream.Collectors.toList());
            case 2: return List.of("Сила");
            case 3: return List.of("Радиус");
            case 4: return List.of("Лимит");
            default: return null;
        }
    }

    @Override
    public void noPermission(CommandSender sender, Command command, String label, String... args) {
        messageConfig.getNoPermission().send(sender);
    }

    @Override
    public String getPermission(CommandSender sender, Command command, String label, String... args) {
        return getName();
    }

    @Override
    public void sendUsage(CommandSender sender, Command command, String label, String... args) {
        USAGE.send(sender);
    }

    private String getPermission() {
        return "magnet.give";
    }
}
