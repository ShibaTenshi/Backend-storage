package ku.cs.backendstorage.service;

import ku.cs.backendstorage.common.Directory;
import ku.cs.backendstorage.exception.FileEmptyException;
import ku.cs.backendstorage.exception.ImageFormatException;
import ku.cs.backendstorage.exception.ParamEmptyException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class CustomerPostService {

    public String postCustomerImage(MultipartFile file, String customerUsername) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        if(customerUsername.isEmpty()) throw new ParamEmptyException();
        if(file.isEmpty()) throw new FileEmptyException();
        if(!file.getContentType().contains("image")) throw new ImageFormatException();
        File directory = new File(Directory.customerParent + customerUsername);
        FileSystemUtils.deleteRecursively(directory);
        directory.mkdirs();

        String imageName = "profile-" + UUID.randomUUID().toString().replace("-", "");
        Path path = Path.of(directory.getPath() + "/" + imageName + file.getContentType().replace("image/", "."));
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return path.toString().substring(8);
    }
}