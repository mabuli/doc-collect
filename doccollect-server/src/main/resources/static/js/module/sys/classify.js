
var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        dataPage: {},
        classifyName:'',
        multipleSelection:[],
        classify:{},
        title:null,
        roleList: [],
        checkList: []
    },
    mounted(){
        this.query(true);
        this.getRoles();
    },
    methods: {
        formatter(row, column) {
            let ayy=row.roleIds.split(",")
            return ayy[0];
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
            var url = "sys/classify/page";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                dataType:'json',
                data: {page:this.dataPage.currPage, limit:this.dataPage.pageSize, classifyName:this.classifyName},
                success: function(r){
                    if(r.code === 0){
                        vm.dataPage = r.page;
                    }
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
            console.log(val);
            vm.multipleSelection = val;
        },
        colIndex(row, column, cellValue, index) {
           return (vm.dataPage.currPage - 1) * vm.dataPage.pageSize + index + 1
        },
        del: function () {
            if(vm.multipleSelection.length == 0){
                alert("请选择一条记录");
                return ;
            }
            var classifyIds = vm.multipleSelection.map(x=>{return x.classifyId})

            confirm('确定要删除选中的分类？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/classify/delete",
                    contentType: "application/json",
                    data: JSON.stringify(classifyIds),
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

        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.classify = {};
            vm.classify.orderNum=1;
            vm.checkList= []
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

            $.get(baseURL + "sys/classify/info/"+vm.multipleSelection[0].classifyId, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.classify = r.classify;
                vm.checkList= []
                if(r.classify.roleIds!=''){
                   // vm.checkList = r.classify.roleIds.split(",").map(Number);
                    vm.checkList = r.classify.roleIds.split(",");
                }
            });
        },


        saveOrUpdate: function (event) {
            if(!this.validate()){
                return;
            }
            var url = vm.classify.classifyId == null ? "sys/classify/save" : "sys/classify/update";
            vm.classify.roleIds=vm.checkList.join();
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.classify),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.showList = true;
                            vm.query();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },

        reBack: function () {
            vm.showList = true;
        },

        getRoles: function () {
            $.get(baseURL + "sys/role/select", function(r){
                vm.roleList = r.list;
            });
        },
        validate(){
            if(this.classify.classifyName==null){
                alert('请输入分类名称');
                return false;
            }
            if(this.classify.serviceApi==null){
                alert('请输入接口服务');
                return false;
            }
            if(vm.checkList.length== 0){
                alert('至少选择一个关联角色');
                return false;
            }
            return true;
        }
    }
});
