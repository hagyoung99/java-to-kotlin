package java.travelator;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public class LegsJava {

    public static Optional<Leg> findLongestLegOver(
            List<Leg> legs,
            Duration duration
    ) {
        Leg result = null;
        for (Leg leg : legs) {
            if (isLongerThan(leg, duration))
                if (result == null ||
                        isLongerThan(leg, result.getPlannedDuration())
                ) {
                    result = leg;
                }
        }
        return Optional.ofNullable(result);
    }

    private static boolean isLongerThan(Leg leg, Duration duration) {
        return leg.getPlannedDuration().compareTo(duration) > 0;
    }
}


class Leg{
    private final String description;
    private final ZonedDateTime plannedStart;
    private final ZonedDateTime plannedEnd;

    Leg(String description, ZonedDateTime plannedStart, ZonedDateTime plannedEnd) {
        this.description = description;
        this.plannedStart = plannedStart;
        this.plannedEnd = plannedEnd;
    }

    public Duration getPlannedDuration() {
        return Duration.between(plannedStart, plannedEnd);
    }
}
