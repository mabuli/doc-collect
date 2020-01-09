var vm = new Vue({
  el:'#app',
  components:{

  },
  data() {
    return {
      query: {
        orgName:null,
        orgCode:null,
        credCode:null,
        regAddr:null,
      }
    }
  },
  computed: {
  },
  created: function(){
  },
  methods: {
    handleQuery: function() {
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
        }
      });
    }
  }
});