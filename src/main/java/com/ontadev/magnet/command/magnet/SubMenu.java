// OntaDev_Magnet Plugin
// Авторские права (c) 2026 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.command.magnet;

import com.ontadev.libs.command.SubCommand;
import com.ontadev.libs.message.Message;
import com.ontadev.magnet.config.MessageConfig;
import com.ontadev.magnet.menu.MagnetConfigMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SubMenu extends SubCommand {
    private final Message USAGE = new Message("<#6B7280>» <#F59E0B>/magnet menu <#4B5563>- <#CBD5E1>открыть меню настроек");
    @SuppressWarnings("FieldCanBeLocal")
    private final String PERMISSION = "magnet.menu";
    private final MagnetConfigMenu configMenu;
    private final MessageConfig messageConfig;

    public SubMenu(MagnetConfigMenu configMenu, MessageConfig messageConfig) {
        super("menu");
        this.configMenu = configMenu;
        this.messageConfig = messageConfig;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String... args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only for players");
            return;
        }

        configMenu.open((Player) sender);
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
