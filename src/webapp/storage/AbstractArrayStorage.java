package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int countResume = 0;

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected void doSave(Resume r, Object index) {
        if (countResume >= storage.length) {
            throw new StorageException("Storage overflow ", r.getUuid());
        } else {
            insertResume(r, (Integer) index);
            countResume++;
        }
    }

    @Override
    protected void doDelete(Object index) {
        removeResume((Integer) index);
        storage[countResume - 1] = null;
        countResume--;
    }
    @Override
    public List<Resume> doCopyAll() {
        //not add/remove
        return Arrays.asList(Arrays.copyOf(storage, countResume));
    }

    public int size() {
        return countResume;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insertResume(Resume r, int index);

    protected abstract void removeResume(int index);
}