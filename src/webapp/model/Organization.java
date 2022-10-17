package webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private List<Period> periods = new ArrayList<>();
    private final Link homePage;

    public Organization(Link homePage, List<Period> periods) {
        this.homePage = homePage;
        this.periods = periods;
    }
}
