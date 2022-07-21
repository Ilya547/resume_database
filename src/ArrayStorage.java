import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int countResume = 0;
    private int i = 0;

    void clear() {
        while (storage[i] != null) {
            storage[i] = null;
            i++;
        }
    }

    void save(Resume r) {
        storage[countResume] = r;
        countResume++;
    }

    Resume get(String uuid) {
        while (storage[i] != null) {
            if (storage[i].toString() == uuid) {
                return storage[i];
            }
            i++;
        }
        Resume error = new Resume();
        error.uuid = "dummy";
        return error;
    }

    void delete(String uuid) {
        while (storage[i] != null) {
            if (storage[i].toString() == uuid) {
                System.arraycopy(storage, i + 1, storage, i, countResume);
            }
            i++;
        }
        countResume--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    int size() {
        for (i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
        }
        return i;
    }
}