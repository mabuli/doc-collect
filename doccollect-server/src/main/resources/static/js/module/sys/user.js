
var setting = {
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
var ztree;

var vm = new Vue({
    el:'#rrapp',
    data:{
        username: null,
        showList: true,
        title:null,
        roleList:{},
        user:{
            status:1,
            deptId:null,
            deptName:null,
            roleIdList:[]
        },
        dataPage: {},
        multipleSelection:[],
        codes:[],
    },
    mounted(){
        this.query(true);
        this.getUserPermissionCode();
    },
    methods: {
        ifShow(code){
            return this.codes.indexOf(code)!==-1
        },
        getUserPermissionCode: function () {
            $.get(baseURL + "sys/user/getUserPermissionCode", function(r){
                vm.codes = r.codes;
            });
        },
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
            var url = "sys/user/list";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                dataType:'json',
                data: {page:this.dataPage.currPage, limit:this.dataPage.pageSize, username:this.username},
                success: function(r){
                    if(r.code === 0){
                        vm.dataPage = r.page;
                    }
                }
            });
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.roleList = {};
            vm.user = {deptName:null, deptId:null, status:1, password:'12345678', roleIdList:[]};

            //获取角色信息
            this.getRoleList();

            vm.getDept();
        },
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/list", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.user.deptId);
                if(node != null){
                    ztree.selectNode(node);

                    vm.user.deptName = node.name;
                }
            })
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

            vm.showList = false;
            vm.title = "修改";

            vm.getUser(vm.multipleSelection[0].userId);
            //获取角色信息
            this.getRoleList();
        },
        del: function () {
            if(vm.multipleSelection.length == 0){
                alert("请选择一条记录");
                return ;
            }
            var userIds = vm.multipleSelection.map(x=>{return x.userId})

            confirm('确定要删除选中的用户？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/user/delete",
                    contentType: "application/json",
                    data: JSON.stringify(userIds),
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
        saveOrUpdate: function () {
            var url = vm.user.userId == null ? "sys/user/save" : "sys/user/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.user),
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
        getUser: function(userId){
            $.get(baseURL + "sys/user/info/"+userId, function(r){
                vm.user = r.user;
                vm.user.password = null;

                vm.getDept();
            });
        },
        getRoleList: function(){
            $.get(baseURL + "sys/role/select", function(r){
                vm.roleList = r.list;
            });
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
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.user.deptId = node[0].deptId;
                    vm.user.deptName = node[0].name;

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
