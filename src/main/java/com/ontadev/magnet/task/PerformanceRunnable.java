// OntaDev_Magnet Plugin
// Авторские права (c) 2025 OntaDev
// Лицензия: MIT

package com.ontadev.magnet.task;

import com.ontadev.magnet.service.MagnetService;
import com.sun.management.OperatingSystemMXBean;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.management.ManagementFactory;

public class PerformanceRunnable extends BukkitRunnable {
    private final double cpuLimit;
    private final double memoryLimit;
    private final OperatingSystemMXBean osBean;
    private final MagnetService magnetService;
    private final Runtime runtime;

    public PerformanceRunnable(double cpuLimit, double memoryLimit, MagnetService service) {
        this.cpuLimit = cpuLimit;
        this.memoryLimit = memoryLimit;
        this.osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        this.magnetService = service;
        this.runtime = Runtime.getRuntime();


    }


    @Override
    public void run() {
        double cpu = osBean.getSystemCpuLoad() * 100;

        if (cpu > cpuLimit) {
            magnetService.setWorkingMagnet(false);
            return;
        }

        long usedRam = runtime.totalMemory() - runtime.freeMemory();
        double ram = (double) usedRam / runtime.maxMemory() * 100;

        if (ram > memoryLimit) {
            magnetService.setWorkingMagnet(false);
            return;
        }

        magnetService.setWorkingMagnet(true);
    }
}
