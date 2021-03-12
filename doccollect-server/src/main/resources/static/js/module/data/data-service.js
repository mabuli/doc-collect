// function listDomainShare () {
//     const result = [{gkName: "机关口",gkNameRatio: "0.0833"},
//     {gkName: "综合计划口",gkNameRatio: "0.25"},
//     {gkName: "城建口",gkNameRatio: "0.1667"},
//     {gkName: "国企口",gkNameRatio: "0.0833"},
//     {gkName: "各镇",gkNameRatio: "0.0833"},
//     {gkName: "工业口",gkNameRatio: "0.0833"},
//     {gkName: "政法口",gkNameRatio: "0.1667"},
//     {gkName: "农业口",gkNameRatio: "0.0833"}]
//     const data = result.map(item => {
//         return {
//             name: item.gkName,
//             value: parseInt(item.gkNameRatio),
//         }
//     })
//     // 左中图表
//     var myChart = echarts.init(document.getElementById('leftCen'));
//     optionleftCen = {
//         color: ["#019CFF", "#33C74D", "#F7E71B", "#19D6EC"],
//         grid: {
//             left: 50,
//             top: 50,
//             bottom: 10,
//             right: 10,
//             containLabel: true
//         },
//         tooltip: {
//             trigger: 'item',
//             formatter: "{b} : {c} ({d}%)"
//         },
//
//         calculable: true,
//         series: [{
//             type: 'pie',
//             radius: ["20%", "25%"],
//             hoverAnimation: false,
//             labelLine: {
//                 normal: {
//                     show: false,
//                 },
//                 emphasis: {
//                     show: false
//                 }
//             },
//             data: [{
//                 name: '',
//                 value: 0,
//                 itemStyle: {
//                     normal: {
//                         color: "#0B4A6B"
//                     }
//                 }
//             }]
//         },{
//             type: 'pie',
//             radius: ["0%", "13%"],
//             hoverAnimation: false,
//             labelLine: {
//                 normal: {
//                     show: false,
//                 },
//                 emphasis: {
//                     show: false
//                 }
//             },
//             data: [{
//                 name: '',
//                 value: 0,
//                 itemStyle: {
//                     normal: {
//                         color: "#3A496D"
//                     }
//                 }
//             }]
//         }, {
//             type: 'pie',
//             radius: ["60%", "61%"],
//             hoverAnimation: false,
//             labelLine: {
//                 normal: {
//                     show: false,
//                 },
//                 emphasis: {
//                     show: false
//                 }
//             },
//             name: "",
//             data: [{
//                 name: '',
//                 value: 0,
//                 itemStyle: {
//                     normal: {
//                         color: "#0B4A6B"
//                     }
//                 }
//             }]
//         },{
//             stack: 'a',
//             type: 'pie',
//             radius: ['20%', '50%'],
//             roseType: 'area',
//             zlevel:10,
//             label: {
//                 normal: {
//                     show: true,
//                     formatter: function(params){
//                         return params.name + "  " + params.value
//                     },
//                     textStyle: {
//                         fontSize: 10,
//                     },
//                     position: 'outside',
//                     formatter: '{a|{b}：{d}%}\n{hr|}',
//                     rich: {
//                         a: {
//                             color:'#F9F9F9',
//                             padding: [20, -80, 10, -80]
//                         }
//                     }
//                 },
//                 emphasis: {
//                     show: true
//                 }
//             },
//             labelLine: {
//                 normal: {
//                     show: true,
//                     length: 15,
//                     length2: 60,
//                     lineStyle:{
//                         width:2
//                     }
//                 },
//                 emphasis: {
//                     show: false
//                 }
//             },
//             data: data,
//         }, ]
//     }
//     myChart.setOption(optionleftCen);
// }
//
// function shareOrganTop5 () {
//     const data = [
//         {tgtOrgName: "经信局", exchangeSum: "239906010"},
//         {tgtOrgName: "残联", exchangeSum: "20655634"},
//         {tgtOrgName: "庞各庄镇", exchangeSum: "14894496"},
//         {tgtOrgName: "兴展公司", exchangeSum: "11283987"},
//         {tgtOrgName: "城管委", exchangeSum: "1353484"},
//     ]
//     let salvProValues = []
//     let salvProNames = []
//     data.map(item => {
//         salvProValues.push(parseInt(item.exchangeSum))
//         salvProNames.push(item.tgtOrgName)
//     })
//     // 左上图表
//     var myChart = echarts.init(document.getElementById('leftTop'));
//     var salvProName = salvProNames;
//     var salvProValue = salvProValues;
//     var salvProMax =[];//背景按最大值
//     for (let i = 0; i < salvProValue.length; i++) {
//         salvProMax.push(salvProValue[0])
//     }
//     // 获取最大值
//     const maxNumber = data?.map(i => i.exchangeSum).reduce((num1, num2) => {
//         return num1 > num2 ? num1 : num2
//     })
//     const maxValue = parseInt(maxNumber) * 1.2
//     const maxValueList = []
//     data.forEach(i => maxValueList.push(maxValue))
//
//     leftTop = {
//         grid: {
//             left: '5%',
//             right: '5%',
//             bottom: '1%',
//             top: '1%',
//             containLabel: true
//         },
//         tooltip: {
//             trigger: 'axis',
//             axisPointer: {
//                 type: 'none'
//             },
//             formatter: function(params) {
//                 return params[0].name + '<br/>' +
//                     "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:rgba(36,207,233,0.9)'></span>" +
//                     params[0].seriesName + ' : ' + Number((params[0].value.toFixed(4) / 10000).toFixed(2)).toLocaleString()
//             }
//         },
//         xAxis: {
//             show: false,
//             type: 'value'
//         },
//         yAxis: [{
//             type: 'category',
//             inverse: true,
//             axisLabel: {
//                 show: true,
//                 textStyle: {
//                     color: '#fff'
//                 },
//             },
//             splitLine: {
//                 show: false
//             },
//             axisTick: {
//                 show: false
//             },
//             axisLine: {
//                 show: false
//             },
//             data: salvProName,
//         }, {
//             type: 'category',
//             inverse: true,
//             axisTick: 'none',
//             axisLine: 'none',
//             show: true,
//             axisLabel: {
//                 textStyle: {
//                     color: '#ffffff',
//                     fontSize: '12'
//                 },
//                 formatter: function(value) {
//                     if (value >= 10000) {
//                         return (value / 10000).toLocaleString();
//                     } else {
//                         return value.toLocaleString();
//                     }
//                 },
//             },
//             data: salvProValue,
//         }],
//         series: [{
//             name: '数据量',
//             type: 'bar',
//             zlevel: 1,
//             hoverAnimation:false,
//             itemStyle: {
//                 normal: {
//                     barBorderRadius: 8,
//                     //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
//                     color: function (params) {
//                         var colorList = [
//                             ['#FB7449', '#FC7549'],
//                             ['#FE9937', '#FF9A37'],
//                             ['#F9C816', '#FAC916'],
//                             ['#69CD14', '#6ACE14'],
//                             ['#00CB9C', '#00CD9D'],
//                             ['#00C3FF', '#00C4FF'],
//                             ['#2277FF', '#2378FF'],
//                             ['#6F78F6', '#7079F6'],
//                             ['#B160F0', '#B261F0'],
//                             ['#FE5EA7', '#FF5FA7'],
//                         ];
//                         var index = params.dataIndex;
//                         if (params.dataIndex >= colorList.length) {
//                             index = params.dataIndex - colorList.length;
//                         }
//                         return new echarts.graphic.LinearGradient(1, 0, 0, 0, [{
//                             offset: 0,
//                             color: colorList[index][0]
//                         },
//                             {
//                                 offset: 1,
//                                 color: colorList[index][1]
//                             }
//                         ]);
//                     },
//                     borderColor: '#09298D'//边框颜色
//                 },
//             },
//             barWidth: 10,
//             data: salvProValue,
//         },
//             {
//                 name: '背景',
//                 type: 'bar',
//                 barWidth: 10,
//                 barGap: '-100%',
//                 data: maxValueList,
//                 itemStyle: {
//                     normal: {
//                         color: '#082874',
//                         barBorderRadius: 30,
//                         borderColor: '#09298D',
//                     }
//                 },
//             },
//         ]
//     }
//     myChart.setOption(leftTop);
// }
//
//
//
// function shareRatis () {
//     const result = {cyptNum: "31",
//         cyptRatio: "26.00%",
//         ssjcNum: "32",
//         ssjcRatio: "27.00%",
//         xyptNum: "55",
//         xyptRatio: "47.00%"}
//     const {cyptRatio, ssjcRatio, xyptRatio} = result
//     // 左下图表1
//     var myChart = echarts.init(document.getElementById('leftBomData1'));
//     var datas1 = {
//         value: parseInt(cyptRatio),
//         company: "%",
//         ringColor: [{
//             offset: 0,
//             color: '#FC7549' // 0% 处的颜色
//         }, {
//             offset: 1,
//             color: '#FC7549' // 100% 处的颜色
//         }]
//     }
//     var leftBtnOption = {
//         title: {
//             text: datas1.value + datas1.company,
//             x: 'center',
//             y: 'center',
//             textStyle: {
//                 fontWeight: 'normal',
//                 color: '#fff',
//                 fontSize: '16'
//             },
//             // subtext: '服务占比',
//             // subtextStyle: {
//             //     color: '#fff',
//             //     fontSize: 16
//             // },
//         },
//         color: ['#003766'],
//         legend: {
//             show: false,
//             data: []
//         },
//
//         series: [{
//             name: 'Line 1',
//             type: 'pie',
//             clockWise: true,
//             radius: ['70%', '100%'],
//             itemStyle: {
//                 normal: {
//                     label: {
//                         show: false
//                     },
//                     labelLine: {
//                         show: false
//                     }
//                 }
//             },
//             hoverAnimation: false,
//             data: [{
//                 value: datas1.value,
//                 name: '',
//                 itemStyle: {
//                     normal: {
//                         color: { // 完成的圆环的颜色
//                             colorStops: datas1.ringColor
//                         },
//                         label: {
//                             show: false
//                         },
//                         labelLine: {
//                             show: false
//                         }
//                     }
//                 }
//             }, {
//                 name: '',
//                 value: 100 - datas1.value
//             }]
//         }]
//     };
//     myChart.setOption(leftBtnOption);
//
// // 左下图表2
//     var myChart = echarts.init(document.getElementById('leftBomData2'));
//     var datas2 = {
//         value: parseInt(ssjcRatio),
//         company: "%",
//         ringColor: [{
//             offset: 0,
//             color: '#FF9E01' // 0% 处的颜色
//         }, {
//             offset: 1,
//             color: '#FF9E01' // 100% 处的颜色
//         }]
//     }
//     var leftBomData2 = {
//         title: {
//             text: datas2.value + datas2.company,
//             x: 'center',
//             y: 'center',
//             textStyle: {
//                 fontWeight: 'normal',
//                 color: '#fff',
//                 fontSize: '16'
//             },
//             // subtext: '服务占比',
//             // subtextStyle: {
//             //     color: '#fff',
//             //     fontSize: 16
//             // },
//         },
//         color: ['#003766'],
//         legend: {
//             show: false,
//             data: []
//         },
//
//         series: [{
//             name: 'Line 1',
//             type: 'pie',
//             clockWise: true,
//             radius: ['70%', '100%'],
//             itemStyle: {
//                 normal: {
//                     label: {
//                         show: false
//                     },
//                     labelLine: {
//                         show: false
//                     }
//                 }
//             },
//             hoverAnimation: false,
//             data: [{
//                 value: datas2.value,
//                 name: '',
//                 itemStyle: {
//                     normal: {
//                         color: { // 完成的圆环的颜色
//                             colorStops: datas2.ringColor
//                         },
//                         label: {
//                             show: false
//                         },
//                         labelLine: {
//                             show: false
//                         }
//                     }
//                 }
//             }, {
//                 name: '',
//                 value: 100 - datas2.value
//             }]
//         }]
//     };
//     myChart.setOption(leftBomData2);
//     // 左下图表3
//     var myChart = echarts.init(document.getElementById('leftBomData3'));
//     var datas3 = {
//         value: parseInt(xyptRatio),
//         company: "%",
//         ringColor: [{
//             offset: 0,
//             color: '#00C4FF' // 0% 处的颜色
//         }, {
//             offset: 1,
//             color: '#00C4FF' // 100% 处的颜色
//         }]
//     }
//     var leftBomData3 = {
//         title: {
//             text: datas3.value + datas3.company,
//             x: 'center',
//             y: 'center',
//             textStyle: {
//                 fontWeight: 'normal',
//                 color: '#fff',
//                 fontSize: '16'
//             },
//             // subtext: '服务占比',
//             // subtextStyle: {
//             //     color: '#fff',
//             //     fontSize: 16
//             // },
//         },
//         color: ['#003766'],
//         legend: {
//             show: false,
//             data: []
//         },
//
//         series: [{
//             name: 'Line 1',
//             type: 'pie',
//             clockWise: true,
//             radius: ['70%', '100%'],
//             itemStyle: {
//                 normal: {
//                     label: {
//                         show: false
//                     },
//                     labelLine: {
//                         show: false
//                     }
//                 }
//             },
//             hoverAnimation: false,
//             data: [{
//                 value: datas3.value,
//                 name: '',
//                 itemStyle: {
//                     normal: {
//                         color: { // 完成的圆环的颜色
//                             colorStops: datas3.ringColor
//                         },
//                         label: {
//                             show: false
//                         },
//                         labelLine: {
//                             show: false
//                         }
//                     }
//                 }
//             }, {
//                 name: '',
//                 value: 100 - datas3.value
//             }]
//         }]
//     };
//     myChart.setOption(leftBomData3);
// }
//
//
// function shareRelation () {
//     const data = {"data":[{"id":"城指中心","name":"城指中心","symbolSize":17.759683406700805,"value":18368733},{"id":"庞各庄镇","name":"庞各庄镇","symbolSize":17.23772024085298,"value":14894496},{"id":"气象局","name":"气象局","symbolSize":15.165185715638962,"value":1099507},{"id":"产促局","name":"产促局","symbolSize":15.055316358670026,"value":368206},{"id":"城管委","name":"城管委","symbolSize":15.01550097808512,"value":103191},{"id":"残联","name":"残联","symbolSize":18.053130641605946,"value":20321946},{"id":"经信局","name":"经信局","symbolSize":50.01550097808512,"value":233066555},{"id":"生态环境局","name":"生态环境局","symbolSize":15.019824232963943,"value":131967},{"id":"财政局","name":"财政局","symbolSize":10.0,"value":null},{"id":"政法委","name":"政法委","symbolSize":10.0,"value":null},{"id":"兴展公司","name":"兴展公司","symbolSize":10.0,"value":null},{"id":"政务服务局","name":"政务服务局","symbolSize":10.0,"value":null}],"links":[{"id":"城管委","target":"城管委","source":"城管委"},{"id":"产促局","target":"产促局","source":"产促局"},{"id":"城指中心","target":"经信局","source":"城指中心"},{"id":"产促局","target":"财政局","source":"产促局"},{"id":"城指中心","target":"城管委","source":"城指中心"},{"id":"生态环境局","target":"生态环境局","source":"生态环境局"},{"id":"残联","target":"残联","source":"残联"},{"id":"气象局","target":"政法委","source":"气象局"},{"id":"气象局","target":"气象局","source":"气象局"},{"id":"气象局","target":"城管委","source":"气象局"},{"id":"气象局","target":"城指中心","source":"气象局"},{"id":"庞各庄镇","target":"庞各庄镇","source":"庞各庄镇"},{"id":"经信局","target":"城管委","source":"经信局"},{"id":"经信局","target":"兴展公司","source":"经信局"},{"id":"经信局","target":"经信局","source":"经信局"},{"id":"经信局","target":"政务服务局","source":"经信局"}]}
//
//     // 正中央图表
//     var myChart = echarts.init(document.getElementById('centerCen'));
//     var colorcenterCen = ['#E6A23C','#04f2a7','#b457ff','#67C23A']
//     centerCen = {
//         color: colorcenterCen,
//         formatter: function(param) {
//             if (param.dataType === 'edge') {
//                 return param.data.target;
//             }
//             return param.data.name;
//         },
//         legend: [],
//         animationDuration: 1500,
//         animationEasingUpdate: 'quinticInOut',
//         series: [
//             {
//                 name: 'Les Miserables',
//                 type: 'graph',
//                 layout: 'circular',
//                 data: data.data,
//                 links: data.links,
//                 roam: true,
//                 label: {                // 关系对象上的标签
//                     normal: {
//                         show: true,                 // 是否显示标签
//                         position: "inside",         // 标签位置:'top''left''right''bottom''inside''insideLeft''insideRight''insideTop''insideBottom''insideTopLeft''insideBottomLeft''insideTopRight''insideBottomRight'
//                         textStyle: {                // 文本样式
//                             fontSize: 14
//                         }
//                     }
//                 },
//                 lineStyle: {
//                     color: 'source',
//                     curveness: 0.6,
//                 },
//                 emphasis: {
//                     focus: 'adjacency',
//                     lineStyle: {
//                         width: 10
//                     }
//                 }
//             }
//         ]
//     };
//     myChart.setOption(centerCen);
// }
//
//
// function exchangeTrend () {
//     const data = [{exchangeSum: '576934',exchangeTaskCnt:'49',operateDate:'2021-02-08'},
//         {exchangeSum: '574797',exchangeTaskCnt:'49',operateDate:'2021-02-09'},
//         {exchangeSum: '657042',exchangeTaskCnt:'66',operateDate:'2021-02-10'},
//         {exchangeSum: '839506',exchangeTaskCnt:'66',operateDate:'2021-02-11'},
//         {exchangeSum: '780046',exchangeTaskCnt:'62',operateDate:'2021-02-12'},
//         {exchangeSum: '870632',exchangeTaskCnt:'66',operateDate:'2021-02-13'},
//         {exchangeSum: '876368',exchangeTaskCnt:'66',operateDate:'2021-02-14'},
//         {exchangeSum: '871442',exchangeTaskCnt:'68',operateDate:'2021-02-15'},
//         {exchangeSum: '870969',exchangeTaskCnt:'66',operateDate:'2021-02-16'},
//         {exchangeSum: '870765',exchangeTaskCnt:'65',operateDate:'2021-02-17'},
//         {exchangeSum: '875464',exchangeTaskCnt:'66',operateDate:'2021-02-18'},
//         {exchangeSum: '875411',exchangeTaskCnt:'66',operateDate:'2021-02-19'},
//         {exchangeSum: '876035',exchangeTaskCnt:'67',operateDate:'2021-02-20'},
//         {exchangeSum: '871663',exchangeTaskCnt:'66',operateDate:'2021-02-21'},
//         {exchangeSum: '877110',exchangeTaskCnt:'70',operateDate:'2021-02-22'},
//         {exchangeSum: '878076',exchangeTaskCnt:'66',operateDate:'2021-02-23'},
//         {exchangeSum: '877033',exchangeTaskCnt:'66',operateDate:'2021-02-24'},
//         {exchangeSum: '876441',exchangeTaskCnt:'68',operateDate:'2021-02-25'},
//         {exchangeSum: '827918',exchangeTaskCnt:'61',operateDate:'2021-02-26'},
//         {exchangeSum: '908767',exchangeTaskCnt:'64',operateDate:'2021-02-27'},
//         {exchangeSum: '781959',exchangeTaskCnt:'57',operateDate:'2021-02-28'},
//         {exchangeSum: '914289',exchangeTaskCnt:'72',operateDate:'2021-03-01'},
//         {exchangeSum: '784491',exchangeTaskCnt:'65',operateDate:'2021-03-02'},
//         {exchangeSum: '916103',exchangeTaskCnt:'68',operateDate:'2021-03-03'},
//         {exchangeSum: '957648',exchangeTaskCnt:'76',operateDate:'2021-03-04'},
//         {exchangeSum: '925391',exchangeTaskCnt:'73',operateDate:'2021-03-05'},
//         {exchangeSum: '1028015',exchangeTaskCnt:'78',operateDate:'2021-03-06'},
//         {exchangeSum: '1038677',exchangeTaskCnt:'69',operateDate:'2021-03-07'},
//         {exchangeSum: '1561583',exchangeTaskCnt:'91',operateDate:'2021-03-08'}]
//
//     let exchangeSum = []
//     let exchangeTaskCnt = []
//     let operateDate = []
//     data.map(item => {
//         exchangeSum.push(parseInt(item.exchangeSum))
//         exchangeTaskCnt.push(parseInt(item.exchangeTaskCnt))
//         operateDate.push(item.operateDate)
//     })
//     // // 中下图表
//     var myChart = echarts.init(document.getElementById('centerBom'));
//     centerBom = {
//         grid: {
//             left: "10%",
//             right: "10%",
//             top: 40,
//             bottom: 30,
//         },
//         tooltip: {
//             trigger: 'axis',
//         },
//         legend: {
//             data: ['共享交换任务数', '共享交换数据量'],
//             textStyle: {
//                 color: "#fff",
//                 fontSize: 12
//             }
//         },
//         xAxis: [
//             {
//                 type: 'category',
//                 axisTick: {
//                     show: false
//                 },
//                 axisLine: {
//                     show: false,
//                     lineStyle: {
//                         color: '#6A989E',
//                     }
//                 },
//                 axisLabel: {
//                     inside: false,
//                     textStyle: {
//                         color: '#fff',// x轴颜色
//                         fontWeight: 'normal',
//                         fontSize: '14',
//                         lineHeight: 22
//                     }
//
//                 },
//                 data: operateDate,
//             },
//             {
//                 type: 'category',
//                 axisLine: {
//                     show: false
//                 },
//                 axisTick: {
//                     show: false
//                 },
//                 axisLabel: {
//                     show: false
//                 },
//                 splitArea: {
//                     show: false
//                 },
//                 splitLine: {
//                     show: false
//                 },
//                 //-----
//                 data: ['1月', '2月', '3月', '4月', '5月', '6月'],
//             },
//         ],
//         yAxis: [
//             {
//                 type: 'value',
//                 axisLine: { //  改变y轴颜色
//                     show: false,
//                     lineStyle: {
//                         color: '#26D9FF'
//                     }
//                 },
//                 axisLabel: { //  改变y轴字体颜色和大小
//                     //formatter: '{value} m³ ', //  给y轴添加单位
//                     textStyle: {
//                         color: "#fff",
//                         fontSize: 12,
//                     },
//                 },
//                 axisTick: {
//                     show: false
//                 },
//                 splitLine: {
//                     show: true,
//                     lineStyle: {
//                         color: ['#315070'],
//                         width: 1,
//                         type: 'solid'
//                     }
//                 },
//             },
//             {
//                 type: 'value',
//                 axisLine: { //  改变y轴颜色
//                     show: false,
//                     lineStyle: {
//                         color: '#26D9FF'
//                     }
//                 },
//                 axisLabel: { //  改变y轴字体颜色和大小
//                     //formatter: '{value} m³ ', //  给y轴添加单位
//                     textStyle: {
//                         color: "rgba(250,250,250,0.6)",
//                         fontSize: 16
//                     },
//                 },
//                 axisTick: {
//                     show: false
//                 },
//                 splitLine: {
//                     show: true,
//                     lineStyle: {
//                         color: ['#315070'],
//                         width: 1,
//                         type: 'solid'
//                     }
//                 },
//             },
//         ],
//         series: [{
//             type: 'line',
//             smooth: true,
//             symbol: 'circle',
//             symbolSize: 4,
//             markPoint: {
//                 symbol: "circle"
//             },
//             name: '共享交换任务数',
//             data: exchangeTaskCnt,
//             itemStyle: {
//                 normal: {
//                     color: "#294E8F",
//                     borderColor: "#3D7EEB",
//                     borderWidth: 2
//                 }
//             },
//             lineStyle: {
//                 normal: {
//                     width: 2,
//                     color: "#327BFA",
//                     shadowColor: "#327BFA",
//                     shadowBlur: 10
//                 }
//             },
//             areaStyle: {
//                 color: {
//                     type: 'linear',
//                     x: 0,
//                     y: 0,
//                     x2: 0,
//                     y2: 1,
//                     colorStops: [{
//                         offset: 0.5,
//                         color: 'rgba(61,126,235,0.5)' // 0% 处的颜色
//                     }, {
//                         offset: 1,
//                         color: 'rgba(61,126,235,0)' // 100% 处的颜色
//                     }],
//                     global: false // 缺省为 false
//                 }
//             }
//         }, {
//             type: 'line',
//             smooth: true,
//             symbol: 'circle',
//             symbolSize: 4,
//             markPoint: {
//                 symbol: "circle"
//             },
//             data: exchangeSum,
//             name: '共享交换数据量',
//             itemStyle: {
//                 normal: {
//                     color: "#0C7E5C",
//                     borderColor: "#17D8A1",
//                     borderWidth: 2
//                 }
//             },
//             lineStyle: {
//                 normal: {
//                     width: 2,
//                     color: "#17D8A1",
//                     shadowColor: "#17D8A1",
//                     shadowBlur: 10
//                 }
//             },
//             areaStyle: {
//                 color: {
//                     type: 'linear',
//                     x: 0,
//                     y: 0,
//                     x2: 0,
//                     y2: 1,
//                     colorStops: [{
//                         offset: 0.5,
//                         color: 'rgba(23,216,161,0.5)' // 0% 处的颜色
//                     }, {
//                         offset: 1,
//                         color: 'rgba(23,216,161,0)' // 100% 处的颜色
//                     }],
//                     global: false // 缺省为 false
//                 }
//             }
//         }]
//     };
//     myChart.setOption(centerBom);
// }
//
// function listToolsServiceVo () {
//     const data = [
//         {moudleNm:'资源目录',num:'2'},
//         {moudleNm:'数据门户',num:'7'},
//         {moudleNm:'数据采集',num:'5'},
//         {moudleNm:'数据治理',num:'3'},
//         {moudleNm:'数据安全管理',num:'2'},
//         {moudleNm:'平台综合管控',num:'4'},
//         {moudleNm:'大数据服务',num:'3'},
//     ]
//     let nums = []
//     let moudleNms = []
//     data.map(item => {
//         nums.push(parseInt(item.num))
//         moudleNms.push(item.moudleNm)
//     })
//     let lists =  moudleNms.map(it => {
//         return {name: it , max: 10}
//     })
//     var moudleNmsMax =[];//背景按最大值
//     for (let i = 0; i < nums.length; i++) {
//         moudleNmsMax.push(nums[0])
//     }
//     // 获取最大值
//     const maxNumber = nums.reduce((num1, num2) => {
//         return num1 > num2 ? num1 : num2
//     })
//     const maxValue = parseInt(maxNumber) * 1.2
//     const maxValueList = []
//     data.forEach(i => maxValueList.push(maxValue))
//     // 右上图表
//     var myChart = echarts.init(document.getElementById('rightTop'));
//     rightTop = {
//         tooltip : {
//             show:false,
//             //雷达图的tooltip不会超出div，也可以设置position属性，position定位的tooltip 不会随着鼠标移动而位置变化，不友好
//             confine: true,
//             enterable: true, //鼠标是否可以移动到tooltip区域内
//             formatter: '{a}: {c}'
//         },
//         radar: {
//             // shape: 'circle',
//             shape:'polygon',
//             center: ['50%', '46%'],
//             radius: '48%',
//             splitNumber: 6, // 雷达图圈数设置
//             name: {
//                 textStyle: {
//                     color: '#838D9E',
//                 },
//             },
//             // 设置雷达图中间射线的颜色
//             axisLine: {
//                 lineStyle: {
//                     color: '#5F65A9',
//                 },
//             },
//             indicator: lists,
//             name: {
//                 rich: {
//                     a: {
//                         color: '#009bff',
//                         fontSize:10,
//                         fontFamily:'SourceHanSansSC-Regular',
//                         padding:[0,0,2,0]
//                         // lineHeight: 20
//                     },
//                     b: {
//                         color: '#009bff',
//                         align: 'center',
//                         fontSize:10,
//                         fontFamily:'SourceHanSansSC-Regular',
//                         // padding: 2,
//                         // borderRadius: 4
//                     }
//                 },
//                 formatter: (a,b)=>{
//                     return `{b|}{a|${a}}`
//                 }
//             },
//
//             //雷达图背景的颜色，在这儿随便设置了一个颜色，完全不透明度为0，就实现了透明背景
//             splitArea : {
//                 show : false,
//                 areaStyle : {
//                     color: 'rgba(255,0,0,0)', // 图表背景的颜色
//                 },
//             },
//             splitLine : {
//                 show : true,
//                 lineStyle : {
//                     width : 1,
//                     color : '#5F65A9', // 设置网格的颜色
//                 },
//             },
//         },
//         series: [{
//             name: '诉求类别：', // tooltip中的标题
//             type: 'radar', //表示是雷达图
//             symbol: 'circle', // 拐点的样式，还可以取值'rect','angle'等
//             symbolSize: 0, // 拐点的大小
//
//             areaStyle: {
//                 normal: {
//                     width: 1,
//                     opacity: 0.2,
//                 },
//             },
//             data: [
//                 {
//                     value: nums,
//                     name: '',
//                     // 设置区域边框和区域的颜色
//                     itemStyle: {
//                         normal: {
//                             color: 'rgba(0,155,255,1)',
//                             lineStyle: {
//                                 color: 'rgba(0,155,255,1)',
//                             },
//                         },
//                     }
//
//                 }
//
//             ],
//         }],
//     }
//     myChart.setOption(rightTop);
// }
//
// //词云图
// var text = '政务服务办事,市监局法人查询,统计局中小微企业数据查询,税务局企业数据填报,人保局人保局企业数据填报,民政局60岁以上老人查询,工商局企业数据填报,疫情专班派单数据查询,'
// // 注意：这里的代码只是对上面的句子进行分词并计算权重（重复次数）并构建词云图需要的数据，其中 arr.find 和
// // 	reduce 函数可能在低版本 IE 中无法使用（属于ES6新增的函数），如果不能正常使用（对应的函数有报错），请自行加相应的 Polyfill
// //  array.find 的 ployfill 参见：https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Array/find#Polyfill
// // 	array.reduce 的 ployfill ：https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Array/reduce#Polyfill
// var data = text.split(/[,\. ]+/g)
//     .reduce(function (arr, word) {
//         var obj = arr.find(function (obj) {
//             return obj.name === word;
//         });
//         if (obj) {
//             obj.weight += 1;
//         } else {
//             obj = {
//                 name: word,
//                 weight: 1
//             };
//             arr.push(obj);
//         }
//         return arr;
//     }, []);
// Highcharts.chart('rightBom', {
//     series: [{
//         type: 'wordcloud',
//         data: data,
//         rotation: 0,//字体不旋转
//         maxFontSize: 40,//最大字体
//         minFontSize: 30,//最小字体
//     }],
//     //highcharts logo
//     credits: { enabled: false },
//     //导出
//     exporting: { enabled: false },
//     //提示关闭
//     tooltip: { enabled: false },
//     //颜色配置
//     colors:[
//         '#06b0EE','#1A4FBB','#F49A09','#0494D6',
//         '#864467','#293490','#e25c52'
//     ],
//     title: {
//         text: ''
//     },
//     chart: {
//         type: 'column',
//         backgroundColor: 'rgba(0,0,0,0)'
//     },
// });
//
// // 右下图表
// var myChart = echarts.init(document.getElementById('rightBom'));
// var rightBomdata = [
//     {
//         "name": "美食",
//         "value": 20
//     },
//     {
//         "name": "网红店",
//         "value": 20
//     },
//     {
//         "name": "新冠肺炎疫情表彰大会",
//         "value": 20
//     },
//     {
//         "name": "打卡",
//         "value": 19
//     },
//     {
//         "name": "十一长假",
//         "value": 18
//     },
//     {
//         "name": "自由行",
//         "value": 18
//     },
//     {
//         "name": "教师节",
//         "value": 17
//     },
//     {
//         "name": "旅游景点攻略",
//         "value": 17
//     },
//     {
//         "name": "坡子街",
//         "value": 17
//     },
//     {
//         "name": "体育",
//         "value": 17
//     },
//     {
//         "name": "综艺",
//         "value": 17
//     },
//     {
//         "name": "商场",
//         "value": 17
//     },
//     {
//         "name": "衡山",
//         "value": 16
//     },
//     {
//         "name": "科学家座谈会",
//         "value": 15
//     },
//     {
//         "name": "潇湘晨报",
//         "value": 15
//     },
//     {
//         "name": "张家界",
//         "value": 15
//     },
//     {
//         "name": "橘子洲",
//         "value": 15
//     },
//     {
//         "name": "房价",
//         "value": 15
//     },
//     {
//         "name": "开学",
//         "value": 15
//     },
//     {
//         "name": "湘江",
//         "value": 15
//     },
//     {
//         "name": "繁华",
//         "value": 15
//     },
//     {
//         "name": "湖南公考",
//         "value": 14
//     },
//     {
//         "name": "中秋节",
//         "value": 14
//     },
//     {
//         "name": "天气",
//         "value": 14
//     },
//     {
//         "name": "幸福感",
//         "value": 14
//     },
//     {
//         "name": "芒果台",
//         "value": 13
//     },
//     {
//         "name": "游戏",
//         "value": 13
//     },
//     {
//         "name": "宝藏小店",
//         "value": 13
//     },
//     {
//         "name": "动漫",
//         "value": 13
//     },
//     {
//         "name": "武汉",
//         "value": 13
//     },
//     {
//         "name": "湖南大学",
//         "value": 13
//     },
//     {
//         "name": "湘西",
//         "value": 12
//     }
// ]
// rightBom = {
//     series: [{
//         type: 'wordCloud',
//         gridSize: 7,
//         sizeRange: [15, 40],
//         rotationRange: [-90, 90],
//         // rotationStep: 45,
//         shape: 'circle',
//         width: '100%',
//         height: '100%',
//         drawOutOfBound: false,
//         textStyle: {
//             normal: {
//                 color: function() {
//                     return (
//                         'rgb(' + [
//                             Math.round(Math.random() * 500),
//                             Math.round(Math.random() * 300),
//                             Math.round(Math.random() * 200)
//                         ].join(',') +
//                         ')'
//                     )
//                 }
//             },
//             emphasis: {
//                 shadowBlur: 10,
//                 shadowColor: '#ffffff'
//             }
//         },
//         data: rightBomdata
//     }]
// }
// // myChart.setOption(rightBom);
//
// var vm = new Vue({
//     el: '#services_box',
//     data () {
//         return {
//             countNum: {},
//             shareRatio:{
//                 cyptNum: "31",
//                 cyptRatio: "26.00%",
//                 ssjcNum: "32",
//                 ssjcRatio: "27.00%",
//                 xyptNum: "55",
//                 xyptRatio: "47.00%"
//             },
//             dateEnum: [
//                 {
//                     label: '近7日',
//                     value: 'WEEK',
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
//             dataTopValue: 'ALL',
//         }
//     },
//     computed: {
//         cypyNum () {
//             return this.shareRatio?.cyptNum?.toString().split('') || []
//         },
//         ssjcNum () {
//             return this.shareRatio?.ssjcNum?.toString().split('') || []
//         },
//         xyptNum () {
//             return this.shareRatio?.xyptNum?.toString().split('') || []
//         },
//     },
//     mounted () {
//         // this.queryCount()
//         // this.queryShareRatio().then(data => shareRatis (data))
//         // this.querylistDomainShare()
//         // this.queryshareOrganTop5()
//         // this.querylistToolsServiceVo()
//         // this.queryexchangeTrend()
//         // this.queryshareRelation()
//         shareOrganTop5()
//         listDomainShare()
//         exchangeTrend()
//         listToolsServiceVo()
//         shareRelation()
//         shareRatis()
//     },
//     methods: {
//         queryshareOrganTop5 () {
//             const _taht = this
//             return $.ajax({
//                 type: "get",
//                 data: {
//                     dateEnum: _taht.dataTopValue,
//                 },
//                 url: baseURL + 'doc-collect/dataService/shareOrganTop5',
//                 success: function(data) {
//                     shareOrganTop5(data)
//                 },
//             });
//         },
//         querylistDomainShare () {
//             return $.ajax({
//                 type: "get",
//                 url: baseURL + 'doc-collect/dataService/listDomainShare',
//                 success: function(data) {
//                     const list = data.map(item => {
//                         return {
//                             name: item.gkName,
//                             value: parseInt(item.gkNameRatio),
//                         }
//                     })
//                     listDomainShare(list)
//                 },
//             });
//         },
//         // 重点平台支撑情况
//         queryShareRatio () {
//             const _that = this
//             return $.ajax({
//                 type: "get",
//                 url: baseURL + 'doc-collect/dataService/getTerritoryShareRatio',
//                 // dataType: 'jsonp',
//                 success: function(data) {
//                     _that.$set(_that, 'shareRatio', data || {})
//                     return data
//                 },
//             });
//         },
//         // 共享交换活跃机构、任务、数据 量
//         queryCount () {
//             const _that = this
//             $.ajax({
//                 type: "get",
//                 url: baseURL + 'doc-collect/dataService/shareNum',
//                 // dataType: 'jsonp',
//                 success: function(data) {
//                     _that.$set(_that, 'countNum', data || {})
//                 },
//             });
//         },
//         queryexchangeTrend () {
//             const _that = this
//             return $.ajax({
//                 type: "get",
//                 data: {
//                     dateEnum: _that.dateValue,
//                 },
//                 url: baseURL + 'doc-collect/dataService/exchangeTrend',
//                 // dataType: 'jsonp',
//                 success: function(data) {
//                     exchangeTrend(data)
//                 },
//             });
//         },
//         querylistToolsServiceVo () {
//             return $.ajax({
//                 type: "get",
//                 url: baseURL + 'doc-collect/dataService/listToolsServiceVo',
//                 // dataType: 'jsonp',
//                 success: function(data) {
//                     listToolsServiceVo(data)
//                 },
//             });
//         },
//         queryshareRelation () {
//             return $.ajax({
//                 type: "get",
//                 url: baseURL + 'doc-collect/dataService/shareRelation',
//                 // dataType: 'jsonp',
//                 success: function(data) {
//                     shareRelation(data)
//                 },
//             });
//         },
//         handlerClickItem (item) {
//             this.dateValue = item.value
//             this.queryDataInsertInit().then(data => this.dataInsertInit(data))
//         },
//         handlerTopItem (item) {
//             this.dataTopValue = item.value
//             this.queryOrgTop10().then(data => this.dataInsertTop10(data))
//         },
//         renderSearch (item) {
//             return this.dateValue === item.value
//         },
//         renderTop (item) {
//             return this.dataTopValue === item.value
//         },
//     },
// })
//
