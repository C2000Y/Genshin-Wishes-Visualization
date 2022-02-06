<template>
    <div :id="chartName" style="width: 100%; height: 100%">
    </div>
</template>

<script>
export default {
  name: 'PieChart',
  props: ['id'],
  data () {
    return {
      data: [],
      text: '',
      code: undefined,
      chartName: 'pieChart',
      echarts: undefined
    }
  },
  mounted () {
    this.chartName += this.id
  },
  methods: {
    getData (uid, code) {
      this.code = code
      this.$axios.get('/summon/rankType', {
        params: {
          uid: uid,
          code: code
        }
      }).then(res => {
        // console.log(res)
        this.data = res.data.data
        this.$emit('data', this.data, code)
        this.chart()
      })
    },
    chart () {
      if (this.echarts != null && this.echarts !== '' && this.echarts !== undefined) {
        this.echarts.dispose()
      }
      this.title()
      this.echarts = this.$echarts.init(document.getElementById(this.chartName))
      this.echarts.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
          top: '5%',
          left: 'center',
          show: true
        },
        series: [
          {
            name: this.text,
            type: 'pie',
            radius: '50%',
            center: ['50%', '65%'],
            // avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 8,
              borderColor: '#fff',
              borderWidth: 1
            },
            label: {
              normal: {
                show: true,
                fontSize: '16',
                formatter: '{b}: {c}({d}%)',
                position: 'outside'
              }
            },
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },
            labelLine: {
              show: true
            },
            data: this.data.filter(v => v.itemType).map((item) => {
              // console.log(item)
              return {
                value: item.count,
                name: item.rankType + '星' + item.itemType
              }
            })
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
