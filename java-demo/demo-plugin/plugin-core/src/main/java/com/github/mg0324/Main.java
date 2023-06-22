package com.github.mg0324;

import com.github.mg0324.plugin.MainApplication;

public class Main {
    public static void main(String[] args) {
        MainApplication mainApplication = new MainApplication();
        mainApplication.loadPlugins();
        mainApplication.startPlugins();
        mainApplication.stopPlugins();
    }
}