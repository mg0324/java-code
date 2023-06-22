package com.github.mg0324.plugin;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class MainApplication {

    // 插件列表
    private List<Plugin> plugins = new ArrayList<>();

    // 加载插件
    public void loadPlugins() {
        // 从指定目录加载插件
        File pluginDir = new File("plugin/plugins");
        File[] pluginFiles = pluginDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".jar");
            }
        });

        // 加载插件
        for (File pluginFile : pluginFiles) {
            Plugin plugin = PluginLoader.loadPlugin(pluginFile);
            if (plugin != null) {
                plugins.add(plugin);
            }
        }
    }

    // 启动插件
    public void startPlugins() {
        for (Plugin plugin : plugins) {
            plugin.start();
        }
    }

    // 停止插件
    public void stopPlugins() {
        for (Plugin plugin : plugins) {
            plugin.stop();
        }
    }
}
