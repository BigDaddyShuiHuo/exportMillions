package cn.hwz.learn.exportmillions.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import com.sun.management.OperatingSystemMXBean;

/**
 * @author needleHuo
 * @version jdk11
 * @description
 */
@Slf4j
public class MonitorUtil {

    public static void getProcessCpuLoad() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(
                OperatingSystemMXBean.class);
        log.debug("进程cpu占用率:{}",osBean.getProcessCpuLoad() * 100);
    }

    public static void getSystemCpuLoad() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(
                OperatingSystemMXBean.class);
        log.debug("系统cpu占用率:{}",osBean.getSystemCpuLoad() * 100);
    }

    public static void getMemoryUsage() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapUsage = memoryMXBean.getNonHeapMemoryUsage();
        log.debug("堆内存占用情况:{}/{}",formatBytes(heapUsage.getUsed()),formatBytes(heapUsage.getMax()));
        log.debug("非堆内存情况:{}", formatBytes(nonHeapUsage.getUsed()));

    }

    private static String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        String pre = "KMGTPE".charAt(exp-1) + "i";
        return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
    }


}
