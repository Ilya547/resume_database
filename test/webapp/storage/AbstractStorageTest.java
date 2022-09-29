package webapp.storage;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.exception.StorageException;
import webapp.model.Resume;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST_OR_EXIST = "dummy";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);

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
        Resume[] emptyArray = new Resume[storage.size()];
        assertSize(0);
//        assertArrayEquals(storage.getAll(), emptyArray);
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
        storage.delete(UUID_1);
        assertAll(
                () -> assertSize(2),
                () -> assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1)));
    }

    @Test
    public Resume[] getAll() {
        Resume[] expectedArray = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        assertAll(
                () -> assertArrayEquals(expectedArray, storage.getAll()),
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

    @Test
    public void saveOverflow() {
        storage.clear();
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        assertThrows(StorageException.class, () -> storage.get(UUID_NOT_EXIST_OR_EXIST));
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}