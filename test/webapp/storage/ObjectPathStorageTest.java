package webapp.storage;

import webapp.storage.Serialization.ObjectStreamSerialization;

public class ObjectPathStorageTest extends AbstractStorageTest{
    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerialization()));
    }
}
