package com.wisdom.service.system;

import com.wisdom.iwcs.common.utils.CompanyFinancialStatusEnum;
import com.wisdom.iwcs.domain.system.CompanyFinancial;
import com.wisdom.iwcs.mapper.system.DepartMapper;
import com.wisdom.service.system.extface.CompanySettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Devin
 * @date 2018-05-08.
 */
@Service
@Transactional
public class CompanySettingServiceImpl implements CompanySettingService {

    @Autowired
    private DepartMapper departMapper;


    @Override
    public Integer setCompanyFinancialDateAndStatus(Integer companyId, Date financialDate, CompanyFinancialStatusEnum financialStatusEnum) {
        CompanyFinancial companyFinancial = new CompanyFinancial();
        companyFinancial.setCompanyId(companyId);
        companyFinancial.setFinanceDate(financialDate);
        companyFinancial.setFinanceStatus(financialStatusEnum.getCode());
        return departMapper.updateCompanyFinancial(companyFinancial);
    }

    @Override
    public CompanyFinancial getCompanyFinancial(Integer companyId) {
        return departMapper.getCompanyFinancial(companyId);
    }
}
