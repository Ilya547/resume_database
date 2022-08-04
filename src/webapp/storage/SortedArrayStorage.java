package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, countResume, searchKey);
    }

    @Override
    protected void insert(Resume r, int index) {
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, countResume - insertIndex);
        storage[insertIndex] = r;
    }

    @Override
    protected void remove(int index) {
        int numElement = countResume - index - 1;
        if (numElement > 0) {
            System.arraycopy(storage, index + 1, storage, index, numElement);
        }
    }
}
