package webapp.model;

public enum ContactType {
    PHONENUMBER("Тел.:"),
    EMAIL("Почта"),
    LINKEDINPROFILE("Профиль LinkedIn"),
    GITHUBPROFILE("Профиль GitHub");

    private String subtitle;

    ContactType(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
