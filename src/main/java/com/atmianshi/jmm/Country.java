package com.atmianshi.jmm;

import lombok.Getter;

/**
 * @program: atguigutwo
 * @description: 战国六熊
 * @author: mxk
 * @create: 2020-06-07 23:22
 **/




enum  Country{

        one(1,"楚国"),two(2,"齐国"),three(3,"燕国"),four(4,"赵国"),five(5,"魏国"),six(6,"韩国");

        @Getter
        private Integer country_code;
        @Getter
        private String country_message;

        Country(Integer country_code, String country_message) {
            this.country_code = country_code;
            this.country_message = country_message;
        }

        public static Country fore_enum(int index){
            Country[] values = Country.values();
            for (Country value : values) {
               if(index==value.getCountry_code()) {
                    return    value;
               }
            }
            return null;

        }
}

