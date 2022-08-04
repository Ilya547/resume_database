package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int countResume = 0;

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Error. " + r.getUuid() + "is missing.");
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (countResume >= storage.length) {
            System.out.println("Error. " + r.getUuid() + " not saved due to lack of free space.");
        } else if (index > 0) {
            System.out.println("Error. " + r.getUuid() + " was saved earlier.");
        } else {
            insert(r, index);
            countResume++;
        }
    }




    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Error. " + uuid + " is missing.");
        } else {
            remove(index);
            storage[countResume - 1] = null;
            countResume--;
        }
    }



    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    public int size() {
        return countResume;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int findIndex(String uuid);

    protected abstract void insert(Resume r, int index);

    protected abstract void remove(int index);
}