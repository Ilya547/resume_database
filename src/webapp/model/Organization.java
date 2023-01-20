package webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Position> periods = new ArrayList<>();
    private Link homePage;
    public static final Organization EMPTY = new Organization("", "", Position.EMPTY);

    public Organization(Link homePage, List<Position> periods) {
        this.homePage = homePage;
    }

    public Organization(String name, String url, Position... Periods) {
        this(new Link(name, url), Arrays.asList(Periods));
    }

    public Organization() {
    }

    public List<Position> getPERIODS() {
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
