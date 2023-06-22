package com.mango.demo.future.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * hello akka
 * @Author: mango
 * @Date: 2022/7/29 5:16 下午
 */
public class HelloAkka {

    static class HelloActor extends UntypedActor {
        public enum MSG{
            HELLO
        }
        @Override
        public void onReceive(Object o) throws Exception {
            if(o == MSG.HELLO){
                System.out.println("hello akka!");
            }
        }
    }
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("Hello");
        ActorRef helloActor = actorSystem.actorOf(Props.create(HelloActor.class), "helloActor");
        helloActor.tell(HelloActor.MSG.HELLO,ActorRef.noSender());
        actorSystem.shutdown();
    }
}
