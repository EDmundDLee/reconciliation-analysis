package com.rongxin.demo.domain;

import java.util.Date;
import java.util.List;
import com.rongxin.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 学生对象 rx_student
 * 
 * @author rx
 * @date 2022-09-06
 */
@Document(indexName = "student")
public class RxStudent extends BaseEntity
{

    @Id
    @Field(store = true, type = FieldType.Keyword)
    private String id;

    @Field(store = true, type = FieldType.Keyword)
    private String name;

    @Field(store = true, type = FieldType.Keyword)
    private String sex;

    @Field(index = false, store = true, type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date birthdate;

    private String intro;
    @Field(index = false, store = true, type = FieldType.Integer)
    private Integer sAge;

    @Field(index = false, store = true, type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date sCreateTime;

    @Field(type = FieldType.Keyword)
    private String[] sCourseList; //数组类型 由数组中第一个非空值决定(这里数组和集合一个意思了)

    @Field(type = FieldType.Keyword)
    private List<String> sColorList; //集合类型 由数组中第一个非空值决定

    @Field(store = true, type = FieldType.Text)
    private String address;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setBirthdate(Date birthdate) 
    {
        this.birthdate = birthdate;
    }

    public Date getBirthdate() 
    {
        return birthdate;
    }
    public void setIntro(String intro) 
    {
        this.intro = intro;
    }

    public String getIntro() 
    {
        return intro;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public Integer getsAge() {
        return sAge;
    }

    public void setsAge(Integer sAge) {
        this.sAge = sAge;
    }

    public Date getsCreateTime() {
        return sCreateTime;
    }

    public void setsCreateTime(Date sCreateTime) {
        this.sCreateTime = sCreateTime;
    }

    public String[] getsCourseList() {
        return sCourseList;
    }

    public void setsCourseList(String[] sCourseList) {
        this.sCourseList = sCourseList;
    }

    public List<String> getsColorList() {
        return sColorList;
    }

    public void setsColorList(List<String> sColorList) {
        this.sColorList = sColorList;
    }

}
