<!--<template>-->
<!--  <div class="content" v-bind:style="{backgroundColor:'rgba('+245+','+245+','+245+','+opacityLevel/100+')'}">-->
<!--    <div class="top-bar">-->
<!--      &lt;!&ndash;<div class="selection-title">&ndash;&gt;-->
<!--        &lt;!&ndash;玩家：&ndash;&gt;-->
<!--      &lt;!&ndash;</div>&ndash;&gt;-->
<!--      <Total :total="totalWishes" :chara="charaWishes" :charaLeft="charaLeft" :weapon="weaponWishes" :weaponLeft="weaponLeft" :stand="standardWishes" :standLeft="standardLeft"/>-->
<!--      <web-site-input @update="updateUids"></web-site-input>-->
<!--      <uid-selection @uid="getUid" ref="uidSelect" class="selection"></uid-selection>-->
<!--      <div class="slider-opacity">-->
<!--        <div>-->
<!--          <el-slider-->
<!--            v-model="opacityLevel"-->
<!--            :step="5"-->
<!--            :max="95"-->
<!--            :format-tooltip="formatTooltip">-->
<!--          </el-slider>-->
<!--        </div>-->
<!--      </div>-->
<!--      <div class="switch-mark">-->
<!--        <span>-->
<!--          显示头像：-->
<!--        </span>-->
<!--        <el-switch-->
<!--          @change = "changeMarkStatus"-->
<!--          v-model="showMark"-->
<!--          active-color="#13ce66"-->
<!--          inactive-color="#ff4949">-->
<!--        </el-switch>-->
<!--      </div>-->
<!--    </div>-->
<!--    <div class="chart-item">-->
<!--      <div class="left-chart">-->
<!--        <bar-chart ref="charaBarData" id="301" style="width: 100%"></bar-chart>-->
<!--      </div>-->
<!--      <div class="right-chart">-->
<!--        <pie-chart ref="charaPieData" id="301"></pie-chart>-->
<!--      </div>-->
<!--    </div>-->
<!--    <div class="chart-item">-->
<!--      <div class="left-chart">-->
<!--        <bar-chart ref="weaponBarData" id="302"></bar-chart>-->
<!--      </div>-->
<!--      <div class="right-chart">-->
<!--        <pie-chart ref="weaponPieData" id="302"></pie-chart>-->
<!--      </div>-->
<!--    </div>-->
<!--    <div class="chart-item">-->
<!--      <div class="left-chart">-->
<!--        <bar-chart ref="standardBarData" id="200"></bar-chart>-->
<!--      </div>-->
<!--      <div class="right-chart">-->
<!--        <pie-chart ref="standardPieData" id="200"></pie-chart>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import WebSiteInput from './Website-Input/WebSiteInput'-->
<!--import UidSelection from './Website-Input/UidSelection'-->
<!--import BarChart from './Data/BarChart'-->
<!--import PieChart from './Data/PieChart'-->
<!--import Total from './Data/Total'-->
<!--import Loading from './Effect/Loading'-->
<!--export default {-->
<!--  name: 'Content',-->
<!--  components: {PieChart, WebSiteInput, Total, BarChart, UidSelection, Loading},-->
<!--  data () {-->
<!--    return {-->
<!--      msg: 'Welcome to Your Vue.js App',-->
<!--      uid: 0,-->
<!--      finishCount: 0,-->
<!--      opacityLevel: 80,-->
<!--      text: '',-->
<!--      loadingText: ['让我康康！', '转啊，转~', '♪好运来，祝你好运来♪', '今天的爆率那叫一个高啊.jpg'],-->
<!--      totalWishes: 0,-->
<!--      charaWishes: 0,-->
<!--      charaLeft: 0,-->
<!--      weaponWishes: 0,-->
<!--      weaponLeft: 0,-->
<!--      standardWishes: 0,-->
<!--      standardLeft: 0,-->
<!--      showMark: true-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    /**-->
<!--     * @param: uid-->
<!--     * @desc: 由UidSelection触发，当uid更新-->
<!--      */-->
<!--    changeMarkStatus () {-->
<!--      if (this.uid > 0) {-->
<!--        this.getUid(this.uid)-->
<!--      }-->
<!--    },-->
<!--    async getUid (uid) {-->
<!--      // 显示加载过场-->
<!--      this.startLoading()-->
<!--      this.uid = uid-->
<!--      // await this.$refs.totalref.uidChanged(uid)-->
<!--      // 初始化图表-->
<!--      this.reInitChart()-->
<!--      this.getChartData(uid, 301, this.showMark)-->
<!--      this.getChartData(uid, 302, this.showMark)-->
<!--      this.getChartData(uid, 200, this.showMark)-->
<!--      // 结束加载过场-->
<!--      await this.endLoading()-->
<!--    },-->
<!--    /**-->
<!--     * @param: uid, code(池子的代码,200标配池，301角色池，302武器池)-->
<!--     * @desc: 由getUid触发，获取特定uid下某个池子的信息-->
<!--     */-->
<!--    async getChartData (uid, code, showMark) {-->
<!--      let count = []-->
<!--      let wishes = 0-->
<!--      let left = 0-->
<!--      this.uid = uid-->
<!--      this.code = code-->
<!--      await this.$axios.get('summon/SummonCount', {-->
<!--        params: {-->
<!--          uid: uid,-->
<!--          code: code-->
<!--        }-->
<!--      }).then(res => {-->
<!--        this.finishCount += 1-->
<!--        console.log(this.finishCount)-->
<!--        const data = res.data.data-->
<!--        // 抽到最后一个五星的总计数量-->
<!--        if (data.length > 0) {-->
<!--          count[0] = {count: data[0].count, name: data[0].name}-->
<!--          left = data[data.length - 1].count-->
<!--          for (let i = data.length - 1; i > 0; i&#45;&#45;) {-->
<!--            count[i] = {count: data[i].count - data[i - 1].count, name: data[i].name}-->
<!--          }-->
<!--        }-->
<!--        switch (code) {-->
<!--          case 301: {-->
<!--            this.$refs.charaBarData.chart(count, code, showMark)-->
<!--            break-->
<!--          }-->
<!--          case 302: {-->
<!--            this.$refs.weaponBarData.chart(count, code, showMark)-->
<!--            break-->
<!--          }-->
<!--          case 200: {-->
<!--            this.$refs.standardBarData.chart(count, code, showMark)-->
<!--            break-->
<!--          }-->
<!--        }-->
<!--      })-->
<!--      await this.$axios.get('/summon/rankType', {-->
<!--        params: {-->
<!--          uid: uid,-->
<!--          csde: code-->
<!--        }-->
<!--      }).then(res => {-->
<!--        let data = res.data.data-->
<!--        for (let i = 0; i < data.length; i++) {-->
<!--          wishes += data[i].count-->
<!--        }-->
<!--        this.finishCount += 1-->
<!--        switch (code) {-->
<!--          case 301: {-->
<!--            this.$refs.charaPieData.chart(data)-->
<!--            this.charaWishes = wishes-->
<!--            this.charaLeft = 90 - wishes + left-->
<!--            break-->
<!--          }-->
<!--          case 302: {-->
<!--            this.$refs.weaponPieData.chart(data)-->
<!--            this.weaponWishes = wishes-->
<!--            this.weaponLeft = 80 - wishes + left-->
<!--            break-->
<!--          }-->
<!--          case 200: {-->
<!--            this.$refs.standardPieData.chart(data)-->
<!--            this.standardWishes = wishes-->
<!--            this.standardLeft = 90 - wishes + left-->
<!--            break-->
<!--          }-->
<!--        }-->
<!--      })-->
<!--    },-->
<!--    // 更新uid下拉列表-->
<!--    updateUids (uid, status) {-->
<!--      this.uid = uid-->
<!--      if (status >= 0) {-->
<!--        this.getUid(uid)-->
<!--      } else {-->
<!--        this.$refs.uidSelect.getUids(uid)-->
<!--      }-->
<!--    },-->
<!--    async endLoading () {-->
<!--      while (this.finishCount <= 5) {-->
<!--        // this.finishCount++-->
<!--        console.log(this.finishCount)-->
<!--        await new Promise(resolve => setTimeout(resolve, 400))-->
<!--      }-->
<!--      await new Promise(resolve => setTimeout(resolve, 400))-->
<!--      this.totalWishes = this.charaWishes + this.weaponWishes + this.standardWishes-->
<!--      ELEMENT.Loading.service().close()-->
<!--    },-->
<!--    startLoading () {-->
<!--      this.text = this.loadingText[Math.floor(Math.random() * this.loadingText.length)]-->
<!--      ELEMENT.Loading.service({ fullscreen: true, text: this.text })-->
<!--    },-->
<!--    reInitChart () {-->
<!--      this.totalWishes = 0-->
<!--      this.charaWishes = 0-->
<!--      this.weaponWishes = 0-->
<!--      this.standardWishes = 0-->
<!--      this.finishCount = 0-->
<!--      this.charaLeft = 0-->
<!--      this.weaponLeft = 0-->
<!--      this.standardLeft = 0-->
<!--      this.$refs.charaBarData.chart([])-->
<!--      this.$refs.weaponBarData.chart([])-->
<!--      this.$refs.standardBarData.chart([])-->
<!--      this.$refs.charaPieData.chart([{itemType: '', rankType: ''}])-->
<!--      this.$refs.weaponPieData.chart([{itemType: '', rankType: ''}])-->
<!--      this.$refs.standardPieData.chart([{itemType: '', rankType: ''}])-->
<!--    },-->
<!--    formatTooltip (val) {-->
<!--      return '透明度：' + (100 - val)-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--&lt;!&ndash; Add "scoped" attribute to limit CSS to this component only &ndash;&gt;-->
<!--<style scoped>-->
<!--.content{-->
<!--  /*position: relative;*/-->
<!--  /*width: 100%;*/-->
<!--  /*background: rgba(245,245,245,0.8);*/-->
<!--  height: 100%;-->
<!--  max-width: 1330px;-->
<!--  width: 90%;-->
<!--  min-width: 1200px;-->
<!--  margin-right: auto;-->
<!--  margin-left: auto;-->
<!--  /*-webkit-backdrop-filter: blur(10px);*/-->
<!--}-->
<!--h1, h2 {-->
<!--  font-weight: normal;-->
<!--}-->
<!--ul {-->
<!--  list-style-type: none;-->
<!--  padding: 0;-->
<!--}-->
<!--li {-->
<!--  display: inline-block;-->
<!--  margin: 0 10px;-->
<!--}-->
<!--a {-->
<!--  color: #42b983;-->
<!--}-->
<!--.chart-item{-->
<!--  position: relative;-->
<!--  /*width: 100%;*/-->
<!--  height: 265px;-->
<!--  margin-top: 5px;-->
<!--  padding: 0px 20px 0px 20px;-->
<!--  /*border-top: 2px solid #cccccc;*/-->
<!--}-->
<!--  .left-chart{-->
<!--    float: left;-->
<!--    width: 55%;-->
<!--    height: 240px;-->
<!--  }-->
<!--.right-chart{-->
<!--  float: right;-->
<!--  width: 40%;-->
<!--  height: 240px;-->
<!--}-->
<!--.top-bar{-->
<!--  padding: 10px 5px 0px 25px;-->
<!--  height: 52px;-->
<!--  /*background: rgb(78,164,220);*/-->
<!--}-->
<!--@media screen and (max-width: 1200px){-->
<!--  .content{-->
<!--    width: 100%;-->
<!--    min-width: unset;-->
<!--  }-->
<!--  .left-chart{-->
<!--    float: unset;-->
<!--    width: 100%;-->
<!--  }-->
<!--  .right-chart{-->
<!--    float: unset;-->
<!--    width: 100%;-->
<!--  }-->
<!--  .chart-item{-->
<!--    height: unset;-->
<!--  }-->
<!--  .top-bar{-->
<!--    height: 110px;-->
<!--    padding: 5px 5px 0px 5px;-->
<!--  }-->
<!--}-->

<!--  .selection-title{-->
<!--    float: left;-->
<!--    padding-left: 3%;-->
<!--    line-height: 50px;-->
<!--    height: 50px;-->
<!--    font-weight: bold;-->
<!--    color: white;-->
<!--    font-size: 20px;-->
<!--  }-->
<!--  .selection{-->
<!--    padding-top: 8px;-->
<!--    float: right;-->
<!--  }-->
<!--  .records{-->
<!--    float: left;-->
<!--    line-height: 55px;-->
<!--  }-->
<!--  .slider-opacity{-->
<!--    float: right;-->
<!--    width: 250px;-->
<!--    padding-top: 8px;-->
<!--  }-->
<!--  .slider-opacity div{-->
<!--    position: relative;-->
<!--    margin: 0 auto;-->
<!--    width: 200px;-->
<!--  }-->
<!--  .switch-mark{-->
<!--    position: relative;-->
<!--    float: right;-->
<!--    padding-top: 13px;-->
<!--    color: #537fb5;-->
<!--    font-weight: bold;-->
<!--    font-size: 1rem;-->
<!--  }-->
<!--</style>-->
