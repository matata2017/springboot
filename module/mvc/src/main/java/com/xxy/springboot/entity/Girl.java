package com.xxy.springboot.entity;

import com.google.common.collect.Lists;
import com.xxy.springboot.Utils.PriceUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Shinelon on 2017/11/28.
 */
@Entity
@Table(name = "sp_girl")
@Slf4j
public class Girl  extends BaseEntity{
//
    @Column(name = "cupeSize",length = 10)
    private  String cupeSize;
    @Column(name = "age",length = 10)
    private  Integer age;
    public String getCupeSize() {
        return cupeSize;
    }

    public void setCupeSize(String cupeSize) {
        this.cupeSize = cupeSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    //首先半仓买入 在震荡期 可以挂最低价买入 挂最高价卖出
    public static void main(String a[]){
         double j= getPinjunPrice(18.2,18.14,18.39,17.86,18.06,18.57);

        double h= getPinjunPrice(9.64,9.68,9.92,9.92,10.2,10.19,9.45,9.19);
        Scanner sc = new Scanner(System.in);
        System.out.println("ScannerTest, Please Enter Salary:");
        float salary = sc.nextFloat();
        double an=(h+salary)/2;
        log.info ("zuidijia"+an);
    }

    public static Double getPinjunPrice(Double ... pricese ){
        if (pricese.length<0){
            return 0.0;
        }
        Double total=0.0D;
        for (Double p :pricese){
            total= PriceUtil.add(total,p);
        }
        double pinjun=PriceUtil.divide(total,pricese.length,2);
        double fangcha =0.0D;
        List<Double> fa= Lists.newArrayList();
        for (Double f :pricese){
            double a = Math.pow((f-pinjun),2);
            fa.add(a);
        }
        for (Double d :fa){
            fangcha=PriceUtil.add(fangcha,d);
            fangcha=PriceUtil.divide(fangcha,pricese.length,2);
        }
        double biaozhuncha =Math.sqrt(fangcha);
        log.info("===========平均值="+pinjun+"方差="+fangcha+"标准差="+biaozhuncha+"==================");
        return pinjun;
    }
}
