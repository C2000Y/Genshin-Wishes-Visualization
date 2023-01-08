<template>
  <div v-if="jsonFlag" :id="chartName" style="width: 100%; height: 100%">
  </div>
</template>

<script>
export default {
  name: 'BarChart',
  props: ['id', 'size'],
  data () {
    return {
      // charaImgJson,
      data: [],
      uid: undefined,
      code: undefined,
      text: '',
      chartName: 'barChart',
      echarts: undefined,
      imgUrl: {},
      jsonFlag: false,
      charaCount: 0,
      iconSize: 30,
      iconNumber: 30,
      chartParam: {
        value: null,
        code: null,
        showMark: null
      },
      charaImg: []
    }
  },
  computed: {
    lang () {
      return this.$i18n.locale
    }
  },
  watch: {
    lang (newV, oldV) {
      if (this.chartParam.value !== null) {
        this.chart(this.chartParam.value, this.chartParam.code, this.chartParam.showMark)
      }
    }
  },
  mounted () {
    this.iconSizeChange()
    this.chartName += this.id
  },
  async created () {
    while (this.$common.hasData() !== true) {
      await new Promise(resolve => setTimeout(resolve, 300))
    }
    this.jsonFlag = this.$common.hasData()
    this.imgUrl = this.$common.getImgJson()
  },
  methods: {
    async chart (value, code, showMark) {
      this.chartParam = {
        value: value,
        code: code,
        showMark: showMark
      }
      const wishNumber = this.$t('line_chart.wish_number')
      const wishTime = this.$t('line_chart.wish_time')
      const lang = this.$i18n.locale
      let ToImg = this.imgUrl
      // console.log(ToImg)
      if (this.echarts != null && this.echarts !== '' && this.echarts !== undefined) {
        this.echarts.dispose()
      }
      this.text = await this.title(code)
      this.echarts = this.$echarts.init(document.getElementById(this.chartName))
      // 设置放大区块是否要缩小（预设：只显示20个）
      let startZoom = 0
      if (value.length > this.iconNumber) {
        startZoom = 100 - (this.iconNumber / value.length * 100)
      }
      this.echarts.setOption({
        title: {
          text: this.text
        },
        xAxis: {
          name: '',
          type: 'category',
          axisLabel: {
            show: false
          },
          axisTick: {
            show: false
          },
          data: value.filter(v => v.name).map((item) => {
            // if (this.$i18n.locale === 'en') {
            //   return ToImg[item.name].nameEn
            // } else {
            return item.name
            // }
          })
        },
        dataZoom: [
          {
            show: true,
            realtime: true,
            start: startZoom,
            end: 100,
            xAxisIndex: [0, 1]
          }
          // {
          //   type: 'inside',
          //   realtime: true,
          //   start: 0,
          //   end: 100,
          //   xAxisIndex: [0, 1]
          // }
        ],
        tooltip: {
          trigger: 'axis',
          formatter: function (params) {
            let name = ToImg[params[0].name].name
            if (lang === 'en') {
              name = ToImg[params[0].name].nameEn
            }
            let url = ToImg[params[0].name].url
            let date = new Date(value[params[0].dataIndex].time)
            let yr = date.getFullYear() + '-'
            let mon = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
            let day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
            var htmlStr = ''
            htmlStr +=
              '<img style="width: 50px" src=' + url + '></img>' +
              '<div style="font-weight: bold; font-size: 1rem;">' + name + '</div>' +
              '<div style="text-align: left">' +
              '<span style="display:inline-block;margin-right:4px;border-radius:10px;width:10px;height:10px;background-color:#5470c6;"></span>' +
              '<span style="font-size:14px;color:#666;font-weight:400;margin-left:2px"> ' + wishNumber + '</span>' +
              '<span style="float:right;margin-left:20px;font-size:14px;color:#666;font-weight:900">' + params[0].data + '</span>' +
              '</div>' +
              '<div style="text-align: left">' +
              '<span style="display:inline-block;margin-right:4px;border-radius:10px;width:10px;height:10px;background-color:#5470c6;"></span>' +
              '<span style="font-size:14px;color:#666;font-weight:400;margin-left:2px"> ' + wishTime + '</span>' +
              '<span style="float:right;margin-left:20px;font-size:14px;color:#666;font-weight:900">' + yr + mon + day + '</span>' +
              '</div>'
            htmlStr += '</div>'
            return htmlStr
          }
        },
        yAxis: {
          name: this.$t('line_chart.wishes'),
          type: 'value',
          max: 90
        },
        series: [
          {
            name: this.$t('line_chart.wishes'),
            data: value.filter(v => v.count).map((item) => {
              return item.count
            }),
            type: 'line',
            markPoint: {
              // itemStyle: { // 转折点 控制
              //   normal: {
              //     shadowBlur: 200
              //   }
              // },
              data: value.filter(v => v.name).map((item, index) => {
                if (!showMark) {
                  return ''
                }
                let url = this.imgUrl[item.name].url
                return {
                  coord: [index, item.count],
                  symbol: 'image://' + url
                }
              }),
              symbolSize: this.iconSize
            }
          }
        ]
      })
      window.onresize = () => {
        this.$echarts.init(document.getElementById(this.chartName)).resize()
      }
      window.addEventListener('resize', () => {
        this.echarts.resize()
      })
    },
    async title (code) {
      console.log(code)
      let text = ''
      switch (code) {
        case 301: {
          text = this.$t('line_chart.wish_type[' + code + ']')
          break
        }
        case 302: {
          text = this.$t('line_chart.wish_type.' + code)
          break
        }
        case 200: {
          text = this.$t('line_chart.wish_type.' + code)
          break
        }
        case 1000: {
          text = this.$t('line_chart.wish_type.' + code)
          break
        }
      }
      return text
    },
    iconSizeChange () {
      if (document.body.clientWidth < 501) {
        this.iconSize = 25
        this.iconNumber = 15
      } else if (document.body.clientWidth < 1960) {
        this.iconSize = 35
        this.iconNumber = 30
      } else if (document.body.clientWidth < 2560) {
        this.iconSize = 45
        this.iconNumber = 35
      }
    }
  }
}
</script>

<style scoped>

</style>
