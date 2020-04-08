var vm = new Vue({
  el: '#app',
  components: {},
  data: {
    query: {
      orgName: '',
      orgCode: '',
      credCode: '',
      regAddr: '',
    },
    list: [],
    showNoData: true,
  },
  computed: {},
  mounted: function () {
    this.handleQuery();
  },
  methods: {
    handleQuery() {
      let url = 'https://10.217.17.110:8243/corpration/v1.1/queryLegalByNmOrCd?1=1'
        + (this.query.orgName == '' ? '' : '&orgName=' + this.query.orgName)
        + (this.query.orgCode == '' ? '' : '&orgCode=' + this.query.orgCode)
        + (this.query.credCode == '' ? '' : '&credCode=' + this.query.credCode)
        + (this.query.regAddr == '' ? '' : '&regAddr=' + this.query.regAddr);
      console.info('-- handleQuery --', url)
      vm.list = []
      $.ajax({
        type: "GET",
        url: baseURL + 'proxy/get',
        data: {url: url},
        dataType: 'text',
        success: function (resp) {
          //resp = resp.replace(/"uni_soci_cred_code":(\d+)/g, '"uni_soci_cred_code":"$1"');
          resp = JSON.parse(resp);
          console.info(resp.data)
          //vm.list = resp.data.Entries ? resp.data.Entries: []
          if (resp.code == 500) {
            alert('服务器错误');
          } else if (resp.data.Entries) {
            vm.showNoData = false
            if (resp.data.Entries.Entry.length == undefined) {
              vm.list.push(resp.data.Entries.Entry)
            } else {
              vm.list = resp.data.Entries.Entry
            }
          } else {
            vm.showNoData = true
          }
        }
      });
    }
  }
});