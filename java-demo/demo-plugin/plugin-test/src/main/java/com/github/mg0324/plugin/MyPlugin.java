package com.github.mg0324.plugin;

public class MyPlugin implements Plugin {

    private String name;

    public MyPlugin(){

    }

    public MyPlugin(String name) {
        this.name = name;
    }

    @Override
    public void start() {
        System.out.println(name + " plugin started.");
    }

    @Override
    public void stop() {
        System.out.println(name + " plugin stopped.");
    }
}
