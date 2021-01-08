
var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        title: null,
        dataPage: {},
        deptName:'',
        dept:{
            parentName:null,
            parentId:0,
            orderNum:0
        },
        parentId:0,
        lableList:[],
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
        getLable: function () {
            $.get(baseURL + "sys/dept/lable/"+this.parentId, function(r){
                vm.lableList = r.list;
            });
        },
        query: function (init) {
            if (init) {
                this.initPage();
            }
            this.getLable();
            var url = "sys/dept/page";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                dataType:'json',
                data: {page:this.dataPage.currPage, limit:this.dataPage.pageSize, name:this.deptName, parentId:this.parentId},
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
            vm.dept = {parentName:null,parentId:0,orderNum:0};
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

            $.get(baseURL + "sys/dept/info/"+vm.multipleSelection[0].deptId, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.dept = r.dept;
            });
        },
        del: function () {
            if(vm.multipleSelection.length == 0){
                alert("请选择一条记录");
                return ;
            }
            if(vm.multipleSelection.length > 1){
                alert("只能选择一条记录");
                return ;
            }

            confirm('确定要删除部门【' + vm.multipleSelection[0].name + "】？", function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/dept/delete",
                    data: "deptId=" + vm.multipleSelection[0].deptId,
                    success: function(r){
                        if(r.code === 0){
                            alert('操作成功', function(){
                                vm.query(true)
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.dept.deptId == null ? "sys/dept/save" : "sys/dept/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.dept),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.parentId = 0;
                            vm.query();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        loadDetail (deptId) {
            vm.parentId = deptId;
            vm.query(true);
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
        deptTree: function(){
            var node = ztree.getSelectedNodes();
            //选择上级部门
            vm.dept.parentId = node[0].deptId;
            vm.dept.parentName = node[0].name;
        },
        reBack: function () {
            vm.showList = true;
        }
    }
});
