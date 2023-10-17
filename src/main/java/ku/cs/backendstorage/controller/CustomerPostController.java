package ku.cs.backendstorage.controller;

import ku.cs.backendstorage.exception.FileEmptyException;
import ku.cs.backendstorage.exception.ImageFormatException;
import ku.cs.backendstorage.exception.ParamEmptyException;
import ku.cs.backendstorage.service.CustomerPostService;
import ku.cs.backendstorage.service.RestaurantPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customer")
public class CustomerPostController {
    @Autowired
    CustomerPostService customerPostService;

    @PostMapping("/image")
    public String postLogoImage(@RequestPart MultipartFile file, @RequestParam String customerUsername) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        return customerPostService.postCustomerImage(file, customerUsername);
    }
}
