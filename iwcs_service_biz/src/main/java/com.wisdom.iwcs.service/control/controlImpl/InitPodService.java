package com.wisdom.iwcs.service.control.controlImpl;

import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.ValidFlagEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.TPSRequest.ReturnPodRequestDTO;
import com.wisdom.iwcs.domain.base.BaseWb;
import com.wisdom.iwcs.domain.control.InitPodRequestDTO;
import com.wisdom.iwcs.mapper.base.*;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.callHik.ITransferHikHttpRequestService;
import com.wisdom.iwcs.service.control.IInitPodService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.RETURN_POD_CODE;

/**
 * 按货架呼叫
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/26 18:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InitPodService implements IInitPodService {
    private final Logger logger = LoggerFactory.getLogger(InitPodService.class);
    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private ITransferHikHttpRequestService ITransferHikHttpRequestService;
    @Autowired
    private ICommonService ICommonService;
    @Autowired
    private BaseWbMapper baseWbMapper;
    @Autowired
    private BasePodMapper basePodMapper;
    @Autowired
    private BasePodBincodeMapper basePodBincodeMapper;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private BaseBincodeDetailMapper baseBincodeDetailMapper;


    /**
     * 初始化货架
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Result initPod(InitPodRequestDTO requestDTO) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reqTime = formatter.format(new Date());
        BaseWb baseWb = baseWbMapper.selectByWbCodeAndValidFlagAndDeleteFlag(requestDTO.getWbCode(), ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        Preconditions.checkBusinessError(baseWb == null, "无效工作台");
        List<String> distinctByPodBincodes = ICommonService.distinctBinCodeByPodCode(requestDTO.getBincodes());
        List<String> podCodes = distinctByPodBincodes.stream().map(bincode -> {
            return bincode.substring(0, 6);
        }).distinct().collect(Collectors.toList());
        basePodMapper.updateValidStatusByPodCodes(ValidFlagEnum.VALID.getStatus(), podCodes);
        basePodBincodeMapper.updateValidStatusByPodCodes(ValidFlagEnum.VALID.getStatus(), podCodes);
        basePodDetailMapper.updateValidStatusByPodCodes(ValidFlagEnum.VALID.getStatus(), podCodes);
        baseBincodeDetailMapper.updateValidStatusByPodCodes(ValidFlagEnum.VALID.getStatus(), podCodes);
        String failureBincode = "";
        for (String bincode : distinctByPodBincodes) {
            ReturnPodRequestDTO returnPodRequestDTO = new ReturnPodRequestDTO();
            returnPodRequestDTO.setClientCode(applicationProperties.getHikParam().getClientCode());
            returnPodRequestDTO.setTokenCode(applicationProperties.getHikParam().getTokenCode());
            returnPodRequestDTO.setReqCode(UUID.randomUUID().toString().replace("-", ""));
            returnPodRequestDTO.setReqTime(reqTime);
            returnPodRequestDTO.setInterfaceName(RETURN_POD_CODE);
            String taskCode = UUID.randomUUID().toString().replaceAll("-", "");
            returnPodRequestDTO.setTaskCode(taskCode);
            String returnPodStra = ICommonService.returnPodStrategyByPodCode(bincode.substring(0, 6));
            returnPodRequestDTO.setTaskTyp("1");
            returnPodRequestDTO.setReturnPodStrategy(returnPodStra);
            returnPodRequestDTO.setBinCode(bincode);
            returnPodRequestDTO.setWbCode(baseWb.getBerCode());
            String body = ITransferHikHttpRequestService.transferHikReturnPod(returnPodRequestDTO);
            try {
                JSONObject obj = new JSONObject(body);
                if (!obj.getString("code").equals("0")) {
                    failureBincode = bincode + ",";
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            commonService.handleHikResponseAndThrowException(body);
        }


        return new Result(200, "初始化失败货架为：" + failureBincode);
    }
}
