package com.wisdom.iwcs.service.sysbase;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.log.dto.HisDataMigrationDTO;
import com.wisdom.iwcs.domain.system.Dictionary;
import com.wisdom.iwcs.mapper.log.HisDataMigrationMapper;
import com.wisdom.iwcs.mapper.system.DictionaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.DictionaryConstants.DictionaryNameConstants.MIGRATIONTABLE;

@Service
@Transactional(rollbackFor = Exception.class)
public class HisDataMigrationService {
    private final Logger logger = LoggerFactory.getLogger(HisDataMigrationService.class);

    @Autowired
    HisDataMigrationMapper hisDataMigrationMapper;
    @Autowired
    private DictionaryMapper dictionaryMapper;

    /**
     * 迁移表中n天前的数据到新表中
     * @return
     */
    public Result dataMigration(){
        //查询数据字典数据
        List<Dictionary> dictionaryList = dictionaryMapper.selectAll();
        //根据dict_type分组
        Map<String,List<Dictionary>> groupMap = dictionaryList.stream().collect(Collectors.groupingBy(Dictionary::getDictType));
        //需要迁移的表
        List<Dictionary> tableList = groupMap.get(MIGRATIONTABLE);
        Preconditions.checkArgument(tableList.size()>0, "没有需要迁移的表！");
        //写入迁移表的信息（添加）
        List<HisDataMigrationDTO> newList = new ArrayList<>();
        tableList.forEach(t->{
            //根据不同的表查询其依据的时间字段以及迁移时间间隔
            List<Dictionary> dictionarys = groupMap.get(t.getDictName());
            if (dictionarys == null){
                logger.error("未找到该表的迁移参数"+t.getDictName());
                return;
            }
            Dictionary dictionary = dictionarys.get(0);
            newList.add(new HisDataMigrationDTO(t.getDictValue(),t.getDictName(),dictionary.getDictValue(),dictionary.getDictName()));
        });

        for (HisDataMigrationDTO hisDataMigrationDTO :newList){
            //把旧表中n天前的数据刷到新表
            int num = hisDataMigrationMapper.taskOperationLogHis(hisDataMigrationDTO);
            if (num>0){
                //迁移成功后，在旧表表中删除已经迁移完成的数据
                HisDataMigrationDTO hisDataMigrationDTO1 = new HisDataMigrationDTO();
                hisDataMigrationDTO1.setTableName(hisDataMigrationDTO.getTableName());
                hisDataMigrationDTO1.setDate(hisDataMigrationDTO.getDate());
                hisDataMigrationDTO1.setDateColName(hisDataMigrationDTO.getDateColName());
                hisDataMigrationMapper.taskOperationLog(hisDataMigrationDTO1);
            }
        }
        return new Result();
    }

}
