package webapp;

import webapp.model.Resume;
import webapp.storage.ArrayStorage;

public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume RESUME_1 = new Resume("uuid1", "Name_1" );
        Resume RESUME_2 = new Resume("uuid2", "Name_2" );
        Resume RESUME_3 = new Resume("uuid3", "Name_3");

        ARRAY_STORAGE.save(RESUME_1);
        ARRAY_STORAGE.save(RESUME_2);
        ARRAY_STORAGE.save(RESUME_3);


        System.out.println("Get RESUME_1: " + ARRAY_STORAGE.get(RESUME_1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        ARRAY_STORAGE.update(RESUME_2);
        printAll();
        ARRAY_STORAGE.delete(RESUME_1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
