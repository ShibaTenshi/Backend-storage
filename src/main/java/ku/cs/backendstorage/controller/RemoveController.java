package ku.cs.backendstorage.controller;

import ku.cs.backendstorage.exception.ParamEmptyException;
import ku.cs.backendstorage.service.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("remove")
public class RemoveController {
    @Autowired
    RemoveService removeService;

    @PostMapping("/{type}/{name}")
    public String removeAll(@PathVariable String type, @PathVariable String name) throws ParamEmptyException {
        removeService.remove(type, name);
        return "";
    }
}