package com.wisdom.iwcs.domain;

import com.deepoove.poi.config.Name;

public class WordTemplate {
    @Name("tel")
    private String tel;
    @Name("company_name")
    private String companyName;
    @Name("fax")
    private String fax;
    @Name("job_no")
    private String jobNo;
    @Name("to_customer")
    private String toCustomer;
    @Name("from_customer")
    private String fromCustomer;
    @Name("shipper")
    private String shipper = "GREENCARRIER SHANGHAI LIMITED";
    @Name("shipper_addr")
    private String shipperAddr = "ROOM 1901, INTERNATIONAL CAPITAL PLAZA,\n" +
            "NO.1318 NORTH OF SICHUAN ROAD, HONGKOU\n" +
            "DISTRICT, SHANGHAI\n";
    @Name("shipper_tel")
    private String shipperTel = "86 21 60950931";
    @Name("shipper_fax")
    private String shipperFax = "86 21 60950931";
    @Name("consigee")
    private String consigee = "GREENCARRIER FREIGHT SERVICES SWEDEN AB\n" +
            "STRANDBADSV. 13 D\n";
    @Name("consignee_addr")
    private String consigneeAddr = "P.O.BOX 945\n" +
            "SE-251 09  HELSINGBORG\n";
    @Name("consignee_tel")
    private String consigneeTel = "+46(0)42-400 41 50";
    @Name("consignee_fax")
    private String consigneeFax = "+46(0)42-284130";
    @Name("notify")
    private String notify = "TEST";
    @Name("finl_dest_name")
    private String finlDestName = "HELSINGBORG,  SWEDEN";
    @Name("load_port_name")
    private String loadPortName = "QINGDAO";
    @Name("carriage_name")
    private String carriageName = "FREIGHT COLLECT";
    @Name("marks")
    private String marks = "N/M";
    @Name("description")
    private String description = "N/M";
    @Name("quantity")
    private String quantity = "28CRTAES";
    @Name("gross_weight")
    private String grossWeight = "28KGS";
    @Name("remark")
    private String remark = "请订CMA  12.15  QGTG005580  1X20GP\n" +
            "MBL请出SEAWAYBILL；\n";


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getFromCustomer() {
        return fromCustomer;
    }

    public void setFromCustomer(String fromCustomer) {
        this.fromCustomer = fromCustomer;
    }

    public String getToCustomer() {
        return toCustomer;
    }

    public void setToCustomer(String toCustomer) {
        this.toCustomer = toCustomer;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getShipperAddr() {
        return shipperAddr;
    }

    public void setShipperAddr(String shipperAddr) {
        this.shipperAddr = shipperAddr;
    }

    public String getShipperTel() {
        return shipperTel;
    }

    public void setShipperTel(String shipperTel) {
        this.shipperTel = shipperTel;
    }

    public String getShipperFax() {
        return shipperFax;
    }

    public void setShipperFax(String shipperFax) {
        this.shipperFax = shipperFax;
    }

    public String getConsigee() {
        return consigee;
    }

    public void setConsigee(String consigee) {
        this.consigee = consigee;
    }

    public String getConsigneeAddr() {
        return consigneeAddr;
    }

    public void setConsigneeAddr(String consigneeAddr) {
        this.consigneeAddr = consigneeAddr;
    }

    public String getConsigneeTel() {
        return consigneeTel;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel;
    }

    public String getConsigneeFax() {
        return consigneeFax;
    }

    public void setConsigneeFax(String consigneeFax) {
        this.consigneeFax = consigneeFax;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getFinlDestName() {
        return finlDestName;
    }

    public void setFinlDestName(String finlDestName) {
        this.finlDestName = finlDestName;
    }

    public String getLoadPortName() {
        return loadPortName;
    }

    public void setLoadPortName(String loadPortName) {
        this.loadPortName = loadPortName;
    }

    public String getCarriageName() {
        return carriageName;
    }

    public void setCarriageName(String carriageName) {
        this.carriageName = carriageName;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
