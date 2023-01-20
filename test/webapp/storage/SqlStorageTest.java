package webapp.storage;

import webapp.main.Config;

public class SqlStorageTest extends AbstractStorageTest{
    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}
