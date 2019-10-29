package com.wisdom.iwcs.service.sysbase;

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

import static com.wisdom.iwcs.common.utils.DictionaryConstants.DictionaryNameConstants.*;

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
    public int dataMigration(){

        int ret=0;
        int num=0;
        //查询迁移条件
        Dictionary dictionary = dictionaryMapper.selectByDictName(HISTORYDATE);
        String date = dictionary.getDictValue();
        //需要迁移的表
        List<Dictionary> tableList = dictionaryMapper.selectByDictType(MIGRATIONTABLE);
        //写入迁移表的信息（添加）
        List<HisDataMigrationDTO> newList = new ArrayList<>();
        //删除原表的信息（删除旧表中的数据）
        List<HisDataMigrationDTO> oldList = new ArrayList<>();
        for (int i=0;i<tableList.size();i++){
            newList.add(new HisDataMigrationDTO(tableList.get(i).getDictValue(),tableList.get(i).getDictName(),date,"created_time"));
            oldList.add(new HisDataMigrationDTO(tableList.get(i).getDictName(),date,"created_time"));
        }

        //把旧表中n天前的数据刷到新表
        for (HisDataMigrationDTO hisDataMigrationDTO :newList){
            num = hisDataMigrationMapper.taskOperationLogHis(hisDataMigrationDTO);
            if (num>0){
                //迁移成功后，在旧表表中删除已经迁移完成的数据
                for (HisDataMigrationDTO hisDataMigrationDTO1 : oldList){
                    ret=hisDataMigrationMapper.taskOperationLog(hisDataMigrationDTO1);
                    if (ret>0){
                        break;
                    }
                }
            }
        }
        return ret;
    }

}
