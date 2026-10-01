// OntaDev_Magnet Plugin
// Авторские права (c) 2025 OntaDev
// Лицензия: MIT

package com.ontadev.magnet;

import com.ontadev.libs.ioc.PluginIoC;
import com.ontadev.libs.plugin.OntaDev_Template;
import com.ontadev.libs.shaded.bstats.bukkit.Metrics;
import com.ontadev.libs.shaded.bstats.charts.SingleLineChart;
import com.ontadev.magnet.service.MagnetService;

public final class OntaDev_Magnet extends OntaDev_Template {

    @Override
    public void onPluginEnable(PluginIoC pluginIoC) {
        MagnetService service = pluginIoC.get(MagnetService.class);

        Metrics metrics = new Metrics(this, 23867);
        metrics.addCustomChart(new SingleLineChart("active_magnets", () -> service.getActiveMagnets().size()));
    }
}
