package com.wisdom.iwcs.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum;
import com.wisdom.iwcs.domain.base.dto.BaseBizConCurrentRulesDTO;
import com.wisdom.iwcs.domain.base.dto.BizAllowExecutePodsDetailDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 15:56
 */
public interface IBaseBizConCurrentRulesService {
    int insert(BaseBizConCurrentRulesDTO record);

    int insertBatch(List<BaseBizConCurrentRulesDTO> records);

    BaseBizConCurrentRulesDTO selectByPrimaryKey(Integer id);

    List<BaseBizConCurrentRulesDTO> selectSelective(BaseBizConCurrentRulesDTO record);

    int updateByPrimaryKey(BaseBizConCurrentRulesDTO record);

    int updateByPrimaryKeySelective(BaseBizConCurrentRulesDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<BaseBizConCurrentRulesDTO> selectPage(GridPageRequest gridPageRequest);

    List<PodTaskLockEnum> returnUnableConcurrentBizType(PodTaskLockEnum srcBizTypeEnum);

    Integer returnUnableConcurrentBizTypeValuesSum(PodTaskLockEnum srcBizTypeEnum);

    boolean returnIfAllowExecuteByPodcodesAndSrcBizType(List<String> podCodes, PodTaskLockEnum srcBizTypeEnum);

    BizAllowExecutePodsDetailDTO returnIfAllowExecuteDetailByPodcodesAndSrcBizType(List<String> podCodes, PodTaskLockEnum srcBizTypeEnum);
}
