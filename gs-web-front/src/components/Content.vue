<template>
  <div class="content">
    <div class="top-bar">
      <div class="selection-title">
        玩家：
      </div>
      <uid-selection @uid="getUid" ref="uidSelect" class="selection"></uid-selection>
      <web-site-input @update="updateUids"></web-site-input>
    </div>
    <total ref="totalref"></total>
    <div class="chart-item">
      <div class="left-chart">
        <bar-chart ref="charaBarData" id="301"></bar-chart>
      </div>
      <div class="right-chart">
        <pie-chart ref="charaPieData" id="301" @data="getData"></pie-chart>
      </div>
    </div>
    <div class="chart-item">
      <div class="left-chart">
        <bar-chart ref="weaponBarData" id="302"></bar-chart>
      </div>
      <div class="right-chart">
        <pie-chart ref="weaponPieData" id="302" @data="getData"></pie-chart>
      </div>
    </div>
    <div class="chart-item">
      <div class="left-chart">
        <bar-chart ref="standardBarData" id="200"></bar-chart>
      </div>
      <div class="right-chart">
        <pie-chart ref="standardPieData" id="200" @data="getData"></pie-chart>
      </div>
    </div>
  </div>
</template>

<script>
import WebSiteInput from './Website-Input/WebSiteInput'
import UidSelection from './Website-Input/UidSelection'
import BarChart from './Data/BarChart'
import PieChart from './Data/PieChart'
import Total from './Data/Total'
export default {
  name: 'Content',
  components: {PieChart, WebSiteInput, Total, BarChart, UidSelection},
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      uid: 0
    }
  },
  methods: {
    async getUid (uid) {
      this.uid = uid
      this.$refs.charaBarData.getData(uid, 301)
      this.$refs.charaPieData.getData(uid, 301)
      this.$refs.weaponBarData.getData(uid, 302)
      this.$refs.weaponPieData.getData(uid, 302)
      this.$refs.standardBarData.getData(uid, 200)
      this.$refs.standardPieData.getData(uid, 200)
      this.endLoading()
    },
    async getData (data, code) {
      let count = 0
      for (let i = 0; i < data.length; i++) {
        count += data[i].count
      }
      await this.$refs.totalref.getTotal(count, code)
      // this.$refs.totalref.charaDetail(data)
    },
    // 更新uid下拉列表
    updateUids (uid) {
      this.uid = uid
      this.$refs.uidSelect.getUids(uid)
      this.getUid(uid)
    },
    endLoading () {
      this.$loading.service().close()
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.content{
  position: relative;
  width: 100%;
  height: 100%;
}
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
.chart-item{
  position: relative;
  width: 100%;
  height: 250px;
  /*border-top: 2px solid #cccccc;*/
}
  .left-chart{
    float: left;
    width: 55%;
    height: 240px;
  }
  .right-chart{
    float: right;
    width: 40%;
    height: 240px;
  }
.top-bar{
  height: 50px;
  background: rgb(78,164,220);
}
.selection-title{
  float: left;
  padding-left: 3%;
  line-height: 50px;
  height: 50px;
  font-weight: bold;
  color: white;
  font-size: 20px;
}
.selection{
  padding-top: 5px;
  float: left;
}
</style>
