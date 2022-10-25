package webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final List<Period> periods = new ArrayList<>();
    private final Link homePage;

    public Organization(Link homePage, List<Period> periods) {
        this.homePage = homePage;
    }

    public Organization(String name, String url, Period... Periods) {
        this(new Link(name, url), Arrays.asList(Periods));
    }

    public List<Period> getPERIODS() {
        return periods;
    }

    public Link getHomePage() {
        return homePage;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "PERIODS=" + periods +
                ", homePage=" + homePage +
                '}';
    }
//not null
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return periods.equals(that.periods) && homePage.equals(that.homePage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periods, homePage);
    }
}
