package ku.cs.backendstorage.controller;

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

    @PostMapping("/image")
    public String postImage(@RequestPart MultipartFile file, @RequestParam String restaurantName) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        return restaurantPostService.postRestaurantImage(file, restaurantName);
    }

    @PostMapping("/image/logo")
    public String postLogoImage(@RequestPart MultipartFile file, @RequestParam String restaurantName) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        return restaurantPostService.postRestaurantLogoImage(file, restaurantName);
    }
}
