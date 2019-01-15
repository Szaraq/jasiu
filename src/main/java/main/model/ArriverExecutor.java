package main.model;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ArriverExecutor {

    public static Map<Boolean, Long> arriveTimes(Arriver bus, Arriver jasiu, int times) {
        List<Boolean> result = new LinkedList<>();
        for (int i = 0; i < times; i++) {
            result.add(arriveAndCheckIfJasiuIsOnTime(bus, jasiu));
        }
        return new HashMap<Boolean, Long>() {{
            put(true, result.stream().filter(elem -> elem).count());
            put(false, result.stream().filter(elem -> !elem).count());
        }};
    }

    private static boolean arriveAndCheckIfJasiuIsOnTime(Arriver bus, Arriver jasiu) {
        LocalTime busTime = bus.arrive();
        LocalTime jasiuTime = jasiu.arrive();
        return jasiuTime.isBefore(busTime) || jasiuTime.equals(busTime);
    }
}
