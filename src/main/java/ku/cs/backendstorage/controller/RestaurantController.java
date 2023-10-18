package ku.cs.backendstorage.controller;

import ku.cs.backendstorage.common.RestaurantImage;
import ku.cs.backendstorage.exception.FileEmptyException;
import ku.cs.backendstorage.exception.ImageFormatException;
import ku.cs.backendstorage.exception.ParamEmptyException;
import ku.cs.backendstorage.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/image/env/{name}")
    public String envImage(@RequestPart MultipartFile file, @PathVariable String name) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        return restaurantService.postRestaurantImage(file, name, RestaurantImage.ENV);
    }

    @PostMapping("/image/logo/{name}")
    public String logoImage(@RequestPart MultipartFile file, @PathVariable String name) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        return restaurantService.postRestaurantImage(file, name, RestaurantImage.LOGO);
    }

    @PostMapping("/image/menu/{name}")
    public String menuImage(@RequestPart MultipartFile file, @PathVariable String name) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        return restaurantService.postRestaurantImage(file, name, RestaurantImage.MENU);
    }

    @GetMapping("/image/logo/{name}")
    public String logoImage(@PathVariable String name) throws ParamEmptyException {
        return restaurantService.getLogoImage(name);
    }

    @GetMapping("/image/menu/{name}")
    public List<String> menuImage(@PathVariable String name) throws ParamEmptyException {
        return restaurantService.getMenuImage(name);
    }

    @GetMapping("/image/env/{name}")
    public List<String> envImage(@PathVariable String name) throws ParamEmptyException {
        return restaurantService.getEnvImage(name);
    }
}
