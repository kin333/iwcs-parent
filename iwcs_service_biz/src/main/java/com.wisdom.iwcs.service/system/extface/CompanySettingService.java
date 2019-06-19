package com.wisdom.iwcs.service.system.extface;

import com.wisdom.iwcs.common.utils.CompanyFinancialStatusEnum;
import com.wisdom.iwcs.domain.system.CompanyFinancial;

import java.util.Date;

/**
 * @author Devin
 * @date 2018-05-08.
 */
public interface CompanySettingService {

    /**
     * 更新公司财务信息
     *
     * @param companyId           分公司id
     * @param financialDate       财务日期
     * @param financialStatusEnum 公司财务状态
     * @return int
     */
    Integer setCompanyFinancialDateAndStatus(Integer companyId, Date financialDate, CompanyFinancialStatusEnum financialStatusEnum);

    /**
     * 公司财务信息
     *
     * @param companyId 分公司id
     * @return 公司财务信息
     */
    CompanyFinancial getCompanyFinancial(Integer companyId);
}
