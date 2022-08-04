package webapp.storage;

import webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    protected int findIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insert(Resume r, int index) {
        storage[countResume] = r;
    }

    @Override
    protected void remove(int index) {
        storage[index] = storage[countResume - 1];
    }
}