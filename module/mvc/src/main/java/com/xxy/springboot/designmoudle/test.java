package com.xxy.springboot.designmoudle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shinelon on 2018/11/4.
 */
public class test {

        public static void main(String[] args) {

            List<Object> list = new ArrayList<Object>();

            for (int i = 0; i < 100000000; i++) {

                list.add(new byte[1024*1024]);

            }

        }
}
