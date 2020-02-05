var vm = new Vue({
  el: '#app',
  components: {},
  data: {
    query: {
      housAddr: '',
      currAddr: '',
      hltySituCd: null,
      start: 1,
      count: 10
    },
    list: [],
    queryRule: {
      housAddr: [
        {required: true, message: '不能为空', trigger: 'blur'}
      ],

      currAddr: [
        {required: true, message: '不能为空', trigger: 'blur'}
      ],
    },

    hltySituCds: [{
      value: '0',
      label: '健康'
    }]
  },
  computed: {},
  mounted: function () {
  //  this.handleQuery();
  },
  methods: {
    handleQuery() {
      if (this.query.housAddr == '' && this.query.currAddr == '') {
        this.$message({
          message: '户口地址 和 现地址 至少输入一个',
          type: 'error'
        });
        return;
      }
      let url = 'https://10.217.17.110:8243/query/v1.0/querySixtyPopusByNameOrNum?1=1'
        + (this.query.housAddr == '' ? '' : '&housAddr=' + this.query.housAddr)
        + (this.query.currAddr == '' ? '' : '&currAddr=' + this.query.currAddr)
        + (this.query.hltySituCd == '' ? '' : '&hltySituCd=' + this.query.hltySituCd)
        + (this.query.start == '' ? '' : '&start=' + this.query.start)
        + (this.query.count == '' ? '' : '&count=' + this.query.count);
      console.info('-- handleQuery --', url)
      vm.list = []
      $.ajax({
        type: "GET",
        url: baseURL + '/proxy/get',
        data: {url: url},
        dataType: 'json',
        success: function (resp) {
          console.info(resp.data)
          vm.list = resp.data.Pops.pop ? resp.data.Pops.pop : []
        }
      });
    }
  }
});