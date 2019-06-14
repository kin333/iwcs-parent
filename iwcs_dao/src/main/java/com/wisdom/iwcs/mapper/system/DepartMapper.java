package com.wisdom.iwcs.mapper.system;


import com.wisdom.iwcs.domain.system.CompanyFinancial;
import com.wisdom.iwcs.domain.system.Depart;
import com.wisdom.iwcs.domain.system.DepartRole;
import com.wisdom.iwcs.domain.system.DepartUser;
import com.wisdom.iwcs.domain.system.dto.DepartUserDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartMapper {
    int deleteById(Integer id);

    int insert(Depart record);

    int insertSelective(Depart record);

    int addDepartUser(DepartUser departUser);

    int deleteDepartUser(DepartUser departUser);

    int addRoleToDeport(DepartRole departRole);

    int deleteRoleByDepartId(Integer id);

    Depart getDepartmentById(Integer id);

    List<Depart> selectAllDepart();

    List<DepartUserDto> getUserById(Integer id);

    /**
     * 获取所属部门为该departmentId的用户
     *
     * @param departmentId
     * @return
     */
    List<DepartUserDto> getUserFromThisDepartment(Integer departmentId);

    /**
     * 获取所属部门为该departmentId的用户Id
     *
     * @param departmentId
     * @return
     */
    List<Integer> getUserIdFromThisDepartment(Integer departmentId);


    List<Integer> getRoleById(Integer id);

    Depart selectAllDepartById(Integer id);

    Depart selectOneById(Integer id);

    List<Depart> selectDepartByParentId(@Param("departmentId") Integer parentdepartId);

    int updateByPrimaryKeySelective(Depart record);

    int updateByPrimaryKey(Depart record);

    int insertUserDepartmentList(List<DepartUser> departUsers);

    int deleteUserDepartmentsByUserId(Integer userId);

    int deleteUserListFromDepartment(@Param("userIds") List<Integer> userIds, @Param("departmentId") Integer departmentId);

    Integer deleteUserFromDepartmentAndCompanyId(@Param("userId") Integer userId, @Param("departmentId") Integer departmentId, @Param("companyId") Integer companyId);

    //department role

    List<DepartUser> selectDepartmentsByUserId(Integer userId);

    List<DepartRole> selectDepartmentRolesByDepartmentIds(List<Integer> departmentIds);

    List<DepartUser> getAllDepartUserByDepartmentId(Integer departmentId);

    List<Depart> getAllCompanys();

    List<Depart> getAllUsingCompanyForLogin();

    List<DepartUser> selectByUserIdAndCompanyId(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    /**
     * 用户在公司下的所属部门
     *
     * @param userId
     * @param companyId
     * @return
     */
    DepartUser selectByUserBelongDepartment(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    /**
     * 删除用户所属公司
     *
     * @param departUser
     * @return
     */
    int deleteUserBelongDepartment(DepartUser departUser);

    /**
     * 删除公司下的管辖部门
     *
     * @return
     */
    int deleteOtherDepartment(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    /**
     * 删除用户在某公司下的部门
     *
     * @return
     */
    int deletUserCompanyDepart(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    /**
     * 获取公司默认港口
     *
     * @param departmentId
     * @return
     */
    String getDefaultPortCodeById(Integer departmentId);


    String getDefaultAirPortCodeById(Integer departmentId);

    @Select("select departname from s_depart where ID = #{departmentId}")
    String getDepartNameById(Integer departmentId);


    List<DepartUser> companyDepartmentUserSelectAll(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    List<DepartUser> companyDepartmentUserSelectSelf(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    List<Depart> getDepartmentByCode(String orgCode);

    List<Depart> getCompanyByName(String name);

    List<Depart> getDepartmentByNameAndParent(@Param("name") String name, @Param("parent") Integer parent);

    /**
     * 指定用户在 指定公司下的部门（包括所属部门与管辖部门）
     *
     * @return
     */
    List<Depart> getUserCompanyDepartments(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    /**
     * 获取公司财务信息
     *
     * @param companyId 公司
     * @return CompanyFinancial
     */
    CompanyFinancial getCompanyFinancial(@Param("companyId") Integer companyId);

    /**
     * 更新公司财务信息
     *
     * @param companyFinancial 公司财务信息
     * @return count
     */
    int updateCompanyFinancial(CompanyFinancial companyFinancial);

    /**
     * 获取默认出发地点
     *
     * @param departmentId 公司id
     * @return 地点id
     */
    @Select("select default_departure_site_id from s_depart where id = #{departmentId}")
    Integer getDefaultDepartureSite(Integer departmentId);


    List<Depart> getAllCompany();

    /**
     * 获取分公司下所有的用户
     */
    List<DepartUser> allFromCompany(Integer companyId);


    List<Integer> hasBelongDepartmentUSerId(Integer companyId);

    Depart selectByPrimaryKey(Integer id);
}