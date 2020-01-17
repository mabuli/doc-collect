var vm = new Vue({
  el:'#app',
  components:{},
  data :{
      query: {
        orgName:'',
        orgCode:'',
        credCode:'',
        regAddr:'',
      },
      list:[]
  },
  computed: {
  },
  mounted:function(){
    this.handleQuery();
  },
  methods: {
    handleQuery () {
      let url = 'https://10.217.17.110:8243/corpration/v1.1/queryLegalByNmOrCd?1=1'
      + (this.query.orgName == '' ? '' : '&orgName=' + this.query.orgName)
      + (this.query.orgCode == '' ? '' : '&orgCode=' + this.query.orgCode)
      + (this.query.credCode == '' ? '' : '&credCode=' + this.query.credCode)
      + (this.query.regAddr == '' ? '' : '&regAddr=' + this.query.regAddr);
      console.info('-- handleQuery --', url)
      vm.list = []
      $.ajax({
        type: "GET",
        url: baseURL + '/proxy/get',
        data: {url:url},
        dataType:'json',
        success: function(resp){
          console.info(resp.data)
          //vm.list = resp.data.Entries ? resp.data.Entries: []
          if(resp.data.Entries) {
            for(var i in resp.data.Entries) {
              vm.list.push(resp.data.Entries[i])
            }
          }
        }
      });
    }
  }
});