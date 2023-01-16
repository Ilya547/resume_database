package webapp.storage;


import org.junit.Before;
import org.junit.Test;
import webapp.Config;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.ContactType;
import webapp.model.Resume;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static webapp.storage.TestData.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    static final String UUID_NOT_EXIST_OR_EXIST = "dummy";
    protected final Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

//    private static final String UUID_1 = UUID.randomUUID().toString();
//    private static final String UUID_2 = UUID.randomUUID().toString();
//    private static final String UUID_3 = UUID.randomUUID().toString();
//    private static final String UUID_4 = UUID.randomUUID().toString();
//
//    private static final Resume R1;
//    private static final Resume R2;
//    private static final Resume R3;
//    private static final Resume R4;
//
//    static {
//        R1 = TestData.fillResume(UUID_1, "Name1");
//        R2 = TestData.fillResume(UUID_2, "Name2");
//        R3 = TestData.fillResume(UUID_3, "Name3");
//        R4 = TestData.fillResume(UUID_4, "Name4");
//    }
    @Before
    public void setUp() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "New Name");
        newResume.addContact(ContactType.EMAIL, "mail1@google.com");
        newResume.addContact(ContactType.GITHUBPROFILE, "https://github.com/Ilya547");
        newResume.addContact(ContactType.PHONENUMBER, "+7 921 222-22-22");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test
    public void save() throws ExistStorageException {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);
    }

//    @Test
//    public void delete() throws NotExistStorageException {
//        storage.delete(UUID_1);
//        assertAll(
//                () -> assertSize(2),
//                () -> assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1)));
//    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        List<Resume> sortedResumes = Arrays.asList(R1, R2, R3);
        Collections.sort(sortedResumes);
        assertEquals(sortedResumes, list);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() throws NotExistStorageException {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}