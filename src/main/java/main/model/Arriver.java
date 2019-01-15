package main.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.SplittableRandom;

import static java.time.temporal.ChronoUnit.MINUTES;

@AllArgsConstructor
@Setter
@Getter
public class Arriver {

    private LocalTime minTime;
    private LocalTime maxTime;

    public LocalTime arrive() {
        long arrivalRange = MINUTES.between(minTime, maxTime);
        long randomMinutes = new SplittableRandom().nextInt(0, (int) (arrivalRange + 1));
        return minTime.plus(randomMinutes, MINUTES);
    }
}
