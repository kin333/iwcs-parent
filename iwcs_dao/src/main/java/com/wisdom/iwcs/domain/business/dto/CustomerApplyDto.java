package com.wisdom.iwcs.domain.business.dto;

import java.util.Date;

public class CustomerApplyDto {

    private Long id;

    private String osCode;

    private String customerName;

    private String customerIntro;

    private String mnemonicCode;

    private String address;

    private String corporateRepresentative;

    private String financeContacts;

    private String email;

    private String telephone;

    private String fax;

    private String rmbBankName;

    private String rmbBankAccount;

    private String usdBankName;

    private String usdBankAccount;

    private String dutyParagraph;

    private String businessLicence;

    private String dutyType;

    private String companyType;

    private String taxRegistrationFile;

    private String taxRegistrationData;

    private String businessLicenceFile;

    private String businessLicenceData;

    private String approval;

    private Integer recorder;

    private Date recordDate;

    private String refusalReason;

    private Date refusalDate;

    private Integer createdBy;

    private Date createdTime;

    private Integer lastModifiedBy;

    private Date lastModifiedTime;

    private Integer deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOsCode() {
        return osCode;
    }

    public void setOsCode(String osCode) {
        this.osCode = osCode == null ? null : osCode.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerIntro() {
        return customerIntro;
    }

    public void setCustomerIntro(String customerIntro) {
        this.customerIntro = customerIntro == null ? null : customerIntro.trim();
    }

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode == null ? null : mnemonicCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCorporateRepresentative() {
        return corporateRepresentative;
    }

    public void setCorporateRepresentative(String corporateRepresentative) {
        this.corporateRepresentative = corporateRepresentative == null ? null : corporateRepresentative.trim();
    }

    public String getFinanceContacts() {
        return financeContacts;
    }

    public void setFinanceContacts(String financeContacts) {
        this.financeContacts = financeContacts == null ? null : financeContacts.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getRmbBankName() {
        return rmbBankName;
    }

    public void setRmbBankName(String rmbBankName) {
        this.rmbBankName = rmbBankName == null ? null : rmbBankName.trim();
    }

    public String getRmbBankAccount() {
        return rmbBankAccount;
    }

    public void setRmbBankAccount(String rmbBankAccount) {
        this.rmbBankAccount = rmbBankAccount == null ? null : rmbBankAccount.trim();
    }

    public String getUsdBankName() {
        return usdBankName;
    }

    public void setUsdBankName(String usdBankName) {
        this.usdBankName = usdBankName == null ? null : usdBankName.trim();
    }

    public String getUsdBankAccount() {
        return usdBankAccount;
    }

    public void setUsdBankAccount(String usdBankAccount) {
        this.usdBankAccount = usdBankAccount == null ? null : usdBankAccount.trim();
    }

    public String getDutyParagraph() {
        return dutyParagraph;
    }

    public void setDutyParagraph(String dutyParagraph) {
        this.dutyParagraph = dutyParagraph == null ? null : dutyParagraph.trim();
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence == null ? null : businessLicence.trim();
    }

    public String getDutyType() {
        return dutyType;
    }

    public void setDutyType(String dutyType) {
        this.dutyType = dutyType == null ? null : dutyType.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public String getTaxRegistrationFile() {
        return taxRegistrationFile;
    }

    public void setTaxRegistrationFile(String taxRegistrationFile) {
        this.taxRegistrationFile = taxRegistrationFile == null ? null : taxRegistrationFile.trim();
    }

    public String getTaxRegistrationData() {
        return taxRegistrationData;
    }

    public void setTaxRegistrationData(String taxRegistrationData) {
        this.taxRegistrationData = taxRegistrationData == null ? null : taxRegistrationData.trim();
    }

    public String getBusinessLicenceFile() {
        return businessLicenceFile;
    }

    public void setBusinessLicenceFile(String businessLicenceFile) {
        this.businessLicenceFile = businessLicenceFile == null ? null : businessLicenceFile.trim();
    }

    public String getBusinessLicenceData() {
        return businessLicenceData;
    }

    public void setBusinessLicenceData(String businessLicenceData) {
        this.businessLicenceData = businessLicenceData == null ? null : businessLicenceData.trim();
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval == null ? null : approval.trim();
    }

    public Integer getRecorder() {
        return recorder;
    }

    public void setRecorder(Integer recorder) {
        this.recorder = recorder;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRefusalReason() {
        return refusalReason;
    }

    public void setRefusalReason(String refusalReason) {
        this.refusalReason = refusalReason == null ? null : refusalReason.trim();
    }

    public Date getRefusalDate() {
        return refusalDate;
    }

    public void setRefusalDate(Date refusalDate) {
        this.refusalDate = refusalDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}