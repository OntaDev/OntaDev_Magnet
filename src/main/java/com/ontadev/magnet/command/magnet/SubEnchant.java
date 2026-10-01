// OntaDev_Magnet Plugin
// Авторские права (c) 2025 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.command.magnet;

import com.ontadev.libs.command.SubCommand;
import com.ontadev.libs.message.Message;
import com.ontadev.magnet.config.MessageConfig;
import com.ontadev.magnet.model.MagnetData;
import com.ontadev.magnet.service.MagnetService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SubEnchant extends SubCommand {
    private final Message USAGE = new Message(
            "<#6B7280>» <#F59E0B>/magnet enchant {Сила} {Радиус} {Лимит} <#4B5563>- <#CBD5E1>зачарование предмета в руке"
    );
    private final MagnetService magnetService;
    private final MessageConfig messageConfig;

    public SubEnchant(MagnetService magnetService, MessageConfig messageConfig) {
        super("enchant");

        this.magnetService = magnetService;
        this.messageConfig = messageConfig;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String... args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command!");
            return;
        }
        Player player = (Player) sender;

        if (args.length < 3){
            USAGE.send(sender);
            return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().isAir()){
            messageConfig.getNoItemInHand().send(sender);
            return;
        }

        MagnetData magnetData;
        try{
            double strength = Double.parseDouble(args[0]);
            int radius = Integer.parseInt(args[1]);
            int limit = Integer.parseInt(args[2]);

            magnetData = new MagnetData(radius, strength, limit);
        }catch (NumberFormatException e){
            messageConfig.getNotNumber().send(sender);
            return;
        }

        magnetService.setupMagnetData(item, magnetData);
        messageConfig.getMagnetEnchanted().send(sender);
    }

    @Override
    public String getPermission(CommandSender sender, Command command, String label, String... args) {
        return getPermission();
    }

    @Override
    public List<String> complete(CommandSender sender, String... args) {
        if (!(sender.hasPermission(getPermission()))){
            return null;
        }

        switch (args.length){
            case 1: return List.of("Сила");
            case 2: return List.of("Радиус");
            case 3: return List.of("Лимит");
            default: return null;
        }
    }

    private String getPermission(){
        return "magnet.enchant";
    }

    @Override
    public void sendUsage(CommandSender sender, Command command, String label, String... args) {
        USAGE.send(sender);
    }
}
