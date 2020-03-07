package com.wisdom.iwcs.domain.log.dto;

public class HisDataMigrationDTO {
    //想要迁移的数据表名
    private String tableName;
    //迁移的表中的时间字段名
    private String dateColName;
    //迁移条件
    private String date;
    //迁移到的新表名
    private String toTableName;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HisDataMigrationDTO() {
    }

    public HisDataMigrationDTO(String toTableName, String tableName, String date, String dateColName) {
        super();
        this.toTableName = toTableName;
        this.tableName = tableName;
        this.date = date;
        this.dateColName = dateColName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDateColName() {
        return dateColName;
    }

    public void setDateColName(String dateColName) {
        this.dateColName = dateColName;
    }

    public String getToTableName() {
        return toTableName;
    }

    public void setToTableName(String toTableName) {
        this.toTableName = toTableName;
    }
}
