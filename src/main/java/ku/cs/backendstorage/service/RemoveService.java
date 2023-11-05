package ku.cs.backendstorage.service;

import ku.cs.backendstorage.common.Directory;
import ku.cs.backendstorage.exception.ParamEmptyException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;

@Service
public class RemoveService {
    public void remove(String type, String name) throws ParamEmptyException {
        System.out.println(type + " " + name);
        if (!type.equals("restaurant") && !type.equals("customer")) throw new ParamEmptyException();
        if (name.isEmpty()) throw new ParamEmptyException();
        String newPath = Directory.parent + type + "/" + name;
        System.out.println("Delete " + newPath);
        deleteDir(new File(newPath));

    }

    private void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (! Files.isSymbolicLink(f.toPath())) {
                    deleteDir(f);
                }
            }
        }
        file.delete();
    }
}
