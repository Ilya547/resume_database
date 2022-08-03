package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{
        @Override
        protected int findIndex(String uuid) {
            Resume searchKey = new Resume();
            searchKey.setUuid(uuid);
            return Arrays.binarySearch(storage, 0, countResume, searchKey);
        }
}
