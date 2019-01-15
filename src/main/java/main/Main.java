package main;

import main.model.Arriver;
import main.model.ArriverExecutor;
import main.view.MyFrame;

import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
//        Arriver bus = new Arriver(LocalTime.of(7, 55), LocalTime.of(8, 2));
//        Arriver jasiu = new Arriver(LocalTime.of(7, 55), LocalTime.of(8, 2));
//        System.out.println(ArriverExecutor.arriveTimes(bus, jasiu, 1000));
        new MyFrame();
    }
}
