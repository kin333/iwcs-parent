package com.wisdom.iwcs.service.base.baseImpl;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.BasePodAndMapDTO;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.service.callHik.callHikImpl.BindPodAndBerthService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class BasePodAndMapService {
    private final Logger logger = LoggerFactory.getLogger(BasePodAndMapService.class);
    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private  BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private BindPodAndBerthService bindPodAndBerthService;

    /**
     * 解绑
     * @param basePodAndMapDTO
     * @return
     */
    public int untying(BasePodAndMapDTO basePodAndMapDTO){
        int num = 0;
        if (StringUtils.isBlank(basePodAndMapDTO.getPodCode())) {
            throw new BusinessException("货架编号不能为空");
        }
        //根据货架编号查询base_pod_detail表的信息
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(basePodAndMapDTO.getPodCode());
        Preconditions.checkBusinessError(basePodDetail == null,
                basePodAndMapDTO.getPodCode() + "找不到该货架编号对应的货架信息");
        if(basePodDetail.getInLock()==1 || StringUtils.isNotBlank(basePodDetail.getLockSource())){
            throw new BusinessException("货架已被锁定");
        }
        //根据货架编号查询该货架的原来位置信息
        BaseMapBerth berthData = baseMapBerthMapper.selectDataByPodCode(basePodAndMapDTO.getPodCode());
        Preconditions.checkBusinessError(berthData == null,
                basePodAndMapDTO.getPodCode() + "找不到该货架编号对应的初始位置信息");

        if (basePodDetail.getInLock()==0 && StringUtils.isBlank(basePodDetail.getLockSource()) && berthData.getInLock() == 0
                && StringUtils.isBlank(berthData.getLockSource())){
            //在海康中解绑旧的位置信息
            BasePodAndMapDTO basePodAndMapDTO1 = new BasePodAndMapDTO();
            basePodAndMapDTO1.setPodCode(basePodAndMapDTO.getPodCode());
            basePodAndMapDTO1.setPoint(berthData.getBerCode());
            basePodAndMapDTO1.setIndBind("0");
            Result result = bindPodAndBerthService.bindPodAndBerth(basePodAndMapDTO1);
            //海康解绑成功后   清除该货架在map_berth表中原来的位置
            if (result.getReturnMsg().equals("操作成功")){
                BaseMapBerth baseMapBerth2 = new BaseMapBerth();
                int version = 0;
                if (berthData.getVersion()!=null){
                    version = berthData.getVersion();
                }
                baseMapBerth2.setVersion(version);
                baseMapBerth2.setBerCode(berthData.getBerCode());

                num = baseMapBerthMapper.deletePodCodeByBerCode(baseMapBerth2);
                Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

                //清除该货架的货架表中的位置信息
                BasePodDetail update = new BasePodDetail();
                update.setId(basePodDetail.getId());
                update.setBerCode("");
                update.setCoox("");
                update.setCooy("");
                update.setLastModifiedTime(new Date());
                Integer v = basePodDetail.getVersion()+1;
                update.setVersion(v);
                basePodDetailMapper.updateByPrimaryKeySelective(update);
            }
        }
        return num;

    }


    public int bind(BasePodAndMapDTO basePodAndMapDTO){
        int num = 0;
        if (StringUtils.isBlank(basePodAndMapDTO.getPodCode())) {
            throw new BusinessException("货架编号不能为空");
        }
        if (StringUtils.isBlank(basePodAndMapDTO.getPoint())) {
            throw new BusinessException("目标点不能为空");
        }
        //将新的点位信息转换为berCode
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(basePodAndMapDTO.getPoint());
        Preconditions.checkBusinessError(baseMapBerth == null,
                basePodAndMapDTO.getPoint() + "找不到别名对应的地图编码");
        if (StringUtils.isNotBlank(baseMapBerth.getPodCode())){
            throw new BusinessException("目标点位已有货架");
        }
        if (baseMapBerth.getInLock()==1 || StringUtils.isNotBlank(baseMapBerth.getLockSource())){
            throw new BusinessException("目标点位已锁定");
        }
        //根据货架编号查询base_pod_detail表的信息
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(basePodAndMapDTO.getPodCode());
        Preconditions.checkBusinessError(basePodDetail == null,
                basePodAndMapDTO.getPodCode() + "找不到该货架编号对应的货架信息");
        if(basePodDetail.getInLock()==1 || StringUtils.isNotBlank(basePodDetail.getLockSource())){
            throw new BusinessException("货架已被锁定");
        }

        if (StringUtils.isBlank(baseMapBerth.getPodCode()) && baseMapBerth.getInLock()==0 && StringUtils.isBlank(baseMapBerth.getLockSource())
                && basePodDetail.getInLock()==0 && StringUtils.isBlank(basePodDetail.getLockSource())){
            //在海康中绑定新的位置信息
            BasePodAndMapDTO basePodAndMapDTO2 = new BasePodAndMapDTO();
            basePodAndMapDTO2.setPodCode(basePodAndMapDTO.getPodCode());
            basePodAndMapDTO2.setPoint(baseMapBerth.getBerCode());
            basePodAndMapDTO2.setIndBind("1");
            Result result1 = bindPodAndBerthService.bindPodAndBerth(basePodAndMapDTO2);
            if (result1.getReturnMsg().equals("操作成功")){
                //更新货架信息的新的点位
                BasePodDetail basePodDetail1 = new BasePodDetail();
                basePodDetail1.setBerCode(baseMapBerth.getBerCode());
                basePodDetail1.setCoox(bigDecimalToString(baseMapBerth.getCoox()));
                basePodDetail1.setCooy(bigDecimalToString(baseMapBerth.getCooy()));
                basePodDetail1.setVersion(basePodDetail.getVersion());
                basePodDetail1.setPodCode(basePodAndMapDTO.getPodCode());
                //开始更新base_pod_detail表的信息
                num = basePodDetailMapper.updateMapsByPodCode(basePodDetail1);
                Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

                //更新货架编号的新位置信息
                BaseMapBerth baseMapBerth1 = new BaseMapBerth();
                baseMapBerth1.setPodCode(basePodAndMapDTO.getPodCode());
                int version1 = 0;
                if (baseMapBerth.getVersion()!=null){
                    version1 = baseMapBerth.getVersion();
                }
                baseMapBerth1.setVersion(version1);
                baseMapBerth1.setBerCode(baseMapBerth.getBerCode());
                //将货架编号写入新的位置
                num=baseMapBerthMapper.updatePodByBerCode(baseMapBerth1);
                Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);
            }
        }
        return num;

    }

    //将BigDecimal转换为String类型
    public String bigDecimalToString(BigDecimal bigDecimal) {
        BigDecimal bigRate = new BigDecimal(1000);
        BigDecimal bd = bigDecimal.multiply(bigRate);
        String rst = bd.toString();
        rst = rst.substring(0,rst.indexOf("."));
        StringBuffer sb = new StringBuffer();
        if (rst.length() < 6) {
            for (int i = 0; i < 6 - rst.length(); i++) {
                sb.append("0");
            }
            sb.append(rst);
        }else{
            sb.append(rst);
        }
        String result = new String(sb);
        return result;
    }

}
