// OntaDev_Magnet Plugin
// Авторские права (c) 2025 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.command.magnet;

import com.ontadev.libs.command.SubCommand;
import com.ontadev.libs.config.YamlConfigLoader;
import com.ontadev.magnet.config.Config;
import com.ontadev.magnet.config.MessageConfig;
import com.ontadev.magnet.service.MagnetService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class SubReload extends SubCommand {
    private final MagnetService magnetService;
    private final Config config;
    private final MessageConfig messageConfig;
    private final Plugin plugin;

    public SubReload(
            MagnetService magnetService,
            Config config,
            MessageConfig messageConfig,
            Plugin plugin
    ) {
        super("reload");

        this.magnetService = magnetService;
        this.config = config;
        this.messageConfig = messageConfig;
        this.plugin = plugin;
    }


    private String getPermission(){
        return "magnet.reload";
    }

    @Override
    public String getPermission(CommandSender sender, Command command, String label, String... args) {
        return getPermission();
    }

    @Override
    public void noPermission(CommandSender sender, Command command, String label, String... args) {
        messageConfig.getNoPermission().send(sender);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String... args) {
        YamlConfigLoader freshLoader = new YamlConfigLoader(plugin.getDataFolder().toPath(), null);

        config.applyFrom(freshLoader.loadFromClass(Config.class));
        messageConfig.applyFrom(freshLoader.loadFromClass(MessageConfig.class));

        magnetService.reload();

        messageConfig.getPluginReloaded().send(sender);
    }

    @Override
    public void sendUsage(CommandSender sender, Command command, String label, String... args) {
        if (!sender.hasPermission(getPermission())) {
            noPermission(sender, command, label, args);
            return;
        }

        messageConfig.getPluginReloadUsage().send(sender);
    }
}
