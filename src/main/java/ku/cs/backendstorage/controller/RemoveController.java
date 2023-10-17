package ku.cs.backendstorage.controller;

import ku.cs.backendstorage.exception.ParamEmptyException;
import ku.cs.backendstorage.service.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("remove")
public class RemoveController {
    @Autowired
    RemoveService removeService;

    @PostMapping
    public void remove(@RequestParam String type, String name) throws ParamEmptyException {
        removeService.remove(type, name);
    }
}
