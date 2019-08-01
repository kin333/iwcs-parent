package com.wisdom.iwcs.mapper.version;


import com.wisdom.iwcs.domain.version.Version;
import com.wisdom.iwcs.domain.version.VersionDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VersionMapper {
    String selectinformation(Integer version);
    List<Version> selectVersionPage(Map map);
    int insert(VersionDto versionDto);
    Version selectnewVersion();

}
