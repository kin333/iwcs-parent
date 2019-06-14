package com.wisdom.service;

import com.wisdom.iwcs.domain.GeneratorColumnDto;
import com.wisdom.iwcs.mapper.GeneratorColumnMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class GeneratorColumnService {
    private final Logger logger = LoggerFactory.getLogger(GeneratorColumnService.class);

    @Autowired
    GeneratorColumnMapper generatorColumnMapper;

    /**
     * 根据主键-ID查询
     *
     * @param id
     * @return
     */
    public List<GeneratorColumnDto> selectByPrimaryKey(String tableName) {
        List<GeneratorColumnDto> generatorColumnDtoList = generatorColumnMapper.getColumnInfo(tableName);
        return generatorColumnDtoList;
    }
}
