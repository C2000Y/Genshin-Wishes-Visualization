<template>
    <div v-loading="isLoading" element-loading-background="rgba(255, 255, 255, 0.5)" :element-loading-text="$t('loading_text.text_'+loadingTextValue)">
      <div class="stat-box">
        <div class="last-update-text-box">
          {{ $t('overall_data.last_update') }}
          <span class="last-update-text">
          {{lastUpdate === '' ? $t('overall_data.last_update_default') : lastUpdate !== 0 ? lastUpdate + ' ' + $t('overall_data.days_ago') : $t('overall_data.update_tips.tip_0')}}
        </span>
          {{tipsForUpdate === '' ? $t('overall_data.tips_for_update') : $t('overall_data.update_tips.tip_'+tipsForUpdate)}}
        </div>
        <div class="stat">
          <div class="avg-stat-left">
            <!--5星平均抽卡次数-->
            <div class="text-box">
              {{ $t('overall_data.five_star_avg_count') }}
              <span class="text-value">
              {{avgGachaCount === null ? $t('loading_text.calculating') : avgGachaCount}}
            </span>
            </div>
            <!--5星抽卡次数-->
            <div class="text-box">
              {{ $t('overall_data.five_star_count') }}
              <span class="text-value">
              {{totalGachaCount === null ? $t('loading_text.calculating') : totalGachaCount}}
            </span>
            </div>
          </div>
          <div class="avg-stat-right">
            <!--5星角色抽卡次数-->
            <div class="text-box">
              {{ $t('overall_data.five_star_character_avg_count') }}
              <span class="text-value">
              {{avgCharaGachaCount === null ? $t('loading_text.calculating') : avgCharaGachaCount}}
            </span>
            </div>
            <!--5星武器抽卡次数-->
            <div class="text-box">
              {{ $t('overall_data.five_star_weapon_avg_count') }}
              <span class="text-value">
                {{avgWeaponGachaCount === null ? $t('loading_text.calculating') : avgWeaponGachaCount}}
              </span>
            </div>
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
        <bar-chart ref="totalBarData" :size=30 id="1000" style="width: 100%;"></bar-chart>
      </div>
    <!--TODO：历史最欧（两发内最少出货数）-->
    <!--TODO：历史最非（两发内最少出货数）-->
    <!--TODO：近6个月最欧（两发内最少出货数）-->
    <!--TODO：近6个月最非（两发内最少出货数）-->
    <!--TODO：武器出货平均次数-->
    </div>
</template>

<script>
import BarChart from './BarChart'
import CharaList from '../CharaList/CharaList'
export default {
  name: 'OverallData',
  props: ['uid'],
  components: {BarChart, CharaList},
  data () {
    return {
      loadingText: [
      ],
      loadingCount: 0,
      showMark: true,
      isLoading: false,
      chartData: [{}],
      lastUpdate: '',
      tipsForUpdate: '',
      avgGachaCount: null,
      avgCharaGachaCount: null,
      avgWeaponGachaCount: null,
      totalGachaCount: null,
      loadingTextValue: '',
      iconSize: 30
    }
  },
  created () {
    this.loadingText = ['5', '6', '7']
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
        await this.getChartData()
        await this.getLastUpdate()
        await this.getPickUpCountForAll()
        await this.endLoading()
      }
    },
    // 切换玩家
    async changUid () {
      // 显示加载过场
      this.loading()
      // await this.$refs.totalref.uidChanged(uid)
      // 初始化图表
      this.reInitChart()
      this.getLastUpdate()
      await this.getPickUpCountForAll()
      await this.getChartData()
      // this.getChartData(this.uid, 302, this.showMark)
      // this.getChartData(this.uid, 200, this.showMark)
      // 结束加载过场
      await this.endLoading()
    },
    /**
     * 触发条件：关闭或打开头像选择
     * 结果：重新录数据
     */
    changeMarkStatus () {
      if (this.uid > 0) {
        this.$refs.totalBarData.chart(this.chartData, 1000, this.showMark)
      }
    },
    // 查所有的抽卡数据
    async getChartData () {
      await this.$axios.get('summon/TotalSummonCount', {
        params: {
          uid: this.uid
        }
      }).then(res => {
        this.loadingCount++
        let data = res.data.data
        // console.log(data)
        let avgCharCost = 0
        let avgWeaponCost = 0
        let avgCost = 0
        let charaCount = 0
        let WeaponCount = 0
        let count = {'301': 0, '302': 0, '200': 0}
        // 简单算法优化查询结果
        this.chartData = data.map((data) => {
          // 减去上一个抽卡次数的值
          let tmp = data.count - count[data.type]
          // 取平均值的分子
          avgCost += tmp
          if (data.itemType === '角色') {
            avgCharCost += tmp
            charaCount++
          } else {
            avgWeaponCost += tmp
            WeaponCount++
          }
          // 更新 previous
          count[data.type] = data.count
          return {
            ...data, count: tmp
          }
        })
        this.totalGachaCount = res.data.data.length
        // avg all
        let tmp = avgCost / res.data.data.length
        this.avgGachaCount = tmp.toFixed(2)
        // avg Character
        tmp = avgCharCost / charaCount
        charaCount === 0 ? this.avgCharaGachaCount = null : this.avgCharaGachaCount = tmp.toFixed(2) + ' (' + charaCount + ')'
        // avg Weapon
        tmp = avgWeaponCost / WeaponCount
        WeaponCount === 0 ? this.avgWeaponGachaCount = null : this.avgWeaponGachaCount = tmp.toFixed(2) + ' (' + WeaponCount + ')'
        this.$refs.totalBarData.chart(this.chartData, 1000, this.showMark)
      })
    },
    /**
     * 用户距离上次更新的天数
     */
    getLastUpdate () {
      this.$axios.get('uid/getLastDate', {
        params: {
          uid: this.uid
        }
      }).then(res => {
        // console.log(res)
        if (res.data.code === 0) {
          let data = res.data.data
          this.lastUpdate = parseInt(data)
          // console.log(this.lastUpdate < 31)
          if (this.lastUpdate === 0) {
            this.lastUpdate = 0
            this.tipsForUpdate = 1
          } else if (this.lastUpdate < 31) {
            this.tipsForUpdate = 2
          } else if (this.lastUpdate < 90) {
            this.tipsForUpdate = 3
          } else if (this.lastUpdate < 180) {
            this.tipsForUpdate = 4
          } else {
            this.tipsForUpdate = 5
          }
        } else {
          this.lastUpdate = ''
          this.tipsForUpdate = 0
        }
      })
    },
    // 获得所有以及抽到的角色以及次数
    async getPickUpCountForAll () {
      this.$axios.get('summon/GetPickUps', {
        params: {
          uid: this.uid,
          type: 0
        }
      }).then(res => {
        // console.log(res)
        this.$refs.charaList.getCharaListData(res.data.data)
      })
    },
    loading () {
      this.loadingTextValue = this.loadingText[Math.floor(Math.random() * this.loadingText.length)]
      this.isLoading = true
      // ELEMENT.Loading.service({ fullscreen: true, text: this.text })
    },
    async endLoading () {
      while (this.loadingCount < 1) {
        // this.finishCount++
        // console.log(this.loadingCount)
        await new Promise(resolve => setTimeout(resolve, 400))
      }
      await new Promise(resolve => setTimeout(resolve, 400))
      // ELEMENT.Loading.service().close()
      this.isLoading = false
    },
    reInitChart () {
      this.$refs.charaList.getCharaListData({'': [{}]})
      this.loadingCount = 0
      this.lastUpdate = '?'
      this.tipsForUpdate = ''
      this.$refs.totalBarData.chart([])
      this.avgGachaCount = ''
      this.totalGachaCount = null
      this.avgCharaGachaCount = null
      this.avgWeaponGachaCount = null
    }
  }
}
</script>

<style scoped>
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
  .last-update-text-box{
    font-size: 1.2rem;
    color: #61b5b0;
    height: 30px;
    line-height: 30px;
  }
  .last-update-text{
    font-size: 1.5rem;
    color: #537fb5;
  }
  .chart-item{
    position: relative;
    /*width: 100%;*/
    height: 350px;
    margin-top: 5px;
    padding: 0px 20px 0px 20px;
    /*border-top: 2px solid #cccccc;*/
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
  @media screen and (max-width: 600px){
    .last-update-text-box{
      font-size: 1rem;
      height: 60px;
    }
    .stat{
      width: 260px;
    }
  }
</style>
