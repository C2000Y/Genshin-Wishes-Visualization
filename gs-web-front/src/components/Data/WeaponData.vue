<template>
  <div style="padding: 10px 0 10px 0" v-loading="isLoading" element-loading-background="rgba(255, 255, 255, 0.5)" :element-loading-text="$t('loading_text.text_'+loadingTextValue)">
    <div class="stat">
      <div class="avg-stat-left">
        <!--5星抽卡次数-->
        <div class="text-box">
          {{$t('weapon_data.five_star')}}
          <span class="text-value">
            {{totalGachaCount === null ? $t('loading_text.calculating') : totalGachaCount}}
          </span>
        </div>
      </div>
      <div class="avg-stat-right">
        <!--5星角色抽卡次数-->
        <div class="text-box">
          {{$t('weapon_data.five_star_avg')}}
          <span class="text-value">
            {{avgCharaGachaCount === null ? $t('loading_text.calculating') : avgCharaGachaCount}}
          </span>
        </div>
      </div>
    </div>
    <!--角色列表,显示所有角色以及五星武器抽到的数量，以卡片的形式显示-->
    <CharaList ref="charaList"/>
    <div class="tool-bar">
      <div class="mark-change">
        {{ $t('data_table.show_profile') }}
        <el-switch
          @change = "changeMarkStatus"
          v-model="showMark"
          active-color="#13ce66"
          inactive-color="#ff4949">
        </el-switch>
      </div>
      <!--          <div style="float: right">-->
      <!--            <el-tooltip class="item" effect="dark" content="Top Center 提示文字" placement="top">-->
      <!--              <span>查看详情</span>-->
      <!--            </el-tooltip>-->
      <!--          </div>-->
    </div>
    <div class="chart-item">
      <bar-chart ref="totalBarData" size="30" id="302" style="width: 100%;"></bar-chart>
    </div>
  </div>
</template>

<script>
import BarChart from './BarChart'
import CharaList from '../CharaList/CharaList'
export default {
  name: 'Character',
  components: {BarChart, CharaList},
  props: ['uid'],
  data () {
    return {
      loadingText: ['5', '6', '7'],
      isLoading: false,
      loadingTextValue: '',
      chartData: [],
      avgCharaGachaCount: null,
      showMark: true,
      totalGachaCount: null
    }
  },
  computed: {
    uidChanged () {
      return this.uid
    }
  },
  watch: {
    uidChanged (newV, oldV) {
      // do something
      this.changUid()
      // console.log(this.uid)
    }
  },
  mounted () {
    this.changeInUrl()
  },
  methods: {
    update () {
      this.changeInUrl()
    },
    async changeInUrl () {
      if (this.uid > 0) {
        this.loading()
        await this.getPickUpCountForAll()
        await this.getChartData()
        await this.endLoading()
      }
    },
    async changUid () {
      this.loading()
      await this.getChartData()
      await this.getPickUpCountForAll()
      await this.endLoading()
    },
    // 获得所有以及抽到的角色以及次数
    async getPickUpCountForAll () {
      this.$axios.get('summon/GetPickUps', {
        params: {
          uid: this.uid,
          type: 302
        }
      }).then(res => {
        // console.log(res)
        this.$refs.charaList.getCharaListData(res.data.data)
      })
    },
    // 查所有的角色抽卡数据
    async getChartData () {
      let total = await this.getCharaTotalCount()
      await this.$axios.get('summon/SummonCount', {
        params: {
          uid: this.uid,
          code: 302
        }
      }).then(res => {
        let data = res.data.data
        this.totalGachaCount = data.length
        let count = 0
        let charaCount = 0
        let avgCharCost = 0
        this.chartData = data.map((data) => {
          // 减去上一个抽卡次数的值
          let tmp = data.count - count
          if (data.itemType === '武器') {
            avgCharCost += tmp
            charaCount++
          }
          // 更新 previous
          count = data.count
          return {
            ...data, count: tmp
          }
        })
        let tmp = avgCharCost / charaCount
        charaCount === 0 ? this.avgCharaGachaCount = null : this.avgCharaGachaCount = tmp.toFixed(2)
        total === 0 ? this.avgCharaGachaCount = null : this.avgCharaGachaCount += ' (' + (this.totalGachaCount / total * 100).toFixed(2) + '%)'
        this.$refs.totalBarData.chart(this.chartData, 302, this.showMark)
      })
    },
    // 查所有的角色抽卡数据
    async getCharaTotalCount () {
      let result = 0
      await this.$axios.get('summon/totalCount', {
        params: {
          uid: this.uid
        }
      }).then(res => {
        result = res.data.data.weapon
      })
      return result
    },
    changeMarkStatus () {
      if (this.uid > 0) {
        this.$refs.totalBarData.chart(this.chartData, 1000, this.showMark)
      }
    },
    loading () {
      this.loadingTextValue = this.loadingText[Math.floor(Math.random() * this.loadingText.length)]
      this.isLoading = true
    },
    async endLoading () {
      this.isLoading = false
    },
    reInitChart () {
      this.$refs.charaList.getCharaListData({'': [{}]})
      // this.loadingCount = 0
      // this.lastUpdate = '?'
      // this.tipsForUpdate = '不选中玩家的话，就没法提供数据了哦~'
      this.$refs.totalBarData.chart([])
      this.totalGachaCount = null
      this.avgCharaGachaCount = null
    }
  }
}
</script>

<style scoped>
.chart-item{
  position: relative;
  /*width: 100%;*/
  height: 350px;
  margin-top: 5px;
  padding: 0px 20px 0px 20px;
  /*border-top: 2px solid #cccccc;*/
}
.stat{
  position: relative;
  margin: 0 auto;
  width: 40%;
  height: 50px;
  text-align: left;
}
.avg-stat-left{
  float: left;
}
.avg-stat-right{
  float: right;
}
.text-box {
  font-size: 1rem;
  color: #61b5b0;
  /*font-weight: bold;*/
}
.text-value {
  font-size: 1rem;
  color: #537fb5;
  font-weight: bold;
}
.tool-bar{
  position: relative;
  margin: 0 auto;
  width: 96%;
  height: 20px;
  line-height: 20px;
  color: #537fb5;
}
.mark-change{
  float: left;
}
@media screen and (max-width: 1180px){
  .stat{
    height: unset;
    width: 300px;
  }
  .avg-stat-left{
    float: unset;
  }
  .avg-stat-right{
    float: unset;
  }
}
</style>
