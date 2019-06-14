package com.wisdom.iwcs.mapper;


import com.wisdom.iwcs.domain.GeneratorColumnDto;

import java.util.List;

public interface GeneratorColumnMapper {
    List<GeneratorColumnDto> getColumnInfo(String tableName);
}