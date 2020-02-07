package com.wisdom.iwcs.service.hikCallback.impl;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.XmlToBeanUtils;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.base.*;
import com.wisdom.iwcs.domain.hikSync.*;
import com.wisdom.iwcs.domain.task.BaseCtnrType;
import com.wisdom.iwcs.mapper.base.*;
import com.wisdom.iwcs.mapper.stock.StockMapper;
import com.wisdom.iwcs.mapper.task.BaseCtnrTypeMapper;
import com.wisdom.iwcs.mapper.task.WbAgvTaskMapper;
import com.wisdom.iwcs.mapper.task.WbTaskDetailMapper;
import com.wisdom.iwcs.service.hikCallback.IHikCallBackSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.DELETED;
import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.ValidFlagEnum.NOT_VALID;
import static com.wisdom.iwcs.common.utils.ValidFlagEnum.VALID;
import static com.wisdom.iwcs.common.utils.WhTypeEnum.INTELLIGENCE;
import static com.wisdom.iwcs.common.utils.YZConstants.LOCK;
import static com.wisdom.iwcs.common.utils.YZConstants.UNLOCK;
import static com.wisdom.iwcs.common.utils.podUtils.PodConstants.BinCargoCapacityStatus.EMPTY_BIN;
import static com.wisdom.iwcs.common.utils.syncUtils.PodDirectionEnum.*;
import static com.wisdom.iwcs.common.utils.syncUtils.ResolveSyncContentsConstants.*;
import static com.wisdom.iwcs.common.utils.syncUtils.SyncNotifyOperTypeConstants.*;
import static com.wisdom.iwcs.common.utils.syncUtils.SyncNotifyTypeConstants.*;
import static com.wisdom.iwcs.common.utils.taskUtils.AgvTaskConstants.AgvTaskStatusConstants.TASK_ENDED;

/**
 * @author cecilia.yang
 * 海康同步基础信息仓库、货架等
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HikCallBackSyncService implements IHikCallBackSyncService {
    private final Logger logger = LoggerFactory.getLogger(HikCallBackSyncService.class);

    @Autowired
    private BaseMapSectionMapper baseMapSectionMapper;
    @Autowired
    private BaseStgTypeMapper baseStgTypeMapper;
    @Autowired
    private BaseWaMapMapper baseWaMapMapper;
    @Autowired
    private BaseBincodeTypeMapper baseBincodeTypeMapper;
    @Autowired
    private BasePodTypeMapper basePodTypeMapper;
    @Autowired
    private BasePodTypeBincodeDetailMapper basePodTypeBincodeDetailMapper;
    @Autowired
    private BasePodMapper basePodMapper;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private BasePodBincodeMapper basePodBincodeMapper;
    @Autowired
    private BaseBincodeDetailMapper baseBincodeDetailMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private WbTaskDetailMapper wbTaskDetailMapper;
    @Autowired
    private BaseWhAreaMapper baseWhAreaMapper;
    @Autowired
    private BaseWhMapper baseWhMapper;
    @Autowired
    private BaseWbMapper baseWbMapper;
    @Autowired
    private WbAgvTaskMapper wbAgvTaskMapper;
    @Autowired
    private BaseMapMapper baseMapMapper;
    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private BasePodLayerStkMapper basePodLayerStkMapper;
    @Autowired
    private BaseCtnrTypeMapper baseCtnrTypeMapper;

    /**
     * 同步海康基础数据
     *
     * @param syncNotifyRequestDto
     * @return
     */
    @Override
    public HikSyncResponse receiveSyncBaseInfoNotify(SyncNotifyRequestDto syncNotifyRequestDto) {

        String requestNotifyType = syncNotifyRequestDto.getNotifyType();
        switch (requestNotifyType) {
            /**
             * 存储区同步
             */
            case SYNC_STG_SEC:
                return syncStgSecInfo(syncNotifyRequestDto);
            /**
             * 存储类型同步
             */
            case SYNC_STG_TYPE:
                return syncStgTypeInfo(syncNotifyRequestDto);
            /**
             * 仓位类型同步
             */
            case SYNC_STG_BIN_TYPE:
                return syncBinTypeInfo(syncNotifyRequestDto);
            /**
             * 货架类型同步
             */
            case SYNC_POD_TYPE:

                return syncPodTypeInfo(syncNotifyRequestDto);
            /**
             * 货架信息同步
             */
            case SYNC_POD_SYNC:
                return syncPodInfo(syncNotifyRequestDto);
            /**
             * 仓库信息同步
             */
            case SYNC_WAREHOUSE:
                return syncWarehouseInfo(syncNotifyRequestDto);
            /**
             * 地图信息同步
             */
            case SYNC_ELC_MAP:
                return syncMapData(syncNotifyRequestDto);
            /**
             * 工作台同步
             */
            case SYNC_WORK_BENCH:
                return syncWorkBenchInfo(syncNotifyRequestDto);
            /**
             * 容器类型同步
             */
            case SYNC_CTNR_TYP:
                return syncCtnrTypInfo(syncNotifyRequestDto);
            default:
                logger.error("错误的同步类型，{}", requestNotifyType);
                throw new BusinessException("错误的同步类型");
        }
    }

    /**
     * 容器类型同步
     * @param syncNotifyRequestDto
     * @return
     */
    private HikSyncResponse syncCtnrTypInfo(SyncNotifyRequestDto syncNotifyRequestDto) {
        syncNotifyRequestDto.getData().stream().forEach(data -> {
            String requestCtnrTypeCode = data.getCtnrTypCode();
            BaseCtnrType existsCtnrType = baseCtnrTypeMapper.selectByCtnrTypeAndValidAndDelete(requestCtnrTypeCode, VALID.getStatus(), NOT_DELETED.getStatus());
            if (isHikDataSyncDelOpt(data) && existsCtnrType != null) {
                baseCtnrTypeMapper.deleteByPrimaryKey(existsCtnrType.getId());
            } else if (isHikDataSyncMergeOpt(data)) {
                BaseCtnrType baseCtnrType = new BaseCtnrType();
                baseCtnrType.setCtnrTypCode(data.getCtnrTypCode());
                baseCtnrType.setCtnrTypText(data.getCtnrTypText());
                baseCtnrType.setHeight(data.getHeight());
                baseCtnrType.setLength(data.getLength());
                baseCtnrType.setWidth(data.getWidth());
                if (existsCtnrType == null) {
                    baseCtnrType.setCreatedTime(new Date());
                    baseCtnrTypeMapper.insertSelective(baseCtnrType);
                } else {
                    baseCtnrType.setId(existsCtnrType.getId());
                    baseCtnrType.setLastModifiedTime(new Date());
                    baseCtnrTypeMapper.updateByPrimaryKeySelective(baseCtnrType);
                }
            }
        });
        return new HikSyncResponse();
    }

    /**
     * merge\delete存储区信息
     *
     * @param requestDto
     * @return
     */
    private HikSyncResponse syncStgSecInfo(SyncNotifyRequestDto requestDto) {
        requestDto.getData().stream().forEach(data -> {
            BaseMapSection existsMapSection = baseMapSectionMapper.selectBySecCodeAndValidFlagAndDeleteFlag(data.getStgSecCode(), VALID.getStatus(), NOT_DELETED.getStatus());

            if (isHikDataSyncDelOpt(data) && existsMapSection != null) {
                baseMapSectionMapper.deleteByPrimaryKey(existsMapSection.getId());
            } else if (isHikDataSyncMergeOpt(data)) {
                BaseMapSection mapSection = new BaseMapSection();
                mapSection.setSecCode(data.getStgSecCode());
                mapSection.setSecName(data.getStgSecText());
                String stgTypeCode = data.getStgTypCode();
                BaseStgType baseStgType = baseStgTypeMapper.selectByStgTypeCodeAndValidFlagAndDeleteFlag(stgTypeCode, VALID.getStatus(), NOT_DELETED.getStatus());
                mapSection.setStgTypeCode(stgTypeCode);
                mapSection.setAreaCode(baseStgType.getAreaCode());
                mapSection.setMapCode(baseStgType.getMapCode());
                if (existsMapSection == null) {
                    mapSection.setCreatedTime(new Date());
                    baseMapSectionMapper.insertSelective(mapSection);
                } else {
                    mapSection.setId(existsMapSection.getId());
                    mapSection.setLastModifiedTime(new Date());
                    baseMapSectionMapper.updateByPrimaryKeySelective(mapSection);
                }
            }
        });
        return new HikSyncResponse();
    }


    /**
     * 新增、更新、删除存储区类型信息
     *
     * @param requestDto
     * @return
     */
    private HikSyncResponse syncStgTypeInfo(SyncNotifyRequestDto requestDto) {
        requestDto.getData().stream().forEach(data -> {
            BaseStgType existsStgTypeInfo = baseStgTypeMapper.selectByStgTypeCodeAndValidFlagAndDeleteFlag(data.getStgTypCode(), VALID.getStatus(), NOT_DELETED.getStatus());
            if (isHikDataSyncDelOpt(data) && existsStgTypeInfo != null) {
                baseStgTypeMapper.deleteByPrimaryKey(existsStgTypeInfo.getId());
            } else if (isHikDataSyncMergeOpt(data)) {

                BaseStgType baseStgType = new BaseStgType();
                String requestMapCode = data.getMapCode();
                BaseWaMap baseWaMap = baseWaMapMapper.selectByMapCodeAndValidFlagAndDeleteFlag(requestMapCode, VALID.getStatus(), NOT_DELETED.getStatus());
                if (baseWaMap != null) {
                    baseStgType.setAreaCode(baseWaMap.getAreaCode());
                }
                baseStgType.setMapCode(requestMapCode);
                baseStgType.setStgTypeCode(data.getStgTypCode());
                baseStgType.setStgTypeName(data.getStgTypText());
                if (existsStgTypeInfo == null) {
                    baseStgType.setCreatedTime(new Date());
                    baseStgTypeMapper.insertSelective(baseStgType);
                } else {
                    baseStgType.setId(existsStgTypeInfo.getId());
                    baseStgType.setLastModifiedTime(new Date());
                    baseStgTypeMapper.updateByPrimaryKeySelective(baseStgType);
                }
            }
        });
        return new HikSyncResponse();
    }

    private boolean isHikDataSyncDelOpt(SyncNotifyDataDto data) {
        return data.getOperType().equals(DELETE_OPER);
    }

    private boolean isHikDataSyncMergeOpt(SyncNotifyDataDto data) {
        return data.getOperType().equals(MERGE_OPER);
    }

    /**
     * 新增、更新、删除仓位类型表
     *
     * @param requestDto
     * @return
     */
    private HikSyncResponse syncBinTypeInfo(SyncNotifyRequestDto requestDto) {
        requestDto.getData().stream().forEach(data -> {
            String requestBinTypeCode = data.getStgBinTypCode();
            BaseBincodeType existsBincodeType = baseBincodeTypeMapper.selectByBincodeTypeCode(requestBinTypeCode);
            if (isHikDataSyncDelOpt(data) && existsBincodeType != null) {
                baseStgTypeMapper.deleteByPrimaryKey(existsBincodeType.getId());
            } else if (isHikDataSyncMergeOpt(data)) {
                BaseBincodeType baseBincodeType = new BaseBincodeType();
                baseBincodeType.setWhCode(data.getWhCode());
                baseBincodeType.setBinTypeCode(data.getStgBinTypCode());
                baseBincodeType.setBinTypeName(data.getStgBinTypText());
                baseBincodeType.setDepth(new BigDecimal(data.getLength()));
                baseBincodeType.setWidth(new BigDecimal(data.getWidth()));
                baseBincodeType.setHeight(new BigDecimal(data.getHeight()));
                if (existsBincodeType == null) {
                    baseBincodeType.setCreatedTime(new Date());
                    baseBincodeTypeMapper.insertSelective(baseBincodeType);
                } else {
                    baseBincodeType.setId(existsBincodeType.getId());
                    baseBincodeType.setLastModifiedTime(new Date());
                    baseBincodeTypeMapper.updateByPrimaryKeySelective(baseBincodeType);
                }
            }
        });
        return new HikSyncResponse();
    }

    /**
     * 解析货架类型信息
     *
     * @param requestDto
     * @return
     */
    private HikSyncResponse syncPodTypeInfo(SyncNotifyRequestDto requestDto) {
        requestDto.getData().stream().forEach(data -> {
            String requestPodTypeCode = data.getPodTypCode();
            BasePodType existsPodType = basePodTypeMapper.selectByPodTypeCodeAndValidFlagAndDeleteFlag(requestPodTypeCode, VALID.getStatus(), NOT_DELETED.getStatus());
            if (isHikDataSyncDelOpt(data) && existsPodType != null) {
                basePodTypeMapper.deleteByPrimaryKey(existsPodType.getId());
                basePodTypeBincodeDetailMapper.deleteByPodTypeCode(requestPodTypeCode);
            } else if (isHikDataSyncMergeOpt(data)) {
                BasePodType basePodType = new BasePodType();
                basePodType.setWhCode(data.getWhCode());
                basePodType.setPodTypeCode(data.getPodTypCode());
                basePodType.setPodTypeName(data.getPodTypText());
                Integer totalLayer = returnPodLayerAndRecordPodTypeBincodeDetail(data);
                basePodType.setTotalLayer(totalLayer);
                basePodType.setWeight(new BigDecimal(data.getPodWei()));
                if (existsPodType == null) {
                    basePodType.setCreatedTime(new Date());
                    basePodTypeMapper.insertSelective(basePodType);
                } else {
                    basePodType.setLastModifiedTime(new Date());
                    basePodTypeMapper.updateByPrimaryKeySelective(basePodType);
                }

            }

        });
        return new HikSyncResponse();
    }

    /**
     * 返回该货架类型共几层，且记录货架类型对应的仓位详情
     * 字段名east、west、north、south分别代表货架的东西北南方向
     * 如"east": "2_02,2_02",按逗号隔开分组，表示为东面，共两层（有几组代表几层）
     * 第一层（分组从前往后表示从第一层到最上面一层），有两个仓位（组内下划线"_"前面表示仓位数），仓位类型编码是02（组内下划线"_"后面表示仓位类型编码）
     *
     * @param data
     * @return
     */
    private Integer returnPodLayerAndRecordPodTypeBincodeDetail(SyncNotifyDataDto data) {

        List<ResolveDirectionDto> eastContents = resolveDirectionContent(data.getEast(), DIRECTION_EAST.getCode());
        List<ResolveDirectionDto> westContents = resolveDirectionContent(data.getWest(), DIRECTION_WEST.getCode());
        List<ResolveDirectionDto> southContents = resolveDirectionContent(data.getSouth(), DIRECTION_SOUTH.getCode());
        List<ResolveDirectionDto> northContents = resolveDirectionContent(data.getNorth(), DIRECTION_NORTH.getCode());
        List<ResolveDirectionDto> middleContents = resolveDirectionContent(data.getMiddle(), DIRECTION_MIDDLE.getCode());
        List<ResolveDirectionDto> allDirectionContents = new ArrayList<>();
        allDirectionContents.addAll(eastContents);
        allDirectionContents.addAll(westContents);
        allDirectionContents.addAll(southContents);
        allDirectionContents.addAll(northContents);
        allDirectionContents.addAll(middleContents);

        Integer totalLayer = eastContents.size();
        List<BasePodTypeBincodeDetail> insertDetailList = new ArrayList<>();
        allDirectionContents.stream().forEach(eastContent -> {
            BasePodTypeBincodeDetail basePodTypeBincodeDetail = new BasePodTypeBincodeDetail();
            basePodTypeBincodeDetail.setPodTypeCode(data.getPodTypCode());
            basePodTypeBincodeDetail.setPodTypeName(data.getPodTypText());
            basePodTypeBincodeDetail.setLayer(eastContent.getLayer());
            basePodTypeBincodeDetail.setDirection(eastContent.getDirection());
            basePodTypeBincodeDetail.setBinTypeCode(eastContent.getBinTypeCode());
            basePodTypeBincodeDetail.setBincodeNum(eastContent.getBincodeNum());
            basePodTypeBincodeDetail.setValidFlag(VALID.getStatus());
            basePodTypeBincodeDetail.setDeleteFlag(NOT_DELETED.getStatus());
            basePodTypeBincodeDetail.setCreatedTime(new Date());
            insertDetailList.add(basePodTypeBincodeDetail);
        });

        basePodTypeBincodeDetailMapper.insertList(insertDetailList);
        return totalLayer;
    }

    /**
     * 解析方向的具体内容
     *
     * @param directionContent
     * @param direction
     * @return
     */
    private List<ResolveDirectionDto> resolveDirectionContent(String directionContent, Integer direction) {
        List<ResolveDirectionDto> returnResolveContents = new ArrayList<>();
        List<String> splitDirectionContents = Arrays.asList(directionContent.split(","));
        if (splitDirectionContents.size() != 0) {
            for (int i = 0; i < splitDirectionContents.size(); i++) {
                String directionContentDetail = splitDirectionContents.get(i);
                ResolveDirectionDto resolveDirectionDto = new ResolveDirectionDto();
                resolveDirectionDto.setDirection(direction);
                resolveDirectionDto.setLayer(i + 1);
                if (directionContentDetail.contains(SPLIT_BIN_TYPE_NUM)) {
                    String[] splitDirectionContentDetail = directionContentDetail.split(SPLIT_BIN_TYPE_NUM);
                    resolveDirectionDto.setBincodeNum(splitDirectionContentDetail[0]);
                    resolveDirectionDto.setBinTypeCode(splitDirectionContentDetail[1]);
                } else {
                    resolveDirectionDto.setBincodeNum("0");
                    resolveDirectionDto.setBinTypeCode("0");
                }
                returnResolveContents.add(resolveDirectionDto);

            }
        }
        return returnResolveContents;
    }


    /**
     * 解析货架信息，包括动态信息、静态信息
     * 货架信息不存在更新的情况
     *
     * @param requestDto
     * @return
     */
    private HikSyncResponse syncPodInfo(SyncNotifyRequestDto requestDto) {
        List<BasePod> insertBasePodList = new ArrayList<>();
        List<BasePodDetail> insertBasePodDetailList = new ArrayList<>();
        List<BasePodBincode> insertPodBincodeList = new ArrayList<>();
        List<BaseBincodeDetail> insertBincodeDetailList = new ArrayList<>();
        List<BasePodLayerStk> insertBasePodLayerStkList = new ArrayList<>();
        List<String> deletePodCodes = new ArrayList<>();
        List<String> deleteLogicPodCodes = new ArrayList<>();
        for (SyncNotifyDataDto data : requestDto.getData()) {
            String requestPodCode = data.getPodCode();
            if (isHikDataSyncDelOpt(data)) {
                HikSyncResponse checkResponse = checkIfAllowDeletePodAndBinInfoByPodCode(requestPodCode);
                if (!checkResponse.getCode().equals(SUCCESS_OPER_RETURN_CODE)) {
                    return checkResponse;
                }
                deleteLogicPodCodes.add(requestPodCode);

            } else if (isHikDataSyncMergeOpt(data)) {
                deletePodCodes.add(requestPodCode);
                BasePod basePod = new BasePod();
                basePod.setWhCode(data.getWhCode());
                basePod.setPodCode(requestPodCode);
                basePod.setPodName(data.getPodText());
                basePod.setPodTypeCode(data.getPodTypCode());
                basePod.setBinCnt(data.getBins().size());
                basePod.setValidFlag(NOT_VALID.getStatus());
                basePod.setDeleteFlag(NOT_DELETED.getStatus());
                basePod.setCreatedTime(new Date());
                insertBasePodList.add(basePod);

                BasePodDetail basePodDetail = new BasePodDetail();
                basePodDetail.setPodCode(requestPodCode);
                basePodDetail.setStgTypeCode(data.getStgTypCode());
                basePodDetail.setLockStat(0);
                basePodDetail.setValidFlag(NOT_VALID.getStatus());
                basePodDetail.setDeleteFlag(NOT_DELETED.getStatus());
                basePodDetail.setCreatedTime(new Date());
                basePodDetail.setInStock(Integer.valueOf(InspurBizConstants.InStock.NO_GOODS));
                basePodDetail.setVersion(0);
                basePodDetail.setInLock(UNLOCK);
                insertBasePodDetailList.add(basePodDetail);
                data.getBins().stream().forEach(binData -> {
                    BasePodBincode basePodBincode = new BasePodBincode();
                    basePodBincode.setBincode(binData.getBinCode());
                    basePodBincode.setPodCode(requestPodCode);
                    basePodBincode.setBinTypeCode(binData.getStgBinTyp());
                    ResolveDirectionDto directionDto = resolveLayerAndDirectionByBincode(binData.getBinCode());
                    basePodBincode.setDirection(directionDto.getDirection());
                    basePodBincode.setLayer(directionDto.getLayer());
                    basePodBincode.setCreatedTime(new Date());
                    basePodBincode.setValidFlag(NOT_VALID.getStatus());
                    basePodBincode.setDeleteFlag(NOT_DELETED.getStatus());
                    insertPodBincodeList.add(basePodBincode);

                    BaseBincodeDetail baseBincodeDetail = new BaseBincodeDetail();
                    baseBincodeDetail.setBincode(binData.getBinCode());
                    baseBincodeDetail.setPodCode(requestPodCode);
                    baseBincodeDetail.setLockStat(0);
                    baseBincodeDetail.setLayer(directionDto.getLayer());
                    baseBincodeDetail.setCargoCapacityStatus(EMPTY_BIN);
                    baseBincodeDetail.setValidFlag(NOT_VALID.getStatus());
                    baseBincodeDetail.setDeleteFlag(NOT_DELETED.getStatus());
                    baseBincodeDetail.setCreatedTime(new Date());
                    insertBincodeDetailList.add(baseBincodeDetail);
                });

                List<BasePodTypeBincodeDetail> podTypeBincodeDetails = basePodTypeBincodeDetailMapper.selectLayerAndBincodeNumByPodTypeCode(data.getPodTypCode());
                podTypeBincodeDetails.stream().forEach(podTypeBincodeDetail -> {
                    BasePodLayerStk basePodLayerStk = new BasePodLayerStk();
                    basePodLayerStk.setPodCode(requestPodCode);
                    basePodLayerStk.setPodType(data.getPodTypCode());
                    basePodLayerStk.setTotalLayer(podTypeBincodeDetails.size());
                    basePodLayerStk.setLayer(podTypeBincodeDetail.getLayer());
                    Integer binCnt = Integer.parseInt(podTypeBincodeDetail.getBincodeNum());
                    basePodLayerStk.setBincodeCount(binCnt);
                    basePodLayerStk.seteBincode(binCnt);
                    basePodLayerStk.setfBincode(0);
                    insertBasePodLayerStkList.add(basePodLayerStk);
                });

            }
        }

        if (deletePodCodes.size() != 0) {
            basePodMapper.deleteByPodCodes(deletePodCodes);
            basePodDetailMapper.deleteByPodCodes(deletePodCodes);
            basePodBincodeMapper.deleteByPodCodes(deletePodCodes);
            baseBincodeDetailMapper.deleteByPodCodes(deletePodCodes);

            basePodMapper.insertList(insertBasePodList);
            basePodDetailMapper.insertList(insertBasePodDetailList);
            basePodBincodeMapper.insertList(insertPodBincodeList);
            baseBincodeDetailMapper.insertList(insertBincodeDetailList);
            //basePodLayerStkMapper.insertList(insertBasePodLayerStkList);
        }
        if (deleteLogicPodCodes.size() != 0) {
            basePodMapper.deleteLogicByPodCodes(deleteLogicPodCodes, DELETED.getStatus());
            basePodDetailMapper.deleteLogicByPodCodes(deleteLogicPodCodes, DELETED.getStatus());
            basePodBincodeMapper.deleteLogicByPodCodes(deleteLogicPodCodes, DELETED.getStatus());
            baseBincodeDetailMapper.deleteLogicByPodCodes(deleteLogicPodCodes, DELETED.getStatus());
        }
        return new HikSyncResponse();
    }

    /**
     * 根据仓位编号解析方向和
     *
     * @param bincode
     * @return
     */
    private ResolveDirectionDto resolveLayerAndDirectionByBincode(String bincode) {
        ResolveDirectionDto resolveDirectionDto = new ResolveDirectionDto();
        String direction = String.valueOf(bincode.charAt(APPOINT_DIRECTION_INDEX));
        String layer = String.valueOf(bincode.charAt(APPOINT_LAYER_INDEX));
        resolveDirectionDto.setDirection(Integer.parseInt(direction));
        resolveDirectionDto.setLayer(Integer.parseInt(layer));

        return resolveDirectionDto;
    }

    /**
     * 根据货架编号判断是否允许删除货架
     *
     * @param podCode
     */
    private HikSyncResponse checkIfAllowDeletePodAndBinInfoByPodCode(String podCode) {
        int countExistStock = stockMapper.selectCountExistValidTotalQtyStockByPodCode(podCode);
        if (countExistStock != 0) {
            return new HikSyncResponse(FAIL_OPER_RETURN_CODE, "需删除的货架存在有效库存，请先清空库存再删除货架信息");
        }

        int countUnEndTask = wbTaskDetailMapper.selectCountNotCompletedTaskByPodCodeAndTaskEndStatus(podCode, TASK_ENDED);
        if (countUnEndTask != 0) {
            return new HikSyncResponse(FAIL_OPER_RETURN_CODE, "需删除的货架存在正在完成的任务，无法直接删除货架");
        }


        return new HikSyncResponse();

    }

    /**
     * 同步仓库信息
     *
     * @param requestDto
     * @return
     */
    private HikSyncResponse syncWarehouseInfo(SyncNotifyRequestDto requestDto) {
        for (SyncNotifyDataDto data : requestDto.getData()) {
            BaseWh existsWhInfo = baseWhMapper.selectByWhCodeAndValidFlagAndDeleteFlag(data.getWhCode(), VALID.getStatus(), NOT_DELETED.getStatus());

            if (isHikDataSyncDelOpt(data) && existsWhInfo != null) {
                HikSyncResponse hikSyncResponse = checkIfAllowDeleteWarehouseInfoByWhCode(data.getWhCode());
                if (!hikSyncResponse.getCode().equals(SUCCESS_OPER_RETURN_CODE)) {
                    return hikSyncResponse;
                }
                baseWhMapper.deleteByPrimaryKey(existsWhInfo.getId());
            } else if (isHikDataSyncMergeOpt(data)) {
                BaseWh baseWh = new BaseWh();
                baseWh.setWhCode(data.getWhCode());
                baseWh.setWhName(data.getWhText());
                baseWh.setWhType(INTELLIGENCE.getCode());
                if (existsWhInfo == null) {
                    baseWh.setCreatedTime(new Date());
                    baseWhMapper.insertSelective(baseWh);
                } else {
                    baseWh.setLastModifiedTime(new Date());
                    baseWh.setId(existsWhInfo.getId());
                    baseWhMapper.updateByPrimaryKeySelective(baseWh);
                }
            }
        }
        return new HikSyncResponse();
    }

    /**
     * 根据仓库编号校验是否允许删除仓库信息
     *
     * @param whCode
     */
    private HikSyncResponse checkIfAllowDeleteWarehouseInfoByWhCode(String whCode) {
        int countWhArea = baseWhAreaMapper.selectCountByWhCodeAndDeleteFlag(whCode, NOT_DELETED.getStatus());
        if (countWhArea != 0) {
            return new HikSyncResponse(FAIL_OPER_RETURN_CODE, "当前仓库存在关联库区，不能删除");
        }
        return new HikSyncResponse();
    }

    /**
     * 同步工作台信息
     *
     * @param requestDto
     * @return
     */
    private HikSyncResponse syncWorkBenchInfo(SyncNotifyRequestDto requestDto) {
        for (SyncNotifyDataDto data : requestDto.getData()) {
            BaseWb existsBaseWb = baseWbMapper.selectByBerCodeAndDeleteFlag(data.getWbCode(), NOT_DELETED.getStatus());
            if (isHikDataSyncDelOpt(data) && existsBaseWb != null) {
                HikSyncResponse checkResponse = checkIfAllowDeleteWorkBenchInfoByWbCode(existsBaseWb.getWbCode());
                if (!checkResponse.getCode().equals(SUCCESS_OPER_RETURN_CODE)) {
                    return checkResponse;
                }
                baseWbMapper.deleteByPrimaryKey(existsBaseWb.getId());
            } else if (isHikDataSyncMergeOpt(data)) {
                BaseWb baseWb = new BaseWb();

                baseWb.setBerCode(data.getWbCode());
                baseWb.setWbName(data.getShortWbCode());
                baseWb.setCoox(data.getCoox());
                baseWb.setCooy(data.getCooy());
                baseWb.setMapCode(data.getMapCode());
                BaseWaMap baseWaMap = baseWaMapMapper.selectByMapCodeAndValidFlagAndDeleteFlag(data.getMapCode(), VALID.getStatus(), NOT_DELETED.getStatus());
                if (baseWaMap != null) {
                    baseWb.setAreaCode(baseWaMap.getAreaCode());
                }
                if (existsBaseWb == null) {
                    baseWb.setCreatedTime(new Date());
                    baseWbMapper.insertSelective(baseWb);
                } else {
                    baseWb.setLastModifiedTime(new Date());
                    baseWb.setId(existsBaseWb.getId());
                    baseWbMapper.updateByPrimaryKeySelective(baseWb);
                }
            }

        }

        return new HikSyncResponse();
    }

    /**
     * 校验工作台点位是否允许删除
     *
     * @param wbCode
     * @return
     */
    private HikSyncResponse checkIfAllowDeleteWorkBenchInfoByWbCode(String wbCode) {
        int countNotCompletedTask = wbAgvTaskMapper.selectCountNotCompletedTaskByWbCodeAndTaskEndStatus(wbCode, TASK_ENDED);
        if (countNotCompletedTask != 0) {
            return new HikSyncResponse(FAIL_OPER_RETURN_CODE, "删除的工作台存在未完成的任务，不能删除");
        }

        return new HikSyncResponse();
    }

    /**
     * 同步地图数据
     *
     * @param requestDto
     * @return
     */
    private HikSyncResponse syncMapData(SyncNotifyRequestDto requestDto) {
        requestDto.getData().stream().forEach(data -> {
            BaseMap existsMapInfo = baseMapMapper.selectByMapCodeAndDeleteFlag(data.getMapCode(), NOT_DELETED.getStatus());
            if (isHikDataSyncMergeOpt(data)) {
                if (existsMapInfo == null){
                    BaseMap baseMap = new BaseMap();
                    baseMap.setMapCode(data.getMapCode());
                    baseMap.setMapName(data.getMapName());
                    baseMap.setRowCount(Integer.parseInt(data.getRowCount()));
                    baseMap.setColCount(Integer.parseInt(data.getColCount()));
                    baseMap.setWidth(Integer.parseInt(data.getWidth()));
                    baseMap.setHeight(Integer.parseInt(data.getHeight()));
                    baseMap.setMapType(data.getTypeCode());
                    baseMap.setGroundTypeCode(data.getGroundTypeCode());
                    logger.info("baseMapInsertSelective");
                    baseMapMapper.insertSelective(baseMap);
                }
                //对比需要解析的地码列表和已知的地码列表
                try {
                    MapConfigDto mapContent = XmlToBeanUtils.xmlToBean(data.getMapContent(), MapConfigDto.class);
                    List<BaseMapBerth> insertBaseMapBerthList = new ArrayList<>();
                    List<String> requestBerCodeList = packageBercode(mapContent.getPointInfo(), mapContent.getMapQRCode()).stream().distinct().collect(Collectors.toList());
                    List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectBerthCodeByMapCode(data.getMapCode());
                    List<String> existsBerCodeList = baseMapBerthList.stream().map(BaseMapBerth :: getBerCode).collect(Collectors.toList());
                    //请求列表有、数据库无，新增
                    List<String> newBerCodeList = returnDifferenceList(requestBerCodeList,existsBerCodeList);
                    //请求列表无、数据库有、删除
                    List<String> deleteBerCodeList = returnDifferenceList(existsBerCodeList,requestBerCodeList);
                    //请求列表有、数据库有、更新
                    List<String> updateBerCodeList = returnRetainList(requestBerCodeList,existsBerCodeList);
                     List<BaseMapBerth> updateBaseMapBerthList = baseMapBerthList.stream().filter(b -> requestBerCodeList.contains(b.getBerCode())).collect(Collectors.toList());
                    mapContent.getPointInfo().stream().forEach(pointInfoDto -> {
                        String coox = merge(pointInfoDto.getXpos());
                        String cooy = merge(pointInfoDto.getYpos());
//                        String coox = pointInfoDto.getXpos().replace(".", "");
//                        String cooy = pointInfoDto.getYpos().replace(".", "");
                        String berCode = coox + mapContent.getMapQRCode() + cooy;
                        if (newBerCodeList.contains(berCode)){
                            //组装新增数据
                            BaseMapBerth baseMapBerth = new BaseMapBerth();
                            baseMapBerth.setMapCode(data.getMapCode());
                            baseMapBerth.setCoox(BigDecimal.valueOf(Double.valueOf(pointInfoDto.getXpos())));
                            baseMapBerth.setCooy(BigDecimal.valueOf(Double.valueOf(pointInfoDto.getYpos())));
                            baseMapBerth.setBerCode(berCode);
                            baseMapBerth.setBerthTypeValue(pointInfoDto.getValue());
                            baseMapBerth.setValidFlag(VALID.getStatus());
                            baseMapBerth.setDeleteFlag(NOT_DELETED.getStatus());
                            baseMapBerth.setCreatedTime(new Date());
                            baseMapBerth.setInLock(UNLOCK);
                            insertBaseMapBerthList.add(baseMapBerth);
                        }
                        if (updateBerCodeList.contains(berCode)){
                            //更新地码value
                            BaseMapBerth updateBaseMapBerth = updateBaseMapBerthList.stream().filter(u -> u.getBerCode().equals(berCode)).findAny().get();
                            updateBaseMapBerth.setBerthTypeValue(pointInfoDto.getValue());
                            updateBaseMapBerth.setLastModifiedTime(new Date());
                        }

                    });
                    if (insertBaseMapBerthList.size() != 0) {
                        baseMapBerthMapper.insertList(insertBaseMapBerthList);
                    }
                    if (updateBaseMapBerthList.size() != 0){
                        baseMapBerthMapper.updateList(updateBaseMapBerthList);
                    }
                    if (deleteBerCodeList.size() != 0){
                        baseMapBerthMapper.deleteByBerCodeListAndMapCode(deleteBerCodeList,data.getMapCode());
                    }
                } catch (JAXBException e) {
                    logger.info("mapContent convert JAXBException,{}", e.getMessage());
                }
            }else if (isHikDataSyncDelOpt(data)){
                baseMapMapper.deleteByMapCode(data.getMapCode());
                baseMapBerthMapper.deleteByMapCode(data.getMapCode());
            }


        });
        return new HikSyncResponse();
    }

    /**
     * 求A和B的差集：A-B
     * @param ListA
     * @param ListB
     * @return
     */
    private List<String> returnDifferenceList(List<String> ListA,List<String> ListB){
        //被减数
        List<String> subtractedList = new ArrayList<>(ListA);
        //减数
        List<String> subtractionList = new ArrayList<>(ListB);
        subtractedList.removeAll(subtractionList);
        return subtractedList;
    }

    /**
     * 求A和B的交集
     * @param ListA
     * @param ListB
     * @return
     */
    private List<String> returnRetainList(List<String> ListA,List<String> ListB){
        //被减数
        List<String> calculationListA = new ArrayList<>(ListA);
        //减数
        List<String> calculationListB = new ArrayList<>(ListB);
        calculationListA.retainAll(calculationListB);
        return calculationListA;
    }

    /**
     * 返回地码坐标
     * @param pointInfoDtoList
     * @param mapQRCode
     * @return
     */
    private List<String> packageBercode(List<PointInfoDto> pointInfoDtoList,String mapQRCode){
        List<String> bercodeList = new ArrayList<>();
        pointInfoDtoList.stream().forEach(pointInfoDto -> {
            String coox = merge(pointInfoDto.getXpos());
            String cooy = merge(pointInfoDto.getYpos());
            String bercode = coox + mapQRCode + cooy;
            bercodeList.add(bercode);
            logger.info("bercode,{}",bercode);
        });

        return bercodeList;
    }

    private String merge(String num) {
        String x = "";
        String[] split = num.split("\\.");
        x += addAfter(split[0]);
        if (split.length == 1) {
            x += "000";
        } else {
            x += addBefore(split[1]);
        }
        return x;
    }


    private String addAfter(String num) {
        if (num.length() == 1) {
            num = "00" + num;
        } else if (num.length() == 2) {
            num = "0" + num;
        } else if (num.length() == 0) {
            num = "000";
        }
        return num;
    }
    private String addBefore(String num) {
        if (num.length() == 1) {
            num = num + "00";
        } else if (num.length() == 2) {
            num = num + "0";
        } else if (num.length() == 0) {
            num = "000";
        }
        return num;
    }


}
