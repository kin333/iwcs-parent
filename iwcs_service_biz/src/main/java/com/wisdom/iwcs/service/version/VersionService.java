package com.wisdom.iwcs.service.version;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.MD5Utils;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseWaMapDTO;
import com.wisdom.iwcs.domain.version.Version;
import com.wisdom.iwcs.domain.version.VersionDto;
import com.wisdom.iwcs.mapper.version.VersionMapper;
import com.wisdom.iwcs.mapstruct.version.VersionStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VersionService {
    private final Logger logger = LoggerFactory.getLogger(VersionService.class);
    private final VersionMapper versionMapper;
    private final VersionStruct versionStruct;

    @Autowired
    public VersionService(VersionMapper versionMapper, VersionStruct versionStruct) {
        this.versionMapper = versionMapper;
        this.versionStruct= versionStruct;
    }
    /**
     * 分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData < BaseWaMapDTO > }.
     */
    public GridReturnData<VersionDto> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<VersionDto> mGridReturnData = new GridReturnData<>();
        Map<String, Object> map = new HashMap<>(2);
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize());
        List<Version> list = versionMapper.selectVersionPage(map);
        PageInfo<Version> pageInfo = new PageInfo<>(list);
        PageInfo<VersionDto> pageInfoFinal = new PageInfo<>(versionStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
    public Result Upload(Integer version, String information, MultipartFile file) throws FileNotFoundException {
        if(version==null){
            return new Result(0,"输入的版本号为空");
        }
        if(information.length()==0){
            return new Result(0,"输入的版本信息为空");
        }
        if(!file.isEmpty()){
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf(".")+1);
            try {
                FileUtil.filter(suffixName);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(0, "传入的文件类型错误");
            }
            String uploadPath = ResourceUtils.getURL("classpath:").getPath() + "static/";

            String newName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + "." + suffixName;
            String caselsh = fileName.substring(0, fileName.lastIndexOf("."));
            String newfileName = caselsh + newName;


            VersionDto versionDto = new VersionDto();
            versionDto.setVersion(version);
            versionDto.setInformation(information);
            versionDto.setUrl("http://localhost:8088/" + newfileName);
            int count = versionMapper.insert(versionDto);
            if (count == 0) {
                return new Result(0, "添加到数据库中失败");
            }

            /*  String fullFilePath = request.getSession().getServletContext().getRealPath("/");*/
            try {
                // 该方法是对文件写入的封装，在util类中，导入该包即可使用，后面会给出方法
                FileUtil.fileupload(file.getBytes(), uploadPath, newfileName);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return new Result(0, "上传文件失败");
            }

        }return new Result(200, "上传成功");

    }
    public Result versionupdate(@RequestParam("version") Integer version) {
        Version v = versionMapper.selectnewVersion();
        if (version.intValue() == v.getVersion().intValue()) {
            return new Result(200, "已是最新版本");
        }
        String url = v.getUrl();
        String information = v.getInformation();

        return new Result(200, "是否更新","下载地址"+url+"版本信息"+information);
    }
}