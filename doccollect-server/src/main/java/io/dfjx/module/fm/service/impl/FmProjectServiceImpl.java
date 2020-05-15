package io.dfjx.module.fm.service.impl;

import io.dfjx.common.utils.PageUtils;
import io.dfjx.common.utils.R;
import io.dfjx.core.base.SysBaseEntity;
import io.dfjx.module.fm.dao.FmBaseDao;
import io.dfjx.module.fm.dao.FmProjectDao;
import io.dfjx.module.fm.entity.DocTableMapping;
import io.dfjx.module.fm.service.IFmProjectService;
import io.dfjx.module.fm.service.ISysFormFldService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;


@Service("fmProjectService")
public class FmProjectServiceImpl extends FmBaseServiceImpl<FmProjectDao, SysBaseEntity> implements IFmProjectService {

    @Autowired
    private ISysFormFldService sysFormFldService;

    public FmProjectServiceImpl(){
        BaseTable = "fm_project";
        BaseKey = "project_id";
    }

    @Override
    public int insertMap(Map<String, Object> params) {
        int c = baseMapper.insertMap(BaseTable, BaseKey, params);
        return c;
    }

    @Override
    public R validValue(Map<String, Object> params){
        String errStr = "";
        List<Map<String,Object>> list = this.getFormFields("1");
        //检测日期是否输入合法
        List<Map<String,Object>> dates = list.stream().filter(x-> "date".equals(x.get("data_type").toString())).collect(Collectors.toList());
        for(Map<String,Object> date : dates){
            String name = date.get("fld_name").toString();
            if(!params.containsKey(name))
                continue;
            String val = mstr(params, name);
            if(val.length() == 0)
                continue;
            String str1 = dateStr(val);
            if(str1.length()!=10 && str1.indexOf("-") == -1){
                errStr += date.get("fld_comment").toString() + "填写内容不正确，格式应该为2020-03-08<br/>";
            }
        }

        if(errStr.length()>0){
            return R.error(errStr);
        }
        return R.ok();
    }

    public void formatValue(List<Map<String,Object>> flds, Map<String, Object> params) {
        //数字类型去掉空字符串
        List<Map<String,Object>> nums = flds.stream().filter(x-> "decimal".equals(x.get("data_type").toString())).collect(Collectors.toList());
        for(Map<String,Object> num : nums){
            String name = num.get("fld_name").toString();
            if(!params.containsKey(name))
                continue;
            String val = mstr(params, name);
            if(val.length() == 0)
                params.put(name, null);
        }
    }

    @Override
    public R importFile(InputStream is,String type) {
        List<String> list = Arrays.asList("单位名称|unit_name","法定代表人|unit_person",
                "统一社会信用代码|unit_code","企业登记注册类型|unit_type","联系人|link_man",
                "联系电话|link_mobile","1.项目名称|project_name","2.行业类别名称|industry_name",
                "行业类别代码|industry_code","3.建设内容|project_content",
                "区|loc_dist","街道(乡镇)|loc_street","详细地址|loc_address",
                "东至|loc_eastto","西至|loc_westto","南至|loc_southto","北至|loc_northto",
                "总占地面积|area_floor_total","其中：新增占地面积|area_floor_new",
                "总建筑面积|area_building_total","其中：新增建筑面积|area_building_new",
                "6.项目拟启动时间|plan_start_date","项目拟建成时间|plan_end_date",
                "1.总投资额|invest_total","固定资产投资|invest_fixed",
                "自筹资金|invest_source_self","银行贷款|invest_source_bank","其它资金|invest_source_other",
                "四、需要专门说明的其他内容|other_content|1","五、注意事项|notice_content|1","六、备案机关意见|org_record|1");
        Map<String, DocTableMapping> mapping = DocTableMapping.GetMapping(list);
        R res = null;
        try{
            if(".doc".equals(type)){
                res = handlerByDocFile(is, mapping);
            }else{
                res = handerByDocxFile(is, mapping);
            }
            if(res.isok()){
                Map<String, Object> model = (Map<String, Object>)res.get("info");
                model.put("project_id", null);
                //唯一验证
                R chk = valid(model);
                if(!chk.isok())
                    return chk;
                List<Map<String,Object>> flds = this.getFormFields("1");
                //格式处理
                formatValue(flds, model);
                this.insertMap(model);
                return R.ok("保存成功").put("info", model);
            }else{
                return res;
            }

        }catch (Exception e){
            e.printStackTrace();
            return R.error("解析文档失败，" + e.getMessage());
        }
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params){
        PageUtils page = super.queryPage(params);
        retValue(page);
        return page;
    }

    public R valid(Map<String, Object> model){
        int c = baseMapper.countWhere(BaseTable, " and project_no='" + model.get("project_no") + "'");
        if(c>0) return R.error("验证失败：备案号已存在，请不要重复导入");
        return R.ok();
    }
    public R handerByDocxFile(InputStream is,Map<String, DocTableMapping> mapping) throws IOException {
        Map<String, Object> model = new LinkedHashMap<>();
        XWPFDocument xdoc = new XWPFDocument(is);
        try{
            List<XWPFParagraph> paragraphs = xdoc.getParagraphs();
            //备案号
            int times = 0;
            for(XWPFParagraph p : paragraphs){
                String ss1 = p.getText().trim();
                if(ss1.length()>2)
                    times++;
                if(times==3) {
                    model.put("project_no", ss1);
                    break;
                }
            }
            List<XWPFTable> tables = xdoc.getTables();
            for (XWPFTable table : tables) {
                // 获取表格的行
                List<XWPFTableRow> rows = table.getRows();
                int i, j , trLen = rows.size();
                for (i = 0; i < trLen; i++) {
                    XWPFTableRow tr = rows.get(i);
                    List<XWPFTableCell> cells = tr.getTableCells();
                    int tdLen = cells.size();
                    for (j = 0; j < tdLen; j++) {
                        XWPFTableCell td = cells.get(j);//取得单元格
                        String key = val(td.getText());
                        if(mapping.containsKey(key)){
                            int pos = mapping.get(key).getLinkPos();
                            if(pos == 0){
                                j = j + 1;
                                if(j > tdLen - 1){
                                    continue;
                                }
                                td = cells.get(j);
                                model.put(mapping.get(key).getColumn(), td.getText().trim());
                            }else if(pos == 1){
                                i = i + 1;
                                if(i > trLen - 1){
                                    continue;
                                }
                                tr = rows.get(i);
                                cells = tr.getTableCells();
                                td = cells.get(0);
                                model.put(mapping.get(key).getColumn(), td.getText().trim());
                            }
                        }
                    }
                }
//            for (XWPFTableRow row : rows) {
//                // 获取表格的每个单元格
//                List<XWPFTableCell> tableCells = row.getTableCells();
//                for (XWPFTableCell cell : tableCells) {
//                    // 获取单元格的内容
//                    String text1 = cell.getText();
//                    System.out.println(text1);
//                }
//            }
            }
            //其他信息解析
            parsePubField(model);
        }finally {
            if(xdoc!=null)
                xdoc.close();
        }

        return R.ok().put("info", model);
    }
    public R handlerByDocFile(InputStream is,Map<String, DocTableMapping> mapping) throws IOException {
        Map<String, Object> model = new LinkedHashMap<>();
        HWPFDocument hdoc=null;
        try {
            hdoc=new HWPFDocument(is);
            Range range = hdoc.getRange();
            //备案号
            int start = 0, times = 0, end = range.numParagraphs();
            while(start<end){
                String ss1 = range.getParagraph(start++).text().trim();
                if(ss1.length()>2)
                    times++;
                if(times==3){
                    model.put("project_no", ss1);
                    break;
                }
            }

            TableIterator it = new TableIterator(range);
            if(!it.hasNext()) return R.error("解析失败，无法识别表格");
            Table tb = it.next();
            int i, j , trLen = tb.numRows();
            for (i = 0; i < trLen; i++) {
                TableRow tr = tb.getRow(i);
                int tdLen = tr.numCells();
                for (j = 0; j < tdLen; j++) {
                    TableCell td = tr.getCell(j);//取得单元格
                    String key = val(td.text());
                    if(mapping.containsKey(key)){
                        int pos = mapping.get(key).getLinkPos();
                        if(pos == 0){
                            j = j + 1;
                            if(j > tdLen - 1){
                                continue;
                            }
                            td = tr.getCell(j);
                            model.put(mapping.get(key).getColumn(), td.text().trim());
                        }else if(pos == 1){
                            i = i + 1;
                            if(i > trLen - 1){
                                continue;
                            }
                            tr = tb.getRow(i);
                            td = tr.getCell(0);
                            model.put(mapping.get(key).getColumn(), td.text().trim());
                        }
                    }
                }
            }
            //其他信息解析
            parsePubField(model);
        }finally {
            if(hdoc!=null)
                hdoc.close();
        }
        return R.ok().put("info", model);
    }
    private void parsePubField(Map<String, Object> model){
        if(model.containsKey("org_record")){
            String str1 = model.get("org_record").toString();
            String[] arr = str1.split("备案机关落款（盖章）");
            if(arr.length >= 1) {
                model.put("org_record", arr[0].trim());
                model.put("record_date", dateStr(arr[1]));
            }
        }
        if(model.containsKey("plan_start_date")){
            model.put("plan_start_date", dateStr(model.get("plan_start_date").toString()));
        }
        if(model.containsKey("plan_end_date")){
            model.put("plan_end_date", dateStr(model.get("plan_end_date").toString()));
        }
    }
    private List<Map<String,Object>> getFormFields(String formId){
        Map<String, Object> m = new HashMap<>();
        m.put("form_id", formId);
        List<Map<String,Object>> list = sysFormFldService.queryAll(m);
        return list;
    }
    private void retValue(PageUtils page){
        List<Map<String,Object>> list = (List<Map<String,Object>>)page.getList();
        for(Map<String,Object> m : list){
            for(Map.Entry<String,Object> me : m.entrySet()){
                Object val = me.getValue();
                if(val != null && val instanceof Date){
                    m.put(me.getKey(), dateStr(val.toString()));
                }
            }
        }
    }
    private String val(String str){
        return str.trim();
    }
    private String dateStr(String str){
        String res = "";
        String ss = str.replace("日期:","").replace("年","-").replace("月","-").replace("日","-").trim();
        String[] arr1 = StringUtils.split(ss, "-");
        if(arr1.length < 3){
            return res;
        }else{
            if(StringUtils.isNumeric(arr1[0].trim()))
                res += arr1[0].trim();
            if(StringUtils.isNumeric(arr1[1].trim()))
                res += "-" + arr1[1].trim();
            else
                res += "-01";
            if(StringUtils.isNumeric(arr1[2].trim()))
                res += "-" + arr1[2].trim();
            else
                res += "-01";
        }
        return res;
    }
    private String numStr(String str){
//        if(NumberUtils.isNumber(str))
//            return str;
        return str;
    }
}