// OntaDev_Magnet Plugin
// Авторские права (c) 2025 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.command.magnet;

import com.ontadev.libs.command.AbstractCommand;
import com.ontadev.libs.ioc.annotation.stereotype.RegisterCommand;
import com.ontadev.libs.message.Message;
import com.ontadev.magnet.config.Config;
import com.ontadev.magnet.config.MessageConfig;
import com.ontadev.magnet.menu.MagnetConfigMenu;
import com.ontadev.magnet.menu.MagnetItemMenu;
import com.ontadev.magnet.service.MagnetService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@RegisterCommand
public class MagnetCommand extends AbstractCommand {

    public MagnetCommand(
            MagnetService magnetService,
            Config config,
            MessageConfig messageConfig,
            Plugin plugin,
            MagnetConfigMenu configMenu,
            MagnetItemMenu itemMenu
    ) {
        this.registerSubCommand(new SubGive(magnetService, messageConfig));
        this.registerSubCommand(new SubEnchant(magnetService, messageConfig));
        this.registerSubCommand(new SubRemove(magnetService, messageConfig));
        this.registerSubCommand(new SubReload(magnetService, config, messageConfig, plugin));
        this.registerSubCommand(new SubFilter(config, messageConfig));
        this.registerSubCommand(new SubMenu(configMenu, messageConfig));
        this.registerSubCommand(new SubEdit(magnetService, messageConfig, itemMenu));
    }

    @Override
    public @NotNull String getName() {
        return "magnet";
    }

    @Override
    public @NotNull List<String> getAliases() {
        return List.of("магнит");
    }

    @Override
    protected void handle(CommandSender sender, Command command, String commandLabel, String[] args) {
        new Message("<#4B5563>[<#F59E0B>Magnet<#4B5563>] <#CBD5E1>Доступные команды:").send(sender);
        subCommands.values().forEach(subCommand -> {
            if (!sender.hasPermission(subCommand.getPermission(sender, command, commandLabel, args))) return;
            subCommand.sendUsage(sender, command, commandLabel, args);
        });
    }
}
