package com.wisdom.service.stock.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wisdom.controller.mapstruct.stock.StockAbnormalAdjustRecordMapStruct;
import com.wisdom.controller.mapstruct.stock.StockAdjustQueryVsStockMapStruct;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseBincodeDetail;
import com.wisdom.iwcs.domain.base.BaseMat;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.BaseWhArea;
import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.domain.stock.StockAbnormalAdjustRecord;
import com.wisdom.iwcs.domain.stock.StockSn;
import com.wisdom.iwcs.domain.stock.dto.*;
import com.wisdom.iwcs.mapper.base.BaseBincodeDetailMapper;
import com.wisdom.iwcs.mapper.base.BaseMatMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.base.BaseWhAreaMapper;
import com.wisdom.iwcs.mapper.stock.StockAbnormalAdjustRecordMapper;
import com.wisdom.iwcs.mapper.stock.StockMapper;
import com.wisdom.iwcs.mapper.stock.StockSnMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.baseImpl.CommonService;
import com.wisdom.service.stock.IStockAbnormalAdjustRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.InStockConstants.ContainerFlagConstants.IN_CONTAINER;
import static com.wisdom.iwcs.common.utils.InStockConstants.ContainerFlagConstants.NOT_IN_CONTAINER;
import static com.wisdom.iwcs.common.utils.InStockConstants.freezeFlagConstants.FREEZE_IN;
import static com.wisdom.iwcs.common.utils.podUtils.PodConstants.BinCargoCapacityStatus.EMPTY_BIN;
import static com.wisdom.iwcs.common.utils.stockAdjust.StockAdjustConstants.AdjustType.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class StockAbnormalAdjustRecordService implements IStockAbnormalAdjustRecordService {
    private final Logger logger = LoggerFactory.getLogger(StockAbnormalAdjustRecordService.class);

    private final StockAbnormalAdjustRecordMapper stockAbnormalAdjustRecordMapper;

    private final StockAbnormalAdjustRecordMapStruct stockAbnormalAdjustRecordMapStruct;

    @Autowired
    public StockAbnormalAdjustRecordService(StockAbnormalAdjustRecordMapStruct stockAbnormalAdjustRecordMapStruct, StockAbnormalAdjustRecordMapper stockAbnormalAdjustRecordMapper) {
        this.stockAbnormalAdjustRecordMapStruct = stockAbnormalAdjustRecordMapStruct;
        this.stockAbnormalAdjustRecordMapper = stockAbnormalAdjustRecordMapper;
    }


    @Autowired
    private CommonService commonService;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private BaseBincodeDetailMapper baseBincodeDetailMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockAdjustQueryVsStockMapStruct stockAdjustQueryVsStockMapStruct;
    @Autowired
    private BaseWhAreaMapper baseWhAreaMapper;
    @Autowired
    private BaseMatMapper baseMatMapper;

    /**
     * 写入记录
     *
     * @param record {@link StockAbnormalAdjustRecordDTO }
     * @return int
     */
    @Override
    public int insert(StockAbnormalAdjustRecordDTO record) {
        StockAbnormalAdjustRecord stockAbnormalAdjustRecord = stockAbnormalAdjustRecordMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        stockAbnormalAdjustRecord.setCreatedTime(new Date());
        stockAbnormalAdjustRecord.setLastModifiedTime(new Date());

        int num = stockAbnormalAdjustRecordMapper.insert(stockAbnormalAdjustRecord);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<StockAbnormalAdjustRecordDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<StockAbnormalAdjustRecordDTO> records) {
        List<StockAbnormalAdjustRecord> recordList = stockAbnormalAdjustRecordMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setLastModifiedTime(new Date());
        });

        int num = stockAbnormalAdjustRecordMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link StockAbnormalAdjustRecordDTO }
     */
    @Override
    public StockAbnormalAdjustRecordDTO selectByPrimaryKey(Integer id) {

        StockAbnormalAdjustRecord stockAbnormalAdjustRecord = stockAbnormalAdjustRecordMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(stockAbnormalAdjustRecord, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return stockAbnormalAdjustRecordMapStruct.toDto(stockAbnormalAdjustRecord);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link StockAbnormalAdjustRecordDTO }
     * @return {@link List<StockAbnormalAdjustRecordDTO> }
     */
    @Override
    public List<StockAbnormalAdjustRecordDTO> selectSelective(StockAbnormalAdjustRecordDTO record) {
        StockAbnormalAdjustRecord stockAbnormalAdjustRecord = stockAbnormalAdjustRecordMapStruct.toEntity(record);

        List<StockAbnormalAdjustRecord> stockAbnormalAdjustRecordList = stockAbnormalAdjustRecordMapper.select(stockAbnormalAdjustRecord);
        return stockAbnormalAdjustRecordMapStruct.toDto(stockAbnormalAdjustRecordList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link StockAbnormalAdjustRecordDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(StockAbnormalAdjustRecordDTO record) {
        StockAbnormalAdjustRecord stockAbnormalAdjustRecord = stockAbnormalAdjustRecordMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        stockAbnormalAdjustRecord.setLastModifiedTime(new Date());

        int num = stockAbnormalAdjustRecordMapper.updateByPrimaryKey(stockAbnormalAdjustRecord);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link StockAbnormalAdjustRecordDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(StockAbnormalAdjustRecordDTO record) {
        StockAbnormalAdjustRecord stockAbnormalAdjustRecord = stockAbnormalAdjustRecordMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        stockAbnormalAdjustRecord.setLastModifiedTime(new Date());

        int num = stockAbnormalAdjustRecordMapper.updateByPrimaryKeySelective(stockAbnormalAdjustRecord);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = stockAbnormalAdjustRecordMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return stockAbnormalAdjustRecordMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<StockAbnormalAdjustRecordDTO> }
     */
    @Override
    public GridReturnData<StockAbnormalAdjustRecordDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<StockAbnormalAdjustRecordDTO> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map<String, Object> map = new HashMap<>(2);
        filterList.forEach(gridFilterInfo -> {
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        // 对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<StockAbnormalAdjustRecord> list = stockAbnormalAdjustRecordMapper.selectPage(map);

        PageInfo<StockAbnormalAdjustRecord> pageInfo = new PageInfo<>(list);
        PageInfo<StockAbnormalAdjustRecordDTO> pageInfoFinal = new PageInfo<>(stockAbnormalAdjustRecordMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    @Autowired
    private StockSnMapper stockSnMapper;


    @Override
    public Result stockAdjust(StockAdjustRequestDTO stockAdjustRequestDTO) {
        commonCheckRequestRequiredParamAndBusinessValid(stockAdjustRequestDTO);
        String adjustType = stockAdjustRequestDTO.getAdjustType();
        switch (adjustType) {
            case ADJUST_ADD_STOCK:
                adjustAddStock(stockAdjustRequestDTO);
                break;
            case ADJUST_DELETE_STOCK:
                adjustDeleteStock(stockAdjustRequestDTO);
                break;
            case ADJUST_REPLACE_SN:
                adjustReplaceSn(stockAdjustRequestDTO);
                break;
            case ADJUST_MOVE_POD:
                adjustMovePod(stockAdjustRequestDTO);
                break;
            case ADJUST_CHANGE_CHARACTER:
                adjustChangeCharacter(stockAdjustRequestDTO);
                break;
            default:
                throw new BusinessException("调整类型：" + adjustType + "未定义，请重新选择");
        }
        recordStockAbNormalAdjust(stockAdjustRequestDTO);

        return new Result();
    }

    private void adjustChangeCharacter(StockAdjustRequestDTO requestDTO) {
        List<Stock> insertStockList = new ArrayList<>();
        requestDTO.getData().stream().forEach(requestData -> {
            boolean lockBincode = Strings.isNullOrEmpty(requestData.getSourceBincode()) || Strings.isNullOrEmpty(requestData.getTargetBincode());
            Preconditions.checkBusinessError(lockBincode, "缺少仓位信息，请确定");
            StockAdjustQueryConvertToStockDTO sourceStock = selectSourceCharacterStock(requestData, requestDTO.getStgAreaCode());
            Preconditions.checkBusinessError(sourceStock.isHaveNewStock(), "根据提报信息未定位到现有库存数据，请确定信息");
            BigDecimal adjustQty = returnAdjustQtyByRequestQtyAndRequestSn(requestData.getAdjustQty(), requestData.getAdjustSns());
            deleteExistingStock(sourceStock, adjustQty);
            StockAdjustQueryConvertToStockDTO targetStock = selectTargetCharacterStock(requestData, requestDTO.getStgAreaCode());
            Stock addStock = targetStock.getStock();
            if (targetStock.isHaveNewStock()) {
                newStockByStockQueryInfo(requestDTO.getStgAreaCode(), adjustQty, addStock);
                insertStockList.add(addStock);
            } else {
                addStockByAdjustInfo(adjustQty, addStock);
                stockMapper.updateByPrimaryKeySelective(addStock);
            }
        });
        if (insertStockList.size() != 0) {
            stockMapper.insertList(insertStockList);
        }
    }

    /**
     * 库存移动
     *
     * @param requestDTO
     */
    private void adjustMovePod(StockAdjustRequestDTO requestDTO) {
        List<Stock> insertStockList = new ArrayList<>();
        requestDTO.getData().stream().forEach(requestData -> {
            boolean lockBincode = Strings.isNullOrEmpty(requestData.getSourceBincode()) || Strings.isNullOrEmpty(requestData.getTargetBincode());
            Preconditions.checkBusinessError(lockBincode, "缺少仓位信息，请确定");
            StockAdjustQueryConvertToStockDTO sourceStock = selectSourceCharacterStock(requestData, requestDTO.getStgAreaCode());
            Preconditions.checkBusinessError(sourceStock.isHaveNewStock(), "根据提报信息未定位到现有库存数据，请确定信息");
            BigDecimal adjustQty = returnAdjustQtyByRequestQtyAndRequestSn(requestData.getAdjustQty(), requestData.getAdjustSns());
            copySourceStockCharacterToTargetSourceCharacter(requestData);
            deleteExistingStock(sourceStock, adjustQty);
            StockAdjustQueryConvertToStockDTO targetStock = selectTargetCharacterStock(requestData, requestDTO.getStgAreaCode());
            Stock addStock = targetStock.getStock();
            if (targetStock.isHaveNewStock()) {
                newStockByStockQueryInfo(requestDTO.getStgAreaCode(), adjustQty, addStock);
                insertStockList.add(addStock);
            } else {
                addStockByAdjustInfo(adjustQty, addStock);
                stockMapper.updateByPrimaryKeySelective(addStock);
            }
        });
        if (insertStockList.size() != 0) {
            stockMapper.insertList(insertStockList);
        }
    }


    /**
     * 复制源属性至目标属性
     *
     * @param adjustDataDTO
     */
    private void copySourceStockCharacterToTargetSourceCharacter(StockAdjustDataDTO adjustDataDTO) {
        adjustDataDTO.setTargetCargoOwner(adjustDataDTO.getSourceCargoOwner());
        adjustDataDTO.setTargetMatCode(adjustDataDTO.getSourceMatCode());
        adjustDataDTO.setTargetBatchNum(adjustDataDTO.getSourceBatchNum());
        adjustDataDTO.setTargetContainerCode(adjustDataDTO.getSourceContainerCode());
        adjustDataDTO.setTargetSpecCode(adjustDataDTO.getSourceSpecCode());
        adjustDataDTO.setTargetStkCharacter1(adjustDataDTO.getSourceStkCharacter1());
        adjustDataDTO.setTargetStkCharacter2(adjustDataDTO.getSourceStkCharacter2());
        adjustDataDTO.setTargetStkCharacter3(adjustDataDTO.getSourceStkCharacter3());
        adjustDataDTO.setTargetStkCharacter4(adjustDataDTO.getSourceStkCharacter4());
        adjustDataDTO.setTargetStkCharacter5(adjustDataDTO.getSourceStkCharacter5());
    }

    /**
     * 条码替换
     *
     * @param requestDTO
     */
    private void adjustReplaceSn(StockAdjustRequestDTO requestDTO) {
        requestDTO.getData().stream().forEach(requestData -> {
            StockAdjustQueryConvertToStockDTO targetStock = selectTargetCharacterStock(requestData, requestDTO.getStgAreaCode());
            Preconditions.checkBusinessError(targetStock.isHaveNewStock(), "根据提报信息未定位到现有库存数据，请确定信息");
            StockSn stockSn = stockSnMapper.selectByStkCodeAndSnAndValidFlagAndDeleteFlag(targetStock.getStock().getStkCode(), requestData.getSourceSn(), ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
            Preconditions.checkBusinessError(stockSn == null, "源SN无有效库存信息：" + requestData.getSourceSn());
            stockSn.setSn(requestData.getTargetSn());
            stockSn.setLastModifiedTime(new Date());
            stockSnMapper.updateByPrimaryKeySelective(stockSn);
        });
    }

    /**
     * 库存调整删库存
     *
     * @param requestDTO
     */
    private void adjustDeleteStock(StockAdjustRequestDTO requestDTO) {
        checkAdjustAddOrDeleteStockRequiredParamAndBusinessValid(requestDTO);
        requestDTO.getData().stream().forEach(requestData -> {
            StockAdjustQueryConvertToStockDTO targetStock = selectTargetCharacterStock(requestData, requestDTO.getStgAreaCode());
            BigDecimal adjustQty = returnAdjustQtyByRequestQtyAndRequestSn(requestData.getAdjustQty(), requestData.getAdjustSns());
            Preconditions.checkBusinessError(targetStock.isHaveNewStock(), "根据提报信息未定位到现有库存数据，请确定信息");
            deleteExistingStock(targetStock, adjustQty);
        });
    }

    /**
     * 删除现有库存
     *
     * @param targetStock
     * @param adjustQty
     */
    private void deleteExistingStock(StockAdjustQueryConvertToStockDTO targetStock, BigDecimal adjustQty) {
        Stock adjustStock = targetStock.getStock();
        BigDecimal totalAvailableQty = adjustStock.getAvailableQty().subtract(adjustQty);
        BigDecimal totalTotalQty = adjustStock.getTotalQty().subtract(adjustQty);
        adjustStock.setAvailableQty(totalAvailableQty);
        adjustStock.setTotalQty(totalTotalQty);
        adjustStock.setDateLastRm(new Date());
        adjustStock.setLastModifiedTime(new Date());
        stockMapper.updateByPrimaryKeySelective(adjustStock);
    }

    /**
     * 直接添加库存
     *
     * @param requestDTO
     */
    private void adjustAddStock(StockAdjustRequestDTO requestDTO) {
        checkAdjustAddOrDeleteStockRequiredParamAndBusinessValid(requestDTO);
        String requestAreaCode = requestDTO.getStgAreaCode();
        List<Stock> insertStockList = new ArrayList<>();
        requestDTO.getData().stream().forEach(requestData -> {
            StockAdjustQueryConvertToStockDTO targetStock = selectTargetCharacterStock(requestData, requestAreaCode);
            BigDecimal adjustQty = returnAdjustQtyByRequestQtyAndRequestSn(requestData.getAdjustQty(), requestData.getAdjustSns());
            Stock adjustStock = targetStock.getStock();
            if (targetStock.isHaveNewStock()) {
                newStockByStockQueryInfo(requestAreaCode, adjustQty, adjustStock);
                insertStockList.add(adjustStock);
            } else {
                addStockByAdjustInfo(adjustQty, adjustStock);
                stockMapper.updateByPrimaryKeySelective(adjustStock);
            }
        });
        if (insertStockList.size() != 0) {
            stockMapper.insertList(insertStockList);
        }

    }

    /**
     * 根据查找库存的条件组装一条新的库存
     *
     * @param requestAreaCode
     * @param adjustQty
     * @param adjustStock
     */
    private void newStockByStockQueryInfo(String requestAreaCode, BigDecimal adjustQty, Stock adjustStock) {
        BaseWhArea baseWhArea = baseWhAreaMapper.selectByAreaCodeAndDeleteFlag(requestAreaCode, NOT_DELETED.getStatus());
        adjustStock.setPodCode(adjustStock.getBincode().substring(0, 6));
        adjustStock.setInitQty(adjustQty);
        adjustStock.setTotalQty(adjustQty);
        adjustStock.setAvailableQty(adjustQty);
        adjustStock.setLockQty(new BigDecimal(0));
        adjustStock.setWhCode(baseWhArea.getWhCode());
        BaseMat baseMat = baseMatMapper.selectByMatCode(adjustStock.getMatCode());
        adjustStock.setBumCode(baseMat.getBumCode());
        adjustStock.setMatWeight(baseMat.getNetWeight());
        adjustStock.setWeightUnit(baseMat.getWeightUnit());
        adjustStock.setContainerFlag(NOT_IN_CONTAINER);
        adjustStock.setFreezeFlag(FREEZE_IN);
        if (!Strings.isNullOrEmpty(adjustStock.getContainerCode())) {
            adjustStock.setContainerFlag(IN_CONTAINER);
        }
        adjustStock.setDateLastPm(new Date());
        adjustStock.setDeleteFlag(NOT_DELETED.getStatus());
        adjustStock.setCreatedTime(new Date());
        adjustStock.setLastModifiedTime(new Date());
    }

    /**
     * 根据调整类型直接更新（添加）库存
     *
     * @param adjustQty
     * @param adjustStock
     */
    private void addStockByAdjustInfo(BigDecimal adjustQty, Stock adjustStock) {
        BigDecimal totalInitQty = adjustStock.getInitQty().add(adjustQty);
        BigDecimal totalAvailableQty = adjustStock.getAvailableQty().add(adjustQty);
        BigDecimal totalTotalQty = adjustStock.getTotalQty().add(adjustQty);
        adjustStock.setInitQty(totalInitQty);
        adjustStock.setAvailableQty(totalAvailableQty);
        adjustStock.setTotalQty(totalTotalQty);
        adjustStock.setDateLastPm(new Date());
        adjustStock.setLastModifiedTime(new Date());
    }

    /**
     * 校验加减库存：targetBincode、adjustQty/adjustSns、target开头的库存属性
     *
     * @param stockAdjustRequestDTO
     */
    private void checkAdjustAddOrDeleteStockRequiredParamAndBusinessValid(StockAdjustRequestDTO stockAdjustRequestDTO) {
        stockAdjustRequestDTO.getData().stream().forEach(requestData -> {
            boolean missingQtyInfo = (requestData.getAdjustQty() == null || requestData.getAdjustQty().compareTo(BigDecimal.ZERO) == 0) && (requestData.getAdjustSns() == null || requestData.getAdjustSns().size() == 0);
            Preconditions.checkBusinessError(missingQtyInfo, "未提供调整数量");
            String targetBincode = requestData.getTargetBincode();
            BaseBincodeDetail baseBincodeDetail = baseBincodeDetailMapper.selectByBincodeAndValidFlagAndDeleteFlag(targetBincode, ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
            Preconditions.checkBusinessError(baseBincodeDetail.getCargoCapacityStatus().equals(EMPTY_BIN), "不允许在空仓位上直接操作加减库存");
            BaseMat baseMat = baseMatMapper.selectByMatCode(requestData.getTargetMatCode());
            Preconditions.checkBusinessError(baseMat == null, "未定义的物料信息，请先同步物料基础信息：" + requestData.getTargetMatCode());
        });
    }

    /**
     * 根据请求数量和请求SN返回调整总数量
     *
     * @param requestQty
     * @param requestSns
     * @return
     */
    private BigDecimal returnAdjustQtyByRequestQtyAndRequestSn(BigDecimal requestQty, List<String> requestSns) {
        BigDecimal finalAdjustQty = new BigDecimal(0);
        if (requestSns != null && requestSns.size() != 0) {
            finalAdjustQty = new BigDecimal(requestSns.size());
        } else {
            finalAdjustQty = requestQty;
        }
        return finalAdjustQty;
    }

    /**
     * 获取目标属性库存，如果没有生成一条新的库存属性
     *
     * @param request
     * @param stgAreaCode
     * @return
     */
    private StockAdjustQueryConvertToStockDTO selectTargetCharacterStock(StockAdjustDataDTO request, String stgAreaCode) {
        StockAdjustQueryConvertToStockDTO stockAdjustQueryConvertToStockDTO = new StockAdjustQueryConvertToStockDTO();
        StockQueryDTO stockQueryDTO = new StockQueryDTO();
        stockQueryDTO.setCargoOwner(request.getTargetCargoOwner());
        stockQueryDTO.setMatCode(request.getTargetMatCode());
        stockQueryDTO.setBincode(request.getTargetBincode());
        stockQueryDTO.setStkCharacter1(request.getTargetStkCharacter1());
        stockQueryDTO.setStkCharacter2(request.getTargetStkCharacter2());
        stockQueryDTO.setStkCharacter3(request.getTargetStkCharacter3());
        stockQueryDTO.setStkCharacter4(request.getTargetStkCharacter4());
        stockQueryDTO.setStkCharacter5(request.getTargetStkCharacter5());
        stockQueryDTO.setStgAreaCode(stgAreaCode);
        stockQueryDTO.setBatchNum(request.getTargetBatchNum());
        stockQueryDTO.setContainerCode(request.getTargetContainerCode());
        stockQueryDTO.setSpecCode(request.getTargetSpecCode());
        Stock stock = stockMapper.selectStockByStockCharacter(stockQueryDTO);
        if (stock == null) {
            stockAdjustQueryConvertToStockDTO.setHaveNewStock(true);
            stock = stockAdjustQueryVsStockMapStruct.toEntity(stockQueryDTO);
            String stkCode = UUID.randomUUID().toString().replaceAll("-", "");
            stock.setStkCode(stkCode);
            stockAdjustQueryConvertToStockDTO.setStock(stock);
            return stockAdjustQueryConvertToStockDTO;
        }
        stockAdjustQueryConvertToStockDTO.setHaveNewStock(false);
        stockAdjustQueryConvertToStockDTO.setStock(stock);
        return stockAdjustQueryConvertToStockDTO;
    }

    /**
     * 获取源属性库存，如果没有生成一条新的库存属性
     *
     * @param request
     * @param stgAreaCode
     * @return
     */
    private StockAdjustQueryConvertToStockDTO selectSourceCharacterStock(StockAdjustDataDTO request, String stgAreaCode) {
        StockAdjustQueryConvertToStockDTO stockAdjustQueryConvertToStockDTO = new StockAdjustQueryConvertToStockDTO();
        StockQueryDTO stockQueryDTO = new StockQueryDTO();
        stockQueryDTO.setCargoOwner(request.getSourceCargoOwner());
        stockQueryDTO.setMatCode(request.getSourceMatCode());
        stockQueryDTO.setBincode(request.getSourceBincode());
        stockQueryDTO.setStkCharacter1(request.getSourceStkCharacter1());
        stockQueryDTO.setStkCharacter2(request.getSourceStkCharacter2());
        stockQueryDTO.setStkCharacter3(request.getSourceStkCharacter3());
        stockQueryDTO.setStkCharacter4(request.getSourceStkCharacter4());
        stockQueryDTO.setStkCharacter5(request.getSourceStkCharacter5());
        stockQueryDTO.setStgAreaCode(stgAreaCode);
        stockQueryDTO.setBatchNum(request.getSourceBatchNum());
        stockQueryDTO.setContainerCode(request.getSourceContainerCode());
        stockQueryDTO.setSpecCode(request.getSourceSpecCode());
        Stock stock = stockMapper.selectStockByStockCharacter(stockQueryDTO);
        if (stock == null) {
            stockAdjustQueryConvertToStockDTO.setHaveNewStock(true);
            stock = stockAdjustQueryVsStockMapStruct.toEntity(stockQueryDTO);
            String stkCode = UUID.randomUUID().toString().replaceAll("-", "");
            stock.setStkCode(stkCode);
            stockAdjustQueryConvertToStockDTO.setStock(stock);
            return stockAdjustQueryConvertToStockDTO;
        }
        stockAdjustQueryConvertToStockDTO.setHaveNewStock(false);
        stockAdjustQueryConvertToStockDTO.setStock(stock);
        return stockAdjustQueryConvertToStockDTO;
    }


    /**
     * 插入库存调整记录
     *
     * @param stockAdjustRequestDTO
     */
    private void recordStockAbNormalAdjust(StockAdjustRequestDTO stockAdjustRequestDTO) {
        List<StockAbnormalAdjustRecord> insertStockList = new ArrayList<>();
        stockAdjustRequestDTO.getData().stream().forEach(adjustData -> {
            StockAbnormalAdjustRecord stockAbnormalAdjustRecord = new StockAbnormalAdjustRecord();
            stockAbnormalAdjustRecord.setSrcAdjustNo(stockAdjustRequestDTO.getSrcAdjustNo());
            stockAbnormalAdjustRecord.setAdjustType(stockAdjustRequestDTO.getAdjustType());
            stockAbnormalAdjustRecord.setSrcAdjustUserCode(stockAdjustRequestDTO.getSrcAdjustUserCode());
            stockAbnormalAdjustRecord.setStgAreaCode(stockAdjustRequestDTO.getStgAreaCode());
            String sysAdjustNo = UUID.randomUUID().toString().replaceAll("-", "");
            stockAbnormalAdjustRecord.setSysAdjustNo(sysAdjustNo);
            String beforeAdjustStkCode = "";
            String afterAdjustStkCode = "";
            if (!Strings.isNullOrEmpty(adjustData.getSourceBincode())) {
                StockAdjustQueryConvertToStockDTO sourceStock = selectSourceCharacterStock(adjustData, stockAdjustRequestDTO.getStgAreaCode());
                beforeAdjustStkCode = sourceStock.getStock().getStkCode();
            }
            if (!Strings.isNullOrEmpty(adjustData.getTargetBincode())) {
                StockAdjustQueryConvertToStockDTO targetStock = selectTargetCharacterStock(adjustData, stockAdjustRequestDTO.getStgAreaCode());
                afterAdjustStkCode = targetStock.getStock().getStkCode();
            }
            stockAbnormalAdjustRecord.setBeforeAdjustStkCode(beforeAdjustStkCode);
            stockAbnormalAdjustRecord.setAfterAdjustStkCode(afterAdjustStkCode);
            BigDecimal adjustQty = returnAdjustQtyByRequestQtyAndRequestSn(adjustData.getAdjustQty(), adjustData.getAdjustSns());
            stockAbnormalAdjustRecord.setAdjustQty(adjustQty);
            stockAbnormalAdjustRecord.setBeforeSno(adjustData.getSourceSn());
            stockAbnormalAdjustRecord.setAfterSno(adjustData.getTargetSn());
            stockAbnormalAdjustRecord.setBeforeCargoOwner(adjustData.getSourceCargoOwner());
            stockAbnormalAdjustRecord.setBeforeMatCode(adjustData.getSourceMatCode());
            if (!Strings.isNullOrEmpty(adjustData.getSourceBincode())) {
                stockAbnormalAdjustRecord.setBeforeBincode(adjustData.getSourceBincode());
                stockAbnormalAdjustRecord.setBeforePodCode(adjustData.getSourceBincode().substring(0, 6));
            }
            stockAbnormalAdjustRecord.setBeforeBatchNum(adjustData.getSourceBatchNum());
            stockAbnormalAdjustRecord.setBeforeContainerCode(adjustData.getSourceContainerCode());
            stockAbnormalAdjustRecord.setBeforeSpecCode(adjustData.getSourceSpecCode());
            stockAbnormalAdjustRecord.setBeforeStkCharacter1(adjustData.getSourceStkCharacter1());
            stockAbnormalAdjustRecord.setBeforeStkCharacter2(adjustData.getSourceStkCharacter2());
            stockAbnormalAdjustRecord.setBeforeStkCharacter3(adjustData.getSourceStkCharacter3());
            stockAbnormalAdjustRecord.setBeforeStkCharacter4(adjustData.getSourceStkCharacter4());
            stockAbnormalAdjustRecord.setBeforeStkCharacter5(adjustData.getSourceStkCharacter5());
            stockAbnormalAdjustRecord.setAfterCargoOwner(adjustData.getTargetCargoOwner());
            stockAbnormalAdjustRecord.setAfterMatCode(adjustData.getTargetMatCode());
            if (!Strings.isNullOrEmpty(adjustData.getTargetBincode())) {
                stockAbnormalAdjustRecord.setAfterBincode(adjustData.getTargetBincode());
                stockAbnormalAdjustRecord.setAfterPodCode(adjustData.getTargetBincode().substring(0, 6));
            }
            stockAbnormalAdjustRecord.setAfterBatchNum(adjustData.getTargetBatchNum());
            stockAbnormalAdjustRecord.setAfterContainerCode(adjustData.getTargetContainerCode());
            stockAbnormalAdjustRecord.setAfterSpecCode(adjustData.getTargetSpecCode());
            stockAbnormalAdjustRecord.setAfterStkCharacter1(adjustData.getTargetStkCharacter1());
            stockAbnormalAdjustRecord.setAfterStkCharacter2(adjustData.getTargetStkCharacter2());
            stockAbnormalAdjustRecord.setAfterStkCharacter3(adjustData.getTargetStkCharacter3());
            stockAbnormalAdjustRecord.setAfterStkCharacter4(adjustData.getTargetStkCharacter4());
            stockAbnormalAdjustRecord.setAfterStkCharacter5(adjustData.getTargetStkCharacter5());
            stockAbnormalAdjustRecord.setCreatedTime(new Date());
            stockAbnormalAdjustRecord.setLastModifiedTime(new Date());
            if (adjustData.getAdjustSns() != null && adjustData.getAdjustSns().size() != 0) {
                String adjustSnListString = ListUtils.convertListToString(adjustData.getAdjustSns());
                stockAbnormalAdjustRecord.setAdjustSnList(adjustSnListString);
            }
            insertStockList.add(stockAbnormalAdjustRecord);
        });
        stockAbnormalAdjustRecordMapper.insertList(insertStockList);
    }


    /**
     * 统一校验
     *
     * @param stockAdjustRequestDTO
     */
    private void commonCheckRequestRequiredParamAndBusinessValid(StockAdjustRequestDTO stockAdjustRequestDTO) {
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(stockAdjustRequestDTO.getSrcAdjustUserCode()), "未提供调整人");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(stockAdjustRequestDTO.getAdjustType()), "未提供调整类型");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(stockAdjustRequestDTO.getStgAreaCode()), "未提供调整库区");
        BaseWhArea baseWhArea = baseWhAreaMapper.selectByAreaCodeAndDeleteFlag(stockAdjustRequestDTO.getStgAreaCode(), NOT_DELETED.getStatus());
        Preconditions.checkBusinessError(baseWhArea == null, "库区编码：" + stockAdjustRequestDTO.getStgAreaCode() + "未定义，请重新选择");
        boolean missingAdjustData = stockAdjustRequestDTO.getData() == null || stockAdjustRequestDTO.getData().size() == 0;
        Preconditions.checkBusinessError(missingAdjustData, "未提供调整详情");
        stockAdjustRequestDTO.getData().stream().forEach(requestData -> {
            String targetBincode = requestData.getTargetBincode();
            String sourceBincode = requestData.getSourceBincode();
            Preconditions.checkBusinessError(Strings.isNullOrEmpty(targetBincode), "未提供目标货架");
            BasePodDetail targetPodDetail = basePodDetailMapper.selectByPodCodeAndValidFlagAndDeleteFlag(targetBincode.substring(0, 6), ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
            boolean targetPodNotSameAreaCode = !targetPodDetail.getAreaCode().equals(stockAdjustRequestDTO.getStgAreaCode());
            Preconditions.checkBusinessError(targetPodNotSameAreaCode, "目标货架所在库区与操作库区不同，不允许跨库区调整");
            if (!Strings.isNullOrEmpty(sourceBincode)) {
                BasePodDetail sourcePodDetail = basePodDetailMapper.selectByPodCodeAndValidFlagAndDeleteFlag(sourceBincode.substring(0, 6), ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
                boolean sourcePodNotSameAreaCode = !sourcePodDetail.getAreaCode().equals(stockAdjustRequestDTO.getStgAreaCode());
                Preconditions.checkBusinessError(sourcePodNotSameAreaCode, "源货架所在库区与操作库区不同，不允许跨库区调整");
            }
        });
    }
}
