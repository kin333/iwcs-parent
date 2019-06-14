package com.wisdom.iwcs.mapper.codec;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.codec.Sequence;

import java.util.List;
import java.util.Map;

public interface SequenceMapper extends MyMapperAndIds<Sequence>, DeleteLogicMapper<Sequence> {
    List<Sequence> selectPage(Map map);

    int getSequence(String seqName);

    int resetInterBlNo();

    int resetInvoiceNo();
}