package com.wisdom.controller.system;

import com.wisdom.iwcs.common.utils.Result;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lin on 17-9-7.
 */
@RestController
public class RoleMenuController {

    @PutMapping(path = "/role-menus/", params = {"rid", "mid"})
    public Result updateRule(@RequestBody List<String> ids, @RequestParam Integer rid, @RequestParam Integer mid) {
        return null;
    }
}
