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
      chartName: 'barChart',
      echarts: undefined
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
        const data = res.data.data
        let count = []
        // 抽到最后一个五星的总计数量
        const rs = data[data.length - 1]
        this.$emit('lastFiveStar', rs, code)
        count[0] = {count: data[0].count, name: data[0].name}
        for (let i = data.length - 1; i > 0; i--) {
          count[i] = {count: data[i].count - data[i - 1].count, name: data[i].name}
        }
        this.chart(count)
      })
    },
    chart (value) {
      if (this.echarts != null && this.echarts !== '' && this.echarts !== undefined) {
        this.echarts.dispose()
      }
      this.title()
      this.echarts = this.$echarts.init(document.getElementById(this.chartName))
      this.echarts.setOption({
        title: {
          text: this.text
        },
        xAxis: {
          name: '',
          type: 'category',
          data: value.filter(v => v.name).map((item) => {
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
          name: '祈愿次数',
          type: 'value',
          max: 90
        },
        series: [
          {
            name: '祈愿次数',
            data: value.filter(v => v.count).map((item) => {
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
          this.text = '角色祈愿'
          break
        }
        case 302: {
          this.text = '武器祈愿'
          break
        }
        case 200: {
          this.text = '常驻祈愿'
          break
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
