//获取url上的参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

var vm = new Vue({
    el:'#app',
    components:{
        cmp:svc.cmp
    },
    data(){
        return {
            showList: true,
            showUpload:false,
            showFile:false,
            isEdit:true,
            q:{
                project_name:'',
                unit_name:'',
                page:1,
                limit:10
            },
            tablePage : {
                list: [],
                currPage: 1,
                pageSize: 10,
                totalCount: 0
            },
            filePage : {
              list: [],
              uplist:[],
              parm:{
                table_id:'',
                page: 1,
                limit: 30,
              },
              totalCount: 0
            },
            info:{},
            fileList:[],
            config:[],
            multipleSelection:[],
        }
    },
    mounted(){
        this.query();
        this.queryForm();
        this.initCmp();
    },
    methods: {
      query: function () {
          var url = "fm/project/list";
          $.ajax({
              type: "POST",
              url: baseURL + url,
              dataType:'json',
              data: this.q,
              success: function(r){
                  if(r.code === 0){
                      vm.tablePage.list = r.page.list;
                      vm.tablePage.totalCount = r.page.totalCount;
                  }
              }
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
          vm.config = this.getTreeData(cfg, {labelField:'text',idField:'id'})
        })
      },
      reBack: function () {
          vm.showList = true;
      },
      handleSizeChange(val) {
          vm.q.limit = val
          vm.q.page = 1;
          vm.query();
      },
      handleCurrentChange(val) {
          vm.q.page = val;
          vm.query();
      },
      colIndex(row, column, cellValue, index) {
          return (vm.q.page - 1) * vm.q.limit + index + 1
      },
      colIndex2(row, column, cellValue, index) {
        return (vm.filePage.parm.page - 1) * vm.filePage.parm.limit + index + 1
      },
      upload(){
          this.fileList = [];
          this.showUpload = true;
      },
      add(){
          this.info = {

          };
          this.isEdit = false;
          this.showList = false;
      },
      update(row){
          this.info = row;
          this.isEdit = true;
          this.showList = false;
      },
      save(){
        var url = this.isEdit ? "fm/project/update" : "fm/project/save";
        postdata(url, this.info, function(){
          //vm.query()
        });
      },
      tableClick(val){
        vm.multipleSelection = val;
      },
      startUpload(){
          if(this.fileList.length>1){
            alert("一次只能上传一个文档");
            return;
          }
          this.$refs.upload.submit();
      },
      endUpload(res){
          if(res && res.code == '0'){
              alert("上传成功",()=>{
                  this.showUpload = false;
                  this.query();
              });
          }else{
              alert("【错误】" + (res.msg || res));
          }
      },
      del: function () {
        if(vm.multipleSelection.length == 0){
          alert("请选择一条记录");
          return ;
        }
        var ids = vm.multipleSelection.map(x=>{return x.project_id})
        confirm('确定要删除选中的项目？', function(){
          postdata("fm/project/delete", ids, function(){
            vm.query(true);
          });
        });
      },
      initCmp(){
          this.$nextTick(function () {
              this.$forceUpdate();
          })
      },
      queryFile(x){
        $.get(baseURL + `sys/file/list?f=1&t=${x.project_id}&page=${vm.filePage.parm.page}&limit=${vm.filePage.parm.limit}`,function(r){
            if(isok(r) && r.page){
              vm.filePage.list = r.page.list;
              vm.filePage.totalCount = r.page.totalCount
            }
        });
      },
      listFile(row){
        this.info = row
        this.filePage.uplist = []
        this.queryFile(row)
        this.showFile = true
        this.filePage.parm.table_id = row.project_id
      },
      downloadFile(row){
        var link = document.createElement('a');
        link.setAttribute("download", row.file_name);
        link.href = baseURL + row.file_url
        link.click();
      },
      delFile(row){
        var ids = [row.id]
        postdata("sys/file/delete", ids, function(r){
          vm.queryFile(vm.info);
        });
      },
      startFileUpload(){
        this.$refs.fileUpload.submit();
        setTimeout(()=>{
          vm.$message('上传成功');
        },800)
      },
      endFileUpload(res){
        if(res && res.code == '0'){
          this.queryFile(this.info);
        }else{
          alert("【错误】" + (res.msg || res));
        }
      },
      handleSizeChange2(val) {
        vm.filePage.parm.limit = val
        vm.filePage.parm.page = 1;
        vm.queryFile(vm.info);
      },
      handleCurrentChange2(val) {
        vm.filePage.parm.page = val;
        vm.queryFile(vm.info);
      },
	}
});

