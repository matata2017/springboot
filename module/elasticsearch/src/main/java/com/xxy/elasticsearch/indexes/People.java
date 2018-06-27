package com.xxy.elasticsearch.indexes;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author xxy
 */
//@Document(indexName="people",type="man",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class People {
    public People() {
    }

    public People(String name, String country, Integer age) {
        this.name = name;
        this.country = country;
        this.age = age;
    }

    public People(String name, Date date, Integer age) {
        this.name = name;
        this.date = date;
        this.age = age;
    }

    public People(String name, String country, Date date, Integer age) {
        this.name = name;
        this.country = country;
        this.date = date;
        this.age = age;
    }

    private  Integer id;

    private String name;

    private String country;

    private Date date;

    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
