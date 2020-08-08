package cn.itweknow.sbswagger.controller;

import cn.itweknow.sbswagger.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口", description = "提供用户相关的 Rest API")
public class UserController {

    @PostMapping("/add")
    @ApiOperation("新增用户接口")
    public boolean addUser(@RequestBody User user) {
        return false;
    }

    @GetMapping("/find/{id}")
    @ApiOperation("通过id查找用户接口")
    public User findById(@PathVariable("id") int id) {
        return new User();
    }

    @PutMapping("/update")
    @ApiOperation("更新用户信息接口")
    public boolean update(@RequestBody User user) {
        return true;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除用户接口")
    //@ApiIgnore         //用于不在接口文档中显示这个接口
    public boolean delete(@PathVariable("id") int id) {
        return true;
    }
}
