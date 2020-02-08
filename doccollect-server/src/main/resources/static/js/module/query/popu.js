var vm = new Vue({
  el: '#app',
  components: {},
  data: {
    query: {
      housAddr: '',
      currAddr: '',
      hltySituCd: 0,
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
    }],
    showNoData: true,
    pageIndex: 1,
    pageSize: 10,
    totalCount: 0,
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
      this.query.start = (this.pageIndex - 1) * this.pageSize;
      this.query.count = this.pageSize;

      let url = 'https://10.217.17.110:8243/query/v1.0/querySixtyPopusByNameOrNum?1=1'
        + (this.query.housAddr == '' ? '' : '&housAddr=' + this.query.housAddr)
        + (this.query.currAddr == '' ? '' : '&currAddr=' + this.query.currAddr)
        + (this.query.hltySituCd == '' ? '' : '&hltySituCd=' + this.query.hltySituCd)
        + '&start=' + this.query.start
        + '&count=' + this.query.count;
      console.info('-- handleQuery --', url)
      vm.list = []
      vm.totalCount = 0
      $.ajax({
        type: "GET",
        url: baseURL + '/proxy/get',
        data: {url: url},
        dataType: 'json',
        success: function (resp) {
          console.info(resp.data)

          if (resp.data.Pops) {
            vm.list = resp.data.Pops.pop ? resp.data.Pops.pop : []
            if (vm.list.length != 0) {
              if (vm.list['p'] != undefined) {
                var list = [
                  {
                    'p':vm.list['p'],
                    'ct':vm.list['ct'],
                  }
                ];
                vm.list = list;
              }
              vm.totalCount = vm.list[0].ct.cnt || 0;
            }
            vm.showNoData = false
          } else {
            vm.showNoData = true
          }
        }
      });
    },
    onCurrentChangeHandle(pi) {
      this.pageIndex = pi;
      console.info('onCurrentChangeHandle', pi);
      this.handleQuery();
    },
  }
});