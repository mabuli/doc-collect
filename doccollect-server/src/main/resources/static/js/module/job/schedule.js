
var vm = new Vue({
	el:'#rrapp',
	data:{
		beanName: null,
		showList: true,
		title: null,
		dataPage: {},
		multipleSelection:[],
		schedule: {}
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
			var url = "sys/schedule/list";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				dataType:'json',
				data: {page:this.dataPage.currPage, limit:this.dataPage.pageSize, beanName:this.beanName},
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
			vm.schedule = {};
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

			$.get(baseURL + "sys/schedule/info/"+vm.multipleSelection[0].jobId, function(r){
				vm.showList = false;
                vm.title = "修改";
				vm.schedule = r.schedule;
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.schedule.jobId == null ? "sys/schedule/save" : "sys/schedule/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.schedule),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reBack();
							vm.query();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			if(vm.multipleSelection.length == 0){
				alert("请选择一条记录");
				return ;
			}
			var jobIds = vm.multipleSelection.map(x=>{return x.jobId})

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/schedule/delete",
                    contentType: "application/json",
				    data: JSON.stringify(jobIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								vm.initPage();
								vm.query();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		pause: function (event) {
			if(vm.multipleSelection.length == 0){
				alert("请选择一条记录");
				return ;
			}
			var jobIds = vm.multipleSelection.map(x=>{return x.jobId})

			confirm('确定要暂停选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/schedule/pause",
                    contentType: "application/json",
				    data: JSON.stringify(jobIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								vm.query();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		resume: function (event) {
			if(vm.multipleSelection.length == 0){
				alert("请选择一条记录");
				return ;
			}
			var jobIds = vm.multipleSelection.map(x=>{return x.jobId})

			confirm('确定要恢复选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/schedule/resume",
                    contentType: "application/json",
				    data: JSON.stringify(jobIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								vm.query();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		runOnce: function (event) {
			if(vm.multipleSelection.length == 0){
				alert("请选择一条记录");
				return ;
			}
			var jobIds = vm.multipleSelection.map(x=>{return x.jobId})

			confirm('确定要立即执行选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/schedule/run",
                    contentType: "application/json",
				    data: JSON.stringify(jobIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								vm.query();
							});
						}else{
							alert(r.msg);
						}
					}
				});
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
