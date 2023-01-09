package webapp.model;
//

import webapp.util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public class Position implements Serializable {
    private LocalDate startDate;
    private LocalDate endDate;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    private String title;
    private String description;

    public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, " startDate must not be null");
        Objects.requireNonNull(endDate, " endDate must not be null");
        Objects.requireNonNull(title, " title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description == null ? "" : description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Position() {
    }

    public Position(int startYear, Month startMonth, String title, String description) {
        this(DateUtil.of(startYear, startMonth), DateUtil.NOW, title, description);
    }

    public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
        this(DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth), title, description);
    }

    @Override
    public String toString() {
        return "Period{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position period = (Position) o;
        return startDate.equals(period.startDate) && endDate.equals(period.endDate) && title.equals(period.title) && description.equals(period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, title, description);
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
