<template>
    <div style="padding: 10px 0 10px 0">
      <div class="last-update-text-box">
        上次更新在
        <span class="last-update-text">
          {{lastUpdate}}
        </span>
        ，{{tipsForUpdate}}
      </div>
      <!--TODO： 显示所有角色以及五星武器抽到的数量，以卡片的形式显示，后端已经有了，前端还没做-->
      <div>
      </div>
      <!--5星平均抽卡次数-->
      <div class="text-box">
        五星平均出货次数：
        <span class="text-value">
          {{avgGachaCount}}
        </span>
      </div>
      <!--5星抽卡次数-->
      <div class="text-box">
        五星出货次数：
        <span class="text-value">
          {{totalGachaCount}}
        </span>
      </div>
      <!--角色列表-->
      <CharaList ref="charaList"/>
      <div class="tool-bar">
        <div class="mark-change">
          显示头像：
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
        <bar-chart ref="totalBarData" size="35" id="1000" style="width: 100%;"></bar-chart>
      </div>
    <!--TODO：历史最欧（两发内最少出货数）-->
    <!--TODO：历史最非（两发内最少出货数）-->
    <!--TODO：近6个月最欧（两发内最少出货数）-->
    <!--TODO：近6个月最非（两发内最少出货数）-->
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
      loadingText: ['查询非酋程度...', '你有过十连双黄吗？我有。', '今天的爆率那叫一个高啊.jpg'],
      updateTips: ['建议上传数据后再来', '哦？最近又抽到什么了', '有段时间没上传了，来看看最近运气如何？', '你知道吗？原神官方的抽卡数据只保留6个月哦。', '建议赶紧更新！！'],
      loadingCount: 0,
      showMark: true,
      chartData: [{}],
      lastUpdate: '不知道多少',
      tipsForUpdate: '不选中玩家的话，就没法提供数据了哦~',
      avgGachaCount: '计算中~',
      totalGachaCount: '计算中~'
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
      this.getPickUpCountForAll()
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
        let avgCost = 0
        let count = {'301': 0, '302': 0, '200': 0}
        // 简单算法优化查询结果
        this.chartData = data.map((data) => {
          // 减去上一个抽卡次数的值
          let tmp = data.count - count[data.type]
          // 取平均值的分子
          avgCost += tmp
          // 更新 previous
          count[data.type] = data.count
          return {
            ...data, count: tmp
          }
        })
        this.totalGachaCount = res.data.data.length
        this.avgGachaCount = Math.round(avgCost / res.data.data.length * 100) / 100
        // console.log(this.avgGachaCount)
        // console.log(this.chartData)
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
            this.lastUpdate = '刚刚'
            this.tipsForUpdate = this.updateTips[1]
          } else if (this.lastUpdate < 31) {
            this.tipsForUpdate = this.updateTips[1]
          } else if (this.lastUpdate < 90) {
            this.tipsForUpdate = this.updateTips[2]
          } else if (this.lastUpdate < 180) {
            this.tipsForUpdate = this.updateTips[3]
          } else {
            this.tipsForUpdate = this.updateTips[4]
          }
          if (this.lastUpdate !== '刚刚') {
            this.lastUpdate += '天前'
          }
        } else {
          this.lastUpdate = '不知道多少'
          this.tipsForUpdate = this.updateTips[0]
        }
      })
    },
    // 获得所有以及抽到的角色以及次数
    getPickUpCountForAll () {
      this.$axios.get('summon/TotalPickUpCount', {
        params: {
          uid: this.uid
        }
      }).then(res => {
        console.log(res)
        this.$refs.charaList.getCharaListData(res.data.data)
      })
    },
    loading () {
      this.text = this.loadingText[Math.floor(Math.random() * this.loadingText.length)]
      ELEMENT.Loading.service({ fullscreen: true, text: this.text })
    },
    async endLoading () {
      while (this.loadingCount < 1) {
        // this.finishCount++
        // console.log(this.loadingCount)
        await new Promise(resolve => setTimeout(resolve, 400))
      }
      await new Promise(resolve => setTimeout(resolve, 400))
      ELEMENT.Loading.service().close()
    },
    reInitChart () {
      this.loadingCount = 0
      this.lastUpdate = '?'
      this.tipsForUpdate = '不选中玩家的话，就没法提供数据了哦~'
      this.$refs.totalBarData.chart([])
      this.avgGachaCount = '计算中~'
      this.totalGachaCount = '计算中~'
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
    font-weight: bold;
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
    height: 280px;
    margin-top: 5px;
    padding: 0px 20px 0px 20px;
    /*border-top: 2px solid #cccccc;*/
  }
  .text-box {
    font-size: 1rem;
    color: #61b5b0;
    font-weight: bold;
  }
  .text-value {
    font-size: 1rem;
    color: #537fb5;
    font-weight: bold;
  }
</style>
