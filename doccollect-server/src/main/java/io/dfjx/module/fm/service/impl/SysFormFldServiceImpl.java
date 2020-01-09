package io.dfjx.module.fm.service.impl;

import io.dfjx.common.utils.R;
import io.dfjx.core.base.SysBaseEntity;
import io.dfjx.module.fm.dao.FmFormDao;
import io.dfjx.module.fm.service.ISysFormFldService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("sysFormFldService")
public class SysFormFldServiceImpl extends FmBaseServiceImpl<FmFormDao, SysBaseEntity> implements ISysFormFldService {
    public SysFormFldServiceImpl(){
        BaseTable = "sys_form_fld";
        BaseKey = "id";
    }

    private static final String VARCHAR = "varchar";
    private static final String DECIMAL = "decimal";
    private static final String DATE = "date";
    private static final String TBL_PROJECT = "fm_project";

    @Override
    public String getWhere(Map<String, Object> params){
        String str = " and form_id='" + mstr(params, "form_id") + "'";
        return str;
    }

    @Override
    public R insertColumn(Map<String, Object> params) {
        String tag = mstr(params, "tag");
        R res = R.ok();
        if("input".equals(tag)){
            res = this.addField(params);
            if(!res.isok())
                return res;
            this.insertFld(params);
        }
        return res;
    }

    @Override
    public R delColumn(Map<String, Object> params) {
        String tag = mstr(params, "tag");
        R res = R.ok();
        if("input".equals(tag)){
            String field = mstr(params, "field");
            String tbl = mstr(params, "tbl_name");
            int c = baseMapper.countWhere(tbl, " and " + field + " is not null");
            if(c>0){
                return res.error("该输入项已录入内容，请先清空表中该项的数据再执行操作");
            }
            res = this.delField(params);
            baseMapper.deleteWhere(BaseTable, " and tbl_name='"+tbl+"' and fld_name='" + field + "'");
        }

        return res;
    }

    protected R delField(Map<String, Object> params){
        Map<String, Object> map = new HashMap<>();
        map.put("name", mstr(params, "field"));
        baseMapper.delField(TBL_PROJECT, "", map);
        return R.ok();
    }
    protected R addField(Map<String, Object> params){
        Map<String, Object> map = new HashMap<>();
        map.put("name", mstr(params, "field"));
        map.put("type", getDbFullType(params));
        map.put("comment", mstr(params, "text"));

        baseMapper.addField(TBL_PROJECT, "", map);
        return R.ok();
    }
    protected R insertFld(Map<String, Object> params){
        Map<String, Object> map = new HashMap<>();
        map.put("form_id", 1);
        map.put("tbl_name", mstr(params, "tbl_name"));
        map.put("fld_name", mstr(params, "field"));
        map.put("fld_comment", mstr(params, "text"));
        map.put("is_primary", "0");
        map.put("is_notnull", "0");
        String dbtype = getDbType(params);
        map.put("data_type", dbtype);
        if(dbtype.equals(VARCHAR)){
            String len = mstr(params, "data_len");
            if(len.length() == 0)
                len = "30";
            map.put("data_len", len);
        }else if(dbtype.equals(DECIMAL)){
            map.put("data_len", 18);
            map.put("data_scale", 4);
        }
        map.put("fld_type", "2");
        map.put("order_num", baseMapper.countWhere(BaseTable, "") + 1);
        this.insertMap(map);
        return R.ok();
    }
    private String getDbType(Map<String, Object> params){
        String type = mstr(params, "type");
        switch (type){
            case "date":
                return DATE;
            case "money":
                return DECIMAL;
            default:
                return VARCHAR;
        }
    }
    private String getDbFullType(Map<String, Object> params){
        String res = "";
        String type = mstr(params, "type");
        String len = mstr(params, "data_len");
        switch (type){
            case "date":
                res = "date";
                break;
            case "money":
                res = "decimal(18,4)";
                break;
            case "textarea":
                res = "text";
                break;
            case "year":
            case "month":
            case "radio":
                if(len.length() == 0)
                    len = "10";
                res = "varchar(" + len + ")";
                break;
            case "select":
            case "text":
                if(len.length() == 0)
                    len = "30";
                res = "varchar(" + len + ")";
                break;
        }
        return res;
    }
}
