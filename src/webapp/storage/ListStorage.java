package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> list = new ArrayList<>();

    private Integer convertUUIDToIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)){
                return i;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void update(Resume r) {
        list.set(convertUUIDToIndex(r.getUuid()), r);
    }

    @Override
    public void save(Resume r) {
        list.add(r);
    }

    @Override
    public Resume get(String uuid) {
        return list.get(convertUUIDToIndex(uuid));
    }

    @Override
    public void delete(String uuid) {
        list.remove(convertUUIDToIndex(uuid));
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) list.toArray();
    }

    @Override
    public int size() {
        return list.size();
    }
}
