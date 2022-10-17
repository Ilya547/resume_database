package webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, " startDate must be not null");
        Objects.requireNonNull(endDate, " endDate must be not null");
        Objects.requireNonNull(title, " title must be not null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }
}
