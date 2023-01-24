package webapp.model;

public enum ContactType {
    PHONENUMBER("Тел."),
    EMAIL("Почта") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("mailto" + value, value);
        }
    },
    LINKEDINPROFILE("Профиль LinkedIn") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("LinkedIn:" + value, value);
        }
    },
    GITHUBPROFILE("Профиль GitHub") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("GitHub:" + value, value);
        }
    };

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public String toLink(String href) {
        return toLink(href, title);
    }

    public static String toLink(String href, String title) {
        return "<a class=\"contact-link\" href='" + href + "'>" + title + "</a>";
    }
}
