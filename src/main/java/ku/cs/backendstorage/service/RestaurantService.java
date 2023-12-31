package ku.cs.backendstorage.service;

import ku.cs.backendstorage.common.Directory;
import ku.cs.backendstorage.common.RestaurantImage;
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
import java.util.*;

@Service
public class RestaurantService {

    public String postRestaurantImage(MultipartFile file, String username, RestaurantImage image) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        if (username.isEmpty()) throw new ParamEmptyException();
        if (file.isEmpty()) throw new FileEmptyException();
        if (!file.getContentType().contains("image")) throw new ImageFormatException();
        File directory = new File(Directory.restaurantParent + username);
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

        return "OK";
    }

    public String getLogoImage(String name) throws ParamEmptyException {
        if(name.isEmpty()) throw new ParamEmptyException();
        File directory = new File(Directory.restaurantParent + name);
        if(!directory.exists()) return Directory.defaultImage;

        Optional<String> logo = Arrays.stream(directory.list()).filter(string -> string.startsWith("logo")).findFirst();
        return logo.map(string -> (directory.getPath() + "/" + string).substring(7)).orElseGet(() -> Directory.defaultImage);
    }

    public List<String> getEnvImage(String name) throws ParamEmptyException {
        if(name.isEmpty()) throw new ParamEmptyException();
        File directory = new File(Directory.restaurantParent + name);
        if(!directory.exists()) return Collections.singletonList(Directory.defaultImage);

        List<String> envs = new ArrayList<>(Arrays.stream(directory.list()).filter(string -> string.startsWith("env")).toList());
        if(envs.isEmpty()) return Collections.singletonList(Directory.defaultImage);
        envs.replaceAll(string -> (directory.getPath() + "/" + string).substring(7));
        return envs;
    }

    public List<String> getMenuImage(String name) throws ParamEmptyException {
        if(name.isEmpty()) throw new ParamEmptyException();
        File directory = new File(Directory.restaurantParent + name);
        if(!directory.exists()) return Collections.singletonList(Directory.defaultImage);

        List<String> menus = new ArrayList<>(Arrays.stream(directory.list()).filter(string -> string.startsWith("menu")).toList());
        if(menus.isEmpty()) return Collections.singletonList(Directory.defaultImage);
        menus.replaceAll(string -> (directory.getPath() + "/" + string).substring(7));
        return menus;
    }
}