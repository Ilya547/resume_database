package webapp.storage;

import webapp.model.*;

import java.time.Month;

public  class ResumeTestData extends AbstractStorageTest {
    public ResumeTestData(Storage storage) {
        super(storage);
    }

    public static Resume fillResume(String uuid, String fullName) {
        Resume RESUME = new Resume(uuid, fullName);

        RESUME.addContact(ContactType.EMAIL, "mail1@ya.ru");
        RESUME.addContact(ContactType.PHONENUMBER, "11111");
        RESUME.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        RESUME.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        RESUME.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        RESUME.addSection(SectionType.QUALIFICATION, new ListSection("Java", "SQL", "JavaScript"));
        RESUME.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Position(2005, Month.JANUARY, "position1", "content1"),
                                new Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        RESUME.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        RESUME.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Position(2015, Month.JANUARY, "position1", "content1"))));
        return RESUME;
    }










//    public void fill(String uuid, String fullName) {
//
//        RESUME.addContact(ContactType.PHONENUMBER, "89063477954");
//        RESUME.addContact(ContactType.EMAIL, "ilya547@bk.ru");
//        RESUME.addContact(ContactType.LINKEDINPROFILE, "LINKEDINPROFILE_Ilya");
//        RESUME.addContact(ContactType.GITHUBPROFILE, "https://github.com/Ilya547");
//        RESUME.addSection(SectionType.PERSONAL, new TextSection("Personal"));
//        RESUME.addSection(SectionType.OBJECTIVE, new TextSection("Objective"));
//        List<String> achievementList = new ArrayList<>();
//        RESUME.addSection(SectionType.ACHIEVEMENT, new ListSection(achievementList));
//        List<String> qualificationList = new ArrayList<>();
//        RESUME.addSection(SectionType.QUALIFICATION, new ListSection(qualificationList));
//        Period[] periodArr = new Period[0];
//        RESUME.addSection(SectionType.EXPERIENCE, new OrganizationSection(
//                new Organization("Organization", "url", periodArr)));
//        RESUME.addSection(SectionType.EDUCATION, new OrganizationSection(
//                new Organization("OrganizationName", "url", periodArr)));
//    }
}
