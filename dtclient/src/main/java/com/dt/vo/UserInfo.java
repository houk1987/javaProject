package com.dt.vo;

import org.jivesoftware.smack.packet.Presence;

import java.io.Serializable;


/**
 * 用户详细信息对象
 *
 * @author a
 */
public class UserInfo implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private String id; // 编号
    private String username;// 姓名
    private String age; // 年龄
    private int sex;// 性别（0,1） 0代表女，1代表男
    private String birthday;// 出生日期
    private int rank;// 军衔
    private String workPosition;// 职位
    private String cellPhone;// 手机号码
    private String workPhone;// 办公号码
    private int roleId;// 角色编号  1(训练管理人员),2(受训人员),3(导调人员),4(评估人员)
    private String roleName; // 角色名称
    private Presence presence; // 账号状态
    private String UnitId;// 部门编号
    private String UnitName;// 部门名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public Presence getPresence() {
        return presence;
    }

    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    public String getUnitId() {
        return UnitId;
    }

    public void setUnitId(String unitId) {
        UnitId = unitId;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public String getPartmentId() {
        return UnitId;
    }

    public void setPartmentId(String partmentId) {
        this.UnitId = partmentId;
    }

    public String getPartmentName() {
        return UnitName;
    }

    public void setPartmentName(String partmentName) {
        this.UnitName = partmentName;
    }

    public Object clone() {
        try {
            super.clone();
            return super.clone();
        } catch (Exception e) {
            return null;
        }
    }
}
