package com.wisdom.service.system.extface;

import java.util.List;

public interface SUserInterface {
    /**
     * 获取此用户所在揽货工作组及部门可看到用户列表
     *
     * @param currentUserId
     * @return
     */
    List<Integer> getUserIdsFromCanvassUserGroupAndOrganizeById(Integer currentUserId, Integer companyId);

    /**
     * 获取此用户所在制单工作组及部门可看到用户列表
     *
     * @param currentUserId
     * @return
     */
    List<Integer> getUserIdsFromOperUserGroupAndOrganizeById(Integer currentUserId, Integer companyId);


    /**
     * 获取此用户所在商务工作组及部门可看到用户列表
     *
     * @param currentUserId
     * @return
     */
    List<Integer> getUserIdsFromBusinessUserGroupAndOrganizeById(Integer currentUserId, Integer companyId);

    /**
     * 获取指定部门的编码的前四位
     *
     * @param departmentId 部门id
     * @return 编码的前四位
     */
    String getDepartmentCode(Integer departmentId);

    /**
     * 获取当前登录用户的所属部门的编码前四位
     *
     * @return 编码前四位
     */
    String getCurrentUserDepartmentCode();
}
