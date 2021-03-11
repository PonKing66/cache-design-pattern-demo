package org.ponking.cache.controller;

import org.ponking.cache.entity.User;
import org.ponking.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ponking
 * @Date 2021/3/11 15:53
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("insert")
    public Object insert(User user) {
        userService.insert(user);
        return "insert is OK";
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable Integer id) {
        userService.delete(id);
        return "delete is OK";
    }


    @PutMapping("update")
    public Object update(User user) throws Exception {
        userService.update(user);
        return "OK";
    }


    @GetMapping("{id}")
    public Object getOneById(@PathVariable Integer id) {
        return userService.getOneById(id);
    }
}
