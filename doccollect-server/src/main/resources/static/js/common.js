//jqGrid的配置信息
if($.jgrid){
	$.jgrid.defaults.width = 1000;
	$.jgrid.defaults.responsive = true;
	$.jgrid.defaults.styleUI = 'Bootstrap';
}

var baseURL = "../../";

//工具集合Tools
window.T = {};

//初始化表格高度
function initGridHeight(parentId,jqGrid,delHeight){
   _initGridHeight($("#"+parentId),$("#"+jqGrid),delHeight);
}

function _initGridHeight(_parentId,_jqGrid,delHeight){
    var allH=_parentId.css("height");
    if(delHeight==undefined  ||undefined =="" || undefined ==null){
        _jqGrid.jqGrid("setGridHeight",parseInt(allH)-130);
    }else{
        _jqGrid.jqGrid("setGridHeight",parseInt(allH)-delHeight);
    }
}

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
function rstr(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
}


//操作成功提示
function doSuccess(msg, callback) {
	parent.layer.closeAll();
	layer.open({
		title:"",
		type : 1,
		offset: 'rb',//offset : 'auto',// 具体配置参考：offset参数项
		content : '<div class="do-success-content">' + msg + '</div>',
		//content : '<div class="do-success-content">操 作 成 功</div>',
		shade : 0,
		anim: 6,
		area: ['300px', '60px'],
		skin: 'do-success',
		time:3000,
		closeBtn:0
		//end:callback("ok")
	});
	if (typeof (callback) === "function") {
		callback("ok");
	}
}

//全局配置
$.ajaxSetup({
	dataType: "json",
	cache: false
});

//重写alert
window.alert = function(msg, callback){
	parent.layer.alert(msg, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//重写confirm式样框
window.confirm = function(msg, callback){
	parent.layer.confirm(msg, {btn: ['确定','取消']},
	function(index){//确定事件
		if(typeof(callback) === "function"){
			callback(index);
			parent.layer.close(index);
		}
	});
}
window.showError = function (msg, callback){
	parent.layer.open({
		title: '操作错误'
		,content: msg
		,icon:2
	}, function(){
		if(typeof(callback) === "function"){
			callback(msg);
		}
	});
}

//选择一条记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow")
	// alert("rowKey====" + rowKey);
    if(!rowKey){
    	alert("请选择一条记录");
    	return ;
    }
    
    var selectedIDs = grid.getGridParam("selarrrow");
    if(selectedIDs.length > 1){
    	alert("只能选择一条记录");
    	return ;
    }
    
    return selectedIDs[0];
}




//选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请选择一条记录");
    	return ;
    }
    
    return grid.getGridParam("selarrrow");
}



//选择一条记录
function getSelectedRowById(id) {
  var grid = $("#"+id);
  var rowKey = grid.getGridParam("selrow")
	// alert("rowKey====" + rowKey);
  if(!rowKey){
  	alert("请选择一条记录");
  	return ;
  }
  
  var selectedIDs = grid.getGridParam("selarrrow");
  if(selectedIDs.length > 1){
  	alert("只能选择一条记录");
  	return ;
  }
  return selectedIDs[0];
}

//选择多条记录
function getSelectedRowsById(id) {
    var grid = $("#"+id);
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请选择一条记录");
    	return ;
    }
    
    return grid.getGridParam("selarrrow");
}


//判断是否为空
function isBlank(value) {
    return !value || !/\S/.test(value)
}

function gourl(url){
	location.href = url;
}
function isok(d){
	if(d== null){
		return false;
	}
	if(d.code === undefined){
		return true;
	}
	return d.code === 0;
}

function getdata(url, callback, opt){
	let o = callback, fn = null;
	if(typeof(callback) == 'function'){
		fn = callback;
	}
	if(opt){
		o = opt;
	}
	o = $.extend({
		url: baseURL + url,
		method:'get',
		contentType: "application/json",
		error:function(jq){
			console.log(url+' :ajax error,'+jq.status);
		},
		success:function(r){
			if(isok(r)){
				if(r.page && r.page.list)
					r = r.page.list;
				else if(r.list)
					r = r.list;
				fn && fn(r);
			}else{
				showError(r.msg);
			}
		}
	}, o);
	return $.ajax(o);
}
function postdata(url, parm, callback){
  parm = parm || {};
  let o = callback || {}, fn = null;
  if(callback && typeof(callback) == 'function'){
		fn = callback;
  }
  o = $.extend({
    url: baseURL + url,
    data: JSON.stringify(parm),
    method:'post',
    contentType: "application/json",
    error:function(jq){
			showError('网络异常，无法请求：'+jq.status);
    },
    success:function(r){
      if(r.code === 0){
        alert('操作成功', function(){
          fn && fn(r);
        });
      }else{
        showError(r.msg);
      }
    }
  }, o);
  return $.ajax(o);
}

//Vue扩展
if(window.Vue){
	Vue.prototype.$ELEMENT = { size: 'mini' }
	Vue.prototype.getTreeData = function(d, opt){
		let o = $.extend({idField:'', labelField:'',parentField:'parentId'}, opt);
		let ds = [], idF = o.idField, lbF = o.labelField, ptF = o.parentField;
		for(let i = 0; i < d.length; i++){
			if(!d[i][ptF]){
				var x = copyNode(d[i]);
				addChildNode(d, x, x[idF]);
				ds.push(x);
			}
		}
		return ds;
		function addChildNode(arr, node, nodeid){
			arr.forEach(a=>{
				if(a[ptF] == nodeid){
					var x = copyNode(a);
					addChildNode(arr, x, x[idF]);
					node.children.push(x);
				}
			})
		}
		function copyNode(o){
			return Object.assign({ label: o[lbF], children:[] },o);
		}
	}
}

Date.prototype.format = function (fmt) { //author: meizz
	var o = {
		"M+": this.getMonth() + 1,                 //月份
		"d+": this.getDate(),                    //日
		"h+": this.getHours(),                   //小时
		"m+": this.getMinutes(),                 //分
		"s+": this.getSeconds(),                 //秒
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度
		"S": this.getMilliseconds()             //毫秒
	}
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length))
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)))
	return fmt
}
