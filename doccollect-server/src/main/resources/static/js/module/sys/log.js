
var vm = new Vue({
	el:'#rrapp',
	data:{
        key: null,
        dataPage: {},
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
            var url = "sys/log/list";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                dataType:'json',
                data: {page:this.dataPage.currPage, limit:this.dataPage.pageSize,key:this.key},
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
        colIndex(row, column, cellValue, index) {
            return (vm.dataPage.currPage - 1) * vm.dataPage.pageSize + index + 1
        }
	}
});
