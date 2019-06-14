package com.wisdom.iwcs.domain.system.dto;

import java.util.List;

/**
 * @author Devin
 * @date 2018-03-05.
 */
public class EnableAccountDTO {

    private List<Integer> accountIds;
    /**
     * 0 禁用
     * 1 启用
     */
    private Integer operation;

    public List<Integer> getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(List<Integer> accountIds) {
        this.accountIds = accountIds;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "EnableAccountDTO{" +
                "accountIds=" + accountIds +
                ", operation='" + operation + '\'' +
                '}';
    }
}
