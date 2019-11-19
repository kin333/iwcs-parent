package com.wisdom.iwcs.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.BasePodDetailDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:01
 */
public interface IBasePodDetailService {
    int insert(BasePodDetailDTO record);

    int insertBatch(List<BasePodDetailDTO> records);

    BasePodDetailDTO selectByPrimaryKey(Integer id);

    List<BasePodDetailDTO> selectSelective(BasePodDetailDTO record);

    List<BasePodDetailDTO> judgeBincodeIfTask(String bincode);

    List<BasePodDetailDTO> selectByInLock();

    int updateByPrimaryKey(BasePodDetailDTO record);

    int updateByPrimaryKeySelective(BasePodDetailDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    Result savePodInStock(BasePodDetailDTO record);

    GridReturnData<BasePodDetailDTO> selectPage(GridPageRequest gridPageRequest);

    String returnPodLockNameByResolveLockValue(Integer taskLockValue);

    BasePodDetail selectPodByPodCode(BasePodDetailDTO basePodDetailDTO);

    BasePodDetail selectPodData(BasePodDetailDTO recode);
}
