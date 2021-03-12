//
// var vm = new Vue({
//     el: '#converge',
//     data () {
//         return {
//             todayDataCount: [2,3,4,2,3,9,7,1,3,2],
//             todayCount: {
//                 addRate: '115%',
//                 currRecodAmount: 3421,
//             },
//             people: {
//                 exstLegalNum: 181.4,
//                 exstPopuNum: 13.1381,
//             },
//             countUnit: {
//                 tolWbjAmount: 36,
//                 sysId: 44,
//                 tolTabAmount: 460,
//                 toleFiledCount: 8755,
//                 actRecodAmount: 53524.53,
//             },
//             orgList: [],
//             dateEnum: [
//                 {
//                   label: '近7日',
//                   value: 'WEEK',
//                 },
//                 {
//                     label: '近30日',
//                     value: 'MONTH',
//                 },
//             ],
//             dateValue: 'WEEK',
//             dataTopEnum: [
//                 {
//                     label: '累计',
//                     value: 'ALL',
//                 },
//                 {
//                     label: '近30日',
//                     value: 'MONTH',
//                 },
//                 {
//                     label: '近半年',
//                     value: 'HALF_YEAR',
//                 },
//             ],
//             fangwei: '合接口和开户行',
//             dataTopValue: 'ALL',
//             comBureauNmList: [
//                 {
//                     sjlAmountW: '450702.01',
//                     comBureauNm: '北京市残疾人联合会',
//                 },
//                 {
//                     sjlAmountW: '367345.7',
//                     comBureauNm: '北京市卫生健康委员会',
//                 },
//                 {
//                     sjlAmountW: '62667.49',
//                     comBureauNm: '北京市经济和信息化局',
//                 },
//                 {
//                     sjlAmountW: '27840.28',
//                     comBureauNm: '大兴区政务服务管理局',
//                 },
//                 {
//                     sjlAmountW: '22775.62',
//                     comBureauNm: '大兴区司法局',
//                 },
//                 {
//                     sjlAmountW: '20906.23',
//                     comBureauNm: '大兴区农业农村局',
//                 },
//                 {
//                     sjlAmountW: '11223.42',
//                     comBureauNm: '北京市水务局',
//                 },
//                 {
//                     sjlAmountW: '10481.58',
//                     comBureauNm: '大兴区应急管理局',
//                 },
//                 {
//                     sjlAmountW: '10377.49',
//                     comBureauNm: '大兴区城指中心',
//                 },
//                 {
//                     sjlAmountW: '7476.41',
//                     comBureauNm: '北京市质量技术监督局',
//                 },
//             ],
//         }
//     },
//
//     computed: {
//         // peopleCount () {
//         //     const num = Number(this.people.exstPopuNum)
//         //     if (num > 9999) {
//         //         const data = num > 9999 ? num /10000 : num
//         //         return data.toFixed(1) || 0
//         //     } else {
//         //         return  num
//         //     }
//         // },
//         // legalCount () {
//         //     const num = Number(this.people.exstLegalNum) / 10000
//         //     if (num > 9999) {
//         //         const data = num > 9999 ? num /10000 : num
//         //         return data.toFixed(1) || 0
//         //     } else {
//         //         return  num
//         //     }
//         // },
//         numList () {
//             return this.todayCount.currRecodAmount?.toString().split('') || []
//         },
//         orgName () {
//             return this.comBureauNmList.map(i => i.comBureauNm) || []
//         },
//         org1 () {
//             if (this.orgName && this.orgName.length) return this.orgName[0]
//         },
//         org2 () {
//             if (this.orgName && this.orgName.length) return this.orgName[1]
//         },
//         org3 () {
//             if (this.orgName && this.orgName.length) return this.orgName[2]
//         },
//
//         org4 () {
//             if (this.orgName && this.orgName.length) return this.orgName[3]
//             // return  '委办局'
//         },
//         org5 () {
//             if (this.orgName && this.orgName.length) return this.orgName[4]
//         },
//         org6 () {
//             if (this.orgName && this.orgName.length) return this.orgName[5]
//         },
//         org7 () {
//             if (this.orgName && this.orgName.length) return this.orgName[6]
//         },
//         org8 () {
//             if (this.orgName && this.orgName.length) return this.orgName[7]
//         },
//         org9 () {
//             if (this.orgName && this.orgName.length) return this.orgName[8]
//         },
//         org10 () {
//             if (this.orgName && this.orgName.length) return this.orgName[9]
//         },
//     },
//
//     mounted () {
//         // this.queryConvergeCount()
//         // this.querySysInsertInit().then(data => this.sysInsertInit(data))
//         // this.queryDataInsertInit().then(data => this.dataInsertInit(data))
//         // this.queryTodayInsertCount()
//         // this.queryPeople()
//         // this.queryOrgTop10().then(data => this.dataInsertTop10(data))
//         // console.log(parseInt('89%'), '尝试百分比转数字')
//         this.sysInsertInit()
//         this.dataInsertInit()
//         this.dataInsertTop10()
//     },
//
//     methods: {
//         //获取首页5个指标
//         queryConvergeCount () {
//             const _that = this
//             $.ajax({
//                 type: "get",
//                 url: baseURL + 'doc-collect/dataCollect/dataAccess',
//                 success: function(data) {
//                     _that.$set(_that, 'countUnit', data || {})
//                 },
//                 error: function (error) {
//                     console.log(error, '出错了')
//                 }
//             });
//         },
//         //系统接入领域分布
//         querySysInsertInit () {
//             return $.ajax({
//                 type: "get",
//                 url: baseURL + 'doc-collect/dataCollect/sysAcess',
//                 success: function(data) {
//                     return data
//                 },
//                 error: function (error) {
//                     console.log(error, '出错了')
//                 }
//             });
//         },
//         //数据记录接入趋势
//         queryDataInsertInit () {
//             return $.ajax({
//                 type: "get",
//                 url: baseURL + 'doc-collect/dataCollect/recordTrend',
//                 data: {
//                     dateEnum: this.dateValue,
//                 },
//                 success: function(data) {
//                     return data
//                 },
//                 error: function (error) {
//                     console.log(error, '出错了')
//                 }
//             });
//         },
//         // 今日接入总条数
//         queryTodayInsertCount () {
//             const _that = this
//             $.ajax({
//                 type: "get",
//                 url: baseURL + 'doc-collect/dataCollect/accessRecord',
//                 success: function(data) {
//                     _that.$set(_that, 'todayCount', data || {})
//                     console.log(data)
//                 },
//                 error: function (error) {
//                     console.log(error, '出错了')
//                 }
//             });
//         },
//         //获取中间部门数据
//         queryOrgList () {
//             const _that = this
//             $.ajax({
//                 type: "get",
//                 url: baseURL + 'doc-collect/dataCollect/dataAccess',
//                 success: function(data) {
//                     console.log(data, '5个指标')
//                     _that.$set(_that, 'countUnit', data || {})
//                 },
//                 error: function (error) {
//                     console.log(error, '出错了')
//                 }
//             });
//         },
//         queryPeople () {
//             const _that = this
//             $.ajax({
//                 type: "get",
//                 url: baseURL + 'doc-collect/dataCollect/daXingData',
//                 success: function(data) {
//                     _that.$set(_that, 'people', data || {})
//                 },
//                 error: function (error) {
//                     console.log(error, '出错了')
//                 }
//             });
//         },
//         queryOrgTop10 () {
//             const _that = this
//             return $.ajax({
//                 type: "get",
//                 url: baseURL + 'doc-collect/dataCollect/recordAccess',
//                 data: {
//                     dateEnum: this.dataTopValue,
//                 },
//                 success: function(data) {
//                     if (_that.dataTopValue === 'ALL') _that.orgList = data
//                     return data
//                 },
//                 error: function (error) {
//                     console.log(error, '出错了')
//                 }
//             });
//         },
//         //系统接入领域分布
//         sysInsertInit () {
//             const result = [
//                 {
//                     gkName: '农业口',
//                     accessCount: '3',
//                     zb: '8%',
//                 },
//                 {
//                     gkName: '国企口',
//                     accessCount: '2',
//                     zb: '1%',
//                 },
//                 {
//                     gkName: '文教口',
//                     accessCount: '4',
//                     zb: '11%',
//                 },
//                 {
//                     gkName: '机关口',
//                     accessCount: '3',
//                     zb: '8%',
//                 },
//                 {
//                     gkName: '其他',
//                     accessCount: '2',
//                     zb: '5%',
//                 },
//                 {
//                     gkName: '城建口',
//                     accessCount: '6',
//                     zb: '17%',
//                 },
//                 {
//                     gkName: '工业口',
//                     accessCount: '8',
//                     zb: '22%',
//                 },
//                 {
//                     gkName: '政法口',
//                     accessCount: '3',
//                     zb: '8%',
//                 },
//                 {
//                     gkName: '综合计划口',
//                     accessCount: '5',
//                     zb: '14%',
//                 },
//             ]
//             const resultList = result?.map(i => {
//                 const {accessCount, gkName, zb} = i
//                 return {
//                     value: parseInt(accessCount),
//                     name: gkName,
//                     zb,
//                 }
//             })
//             console.log(resultList)
//             option = {
//                 color: ["#FF5FA7","#FC7549","#FE9938","#FAC916","#6ACD15","#00CD9D","#00C4FF","#2378FF","#7079F6","#B261F0",],
//                 tooltip: {
//                     trigger: 'item'
//                 },
//                 // legend: {
//                 //     top: '5%',
//                 //     left: 'center'
//                 // },
//                 series: [
//                     {
//                         name: '系统接入领域分布',
//                         type: 'pie',
//                         radius: ['40%', '70%'],
//                         avoidLabelOverlap: false,
//                         hoverAnimation:false,
//                         label: {
//                             show: true,
//                             position: 'outside',
//                             formatter: '{b}: {c}',
//                         },
//                         labelLine: {
//                             lineStyle: {
//                                 color: "#fff",
//                             },
//                         },
//
//                         labelLine: {
//                             show: true,
//                             color: "#fff",
//                         },
//                         data: resultList
//                     }
//                 ]
//             };
//             var myChart = echarts.init(document.getElementById('sysInsert'))
//             // 使用刚指定的配置项和数据显示图表。
//             myChart.setOption(option)
//         },
//         //数据记录接入趋势
//         dataInsertInit () {
//             const colorList = ["#9E87FF", '#73DDFF', '#fe9a8b', '#F56948', '#9E87FF']
//             const result1 = [
//                 {
//                     currRecodAmount: '3827.5921',
//                     accessDate: '3月1日',
//                 },
//                 {
//                     currRecodAmount: '1586.0905',
//                     accessDate: '3月2日',
//                 },
//                 {
//                     currRecodAmount: '1585.6807',
//                     accessDate: '3月3日',
//                 },
//                 {
//                     currRecodAmount: '1619.8904',
//                     accessDate: '3月4日',
//                 },
//                 {
//                     currRecodAmount: '1584.6286',
//                     accessDate: '3月5日',
//                 },
//                 {
//                     currRecodAmount: '1588.4235',
//                     accessDate: '3月6日',
//                 },
//                 {
//                     currRecodAmount: '3421.6909',
//                     accessDate: '3月7日',
//                 },
//             ]
//             const result2 = [
//                 {
//                     currRecodAmount: '3236.3317',
//                     accessDate: '2月7日',
//                 },
//                 {
//                     currRecodAmount: '1788.6724',
//                     accessDate: '2月8日',
//                 },
//                 {
//                     currRecodAmount: '1549.7668',
//                     accessDate: '2月9日',
//                 },
//                 {
//                     currRecodAmount: '1533.1869',
//                     accessDate: '2月10日',
//                 },
//                 {
//                     currRecodAmount: '1552.5268',
//                     accessDate: '2月11日',
//                 },
//                 {
//                     currRecodAmount: '1547.4437',
//                     accessDate: '2月12日',
//                 },
//                 {
//                     currRecodAmount: '1572.736',
//                     accessDate: '2月13日',
//                 },
//                 {
//                     currRecodAmount: '1932.9525',
//                     accessDate: '2月14日',
//                 },
//                 {
//                     currRecodAmount: '1822.7612',
//                     accessDate: '2月15日',
//                 },
//                 {
//                     currRecodAmount: '1559.8868',
//                     accessDate: '2月16日',
//                 },
//                 {
//                     currRecodAmount: '1561.1872',
//                     accessDate: '2月17日',
//                 },
//                 {
//                     currRecodAmount: '1562.6548',
//                     accessDate: '2月18日',
//                 },
//                 {
//                     currRecodAmount: '1562.5393',
//                     accessDate: '2月19日',
//                 },
//                 {
//                     currRecodAmount: '1563.4268',
//                     accessDate: '2月20日',
//                 },
//                 {
//                     currRecodAmount: '2906.1692',
//                     accessDate: '2月21日',
//                 },
//                 {
//                     currRecodAmount: '1849.5627',
//                     accessDate: '2月22日',
//                 },
//                 {
//                     currRecodAmount: '1569.8333',
//                     accessDate: '2月23日',
//                 },
//                 {
//                     currRecodAmount: '5140.1242',
//                     accessDate: '2月24日',
//                 },
//                 {
//                     currRecodAmount: '1567.7348',
//                     accessDate: '2月25日',
//                 },
//                 {
//                     currRecodAmount: '1589.064',
//                     accessDate: '2月26日',
//                 },
//                 {
//                     currRecodAmount: '1603.794',
//                     accessDate: '2月27日',
//                 },
//                 {
//                     currRecodAmount: '2905.2024',
//                     accessDate: '2月28日',
//                 },
//                 {
//                     currRecodAmount: '3827.5921',
//                     accessDate: '3月1日',
//                 },
//                 {
//                     currRecodAmount: '1586.0905',
//                     accessDate: '3月2日',
//                 },
//                 {
//                     currRecodAmount: '1585.6807',
//                     accessDate: '3月3日',
//                 },
//                 {
//                     currRecodAmount: '1619.8904',
//                     accessDate: '3月4日',
//                 },
//                 {
//                     currRecodAmount: '1584.6286',
//                     accessDate: '3月5日',
//                 },
//                 {
//                     currRecodAmount: '1588.4235',
//                     accessDate: '3月6日',
//                 },
//                 {
//                     currRecodAmount: '3421.6909',
//                     accessDate: '3月7日',
//                 },
//             ]
//             const result = this.dateValue === 'WEEK' ? result1 : result2
//             option = {
//                 // title: {
//                 //     text: '全国6月销售统计',
//                 //     textStyle: {
//                 //         fontSize: 12,
//                 //         fontWeight: 400
//                 //     },
//                 //     left: 'center',
//                 //     top: '5%'
//                 // },
//                 // legend: {
//                 //     icon: 'circle',
//                 //     top: '5%',
//                 //     right: '5%',
//                 //     itemWidth: 6,
//                 //     itemGap: 20,
//                 //     textStyle: {
//                 //         color: '#556677'
//                 //     }
//                 // },
//                 tooltip: {
//                     trigger: 'axis',
//                     axisPointer: {
//                         label: {
//                             show: true,
//                             backgroundColor: '#fff',
//                             color: '#556677',
//                             borderColor: 'rgba(0,0,0,0)',
//                             shadowColor: 'rgba(0,0,0,0)',
//                             shadowOffsetY: 0
//                         },
//                         lineStyle: {
//                             width: 0
//                         }
//                     },
//                     backgroundColor: '#fff',
//                     textStyle: {
//                         color: '#5c6c7c'
//                     },
//                     padding: [10, 10],
//                     extraCssText: 'box-shadow: 1px 0 2px 0 rgba(163,163,163,0.5)'
//                 },
//                 grid: {
//                     top: '15%',
//                     right: "5%",
//                     bottom: "10%",
//                     left: "15%",
//                 },
//                 xAxis: [{
//                     show: this.dateValue === 'WEEK',
//                     type: 'category',
//                     data: result?.map(i => i.accessDate) || [],
//                     axisLine: {
//                         lineStyle: {
//                             color: '#163387'
//                         }
//                     },
//                     axisTick: {
//                         show: false
//                     },
//                     axisLabel: {
//                         interval: 0,
//                         textStyle: {
//                             color: '#ffffff'
//                         },
//                         // 默认x轴字体大小
//                         fontSize: 12,
//                         // margin:文字到x轴的距离
//                         margin: 15
//                     },
//                     axisPointer: {
//                         label: {
//                             // padding: [11, 5, 7],
//                             padding: [0, 0, 10, 0],
//                             /*
//                 除了padding[0]建议必须是0之外，其他三项可随意设置
//
//                 和CSSpadding相同，[上，右，下，左]
//
//                 如果需要下边线超出文字，设左右padding即可，注：左右padding最好相同
//
//                 padding[2]的10:
//
//                 10 = 文字距下边线的距离 + 下边线的宽度
//
//                 如：UI图中文字距下边线距离为7 下边线宽度为2
//
//                 则padding: [0, 0, 9, 0]
//
//                             */
//                             // 这里的margin和axisLabel的margin要一致!
//                             margin: 15,
//                             // 移入时的字体大小
//                             fontSize: 12,
//                             backgroundColor: {
//                                 type: 'linear',
//                                 x: 0,
//                                 y: 0,
//                                 x2: 0,
//                                 y2: 1,
//                                 colorStops: [{
//                                     offset: 0,
//                                     color: '#fff' // 0% 处的颜色
//                                 }, {
//                                     // offset: 0.9,
//                                     offset: 0.86,
//                                     /*
//             0.86 = （文字 + 文字距下边线的距离）/（文字 + 文字距下边线的距离 + 下边线的宽度）
//
//                                     */
//                                     color: '#fff' // 0% 处的颜色
//                                 }, {
//                                     offset: 0.86,
//                                     color: '#33c0cd' // 0% 处的颜色
//                                 }, {
//                                     offset: 1,
//                                     color: '#33c0cd' // 100% 处的颜色
//                                 }],
//                                 global: false // 缺省为 false
//                             }
//                         }
//                     },
//                     boundaryGap: false
//                 }],
//                 yAxis: [{
//                     type: 'value',
//                     axisTick: {
//                         show: false
//                     },
//                     axisLine: {
//                         show: false,
//                         lineStyle: {
//                             color: '#163387'
//                         }
//                     },
//                     axisLabel: {
//                         textStyle: {
//                             color: '#ffffff'
//                         }
//                     },
//                     splitLine: {
//                         lineStyle: {
//                             type: "dotted", //设置网格线类型 dotted：虚线   solid:实线
//                             width: 1,
//                         },
//                         show: true, //隐藏或显示
//                     },
//                     data: [20],
//                 }],
//                 series: [{
//                     name: '接入数',
//                     type: 'line',
//                     data: result?.map(i => i.currRecodAmount) || [],
//                     symbolSize: 1,
//                     symbol: 'circle',
//                     smooth: true,
//                     areaStyle: {
//                         normal: {
//                             color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
//                                 { offset: 0, color: "#1438A3" },
//                                 { offset: 1, color: "#0E1B6E" }
//                             ])
//                         }
//                     },
//                     yAxisIndex: 0,
//                     showSymbol: false,
//                     lineStyle: {
//                         width: 5,
//                         color: "#2176FD",
//                         // shadowColor: '#2176FD',
//                         // shadowBlur: 10,
//                         // shadowOffsetY: 20
//                     },
//                 }
//                 ]
//             }
//             var myChart = echarts.init(document.getElementById('dataInsert'))
//             // 使用刚指定的配置项和数据显示图表。
//             myChart.setOption(option)
//         },
//         // 接入数据记录TOP10委办局
//         dataInsertTop10 () {
//                 const result1 = [
//                     {
//                         sjlAmountW: '450702.01',
//                         comBureauNm: '北京市残疾人联合会',
//                     },
//                     {
//                         sjlAmountW: '367345.7',
//                         comBureauNm: '北京市卫生健康委员会',
//                     },
//                     {
//                         sjlAmountW: '62667.49',
//                         comBureauNm: '北京市经济和信息化局',
//                     },
//                     {
//                         sjlAmountW: '27840.28',
//                         comBureauNm: '大兴区政务服务管理局',
//                     },
//                     {
//                         sjlAmountW: '22775.62',
//                         comBureauNm: '大兴区司法局',
//                     },
//                     {
//                         sjlAmountW: '20906.23',
//                         comBureauNm: '大兴区农业农村局',
//                     },
//                     {
//                         sjlAmountW: '11223.42',
//                         comBureauNm: '北京市水务局',
//                     },
//                     {
//                         sjlAmountW: '10481.58',
//                         comBureauNm: '大兴区应急管理局',
//                     },
//                     {
//                         sjlAmountW: '10377.49',
//                         comBureauNm: '大兴区城指中心',
//                     },
//                     {
//                         sjlAmountW: '7476.41',
//                         comBureauNm: '北京市质量技术监督局',
//                     },
//                 ]
//                 const result2 = [
//                     {
//                         sjlAmountW: '33868',
//                         comBureauNm: '北京市卫生健康委员会',
//                     },
//                     {
//                         sjlAmountW: '7356.62',
//                         comBureauNm: '北京市经济和信息化局',
//                     },
//                     {
//                         sjlAmountW: '3866.4',
//                         comBureauNm: '大兴区政务服务管理局',
//                     },
//                     {
//                         sjlAmountW: '3415.46',
//                         comBureauNm: '大兴区司法局',
//                     },
//                     {
//                         sjlAmountW: '2676.01',
//                         comBureauNm: '大兴区应急管理局',
//                     },
//                     {
//                         sjlAmountW: '2476.82',
//                         comBureauNm: '大兴区城指中心',
//                     },
//                     {
//                         sjlAmountW: '1315.43',
//                         comBureauNm: '北京市质量技术监督局',
//                     },
//                     {
//                         sjlAmountW: '979.94',
//                         comBureauNm: '北京市残疾人联合会',
//                     },
//                     {
//                         sjlAmountW: '656.77',
//                         comBureauNm: '大兴区经济和信息化局',
//                     },
//                     {
//                         sjlAmountW: '496.46',
//                         comBureauNm: '大兴区生态环境局',
//                     },
//                 ]
//                 const result3 = [
//                     {
//                         sjlAmountW: '205768.99',
//                         comBureauNm: '北京市卫生健康委员会',
//                     },
//                     {
//                         sjlAmountW: '135838.75',
//                         comBureauNm: '北京市残疾人联合会',
//                     },
//                     {
//                         sjlAmountW: '50611.73',
//                         comBureauNm: '北京市经济和信息化局',
//                     },
//                     {
//                         sjlAmountW: '23276.25',
//                         comBureauNm: '大兴区政务服务管理局',
//                     },
//                     {
//                         sjlAmountW: '22775.62',
//                         comBureauNm: '大兴区司法局',
//                     },
//                     {
//                         sjlAmountW: '10326.32',
//                         comBureauNm: '大兴区城指中心',
//                     },
//                     {
//                         sjlAmountW: '9755.52',
//                         comBureauNm: '大兴区应急管理局',
//                     },
//                     {
//                         sjlAmountW: '6272.96',
//                         comBureauNm: '大兴区农业农村局',
//                     },
//                     {
//                         sjlAmountW: '5550.61',
//                         comBureauNm: '北京市质量技术监督局',
//                     },
//                     {
//                         sjlAmountW: '3849.82',
//                         comBureauNm: '北京市教育委员会',
//                     },
//                 ]
//                 const result = this.dataTopValue === 'ALL' ? result1 : this.dataTopValue === 'MONTH' ? result2 : result3
//                 const maxValue = result?.map(i => i.sjlAmountW).reduce((num1, num2) => {
//                     return parseInt(num1) > parseInt(num2) ? num1 : num2
//                 })
//                 const maxValueList = []
//                 result.forEach(() => {
//                     maxValueList.push(parseInt(maxValue))
//                 })
//                 option = {
//                         grid: {
//                             left: '5%',
//                             right: '5%',
//                     bottom: '1%',
//                     top: '1%',
//                     containLabel: true
//                 },
//                 tooltip: {
//                     trigger: 'axis',
//                     axisPointer: {
//                         type: 'none'
//                     },
//                     formatter: function(params) {
//                         return params[0].name + '<br/>' +
//                             "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:rgba(36,207,233,0.9)'></span>" +
//                             params[0].seriesName + ' : ' + Number((params[0].value.toFixed(4) / 10000).toFixed(2)).toLocaleString() + ' 万元<br/>'
//                     }
//                 },
//                 xAxis: {
//                     show: false,
//                     type: 'value'
//                 },
//                 yAxis: [{
//                     type: 'category',
//                     inverse: true,
//                     axisLabel: {
//                         show: true,
//                         textStyle: {
//                             color: '#fff'
//                         },
//                         formatter: function(value) {
//                             if (value.length > 6) {
//                                 return value.substring(0, 8) + "...";
//                             } else {
//                                 return value;
//                             }
//                         },
//                     },
//                     splitLine: {
//                         show: false
//                     },
//                     axisTick: {
//                         show: false
//                     },
//                     axisLine: {
//                         show: false
//                     },
//                     data: result?.map(i => i.comBureauNm) || []
//                 }, {
//                     type: 'category',
//                     inverse: true,
//                     axisTick: 'none',
//                     axisLine: 'none',
//                     show: true,
//                     axisLabel: {
//                         textStyle: {
//                             color: '#ffffff',
//                             fontSize: '12'
//                         },
//                         formatter: function(value) {
//                             if (value >= 10000) {
//                                 return (value / 10000).toLocaleString() + '万';
//                             } else {
//                                 return value.toLocaleString();
//                             }
//                         },
//                     },
//                     data: result?.map(i => i.sjlAmountW) || []
//                 }],
//                 series: [{
//                     name: '接入数',
//                     type: 'bar',
//                     zlevel: 1,
//                     itemStyle: {
//                         normal: {
//                             barBorderRadius: 8,
//                             //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
//                             color: function (params) {
//                                 var colorList = [
//                                     ['#FB7449', '#FC7549'],
//                                     ['#FE9937', '#FF9A37'],
//                                     ['#F9C816', '#FAC916'],
//                                     ['#69CD14', '#6ACE14'],
//                                     ['#00CB9C', '#00CD9D'],
//                                     ['#00C3FF', '#00C4FF'],
//                                     ['#2277FF', '#2378FF'],
//                                     ['#6F78F6', '#7079F6'],
//                                     ['#B160F0', '#B261F0'],
//                                     ['#FE5EA7', '#FF5FA7'],
//                                 ];
//                                 var index = params.dataIndex;
//                                 if (params.dataIndex >= colorList.length) {
//                                     index = params.dataIndex - colorList.length;
//                                 }
//                                 return new echarts.graphic.LinearGradient(1, 0, 0, 0, [{
//                                     offset: 0,
//                                     color: colorList[index][0]
//                                 },
//                                     {
//                                         offset: 1,
//                                         color: colorList[index][1]
//                                     }
//                                 ]);
//                             },
//                             borderColor: '#09298D'//边框颜色
//                         },
//                     },
//                     barWidth: 10,
//                     data: result?.map(i => i.sjlAmountW) || []
//                 },
//                     {
//                         name: '背景',
//                         type: 'bar',
//                         barWidth: 10,
//                         barGap: '-100%',
//                         data: maxValueList,
//                         itemStyle: {
//                             normal: {
//                                 color: '#082874',
//                                 barBorderRadius: 30,
//                                 borderColor: '#09298D',
//                             }
//                         },
//                     },
//                 ]
//             };
//             var myChart = echarts.init(document.getElementById('insertOrg'))
//             // 使用刚指定的配置项和数据显示图表。
//             myChart.setOption(option)
//         },
//         handlerClickItem (item) {
//             this.dateValue = item.value
//             this.dataInsertInit()
//             // this.queryDataInsertInit().then(data => this.dataInsertInit(data))
//         },
//         handlerTopItem (item) {
//             this.dataTopValue = item.value
//             // this.queryOrgTop10().then(data => this.dataInsertTop10(data))
//             this.dataInsertTop10()
//         },
//         renderSearch (item) {
//             return this.dateValue === item.value
//         },
//         renderTop (item) {
//             return this.dataTopValue === item.value
//         },
//     },
// })