// OntaDev_Magnet Plugin
// Авторские права (c) 2025 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.command.magnet;

import com.ontadev.libs.command.SubCommand;
import com.ontadev.libs.message.Message;
import com.ontadev.magnet.config.MessageConfig;
import com.ontadev.magnet.service.MagnetService;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class SubRemove extends SubCommand {
    private final Message USAGE = new Message(
            "<#6B7280>» <#F59E0B>/magnet remove <#4B5563>- <#CBD5E1>удаляет механику магнита с предмета в руке"
    );
    private final Message SUCCESS = new Message("<#4B5563>[<#34D399>✔<#4B5563>] <#CBD5E1>Эффект магнита успешно <#34D399>снят");
    @SuppressWarnings("FieldCanBeLocal")
    private final String PERMISSION = "magnet.remove";
    private final MagnetService magnetService;
    private final MessageConfig messageConfig;

    public SubRemove(MagnetService magnetService, MessageConfig messageConfig) {
        super("remove");
        this.magnetService = magnetService;
        this.messageConfig = messageConfig;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String... args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("Only for players");
            return;
        }
        Player player = (Player) sender;

        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();

        if (item.getType() == Material.AIR) {
            messageConfig.getNoItemInHand().send(sender);
            return;
        }

        magnetService.removeMagnetData(item);
        SUCCESS.send(sender);
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
