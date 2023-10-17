package ku.cs.backendstorage.service;

import ku.cs.backendstorage.common.Directory;
import ku.cs.backendstorage.exception.FileEmptyException;
import ku.cs.backendstorage.exception.ImageFormatException;
import ku.cs.backendstorage.exception.ParamEmptyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class RestaurantPostService {

    public String postRestaurantImage(MultipartFile file, String restaurantName) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        if(restaurantName.isEmpty()) throw new ParamEmptyException();
        if(file.isEmpty()) throw new FileEmptyException();
        if(!file.getContentType().contains("image")) throw new ImageFormatException();
        File directory = new File(Directory.restaurantParent + restaurantName);
        directory.mkdirs();

        String imageName = String.valueOf(UUID.randomUUID()).replace("-", "");
        Path path = Path.of(directory.getPath() + "/" + imageName + file.getContentType().replace("image/", "."));
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return path.toString().substring(8);
    }

    public String postRestaurantLogoImage(MultipartFile file, String restaurantName) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        if(restaurantName.isEmpty()) throw new ParamEmptyException();
        if(file.isEmpty()) throw new FileEmptyException();
        if(!file.getContentType().contains("image")) throw new ImageFormatException();
        File directory = new File(Directory.restaurantParent + restaurantName);
        directory.mkdirs();

        String imageName = "logo";
        Path path = Path.of(directory.getPath() + "/" + imageName + file.getContentType().replace("image/", "."));
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return path.toString().substring(8);
    }
}