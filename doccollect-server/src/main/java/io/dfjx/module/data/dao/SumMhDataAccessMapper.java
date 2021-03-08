package io.dfjx.module.data.dao;

 import com.baomidou.mybatisplus.core.mapper.BaseMapper;
 import io.dfjx.datasource.annotation.DataSource;
 import io.dfjx.module.data.entity.SumMhDataAccess;
 import io.dfjx.module.data.vo.*;
 import org.apache.ibatis.annotations.Select;
 import org.springframework.stereotype.Repository;

 import java.util.LinkedList;
 import java.util.List;

/**
 * 
 *
 * @author ccf
 * @email 674441755@qq.com
 * @date 2021-03-07 15:09:23
 */
@Repository
public interface SumMhDataAccessMapper extends BaseMapper<SumMhDataAccess> {
 @Select("SELECT  " +
         "  cast(tol_wbj_amount as NUMERIC) as tolWbjAmount," +
         "  cast(tol_tab_amount as NUMERIC) as tolTabAmount," +
         "  act_recod_amount " +
         "from " +
         "sum_mh_data_access where access_date = CURRENT_DATE -1")
 public DataAccessVo getAccessData();

 @Select("select sys_id from sd_field_infornation group by sys_id ORDER BY sys_id desc limit 1 ")
 public String getAccessSystem();

 @Select("select count(*) fieldCount from sd_field_infornation ")
 public String getFieldCount();

 /**
  * 接入域
  */

 @Select("SELECT t1.gk_name, " +
         "count( t1.com_bureau_nm ) * 100 / ( " +
         "SELECT   count( * ) FROM   ( SELECT  gk_name, com_bureau_nm " +
         "FROM " +
         "mh_data_access_expand_details s  " +
         "WHERE " +
         "s.gk_name != '' GROUP BY  s.gk_name, s.data_src_nm, s.com_bureau_nm " +
         "ORDER BY   " +
         "s.gk_name DESC  ) t  ) || '%' AS zb,    " +
         "count( t1.com_bureau_nm ) accessCount  " +
         "FROM  " +
         "(  SELECT  gk_name,  com_bureau_nm   " +
         "FROM   " +
         "mh_data_access_expand_details s " +
         "WHERE   " +
         "s.gk_name != '' GROUP BY  s.gk_name, s.data_src_nm, s.com_bureau_nm " +
         "ORDER BY   " +
         "s.gk_name DESC   ) t1  " +
         "GROUP BY   " +
         "t1.gk_name")
 public List<SysAccessVo> getSysAccess();

          /**
           * 数据记录接入趋势
           */

          @Select("select curr_recod_amount, to_char(access_date, 'yyyy-mm-dd') accessDate  " +
                  "from  " +
                  "sum_mh_data_access where access_date between CURRENT_DATE -7 and CURRENT_DATE -1 ORDER BY access_date  ")
         public List<RecordTrendVo> getRecordTrendSeven();

          @Select("select curr_recod_amount , to_char(access_date, 'yyyy-mm-dd')  accessDate " +
                  "from  " +
                  "sum_mh_data_access where access_date between CURRENT_DATE -30 and CURRENT_DATE -1 ORDER BY access_date ")
          public List<RecordTrendVo> getRecordTrendThirty();

       @Select("select exst_popu_num from  s_dx020_003_sum_popu_svy")
       public String getDxFp();

       @Select("select count(*) exst_legal_num from s_dx020_003_b02_corp")
       public String getLegelCn();

         @Select("SELECT s.com_bureau_nm,round(cast(sum(s.recod_amount) as numeric) /10000,2)  sjl_amount_w " +
                 "FROM mh_data_access_expand_details s  " +
                 "GROUP BY s.com_bureau_nm ORDER BY sjl_amount_w desc limit 10 ")
        public List<RecordAccessTopVo> getRecordAccessTopAll();

          @Select("SELECT s.com_bureau_nm, round(cast(sum(s.recod_amount) as numeric) /10000,2)  sjl_amount_w  " +
                  "FROM mh_data_access_expand_details s  " +
                  "where s.access_date between CURRENT_DATE -30 and CURRENT_DATE   " +
                  "GROUP BY s.com_bureau_nm ORDER BY sjl_amount_w desc limit 10 ")
          public List<RecordAccessTopVo> getRecordAccessTopMonth();

           @Select("SELECT s.com_bureau_nm,round(cast(sum(s.recod_amount) as numeric) /10000,2)  sjl_amount_w " +
                   "FROM " +
                   "mh_data_access_expand_details s " +
                   "where s.access_date between CURRENT_DATE-180 and CURRENT_DATE  " +
                   "GROUP BY s.com_bureau_nm " +
                   "ORDER BY sjl_amount_w desc limit 10 ")
           public List<RecordAccessTopVo> getRecordAccessTopYear();


           @Select(" with t1 as ( " +
                   "select curr_recod_amount from sum_mh_data_access where access_date =  CURRENT_DATE -1   " +
                   " ),  " +
                   "t2 as ( " +
                   "select curr_recod_amount from sum_mh_data_access where access_date = CURRENT_DATE -2   " +
                   ") " +
                   "select round(cast(t1.curr_recod_amount*100/t2.curr_recod_amount as NUMERIC)-100,2) ||'%' addRate from t1,t2 ")
           public String getAccessRate();

           @Select("select curr_recod_amount  from  sum_mh_data_access where access_date = CURRENT_DATE -1 ")
           public String getAccessRecordToal();


           @Select("SELECT DISTINCT s.com_bureau_nm wbjName,case when s.com_bureau_nm like '%北京%' then '1' else '0' end as orgLevel FROM mh_data_access_expand_details s where  com_bureau_nm !=''")
           public List<OrgInfoVo> getWblInfo();




}
