//菜单树
var menu_ztree;
var menu_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    },
    check:{
        enable:true,
        nocheckInherit:true
    }
};

//部门结构树
var dept_ztree;
var dept_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};

//数据树
var data_ztree;
var data_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "meaId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            name: "meaName",
            url:"nourl"
        }
    },
    check:{
        enable:true,
        nocheckInherit:true,
    }
};

//应用树
var app_ztree;
var app_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "appId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            name: "appName",
            url:"nourl"
        }
    },
    check:{
        enable:true,
        nocheckInherit:true,
    }
};

var vm = new Vue({
    el:'#rrapp',
    data:{
        roleName: null,
        showList: true,
        title:null,
        role:{
            deptId:null,
            deptName:null
        },
        dataPage: {},
        multipleSelection:[],
        activeName:'first'
    },
    mounted(){
        this.query(true);
    },
    methods: {
        initPage(){
            this.dataPage = {
                list: [],
                currPage: 1,
                pageSize: 10,
                totalCount: 0
            }
        },
        query: function (init) {
            if (init) {
                this.initPage();
            }
            var url = "sys/role/list";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                dataType:'json',
                data: {page:this.dataPage.currPage, limit:this.dataPage.pageSize, roleName:this.roleName},
                success: function(r){
                    if(r.code === 0){
                        vm.dataPage = r.page;
                    }
                }
            });
        },
        add: function(){
            vm.showList = false;
            vm.activeName = 'first';
            vm.title = "新增";
            vm.role = {deptName:null, deptId:null};
            vm.getMenuTree(null);

            vm.getDept();
        },
        update: function () {
            if(vm.multipleSelection.length == 0){
                alert("请选择一条记录");
                return ;
            }
            if(vm.multipleSelection.length > 1){
                alert("只能选择一条记录");
                return ;
            }
            vm.activeName = 'first';
            vm.showList = false;
            vm.title = "修改";
            vm.getMenuTree(vm.multipleSelection[0].roleId);

            vm.getDept();
        },
        del: function () {
            if(vm.multipleSelection.length == 0){
                alert("请选择一条记录");
                return ;
            }
            var roleIds = vm.multipleSelection.map(x=>{return x.roleId})

            confirm('确定要删除选中的角色？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/role/delete",
                    contentType: "application/json",
                    data: JSON.stringify(roleIds),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(){
                                vm.query(true);
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getRole: function(roleId){
            $.get(baseURL + "sys/role/info/"+roleId, function(r){
                vm.role = r.role;
                //勾选角色所拥有的菜单
                var menuIds = vm.role.menuIdList;
                for(var i=0; i<menuIds.length; i++) {
                    var node = menu_ztree.getNodeByParam("menuId", menuIds[i]);
                    if(node)
                        menu_ztree.checkNode(node, true, false);
                }

                vm.getDept();
            });
        },
        saveOrUpdate: function () {
            //获取选择的菜单
            var nodes = menu_ztree.getCheckedNodes(true);
            var menuIdList = new Array();
            for(var i=0; i<nodes.length; i++) {
                menuIdList.push(nodes[i].menuId);
            }
            vm.role.menuIdList = menuIdList;

            var url = vm.role.roleId == null ? "sys/role/save" : "sys/role/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.role),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reBack();
                            vm.query();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        getMenuTree: function(roleId) {
            //加载菜单树
            $.get(baseURL + "sys/menu/list", function(r){
                menu_ztree = $.fn.zTree.init($("#menuTree"), menu_setting, r);
                //展开所有节点
                menu_ztree.expandAll(true);

                if(roleId != null){
                    vm.getRole(roleId);
                }
            });
        },
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/list", function(r){
                dept_ztree = $.fn.zTree.init($("#deptTree"), dept_setting, r);
                var node = dept_ztree.getNodeByParam("deptId", vm.role.deptId);
                if(node != null){
                    dept_ztree.selectNode(node);

                    vm.role.deptName = node.name;
                }
            })
        },
        deptTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = dept_ztree.getSelectedNodes();
                    //选择上级部门
                    vm.role.deptId = node[0].deptId;
                    vm.role.deptName = node[0].name;
                    layer.close(index);
                }
            });
        },
        handleSizeChange(val) {
            vm.dataPage.pageSize = val
            vm.dataPage.currPage = 1;
            vm.query();
        },
        handleCurrentChange(val) {
            vm.dataPage.currPage = val;
            vm.query();
        },
        handleSelectionChange(val) {
            vm.multipleSelection = val;
        },
        colIndex(row, column, cellValue, index) {
            return (vm.dataPage.currPage - 1) * vm.dataPage.pageSize + index + 1
        },
        reBack: function () {
            vm.showList = true;
        }
    }
});
