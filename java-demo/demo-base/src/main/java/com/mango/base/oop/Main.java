package com.mango.base.oop;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Player xm = new Player("小明");
        Player xh = new Player("小红");
        Player xg = new Player("小刚");
        xm.play(game);
        xh.play(game);
        xg.play(game);
        game.exit();
    }
}
