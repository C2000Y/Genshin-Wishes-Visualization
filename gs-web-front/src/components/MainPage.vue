<template>
  <div class="content" v-bind:style="{backgroundColor:'rgba('+245+','+245+','+245+','+opacityLevel/100+')'}">
    <div class="tool-bar">
      <Total ref="uidForTotal"/>
      <web-site-input @update="updateUid"></web-site-input>
      <div class="tool-bar-right">
<!--        <div id="switch-mark">-->
<!--          <span>-->
<!--            显示头像：-->
<!--          </span>-->
<!--          <el-switch-->
<!--            @change = "changeMarkStatus"-->
<!--            v-model="showMark"-->
<!--            active-color="#13ce66"-->
<!--            inactive-color="#ff4949">-->
<!--          </el-switch>-->
<!--        </div>-->

<!--        调整透明度-->
<!--        <div id="slider-opacity">-->
<!--          <div>-->
<!--            <el-slider-->
<!--              v-model="opacityLevel"-->
<!--              :step="5"-->
<!--              :max="95"-->
<!--              :format-tooltip="formatTooltip">-->
<!--            </el-slider>-->
<!--          </div>-->
<!--        </div>-->
        <uid-selection
          @uid="getUid"
          ref="uidSelect"
          id="selection"></uid-selection>
      </div>
    </div>
    <div class="data-display-area">
      <el-menu router :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
        <el-menu-item index="/main/default">
          观星台
        </el-menu-item>
        <el-menu-item index="/main/overall">
          全部祈愿
        </el-menu-item>
        <el-menu-item index="/main/character">
          角色祈愿
        </el-menu-item>
        <el-menu-item index="/main/weapon">
          武器祈愿
        </el-menu-item>
        <el-menu-item index="/main/standard">
          常驻祈愿
        </el-menu-item>
      </el-menu>
      <div>
      </div>
<!--      <keep-alive>-->
      <router-view :uid="uid" ref="views"/>
<!--      </keep-alive>-->
    </div>
  </div>
</template>

<script>
import PieChart from './Data/PieChart'
import WebSiteInput from './Website-Input/WebSiteInput'
import Total from './Data/Total'
import BarChart from './Data/BarChart'
import UidSelection from './Website-Input/UidSelection'
import Loading from './Effect/Loading'
import OverallData from './Data/OverallData'

export default {
  name: 'MainPage',
  components: {PieChart, WebSiteInput, Total, BarChart, UidSelection, Loading, OverallData},
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      uid: 0,
      finishCount: 0,
      opacityLevel: 80,
      text: '',
      loadingText: ['让我康康！', '转啊，转~', '♪好运来，祝你好运来♪', '今天的爆率那叫一个高啊.jpg'],
      totalWishes: 0,
      charaWishes: 0,
      charaLeft: 0,
      weaponWishes: 0,
      weaponLeft: 0,
      standardWishes: 0,
      standardLeft: 0,
      showMark: true,
      activeIndex: '/main/overall'
    }
  },
  created () {
    this.activeIndex = this.$route.path
  },
  // mounted () {
  //   // this.$axios.get('file/getImgJson', {params: {}}).then(res => {
  //   //   console.log(res.data.data)
  //   //   let js = JSON.parse(res.data.data)
  //   //   console.log(js)
  //   //   this.$refs.charaList.getCharaListData(res.data.data)
  //   // })
  //   console.log(common.setUrl().then(
  //     console.log
  //   )
  // },
  methods: {
    // 更新uid下拉列表
    updateUid (uid, status) {
      // console.log(uid, status)
      this.uid = uid
      if (status >= 0) {
        this.getUid(uid)
        this.$refs.views.update()
        this.$refs.uidSelect.getUid(uid)
      } else {
        this.$refs.uidSelect.getUid(uid)
      }
    },
    handleSelect (index) {
      console.log(index)
    },
    async getUid (uid) {
      // 显示加载过场
      // this.startLoading()
      this.uid = uid
      this.$refs.uidForTotal.changeUid(uid)
      // this.reInitChart()
      // this.getChartData(uid, 301, this.showMark)
      // this.getChartData(uid, 302, this.showMark)
      // this.getChartData(uid, 200, this.showMark)
      // 结束加载过场
      // await this.endLoading()
    },
    startLoading () {
      this.text = this.loadingText[Math.floor(Math.random() * this.loadingText.length)]
      ELEMENT.Loading.service({ fullscreen: true, text: this.text })
    },
    // 重置所有参数
    reInitChart () {
      this.totalWishes = 0
      this.charaWishes = 0
      this.weaponWishes = 0
      this.standardWishes = 0
      this.finishCount = 0
      this.charaLeft = 0
      this.weaponLeft = 0
      this.standardLeft = 0
      this.$refs.charaBarData.chart([])
      this.$refs.weaponBarData.chart([])
      this.$refs.standardBarData.chart([])
      this.$refs.charaPieData.chart([{itemType: '', rankType: ''}])
      this.$refs.weaponPieData.chart([{itemType: '', rankType: ''}])
      this.$refs.standardPieData.chart([{itemType: '', rankType: ''}])
    },
    formatTooltip (val) {
      return '透明度：' + (100 - val)
    }
  }
}
</script>

<style scoped>
  h1, h2 {
    font-weight: normal;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }

  .content{
    height: 100%;
    max-width: 1920px;
    width: 90%;
    margin-right: auto;
    margin-left: auto;
  }

  .tool-bar{
    position: relative;
    padding: 10px 5px 0 25px;
    height: 52px;
  }

  .tool-bar-right{
    float: right;
    display: flex;
  }
  .tool-bar-right #slider-opacity{
    width: 250px;
    padding-top: 8px;
  }
  .tool-bar-right #slider-opacity div{
    position: relative;
    margin: 0 auto;
    width: 200px;
  }

  .tool-bar-right #switch-mark{
    position: relative;
    padding-top: 13px;
    color: #537fb5;
    font-weight: bold;
    font-size: 1rem;
  }

  .tool-bar-right #selection{
    padding-top: 8px;
    float: right;
  }

  .data-display-area{
    position: relative;
    margin-top: 5px;
    padding: 0 20px 0 20px;
  }
  .data-display-area .el-menu-demo{
    background-color: transparent;
  }
  .data-display-area .el-menu-item{
    color: #537fb5;
    border-radius: 15px 15px 0 0;
    height: 45px;
    line-height: 45px;
    width: 120px;
  }

  .el-menu.el-menu--horizontal{
    border-bottom: unset;
  }
  .el-menu--horizontal>.el-menu-item:not(.is-disabled):focus, .el-menu--horizontal>.el-menu-item:not(.is-disabled):hover, .el-menu--horizontal>.el-submenu .el-submenu__title:hover{
    background-color: rgba(245,245,245,0.5);
    color: #537fb5;
    font-weight: bold;
  }
  .el-menu--horizontal>.el-menu-item.is-active {
    border-bottom: 2px solid rgb(120,230,211);
    color: #537fb5;
    font-weight: bold;
  }

  @media screen and (max-width: 1920px){
    .content{
      height: 100%;
      max-width: 1330px;
      width: 90%;
      min-width: 1200px;
    }
  }
  @media screen and (max-width: 1200px){
    .content{
      width: 100%;
      min-width: unset;
    }
    .tool-bar{
      padding: 5px 5px 0 5px;
    }
  }
  @media screen and (max-width: 790px){
    .content{
      width: 100%;
      min-width: unset;
    }
    .tool-bar{
      height: 100px;
      padding: 5px 5px 0 5px;
    }
  }
  @media screen and (max-width: 640px){
    .data-display-area{
      padding: unset;
    }
    .el-menu-item{
      width: 90px !important;
    }
  }
  @media screen and (max-width: 450px){
    .tool-bar{
      height: 80px;
    }
    .el-menu-item{
      width: 70px !important;
      padding: unset;
    }
  }
@media screen and (max-width: 380px){
  .el-menu-item{
    width: 65px !important;
    padding: unset;
  }
}
</style>

<style>

</style>
