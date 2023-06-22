package com.github.mg0324.plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;

public class PluginLoader {

    // 从jar包加载插件
    public static Plugin loadPlugin(File pluginFile) {
        try {
            URLClassLoader loader = new URLClassLoader(new URL[] { pluginFile.toURI().toURL() });
            ServiceLoader<Plugin> serviceLoader = ServiceLoader.load(Plugin.class, loader);
            for (Plugin plugin : serviceLoader) {
                return plugin;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
