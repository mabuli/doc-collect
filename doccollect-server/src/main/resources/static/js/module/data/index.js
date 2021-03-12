var vm = new Vue({
    el: '#statistics',
    data: {
        showFlag: true,
    },
    computed: {
        currentTime () {
            return this.time()
        },
        currentDay () {
            const dayList = ['天', '一', '二', '三', '四', '五', '六']
            const date = new Date()
            return '星期' + dayList[date.getDay()]
        },
    },
    methods: {
        tiggerMenu (flag) {
            this.showFlag = Boolean(flag)
        },
        time () {
            const date = new Date()
            let y = date.getFullYear()
            let M = this.checkTime(date.getMonth() + 1)
            let d = this.checkTime(date.getDate())
            let h = this.checkTime(date.getHours())
            let m = this.checkTime(date.getMinutes())
            let s = this.checkTime(date.getSeconds())
            return `${y}-${M}-${d} ${h}:${m}:${s}`
        },
        checkTime (key) {
            if (key.toString().length === 1) {
                return `0${key}`
            } else {
                return key
            }
        },
        layout () {
            window.opener=null;
            window.open('','_self');
            window.close();
        },
    },
})