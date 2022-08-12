package webapp.storage;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;


    private static final String UUID_1 = "uuid1";
    public static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    public static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    public static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    public static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    void update() throws Exception {
        storage.update(RESUME_1);
        assertSize(3);
    }

    @Test
    void save() throws Exception {
        storage.save(RESUME_4);
        assertAll(
                () -> assertSize(4),
                () -> assertEquals(RESUME_4, storage.get(UUID_4)),
                () -> getNotExist());
    }

    @Test
    void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
    }

    @Test
    void getAll() throws Exception {
        Resume[] array = storage.getAll();
        assertAll(
                () -> assertEquals(RESUME_1, array[0]),
                () -> assertEquals(RESUME_2, array[1]),
                () -> assertEquals(RESUME_3, array[2]),
                () -> assertSize(3));
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    void get() throws Exception {
        assertEquals(RESUME_3, storage.get(UUID_3));
    }

    @Test
    void getNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () -> storage.get(" dummy "));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}