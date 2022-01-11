<template>
  <div :id="chartName" style="width: 100%; height: 100%">
  </div>
</template>

<script>
export default {
  name: 'BarChart',
  props: ['id'],
  data () {
    return {
      data: [],
      uid: undefined,
      code: undefined,
      text: '',
      chartName: 'barChart'
    }
  },
  mounted () {
    this.chartName += this.id
  },
  methods: {
    getData (uid, code) {
      this.uid = uid
      this.code = code
      this.$axios.get('summon/SummonCount', {
        params: {
          uid: uid,
          code: code
        }
      }).then(res => {
        this.data = res.data.data
        for (let i = this.data.length - 1; i > 0; i--) {
          this.data[i].count -= this.data[i - 1].count
        }
        // console.log(this.data)
        this.chart()
      })
    },
    chart () {
      this.title()
      const echarts = this.$echarts.init(document.getElementById(this.chartName))
      echarts.setOption({
        title: {
          text: this.text
        },
        xAxis: {
          name: '',
          type: 'category',
          data: this.data.filter(v => v.name).map((item) => {
            return item.name
          })
        },
        dataZoom: [
          {
            show: true,
            realtime: true,
            start: 0,
            end: 100,
            xAxisIndex: [0, 1]
          },
          {
            type: 'inside',
            realtime: true,
            start: 0,
            end: 100,
            xAxisIndex: [0, 1]
          }
        ],
        tooltip: {
          trigger: 'axis'
        },
        // formatter: function (params) {
        //   console.log(params)
        //   // return str;
        // },
        yAxis: {
          name: '抽卡次数',
          type: 'value',
          max: 90
        },
        series: [
          {
            name: '抽卡次数',
            data: this.data.filter(v => v.count).map((item) => {
              return item.count
            }),
            type: 'line'
          }
        ]
      })
      window.onresize = () => {
        this.$echarts.init(document.getElementById(this.chartName)).resize()
      }
    },
    title () {
      switch (this.code) {
        case 301: {
          this.text = '角色up池'
          break
        }
        case 302: {
          this.text = '武器up池'
          break
        }
        case 200: {
          this.text = '标配池'
          break
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
