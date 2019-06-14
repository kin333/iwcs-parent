package com.wisdom.service.base.baseImpl;

import com.csvreader.CsvReader;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.base.BaseMatMapStruct;
import com.wisdom.controller.mapstruct.base.BaseMatPackageSpecMapStruct;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMat;
import com.wisdom.iwcs.domain.base.BaseMatPackageSpec;
import com.wisdom.iwcs.domain.base.dto.BaseMatDTO;
import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.mapper.base.BaseMatMapper;
import com.wisdom.iwcs.mapper.base.BaseMatPackageSpecMapper;
import com.wisdom.iwcs.mapper.stock.StockMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.IBaseMatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class BaseMatService implements IBaseMatService {
    private final Logger logger = LoggerFactory.getLogger(BaseMatService.class);

    private final BaseMatMapper baseMatMapper;

    private final BaseMatMapStruct baseMatMapStruct;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private BaseMatPackageSpecMapper baseMatPackageSpecMapper;

    private BaseMatPackageSpecMapStruct baseMatPackageSpecMapStruct;

    @Autowired
    public BaseMatService(BaseMatMapStruct baseMatMapStruct, BaseMatMapper baseMatMapper) {
        this.baseMatMapStruct = baseMatMapStruct;
        this.baseMatMapper = baseMatMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseMatDTO }
     * @return int
     */
    public int insert(BaseMatDTO record) {
        BaseMat baseMat = baseMatMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMat.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseMat.setCreatedTime(new Date());
        baseMat.setCreatedBy(userId);
        baseMat.setLastModifiedBy(userId);
        baseMat.setLastModifiedTime(new Date());

        int num = baseMatMapper.insert(baseMat);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseMatDTO> }
     * @return int
     */
    public int insertBatch(List<BaseMatDTO> records) {
        List<BaseMat> recordList = baseMatMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseMatMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseMatDTO }
     */
    public BaseMatDTO selectByPrimaryKey(Integer id) {

        BaseMat baseMat = baseMatMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseMat, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseMatMapStruct.toDto(baseMat);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseMatDTO }
     * @return {@link List<BaseMatDTO> }
     */
    public List<BaseMatDTO> selectSelective(BaseMatDTO record) {
        BaseMat baseMat = baseMatMapStruct.toEntity(record);

        List<BaseMat> baseMatList = baseMatMapper.select(baseMat);
        return baseMatMapStruct.toDto(baseMatList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseMatDTO }
     * @return int
     */
    public int updateByPrimaryKey(BaseMatDTO record) {
        BaseMat baseMat = baseMatMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMat.setLastModifiedBy(userId);
        baseMat.setLastModifiedTime(new Date());

        int num = baseMatMapper.updateByPrimaryKey(baseMat);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseMatDTO }
     * @return int
     */
    public int updateByPrimaryKeySelective(BaseMatDTO record) {
        BaseMat baseMat = baseMatMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMat.setLastModifiedBy(userId);
        baseMat.setLastModifiedTime(new Date());

        int num = baseMatMapper.updateByPrimaryKeySelective(baseMat);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = baseMatMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    public int deleteLogicByPrimaryKey(Integer id) {
        return baseMatMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return baseMatMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return baseMatMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseMatDTO> }
     */
    public GridReturnData<BaseMatDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseMatDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseMat> list = baseMatMapper.selectPage(map);

        PageInfo<BaseMat> pageInfo = new PageInfo<>(list);
        PageInfo<BaseMatDTO> pageInfoFinal = new PageInfo<>(baseMatMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 物料同步
     */
    @Override
    public Result saveMat(List<BaseMatDTO> baseMatDTOList) {
        Result result = new Result();
        //检查存在更新，不存在插入
        List<BaseMatDTO> newSaveMatList = new ArrayList<>();
        baseMatDTOList.stream().forEach(matInfo -> {
            BaseMat baseMat = baseMatMapper.selectByMatCode(matInfo.getMatCode());
            if (baseMat == null) {
                newSaveMatList.add(matInfo);
            } else {
                baseMatMapper.updateByMatCode(matInfo);
            }
            if (matInfo.getSpecFlag().equals("1") && matInfo.getBaseMatPackageSpecDTO() != null) {
                //查询当前物料是否存在有效库存，如果有，不能更新物料规格或新增规格或删除规格
                Stock stock = stockMapper.selectStockByMatCode(matInfo.getMatCode());
                if (stock == null) {
                    BaseMatPackageSpec baseMatPackageSpec = baseMatPackageSpecMapStruct.toEntity(matInfo.getBaseMatPackageSpecDTO());
                    baseMatPackageSpecMapper.insert(baseMatPackageSpec);
                } else {
                    result.setReturnCode(99);
                    result.setReturnMsg("物料：" + matInfo.getMatCode() + "存在库存，不可在修改物料规格");
                }
            }
        });
        insertBatch(newSaveMatList);
        return new Result();
    }

    @Override
    public void testSyncMat() {
        try {
            File kkFile = new File("E:/Project/iwcs-wisdom/src/main/webapp/static/commodit1.csv");
            InputStream in = new FileInputStream(kkFile);
            CsvReader cr = new CsvReader(in, ',', Charset.forName("GBK"));
            cr.readHeaders();
            String[] headers = cr.getHeaders();
            System.out.println(headers.length);
            if (headers.length != 3) {
                System.out.println("错误");
            }
            List<BaseMatDTO> baseMatDTOList = new ArrayList<>();
            while (cr.readRecord()) {
                BaseMatDTO baseMatDTO = new BaseMatDTO();
                baseMatDTO.setMatCode(cr.get(0));
                baseMatDTO.setCarnm(cr.get(1));
                baseMatDTO.setLength(new BigDecimal(cr.get(2)));
                baseMatDTO.setWidth(new BigDecimal(cr.get(3)));
                baseMatDTO.setHeight(new BigDecimal(cr.get(4)));
                baseMatDTO.setGrossWeight(new BigDecimal(cr.get(5)));
                baseMatDTO.setNetWeight(new BigDecimal((cr.get(5))));
                baseMatDTO.setPumCode("pkg");
                baseMatDTO.setBumCode("pkg");
                baseMatDTOList.add(baseMatDTO);
            }
            saveMat(baseMatDTOList);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
