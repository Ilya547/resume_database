package webapp.storage;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    protected static final String UUID_NOT_EXIST_OR_EXIST = "dummy";

    private static final Resume RESUME_1 = new Resume(UUID_1, "Name_1" );
    private static final Resume RESUME_2 = new Resume(UUID_2, "Name_2");
    private static final Resume RESUME_3 = new Resume(UUID_3, "Name_3");
    private static final Resume RESUME_4 = new Resume(UUID_4, "Name_4");

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws NotExistStorageException {
        Resume temp = RESUME_1;
        storage.update(RESUME_1);
        assertAll(
                () -> assertSame(temp, RESUME_1),
                () -> assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST_OR_EXIST)));
    }

    @Test
    public void save() throws ExistStorageException {
        storage.save(RESUME_4);
        assertAll(
                () -> assertSize(4),
                () -> assertGet(RESUME_4));
    }

    @Test
    public void delete() throws NotExistStorageException {
        System.out.println("SIZE = " + storage.size());
        storage.delete(UUID_1);
        System.out.println("SIZE = " + storage.size());
        assertAll(
                () -> assertSize(2),
                () -> assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1)));
    }

    @Test
    public Resume[] getAllSorted() {
        List<Resume> expectedList = storage.getAllSorted();
        assertAll(
                () -> assertEquals(expectedList, Arrays.asList(RESUME_1, RESUME_2, RESUME_3)),
                () -> assertSize(3));
        return null;
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() throws NotExistStorageException {
        assertAll(
                () -> assertGet(RESUME_1),
                () -> assertGet(RESUME_2),
                () -> assertGet(RESUME_3),
                () -> assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST_OR_EXIST)));
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}