package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.domain.base.dto.BaseMapUpdateAreaDTO;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.task.MapPodFilterStrategy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-22 11:09:17.
 */
@Repository
public interface BaseMapBerthMapper extends DeleteLogicMapper<BaseMapBerth>, MyMapperAndIds<BaseMapBerth> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseMapBerth> selectPage(Map map);

     /**
     * 根据地图编号删除地图地码信息
     *
     * @param mapCode
     * @return
     */
    int deleteByMapCode(String mapCode);

    /**
     * 根据元素编号查询
     *
     * @param bercode
     * @return
     */
    BaseMapBerth selectOneByBercode(String bercode);

    List<BaseMapBerth> selectEmptyStorageOfInspectionArea(LockMapBerthCondition  lockMapBerthCondition);
    List<BaseMapBerth> selectEmptyStorageOfInspectionAreas(LockMapBerthCondition  lockMapBerthCondition);

    List<BaseMapBerth> selectNotEmptyStorageOfInspectionArea(LockMapBerthCondition  lockMapBerthCondition);

    int lockMapBerth(LockStorageDto lockStorageDto);

    int lockMapBerthByBercode(LockStorageDto lockStorageDto);

    int unlockMapBerth(LockStorageDto lockStorageDto);

    List<BaseMapBerth> selectEmptyStorage(LockMapBerthCondition lockMapBerthCondition);
    List<BaseMapBerth> selectEmptyStorageAging(LockMapBerthCondition lockMapBerthCondition);


    List<BaseMapBerth> selectAlltorageByMapCode(@Param("areaCode") String areaCode, @Param("operateAreaCode") String operateAreaCode);

    BaseMapBerth selectBerData(LockStorageDto lockStorageDto);

    /**
     * 根据点位编号查询
     * @param pointAlias
     * @return
     */
    BaseMapBerth selectByPointAlias(String pointAlias);
    List<BaseMapBerth> selectByPointAliass(String pointAlias);

    List<BaseMapBerth> selectByPointAliaList(BaseMapBerth baseMapBerth);

    BaseMapBerth selectDataByPodCode(String podCode);

    /**
     * 根据点位编号查货架号
     */
    @Select("select pod_code from base_map_berth where delete_flag = 0  and point_alias = #{pointAlias,jdbcType=VARCHAR}")
    String selectBerCodeByPodCode(String pointAlias);

    /**
     * 根据berCode查地码别名
     */
    @Select("select point_alias from base_map_berth where delete_flag = 0  and  ber_code= #{pointAlias,jdbcType=VARCHAR}")
    String selectAliasByBerCode(String berCode);

    /**
     * 根据pointAlias查berCode
     */
    @Select("select ber_code from base_map_berth where delete_flag = 0  and  point_alias = #{pointAlias,jdbcType=VARCHAR}")
    String selectBerCodeByAlias(String pointAlias);

    /**
     * 根据地图编号获取地码列表
     * @param mapCode
     * @return
     */
    List<BaseMapBerth> selectBerthCodeByMapCode(String mapCode);
    List<BaseMapBerth> selectBerthList(String mapCode);
    /**
     * 批量更新
     * @param baseMapBerthList
     * @return
     */
    int updateList(List<BaseMapBerth> baseMapBerthList);

    /**
     *
     * @param baseMapBerthList
     * @return
     */
    int updateLockSource(List<BaseMapBerth> baseMapBerthList);
    /**
     * 批量删除
     * @param berCodeList
     * @param mapCode
     * @return
     */
    int deleteByBerCodeListAndMapCode(@Param("berCodeList") List<String> berCodeList, @Param("mapCode") String mapCode);


    /**
     * 通过berCode更新特定的字段
     * @param baseMapBerthDTOList
     * @return
     */
    int updateListByBerCode(List<BaseMapBerthDTO> baseMapBerthDTOList);

    /**
     * 根据地码编号更新货架号
     * @param podCode
     * @param berCode
     * @return
     */
    int updatePodCodeByBerCode(@Param("podCode") String podCode,@Param("berCode") String berCode);

    /**
     * 清空地图数据中的pod_code信息
     * @return
     */
    @Update("update base_map_berth set pod_code = ''")
    int updateAllCleanPodCode();

    /**
     * 根据地图编号查询产线工作台的别名
     * @param mapCode
     * @return
     */
    List<String> selectAliasByMapCode(String mapCode);

    /**
     * 根据指定的bizType, 查找有货架的储位
     * @param mapCode
     * @param bizType
     * @return
     */
    List<BaseMapBerth> selectHavePodByBizType(@Param("mapCode") String mapCode, @Param("bizType") String bizType);

    /**
     * 查找某区域内空储位
     * @param mapCode
     * @param operateAreaCode
     * @return
     */
    BaseMapBerth selectEmptyPosByOperateAreaCode(@Param("mapCode") String mapCode,@Param("operateAreaCode")  String operateAreaCode);

    /**
     * 查询某区域内空储位
     * @param operateAreaCode
     * @return
     */
    BaseMapBerth selectEmptyPosByBizSecondAreaCode(@Param("operateAreaCode")  String operateAreaCode);

    List<BaseMapBerth> selectByBizTye(String bizType);
    List<BaseMapBerth> selectByOperateAreaCode(String operateAreaCode);


    /**
     * 查询一楼包装缓存区
     * 自动下楼专用
     * @return
     */
    List<BaseMapBerth> selectPageCacheArea(String bizType);

    List<BaseMapBerth> selectLikeBizTye(String bizType);

    @Select("SELECT id FROM `iwcs_us_inspur`.`base_map_berth` WHERE `berth_type_value` = '1' and (point_alias IS NULL or point_alias = '') ORDER BY `cooy` DESC, `coox` LIMIT 1")
    Integer selectFirst();

    /**
     * 根据bercode、version更新货架编号
     * @return
     */
    int updatePodByBerCode(BaseMapBerth baseMapBerth);

    /**
     * 清除该货架在map_berth表中原来的位置
     * @return
     */
    int deletePodCodeByBerCode(BaseMapBerth baseMapBerth);

    /**
     * 批量锁定
     */
    int updateMapBerthById(List<BaseMapBerthDTO> baseMapBerthDTO);

    BaseMapBerth selectMapDataByBerCode(BaseMapBerth baseMapBerth);

    List<BaseMapBerth> selectMapByAreaCode(MapPodFilterStrategy mapPodFilterStrategy);

    Integer updateMapById(@Param("record") BaseMapUpdateAreaDTO record, @Param("list") List<Integer> list);

    int updateLockSourceByBercode(@Param("berCode") String berCode,@Param("lockSource") String lockSource);

    List<BaseMapBerth> selectAllRollerPoint();
    List<BaseMapBerth> selectEmptyPodNormalPoint();
    List<BaseMapBerth> selectEmptyPod2NormalPoint();
    List<BaseMapBerth> selectPodNormalPoint();

    int updateMapByBerCode(BaseMapBerth baseMapBerth);


    int updatePonitAlise(BaseMapBerth baseMapBerth);

    /**
     * 查询指定别名,但是非指定地码的点位数量
     * @param baseMapBerth
     * @return
     */
    int selectByPointAliaAndBercode(BaseMapBerth baseMapBerth);
}