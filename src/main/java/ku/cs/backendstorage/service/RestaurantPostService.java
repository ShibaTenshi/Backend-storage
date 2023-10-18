package ku.cs.backendstorage.service;

import ku.cs.backendstorage.common.Directory;
import ku.cs.backendstorage.common.RestaurantImage;
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
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantPostService {

    public String postRestaurantImage(MultipartFile file, String restaurantName, RestaurantImage image) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        if (restaurantName.isEmpty()) throw new ParamEmptyException();
        if (file.isEmpty()) throw new FileEmptyException();
        if (!file.getContentType().contains("image")) throw new ImageFormatException();
        File directory = new File(Directory.restaurantParent + restaurantName);
        directory.mkdirs();

        String imageName;
        if (image == RestaurantImage.LOGO) {
            System.out.println(Arrays.toString(directory.list()));
            Optional<String> oldLogo = Arrays.stream(directory.list()).filter(string -> string.startsWith("logo")).findFirst();
            if (oldLogo.isPresent()) {
                Files.delete(Path.of(directory.getPath() + "/" + oldLogo.get()));
            }
            imageName = "logo-" + UUID.randomUUID().toString().replace("-", "");
        } else if (image == RestaurantImage.MENU) imageName = "menu-" + UUID.randomUUID().toString().replace("-", "");
        else imageName = "env-" + UUID.randomUUID().toString().replace("-", "");
        Path path = Path.of(directory.getPath() + "/" + imageName + file.getContentType().replace("image/", "."));
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return path.toString().substring(8);
    }
}