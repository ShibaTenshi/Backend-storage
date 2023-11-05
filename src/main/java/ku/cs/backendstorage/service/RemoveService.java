package ku.cs.backendstorage.service;

import ku.cs.backendstorage.common.Directory;
import ku.cs.backendstorage.exception.ParamEmptyException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.File;

@Service
public class RemoveService {
    public void remove(String type, String name) throws ParamEmptyException {
        System.out.println(type + " " + name);
        if (!type.equals("restaurant") && !type.equals("customer")) throw new ParamEmptyException();
        if (name.isEmpty()) throw new ParamEmptyException();
        String newPath = Directory.parent + type + "/" + name;
        System.out.println("Delete " + newPath);
        FileSystemUtils.deleteRecursively(new File(newPath));
    }
}
