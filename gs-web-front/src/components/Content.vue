<template>
  <div class="hello">
    <web-site-input @update="updateUids"></web-site-input>
    <uid-selection @uid="getUid" ref="uidSelect"></uid-selection>
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
  components: {PieChart, WebSiteInput, UidSelection, Total, BarChart},
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
      await this.$refs.totalref.getTotal(uid)
    },
    getData (data) {
      // this.$refs.totalref.charaDetail(data)
    },
    // 更新uid下拉列表
    updateUids (uid) {
      this.uid = uid
      this.$refs.uidSelect.getUids(uid)
      this.getUid(uid)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.hello{
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
  border-top: 2px solid #cccccc;
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
</style>
