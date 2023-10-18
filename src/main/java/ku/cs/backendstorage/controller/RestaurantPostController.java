package ku.cs.backendstorage.controller;

import ku.cs.backendstorage.common.RestaurantImage;
import ku.cs.backendstorage.exception.FileEmptyException;
import ku.cs.backendstorage.exception.ImageFormatException;
import ku.cs.backendstorage.exception.ParamEmptyException;
import ku.cs.backendstorage.service.RestaurantPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/restaurant")
public class RestaurantPostController {

    @Autowired
    RestaurantPostService restaurantPostService;

    @PostMapping("/image/env/{name}")
    public String postImage(@RequestPart MultipartFile file, @PathVariable String name) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        return restaurantPostService.postRestaurantImage(file, name, RestaurantImage.ENV);
    }

    @PostMapping("/image/logo/{name}")
    public String postLogoImage(@RequestPart MultipartFile file, @PathVariable String name) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        return restaurantPostService.postRestaurantImage(file, name, RestaurantImage.LOGO);
    }

    @PostMapping("/image/menu/{name}")
    public String postMenuImage(@RequestPart MultipartFile file, @PathVariable String name) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        return restaurantPostService.postRestaurantImage(file, name, RestaurantImage.MENU);
    }
}
