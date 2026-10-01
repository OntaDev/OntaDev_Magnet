// OntaDev_Magnet Plugin
// Авторские права (c) 2026 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.command.magnet;

import com.ontadev.libs.command.SubCommand;
import com.ontadev.libs.message.Message;
import com.ontadev.magnet.config.MessageConfig;
import com.ontadev.magnet.menu.MagnetItemMenu;
import com.ontadev.magnet.service.MagnetService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SubEdit extends SubCommand {
    private final Message USAGE = new Message("<#6B7280>» <#F59E0B>/magnet edit <#4B5563>- <#CBD5E1>открыть меню настройки магнита в руке");
    @SuppressWarnings("FieldCanBeLocal")
    private final String PERMISSION = "magnet.edit";
    private final MagnetService magnetService;
    private final MessageConfig messageConfig;
    private final MagnetItemMenu itemMenu;

    public SubEdit(MagnetService magnetService, MessageConfig messageConfig, MagnetItemMenu itemMenu) {
        super("edit");
        this.magnetService = magnetService;
        this.messageConfig = messageConfig;
        this.itemMenu = itemMenu;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String... args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only for players");
            return;
        }
        Player player = (Player) sender;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (magnetService.getMagnetData(item) == null) {
            messageConfig.getNotAMagnet().send(player);
            return;
        }

        itemMenu.open(player);
    }

    @Override
    public String getPermission(CommandSender sender, Command command, String label, String... args) {
        return PERMISSION;
    }

    @Override
    public void noPermission(CommandSender sender, Command command, String label, String... args) {
        messageConfig.getNoPermission().send(sender);
    }

    @Override
    public void sendUsage(CommandSender sender, Command command, String label, String... args) {
        USAGE.send(sender);
    }
}
