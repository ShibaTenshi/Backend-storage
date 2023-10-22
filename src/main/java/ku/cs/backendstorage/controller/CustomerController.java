package ku.cs.backendstorage.controller;

import ku.cs.backendstorage.exception.FileEmptyException;
import ku.cs.backendstorage.exception.ImageFormatException;
import ku.cs.backendstorage.exception.ParamEmptyException;
import ku.cs.backendstorage.exception.UserNotfoundException;
import ku.cs.backendstorage.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/image/profile/{name}")
    public String profileImage(@RequestParam MultipartFile file, @PathVariable String name) throws IOException, FileEmptyException, ImageFormatException, ParamEmptyException {
        return customerService.postCustomerImage(file, name);
    }

    @GetMapping("/image/profile/{name}")
    public String profileImage(@PathVariable String name) throws ParamEmptyException, UserNotfoundException {
        return customerService.getCustomerImage(name);
    }
}
