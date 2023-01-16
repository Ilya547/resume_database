package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;
import webapp.Serialization.SerializationStrategy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final SerializationStrategy serializationStrategy;

    protected PathStorage(String dir, SerializationStrategy serializationStrategy) {
        Objects.requireNonNull(dir, "directory must not be null");
        this.serializationStrategy = serializationStrategy;
        directory = Paths.get(dir);
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not a directory");
        }
    }

    @Override
    protected void doUpdate(Resume r, Path dir) {
        try {
            serializationStrategy.doWrite(r, new BufferedOutputStream(Files.newOutputStream(dir)));
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
            return serializationStrategy.doRead(new BufferedInputStream(Files.newInputStream(dir)));
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
        return getFilesList().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    private String getFileName(Path dir) {
        return dir.getFileName().toString();
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", e);
        }
    }
}
