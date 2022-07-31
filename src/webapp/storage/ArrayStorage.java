package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    public final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int countResume = 0;

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

     public void update(Resume rBefore, Resume rAfter) {
        int index = findIndex(rBefore.getUuid());
        if (index < 0) {
            System.out.println("Error. " + rBefore.getUuid() + "is missing.");
        } else {
            storage[index] = rAfter;
        }
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (countResume >= storage.length) {
            System.out.println("Error. " + r.getUuid() + " not saved due to lack of free space.");
        } else if (index > 0) {
            System.out.println("Error. " + r.getUuid() + " was saved earlier.");
        } else {
            storage[countResume] = r;
            countResume++;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Error. " + uuid + " is missing.");
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Error. " + uuid + " is missing.");
        } else {
            countResume--;
            List<Resume> list = Arrays.asList(storage);
            LinkedList<Resume> linkedList = new LinkedList<>(list);
            linkedList.remove(index);
            linkedList.toArray(storage);
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