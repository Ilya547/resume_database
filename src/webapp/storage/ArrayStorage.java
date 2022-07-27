package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int countResume = 0;

    public void clear() {
        Arrays.fill(storage, 0, countResume + 1, null);
        countResume = 0;
    }

    public void update(Resume r, String s) {
        if (checkPresent(r.getUuid())) {
            for (int i = 0; i < countResume; i++) {
                if (storage[i].getUuid() == r.getUuid()) {
                    r.setUuid(s);
                    break;
                }
            }
        } else {
            System.out.println("Error. " + r.getUuid() + "is missing.");
        }

    }

    public void save(Resume r) {
        if (countResume <= storage.length) {
            if (!checkPresent(r.getUuid())) {
                storage[countResume] = r;
                countResume++;
            } else {
                System.out.println("Error. " + r.getUuid() + " was saved earlier.");
            }
        } else {
            System.out.println("Error. " + r.getUuid() + " not saved due to lack of free space.");
        }

    }

    public Resume get(String uuid) {
        if (checkPresent(uuid)) {
            for (int i = 0; i < countResume; i++) {
                if (storage[i].toString() == uuid) {
                    return storage[i];
                }
            }
        } else {
            System.out.println("Error. " + uuid + " is missing.");
        }
        return null;
    }

    public void delete(String uuid) {
        if (checkPresent(uuid)) {
            for (int i = 0; i < countResume; i++) {
                if (uuid == storage[i].toString()) {
                    countResume--;
                    System.arraycopy(storage, i + 1, storage, i, countResume - i);
                }
            }
        } else {
            System.out.println("Error. " + uuid + "is missing.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    public int size() {
        return countResume;
    }

    private boolean checkPresent(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (uuid == storage[i].toString()) {
                return true;
            }
        }
        return false;
    }
}