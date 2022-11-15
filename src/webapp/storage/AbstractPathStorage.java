package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
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
            throw new StorageException("Path is not update", getFileName(dir), e);
        }
    }

    @Override
    protected void doSave(Resume r, Path dir) {
        try {
            Files.createFile(dir);
        } catch (IOException e) {
            throw new StorageException("Path is not save", getFileName(dir), e);
        }
        doUpdate(r, dir);
    }

    @Override
    protected boolean isExist(Path dir) {
        return Files.exists(dir);
    }

    @Override
    protected Resume doGet(Path dir) {
        try {
            return doRead((InputStream) dir);
        } catch (IOException e) {
            throw new StorageException("Path is not read", getFileName(dir), e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doDelete(Path dir) {
        try {
            Files.delete(dir);
        } catch (IOException e) {
            throw new StorageException("Path delete error", getFileName(dir), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        try {
            return Files.list(directory).map(directory -> doGet(directory)).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Path delete error", getFileName(directory), e);
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", e);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.size(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", e);
        }
    }

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    private String getFileName(Path dir) {
        return dir.getFileName().toString();
    }
}
