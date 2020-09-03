package io.dfjx;

import io.dfjx.core.util.common.WebClient;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TestQuery {

    @Test
    public void testQ() {
        String url = "https://www.baidu.com/";
        WebClient client = new WebClient();


        System.out.println(client.getString(url));
    }

    public static void main(String[] args) {
        String s="2020-04";
        String s2="2020-09";
        int size=2;
        /*List<Map<String,Object>> allEventCountList=new ArrayList<>();
        Map<String,Object>
        allEventCountList.add();*/
        Map<String,Integer> map2=new LinkedHashMap<>();
        if(size==3){
            //按天
            LocalDate startDate=LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate endDate=LocalDate.parse(s2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            while(startDate.isBefore(endDate)){
                map2.put(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),1);
                startDate=startDate.plusDays(1);
            }
        }else if(size==2){
            //按月
            LocalDate startDate=LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM"));
            LocalDate endDate=LocalDate.parse(s2, DateTimeFormatter.ofPattern("yyyy-MM"));
            while(startDate.isBefore(endDate)){
                map2.put(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM")),0);
                startDate=startDate.plusMonths(1);
            }
        }else if(size==1){
            //按年
            LocalDate startDate=LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy"));
            LocalDate endDate=LocalDate.parse(s2, DateTimeFormatter.ofPattern("yyyy"));
            while(startDate.isBefore(endDate)){
                map2.put(startDate.format(DateTimeFormatter.ofPattern("yyyy")),0);
                startDate=startDate.plusYears(1);
            }
        }
        System.out.print(map2);
    }
}
