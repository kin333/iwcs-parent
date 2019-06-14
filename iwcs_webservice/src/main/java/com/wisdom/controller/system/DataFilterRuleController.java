package com.wisdom.controller.system;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.DataFilterRule;
import com.wisdom.iwcs.domain.system.RoleAuthority;
import com.wisdom.service.system.DataFilterRuleService;
import com.wisdom.service.system.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lin on 17-9-7.
 */
@RestController
public class DataFilterRuleController {
    @Autowired
    private DataFilterRuleService filterRuleService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @GetMapping(path = "/filter-data-rules/", params = {"rid", "mid"})
    public Result list(@RequestParam Integer rid, @RequestParam Integer mid) {
        Preconditions.checkNotNull(rid, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        Preconditions.checkNotNull(mid, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        Result result = new Result();
        DataFilterRule rule = new DataFilterRule();
//        rule.setMenuId(String.valueOf(mid));
        Map<String, Object> data = Maps.newHashMapWithExpectedSize(2);
        List<DataFilterRule> rules = filterRuleService.list(rule);
        data.put("rules", rules);
        data.put("selectedRuleIds", this.getSelectRuleIdsByRoleIdAndMenuId(rid, mid));
        result.setReturnData(data);
        return result;
    }

    private List<Integer> getSelectRuleIdsByRoleIdAndMenuId(Integer rid, Integer mid) {
        RoleAuthority roleAuthorityCondition = new RoleAuthority();
        roleAuthorityCondition.setRoleid(rid);
        roleAuthorityCondition.setAuthorityId(mid);
        RoleAuthority roleAuthority = roleAuthorityService.getOne(roleAuthorityCondition);
        if (roleAuthority == null) {
            return new ArrayList<>(1);
        }
        String dataRuleStr = roleAuthority.getDataRule();

        if (Strings.isNullOrEmpty(dataRuleStr)) {
            return new ArrayList<>(1);
        }
        return Splitter
                .on(",")
                .trimResults()
                .omitEmptyStrings()
                .splitToList(dataRuleStr)
                .stream()
                .map(s -> Integer.valueOf(s))
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/filter-data-rules/")
    public Result add(@RequestBody DataFilterRule rule) {
        int num = filterRuleService.add(rule);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        Result result = new Result();
        return result;
    }

    @PutMapping(path = "/filter-data-rules/{id}/")
    public Result add(@RequestBody DataFilterRule rule, @PathVariable Integer id) {
        Preconditions.checkNotNull(id, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        rule.setId(id);
        int num = filterRuleService.update(rule);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        Result result = new Result();
        return result;
    }

    @DeleteMapping(path = "/filter-data-rules/")
    public Result delete(@RequestBody List<Integer> ids) {
        boolean hasDeleteId = ids != null && ids.size() > 0;
        Preconditions.checkArgument(hasDeleteId, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        int num = filterRuleService.removePhysicalById(ids);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        Result result = new Result();
        return result;
    }
}
