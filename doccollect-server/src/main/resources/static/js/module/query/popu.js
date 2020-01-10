var vm = new Vue({
  el:'#app',
  components:{},
  data :{
      query: {
        housAddr:null,
        currAddr:null,
        hltySituCd:null,
        start:1,
        count:10
      },
      list: [],
  },
  computed: {
  },
  mounted:function(){
    this.handleQuery();
  },
  methods: {
    handleQuery () {
      let url = 'https://10.217.17.110:8243/query/v1.0/querySixtyPopusByNameOrNum?housAddr=' + this.query.housAddr
          + '&currAddr=' + this.query.currAddr
          + '&hltySituCd=' + this.query.hltySituCd
          + '&start=' + this.query.start
          + '&count=' + this.query.count;
      console.info('-- handleQuery --', url)
      $.ajax({
        type: "GET",
        url: baseURL + '/proxy/get',
        data: {url:url},
        dataType:'json',
        success: function(resp){
          console.info(resp.data)
          vm.list = resp.data.Pops.pop ? resp.data.Pops.pop: []
        }
      });
    }
  }
});