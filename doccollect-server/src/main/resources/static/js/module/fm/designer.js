var vm = new Vue({
	el:'#app',
	components:{
		cmp:svc.cmp
	},
	data:{
		showList: true,
		showForm: false,
		showParent:false,
		title: null,
		parentName:'',
		isEdit:false,
		lenForm:0,
		curr:null,
		currLabel:null,
		form:{},
		formValue:{},
		formInfo:{},
		formList:[],
		formTree:[],
		formListLoading:false,
		default_expanded_keys: null,
		tagProps: {
			children: 'children',
			label: 'text'
		},
		labelTree:[],
		store:{
			tag:[
				{name:'标题项',value:'div'},
				{name:'输入项',value:'input'},
			],
			type:{
				curr:[],
				div:[
					{name:'标题列',value:'label'},
					{name:'标题行',value:'section'},
				],
				input:[
					{name:'文本框',value:'text'},
					{name:'单选框',value:'radio'},
					{name:'数字框',value:'money'},
					{name:'日期框',value:'date'},
					{name:'月份框',value:'month'},
					{name:'下拉框',value:'select'},
					{name:'长文本',value:'textarea'},
				],

			},
			span:[
				{name:'整行[24]',value:24},
				{name:'左半行[14]',value:14},
				{name:'右半行[10]',value:10},
				{name:'半行[12]',value:12},
			],
			dict:[],
			dictSub:[]
		}
	},
	computed: {
	},
	created: function(){
		this.queryForm();
		this.initCmp();
		this.queryDict();
	},
	methods: {
		add: function(){
			let newObj = {
				id:this.treeNextNo(),
				tag:'input',
				type:'text',
				text:'',
				value:'',
				field:'',
				parentId:'',
				source:'',
				data_len:30,
				span:24,
				row:1,
				notext:false,
			};
			newObj.field = 'ext_' + newObj.id
			if(this.curr && this.curr.type == 'label'){
				newObj.parentId = this.curr.id
			}
			this.form = newObj
			vm.openForm(true)
		},
		update: function (data) {
			this.form = this.formList.find(x=>{return x.id == data.id})
			vm.openForm(false)
		},
		del: function (data) {
			confirm('确定要删除选中的单元格？', function(){
				let json = vm.updateJson(data)
				let formInfo = Object.assign({tbl_name:'fm_project',json_config:json},vm.form);
				postdata("sys/form/delfld", formInfo, function(){
					vm.queryForm();
				});
			});
		},
		saveForm: function () {
			let json = this.updateJson()
      let formInfo = Object.assign({tbl_name:'fm_project',json_config:json},vm.form);
			var url = this.isEdit ? "sys/form/updatefld" : "sys/form/addfld";
			postdata(url, formInfo, function(){
        vm.queryForm()
				vm.backForm()
      });
		},
		queryForm: function(){
			$.when($.get(baseURL + "sys/form/info/1")).then((d2)=>{
				if(!isok(d2)){
					alert(d2.msg);
					return;
				}
				let cfg = []
				try{
					if(d2.info && d2.info.json_config) cfg = svh.decode(d2.info.json_config)
				}catch(e){
					alert('解析失败,格式不正确');
					return;
				}
				vm.formList = cfg
				vm.formTree = this.getTreeData(cfg, {labelField:'text',idField:'id'})
				vm.formInfo = d2.info
				vm.lenForm = this.treeCount(cfg)
				vm.setFormValue(vm.formList)
				vm.setLabelTree()
			})
		},
		setFormValue(list){
			list.forEach(x=>{
				if(x.type=='input')
					vm.formValue[x.field] = ''
			})
		},
		updateJson(delnode){
			if(delnode){
				this.formList.splice(this.formList.findIndex(x => x.id === delnode.id), 1)
			}else if(!this.isEdit){
				this.formList.push(this.form)
			}else{
				for(let i = 0; i < this.formList.length; i++){
					if(this.formList[i].id == this.form.id){
						this.formList[i] = this.form;
						break;
					}
				}
			}
			return svh.encode(this.formList);
		},
		backForm: function(){
			vm.showForm = false;
		},
    treeNodeClick: function(data){
      vm.curr = data;
    },
		labelTreeClick:function(data){
			vm.currLabel = data
		},
		treeIcon(x){
			if(x.tag == 'div'){
				if(x.children)
					return 'fa fa-folder-o'
				else
					return 'fa fa-file-text-o'
			}
			return 'fa fa-key'
		},
		tagChk(x, a1, a2){
			if(x.tag == 'div')
				return a1
			return a2
		},
		treeCount(lst){
			let mx = 0
			lst.forEach(x=>{
				if(x.id > mx)
					mx = x.id
			})
			return mx
		},
		treeNextNo(){
			return this.lenForm + 1
		},
		setLabelTree(){
			let labels = this.formList.filter(x=>{
				return x.tag == 'div' && x.type == 'label'
			})
			this.labelTree = this.getTreeData(labels, {labelField:'text',idField:'id'})
		},
		setParentName(pid){
			if(!pid) return ''
			let x = this.formList.find(x=>{return x.id == pid})
			return x.text || ''
		},
		initCmp(){
			this.$nextTick(function () {
				this.$forceUpdate();
			})
		},
		setTag(v){
			if(this.store.type[v])
				this.store.type.curr = this.store.type[v]
			else
				this.store.type.curr = []
			this.form.type = ''
		},
		setType(v){
			if(v=='section'){
				this.form.span = 24
			}
			if(v=='date'){
				this.form.value = '${今天}'
			}
		},
		openForm(isadd){
			this.showForm = true
			this.title = isadd ? '新增单元格' : '编辑单元格'
			this.isEdit = !isadd
			this.parentName = this.setParentName(this.form.parentId)
			this.store.type.curr = this.store.type[this.form.tag]
		},
		queryDict(){
			$.get(baseURL + 'sys/dict/types').then(d=>{
				this.store.dict = d.list
			})
		},
		linkSource(v){
			if(!v) return;
			$.get(baseURL + 'sys/dict/values?type=' + v).then(d=>{
				this.store.dictSub = d.list
			})
		},
		showLabel(){
			this.showParent = true
		},
		closeLabel(){
			this.showParent = false
		},
		choseLabel(){
			this.form.parentId = this.currLabel.id
			this.parentName = this.currLabel.text
			this.closeLabel()
		},
		clearLabel(){
			this.form.parentId = ''
			this.parentName = ''
		}
	}
});