package ku.cs.backendstorage.controller;

import ku.cs.backendstorage.exception.FileEmptyException;
import ku.cs.backendstorage.exception.ImageFormatException;
import ku.cs.backendstorage.exception.ParamEmptyException;
import ku.cs.backendstorage.service.CustomerPostService;
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

    @PostMapping("/image/profile/{name}")
    public String postLogoImage(@RequestParam MultipartFile file, @PathVariable String name) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        return customerPostService.postCustomerImage(file, name);
    }
}
