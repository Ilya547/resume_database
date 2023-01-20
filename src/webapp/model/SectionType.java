package webapp.model;

public enum SectionType {
    PERSONAL("Личные качества") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": "  + value;
        }
    },
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения") {
        @Override
        public String subSection0(String value) {
            return (value == null) ? "nothing " : toHtml0(value);
        }
    },
    QUALIFICATION("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
//my code

    public String subSection(String value) {
        return (value == null) ? " " : subSection0(value);
    }

    public String subSection0(String value) {
        return (value == null) ? " " : toHtml0(value);
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
        return "<a href='" + href + "'>" + title + "</a>";
    }
}
