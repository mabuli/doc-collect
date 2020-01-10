var vm = new Vue({
  el:'#app',
  components:{},
  data :{
      query: {
        orgName:null,
        orgCode:null,
        credCode:null,
        regAddr:null,
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
      let url = 'https://10.217.17.110:8243/corpration/v1.1/queryLegalByNmOrCd?orgName=' + this.query.orgName
          + '&orgCode=' + this.query.orgCode
          + '&credCode=' + this.query.credCode
          + '&regAddr=' + this.query.regAddr;
      console.info('-- handleQuery --', url)
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