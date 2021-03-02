var vm = new Vue({
  el: '#app',
  components: {},
  data: {
    classifyOptions:[],
    value:'',
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
    loading: false,
    address1:[],
    address2:[],
    q1loading: false,
    q2loading: false,
  },
  computed: {},
  mounted: function () {
      this.getService();
  },
  methods: {
    handleQuery() {
      vm.pageIndex = 1;
      vm.totalCount = 0;
      this.doQuery();

    },
    doQuery() {
        if (this.value == '') {
            this.$message({
                message: '查询类型不能为空',
                type: 'error'
            });
            return;
        }
      if (this.query.housAddr == '' && this.query.currAddr == '') {
        this.$message({
          message: '户口地址 和 现地址 至少输入一个',
          type: 'error'
        });
        return;
      }
      this.query.start = (this.pageIndex - 1) * this.pageSize;
      this.query.count = this.pageSize;
      this.loading = true;
      let url = this.value
          +"?1=1"
        + (this.query.housAddr == '' ? '' : '&housAddr=' + this.query.housAddr)
        + (this.query.currAddr == '' ? '' : '&currAddr=' + this.query.currAddr)
        + (this.query.hltySituCd == '' ? '' : '&hltySituCd=' + this.query.hltySituCd)
        + '&start=' + this.query.start
        + '&count=' + this.query.count;
        vm.list = []
        $.ajax({
            type: "GET",
            url: baseURL + 'proxy/get',
            data: {url: url},
            dataType: 'json',
            success: function (resp) {
                vm.loading = false;
                vm.showNoData = false
                if (resp.code == 500) {
                    alert('服务器错误');
                } else if (resp.data.Pops) {
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
                } else {
                    vm.list = []
                }
            }
        });
      /*$.ajax({
        type: "GET",
        url: baseURL + 'proxy/get',
        data: {url: url},
        dataType: 'json',
        success: function (resp) {
          console.info(resp.data)
            debugger
          if (resp.code == 500) {
            alert('服务器错误');
          } else if (resp.data.Pops) {
              vm.list = resp.data.Pops.pop ? resp.data.Pops.pop : []
                if (vm.list.length != 0) {
                    vm.totalCount = vm.list[0].cnt || 0;
            }
            vm.showNoData = false
          } else {
            vm.showNoData = true
          }
          vm.loading = false;
        }
      });*/
    },
    onCurrentChangeHandle(pi) {
      this.pageIndex = pi;
      console.info('onCurrentChangeHandle', pi);
      this.doQuery();
    },

    doQueryAddr1(query) {
        console.info('doQueryAddr', query)
        if (query.length >= 2) {
          vm.loading = vm.q1loading = true;
          vm.address1 = []
          let url = '/query/v1.0/queryPopAddrs?housAddr=' + query;
          $.ajax({
            type: "GET",
            url: baseURL + 'proxy/get',
            data: {url: url},
            dataType: 'json',
            success: function (resp) {
              console.info(resp.data)
              vm.loading = vm.q1loading = false;
              if (resp.data.addrs) {
                if (resp.data.addrs.addr.length == undefined) {
                  vm.address1.push(resp.data.addrs.addr)
                } else {
                  vm.address1 = resp.data.addrs.addr
                }
              } else {

              }
            }
          });
        }
    },

    doQueryAddr2(query) {
      console.info('doQueryAddr', query)
      if (query.length >= 2) {
        vm.loading = vm.q2loading = true;
        vm.address2 = []
        let url = '/query/v1.0/queryPopAddrs?currAddr=' + query;
        $.ajax({
          type: "GET",
          url: baseURL + 'proxy/get',
          data: {url: url},
          dataType: 'json',
          success: function (resp) {
            console.info(resp.data)
            vm.loading = vm.q2loading = false;
            if (resp.data.addrs) {
              if (resp.data.addrs.addr.length == undefined) {
                vm.address2.push(resp.data.addrs.addr)
              } else {
                vm.address2 = resp.data.addrs.addr
              }
            } else {

            }
          }
        });
      }
    },
    getService: function () {
        $.get(baseURL + "/sys/classify/select", function(r){
            vm.classifyOptions = r.list;
            console.log(r.list)
        });
    },
    clear1() {
      vm.address1 = []
    },

    clear2() {
      vm.address2 = []
    },
  }
});