package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int countResume = 0;

    public void clear() {
        Arrays.fill(storage, 0, countResume + 1, null);
        countResume = 0;
    }

    public void update(Resume r, String s) {
        try {
            int index = findIndex(r.getUuid());
            storage[index].setUuid(s);
        } catch (NumberFormatException e) {
            System.out.println("Error. " + r.getUuid() + "is missing.");
        }
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (countResume > storage.length) {
            System.out.println("Error. " + r.getUuid() + " not saved due to lack of free space.");
        } else if (index > 0) {
            System.out.println("Error. " + r.getUuid() + " was saved earlier.");
        } else {
            storage[countResume] = r;
            countResume++;
        }
    }

    public Resume get(String uuid) {
        try {
            int index = findIndex(uuid);
            return storage[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error. " + uuid + " is missing.");
        }
        return null;
    }

    public void delete(String uuid) {
        try {
            int index = findIndex(uuid);
            countResume--;
            System.arraycopy(storage, index + 1, storage, index, countResume);
        } catch (NumberFormatException e) {
            System.out.println("Error. " + uuid + " is missing.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    public int size() {
        return countResume;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}