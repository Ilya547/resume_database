package webapp.storage;

import com.sun.org.apache.xpath.internal.operations.String;
import webapp.exception.StorageException;
import webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(java.lang.String.valueOf(dir));
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not a directory");
        }
    }

    @Override
    protected void doUpdate(Resume r, Path dir) {
        try {
            doWrite(r, (OutputStream) dir);
        } catch (IOException e) {
            throw new StorageException("Path is not update", dir.toString(), e);
        }
    }

    @Override
    protected void doSave(Resume r, Path dir) {
        try {
            dir.toFile().createNewFile();
        } catch (IOException e) {
            throw new StorageException("Path is not save", dir.toString(), e);
        }
    }

    @Override
    protected boolean isExist(Path dir) {
        return dir.toFile().exists();
    }

    @Override
    protected Resume doGet(Path dir) {
        try {
            return doRead((InputStream) dir);
        } catch (IOException e) {
            throw new StorageException("Path is not read", dir.toString(), e);
        }
    }

    @Override
    protected Path getSearchKey(java.lang.String uuid) {
        Path dir = Paths.get(java.lang.String.valueOf(uuid));
        return dir;
    }

    @Override
    protected void doDelete(Path dir) {
        if (!dir.toFile().delete()) {
            throw new StorageException("Path delete error", dir.toFile().getName());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] Paths = directory.toFile().listFiles();
        if (Paths == null) {
            throw new StorageException("Directory read error", null);
        }
        List<Resume> list = new ArrayList<>(Paths.length);
        for (File Path : Paths) {
            list.add(doGet(Path.toPath()));
        }
        return list;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.size(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
    }

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

}
