if(top.location != location){
    top.location.href= location.href;
}
//生成菜单
var menuItem = Vue.extend({
    name : 'menu-item',
    props : ['data'],
    methods:{
        getActiveTab:function(){
            for (var index = 0; index<this.data.length; index++) {
                let nd = this.data[index];
                if (nd.url && location.href.indexOf(nd.url) > -1) {
                    return nd.menuId.toString();
                }
                if(nd.list){
                    for(var j = 0; j < nd.list.length; j++){
                        let sn = nd.list[j];
                        if(sn.url && location.href.indexOf(sn.url) > -1){
                            return sn.menuId.toString();
                        }
                    }
                }
            }
            return '';
        },
        toLink:function(url){
            if(url && url.indexOf('http')==0){
                vm.goUrl(url);
            }else{
                top.location.href = '#' + url
            }

        }
    },
    template : '<div>\n' +
        '<el-menu mode="horizontal" :default-active="getActiveTab()" background-color="#1F2E4D" text-color="#FFFFFF" active-text-color="#FFFFFF"> \n' +
        '      <template v-for="item in data" >' +
        '        <el-submenu :index="item.menuId.toString()" v-if="item.list && item.list.length>0" :key="item.menuId" > \n' +
        '          <template slot="title"  style="padding-left:10px" >\n' +
        '            <span slot="title">{{ item.name}}</span>\n' +
        '          </template>\n' +
        '          <menu-item :data="item.list"></menu-item>' +
        '        </el-submenu>\n' +
        '        <el-menu-item v-else :index="item.menuId.toString()" :key="item.menuId"  @click="toLink(item.url)">\n' +
        '          <span>{{item.name}}</span>\n' +
        '        </el-menu-item>\n' +
        '      </template>\n' +
        '</el-menu>' +
        '    </div>'
});

// iframe自适应
$(window).on('resize', function() {
    var $content = $('.content');
    //console.info('resize:' + ($(this).height()))
    $content.height($(this).height() - 50);//143
    // $content.height($(this).height());
    $content.find('iframe').each(function() {
        $(this).height($content.height());
    });
}).resize();
$(function() {
    $("#sidebarMenu").height($("#mainSidebar").height() - 10);
    //设置二级菜单图标为空
});
// 注册菜单组件
Vue.component('menuItem', menuItem);

var vm = new Vue({
    el : '#rrapp',
    data : {
        user : {},
        menuList : {},
        main : "module/fm/project.html",
        password : '',
        newPassword : '',
        navTitle : "项目列表",
        localMenu:[],
        headerMenus: [],
        headerMenus2: [],
    },
    computed: {
        getActiveTab(){
            for (var index = 0; index<this.menuList.length; index++) {
                console.log('===',this.menuList[index].url)
                if (location.href.indexOf(this.menuList[index].url) > -1) {
                    return index;
                }
            }
            return '';
        },
    },
    methods : {
        getMenuList : function(event) {
            $.getJSON("sys/menu/nav?_" + $.now(), function(r) {
                vm.menuList = r.menuList;
            });
        },
        getUser : function() {
            $.getJSON("sys/user/info?_" + $.now(), function(r) {
                vm.user = r.user;
                $.ajax({
                    url: 'http://10.217.17.116:37799/webroot/decision/login',//单点登录的管理平台报表服务器
                    data: {'fine_username': vm.user.username, 'fine_password':'xmgladmin', 'validity': -1},
                    timeout: 5000,//超时时间（单位：毫秒）
                    dataType:"jsonp",//跨域采用jsonp方式
                    jsonp:"callback",
                    success: function (res) {
                        console.log(res);
                        if (res.errorCode) {
                            window.alert(res.errorMsg);
                        }else {
                        }
                    },
                    error: function () {
                        alert("超时或服务器其他错误");// 登录失败（超时或服务器其他错误） 
                    }
                });
            });
        },
        menuHoverOrClick : function(eventVal){
            // this.headerMenus.forEach(headerMenu=>{
            //     headerMenu.actived = false
            // })
            for(var i=0; i<this.headerMenus.length; i++) {
                this.headerMenus[i].actived = false;
            }
            eventVal.actived = !eventVal.actived

        },
        menuClick : function(eventVal){
            console.info('menuClick', eventVal)
            if(eventVal.url&&eventVal.url!=''&&eventVal.outLink=='Y'){
                window.location.href = eventVal.url
            }else{
                this.$emit('changeTaijiMenuList',eventVal.name)
            }
        },
        goUrl: function(url){
            this.main = url
        },
        updatePassword : function() {
            layer.open({
                type : 1,
                skin : 'layui-layer-molv',
                title : "修改密码",
                area : [ '550px', '270px' ],
                shadeClose : false,
                content : jQuery("#passwordLayer"),
                btn : [ '修改', '取消' ],
                btn1 : function(index) {
                    var data = "password=" + vm.password + "&newPassword=" + vm.newPassword;
                    $.ajax({
                        type : "POST",
                        url : "sys/user/password",
                        data : data,
                        dataType : "json",
                        success : function(result) {
                            if (result.code == 0) {
                                layer.close(index);
                                layer.alert('修改成功', function(index) {
                                    location.reload();
                                });
                            } else {
                                layer.alert(result.msg);
                            }
                        }
                    });
                }
            });
        },
        donate : function() {
            layer.open({
                type : 2,
                title : false,
                area : [ '806px', '467px' ],
                closeBtn : 1,
                shadeClose : false,
                content : [ 'http://cdn.dfjx.io/donate.jpg', 'no' ]
            });
        },
        logout: function() {
            vm.$confirm('确定要退出?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                $.ajax({
                    type: "GET",
                    url: "ca/logout/",
                    dataType: "json",
                    success: function(result){
                        if(result.code == 0){//成功
                            Cookies.remove('authList');
                            Cookies.remove('user');
                            parent.location.href ='http://172.26.60.219/zyzx/logout?remPath=/zyzx/portal/index.htm';
                        }else{
                        }
                    }
                });
            }).catch(() => {
            });
        },
        getMenuTop(){
            var me = this;
            // /jx-etl/statics/js/top.json 测试
            $.getJSON("sys/menu/top?" + $.now(), function(taijiMenuResponse) {
                if(taijiMenuResponse&&taijiMenuResponse.code==0){
                    const taijiMenuArray = taijiMenuResponse.data

                    const fullMenuArrayList = JSON.parse(sessionStorage.getItem('menuList') || '[]')

                    const taijiAuthMenuMapTMp = {}

                    function parseTaijiMenu(menuArrayParam){
                        const parseResultMenu = []
                        if(menuArrayParam&&menuArrayParam.length>0){
                            // debugger
                            // menuArrayParam.forEach(taijiMenuData=>{
                            //     const menuCode = taijiMenuData.code
                            //     const menuName = taijiMenuData.name
                            //     const menuUrl = taijiMenuData.url
                            //     const children = taijiMenuData.subMenu
                            //
                            //     taijiAuthMenuMapTMp[menuName] = true
                            //
                            //     parseResultMenu.push({
                            //         id:menuCode,
                            //         name:menuName,
                            //         actived:menuName=='数据处理'?true:false,
                            //         url:menuUrl,
                            //         outLink:me.localMenu[menuName]?'Y':'N',
                            //         children:parseTaijiMenu(children)
                            //     })
                            // })
                            for (var i = 0; i < menuArrayParam.length; i++) {
                                var taijiMenuData = menuArrayParam[i];

                                const menuCode = taijiMenuData.code
                                const menuName = taijiMenuData.name
                                const menuUrl = taijiMenuData.url
                                const children = taijiMenuData.subMenu

                                taijiAuthMenuMapTMp[menuName] = true

                                parseResultMenu.push({
                                    id:menuCode,
                                    name:menuName,
                                    actived:menuName=='数据处理'?true:false,
                                    url:menuUrl,
                                    outLink:me.localMenu[menuName]?'Y':'N',
                                    children:parseTaijiMenu(children)
                                })
                            }
                        }
                        return parseResultMenu
                    }

                    function localMenuFilter(menuList){
                        if(menuList&&menuList.length>0){
                            const resultFilterMenu = new Array()
                            //     menuList.forEach(localMenuTmp=>{
                            //         const localMenuTmpName = localMenuTmp.name
                            //         if(taijiAuthMenuMapTMp[localMenuTmpName]){
                            //         const localMenuTmpParse = JSON.parse(JSON.stringify(localMenuTmp))
                            //         localMenuTmpParse.list=[]
                            //         resultFilterMenu.push(localMenuTmpParse)
                            //         // console.log(localMenuTmp)
                            //         if(localMenuTmp.list&&localMenuTmp.list.length>0){
                            //             localMenuTmpParse.list = localMenuFilter(localMenuTmp.list)
                            //         }
                            //     }
                            // })
                            for (var i = 0; i < menuList.length; i++) {
                                var localMenuTmp = menuList[i];
                                const localMenuTmpName = localMenuTmp.name
                                if (taijiAuthMenuMapTMp[localMenuTmpName]) {
                                    const localMenuTmpParse = JSON.parse(JSON.stringify(localMenuTmp))
                                    localMenuTmpParse.list = []
                                    resultFilterMenu.push(localMenuTmpParse)
                                    // console.log(localMenuTmp)
                                    if (localMenuTmp.list && localMenuTmp.list.length > 0) {
                                        localMenuTmpParse.list = localMenuFilter(localMenuTmp.list)
                                    }
                                }
                            }
                            return resultFilterMenu
                        }
                    }
                    me.headerMenus = parseTaijiMenu(taijiMenuArray)
                    for (var i = 0; i < me.headerMenus.length; i++) {
                        if (me.headerMenus[i].id == '80000' || me.headerMenus[i].id == 'A0000') {
                            me.headerMenus2.push(me.headerMenus[i])
                            me.headerMenus.splice(i, 1)
                        }
                    }
                    console.info('topmenu', me.headerMenus)
                    // console.log(fullMenuArrayList)
                    const localShowMenus = localMenuFilter(fullMenuArrayList)
                    // console.log(localShowMenus)
                    //me.$emit('initTaijiMenuList',localShowMenus)

                }
            });
        }
    },
    watch:{
        main:function(val){
        }
    },
    created : function() {
        this.getMenuList();
        this.getUser();
        //this.getMenuTop();
    },
     
     
    updated : function() {
        // 路由
        if(vm.main.indexOf('http')==0) return;
        var router = new Router();
        routerList(router, vm.menuList);
        router.start();
    }
});

function routerList(router, menuList) {
for ( var key in menuList) {
        var menu = menuList[key];
        if (menu.type == 0) {
            routerList(router, menu.list);
            // if (menu.name == '系统管理') {
            // //thp改 导航菜单初始化展开
            // $(".header").next().removeClass("active");
            // }
        } else if (menu.type == 1) {
            let surl = menu.url.indexOf('http') == 0 ? menu.url : '#' + menu.url
            if(menu.url.indexOf('http')==0){
                continue;
            }
            router.add(surl, function() {
                var url = window.location.hash;
                // 替换iframe的url
                vm.main = url.replace('#', '');

                // 导航菜单展开
                $(".treeview-menu li").removeClass("active");
                $(".sidebar-menu li").removeClass("active");

                $("a[href='" + url + "']").parents("li").addClass("active");

                vm.navTitle = $("a[href='" + url + "']").text();
            });
        }
    }
}
// 首页
function firstPage() {
    window.location.href = "index.html";
}
