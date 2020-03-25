const svf = {
  _h : 40,
  _attr(x){
    let row = x.row, css = '', ext = ''
    if(row>1){
      let h = row * this._h
      css = ` style="height:${h}px" `
    }
    if(x.align){
      let p = 24 - x.span
      if(x.align=='right'){
        ext = ` :offset="${p}" `
      }else if(x.align=='left'){
        ext = ` :offset="${p}" `
      }
    }
    return `:span="${x.span}"${ext}${css}`
  },
  parseForm(cols){
    let str = ['<el-row class="doc-table">']
    svf.parseAll(cols, str)
    str.push('</el-row>')
    return str.join('')
  },
  parseAll(arr, str){
    for(let i = 0, l = arr.length;i<l; i++){
      str.push(svf.parse(arr[i]))
    }
  },
  parse(x){
    if(!x.type) return ''
    return svf[x.type](x)
  },
  cell(x){
    if(!x.children || x.children.length == 0) return ''
    let res = ['<el-row class="cell">']
    svf.parseAll(x.children, res)
    res.push('</el-row>')
    return res.join('')
  },
  section(x){
    return `<el-col class="section" ${svf._attr(x)}>${x.text}</el-col>`
  },
  label(x){
    return `<el-col class="item" ${svf._attr(x)}>${svf._lab(x)}${svf.cell(x)}</el-col>`
  },
  _lab(x){
    let strlab = x.notext ? '' : `<div class="label">${x.text}</div>`
    return strlab
  },
  item(x, strin){
    return `<el-col class="item" ${svf._attr(x)}>${svf._lab(x)}<div class="value">${strin}</div></el-col>`
  },
  text(x){
    let strlen = x.data_len > 0 ? ' maxlength="' + x.data_len + '"' : ''
    let strin = `<el-input v-model="info.${x.field}" ${strlen}  />`
    return svf.item(x, strin)
  },
  textarea(x){
    let strin = `<el-input type="textarea" v-model="info.${x.field}" :rows="5" />`
    return svf.item(x, strin)
  },
  money(x){
    let strlen = 'maxlength="14"'
    let strin = `<el-input-number v-model="info.${x.field}" ${strlen} :controls="false" :precision="2" :step="1" />`
    return svf.item(x, strin)
  },
  date(x){
    let strin = `<el-date-picker type="date" v-model="info.${x.field}" />`
    return svf.item(x, strin)
  },
  month(x){
    let strin = `<el-date-picker type="month" format="yyyy/MM" v-model="info.${x.field}" />`
    return svf.item(x, strin)
  },
  radio(x){
    let strin = `<template v-for="item in (dic['${x.field}'] || [])"><el-radio v-model="info.${x.field}" :label="item.value">{{item.name}}</el-radio></template>`
    return svf.item(x, strin)
  },
  select(x){
    let strin = `<el-select v-model="info.${x.field}" style="width:100%;"><el-option v-for="item in (dic['${x.field}'] || [])" :key="item.value" :label="item.name" :value="item.value"></el-option></el-select>`
    return svf.item(x, strin)
  },
  tabs(x){
    return `<el-col class="item" ${svf._attr(x)}><el-tabs>${svf.group(x)}</el-tabs></el-col>`
  },
  group(x){
    if(!x.children || x.children.length == 0) return ''
    let res = ['<el-row class="cell">']
    svf.parseAll(x.children, res)
    res.push('</el-row>')
    return res.join('')
  },
}
const svc = {
  cmp:{
    data(){
      return {
        html:'',
        dic:{},
      }
    },
    props: {
      info:Object,
      column:Array,
      nocache:{
        type:Boolean,
        default:true
      }
    },
    render(h) {
      if(this.html.length == 0 || this.nocache)
        this.html = svf.parseForm(this.column)
      const com = Vue.extend({
        template: this.html,
        props: {
          info:Object,
          dic:Object,
        }
      })
      return h(com, {
        props: {
          info: this.info,
          dic:this.dic
        }
      })
    },
    watch:{
      column:function(val){
        if(val){
          this.queryDict(val)
          //this.setDefault(val)
        }
      },
      info:function(val){
        if(val){
          for(let n in val){
            if(this.info[n] == '')
              this.info[n] = val[n]
          }
        }
        //console.log(this.info['ext_44'])
      }
    },
    methods:{
      setDefault(cols){
        let $cmp = this
        let 今天=new Date().format('yyyy-MM-dd')
        cols.filter(c=>{return c.value != null}).forEach(x=>{
          if(x.value && !$cmp.info[x.field]){
            if(x.type=='date'){
              $cmp.info[x.field] = eval('`'+x.value+'`')
            }else{
              $cmp.info[x.field] = x.value
            }
          }
        })
      },
      queryDict(cols){
        let arr = [], tps = [], res = [], $cmp = this
        this.filter(cols, res, (c=>{return c.type=='radio'||c.type=='select'}))
        res.forEach(x=>{
          $cmp.dic[x.field] = []
          tps.push(x.field)
          if(x.source){
            arr.push($.get(baseURL + 'sys/dict/values?type='+x.source))
          }
        })

        $.when.apply(null, arr).then(function(){
          let args = []
          if(arr.length == 1){
            args = [arguments[0]]
          }else{
            for(let i = 0;i< arguments.length;i++){
              args[i] = arguments[i][0]
            }
          }
          for(let i = 0; i < args.length; i++){
            if(isok(args[i]) && args[i].list){
              $cmp.dic[tps[i]] = args[i].list
            }
          }
          $cmp.redraw()
        })
      },
      filter(lst, res, fn){
        lst.forEach(x=>{
          if(fn(x)){
            res.push(x)
          }
          if(x.children){
            this.filter(x.children, res, fn)
          }
        })
      },
      redraw(){
        this.$nextTick(function () {
          this.$forceUpdate();
        })
      }
    }

  }
}
const svh = {
  encode(s){
    return btoa(encodeURIComponent(JSON.stringify(s)))
  },
  decode(s){
    return JSON.parse(decodeURIComponent(atob(s)))
  },
}