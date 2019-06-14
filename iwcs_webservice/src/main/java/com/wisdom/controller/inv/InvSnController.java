package com.wisdom.controller.inv;

import com.wisdom.controller.mapstruct.inv.InvSnMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.inv.dto.InvSnDTO;
import com.wisdom.service.inv.invImpl.InvSnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对InvSn的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/inv-sn")
public class InvSnController {
    @Autowired
    InvSnService invSnServiceImpl;
    @Autowired
    InvSnMapStruct invSnMapStruct;

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<InvSnDTO> records = invSnServiceImpl.selectPage(gridPageRequest);

        return new Result(records);
    }
}
