package jk_5.nailed.api.mappack.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.Nonnull;

import jk_5.nailed.api.util.Checks;

/**
 * No description given
 *
 * @author jk-5
 */
public class DirectoryMount implements IMount {

    private final File root;
    private boolean created;

    public DirectoryMount(@Nonnull File root) {
        Checks.notNull(root, "root");
        this.root = root;
        this.created = root.exists();
    }

    @Override
    public boolean exists(@Nonnull String path) {
        Checks.notNull(path, "path");
        if(!created){
            return path.length() == 0;
        }
        return getRealPath(path).exists();
    }

    @Override
    public boolean isDirectory(@Nonnull String path) {
        Checks.notNull(path, "path");
        if(!created){
            return path.length() == 0;
        }
        File file = getRealPath(path);
        return file.exists() && file.isDirectory();
    }

    @Override
    public void list(@Nonnull String path, @Nonnull List<String> contents) throws IOException {
        Checks.notNull(path, "path");
        Checks.notNull(contents, "contents");
        if(!created){
            if(path.length() != 0){
                throw new IOException("Not a directory");
            }
        }else{
            File file = getRealPath(path);
            if(file.exists() && file.isDirectory()){
                for(String sub : file.list()){
                    if(new File(file, sub).exists()){
                        contents.add(sub);
                    }
                }
            }else{
                throw new IOException("Not a directory");
            }
        }
    }

    @Override
    public long getSize(@Nonnull String path) throws IOException {
        Checks.notNull(path, "path");
        if(!created){
            if(path.length() == 0){
                return 0;
            }
        }else{
            File file = getRealPath(path);
            if(file.exists()){
                if(file.isDirectory()){
                    return 0;
                }
                return file.length();
            }
        }
        throw new IOException("No such file");
    }

    @Nonnull
    @Override
    public InputStream openForRead(@Nonnull String path) throws IOException {
        if(created){
            File file = getRealPath(path);
            if(file.exists() && !file.isDirectory()){
                return new FileInputStream(file);
            }
        }
        throw new IOException("No such file");
    }

    @Nonnull
    private File getRealPath(@Nonnull String path){
        return new File(root, path);
    }
}
