<template>
  <div :id="chartName" style="width: 100%; height: 100%">
  </div>
</template>

<script>
export default {
  name: 'BarChart',
  props: ['id', 'size'],
  data () {
    return {
      data: [],
      uid: undefined,
      code: undefined,
      text: '',
      chartName: 'barChart',
      echarts: undefined,
      imgUrl: {
        '胡桃': 'https://patchwiki.biligame.com/images/ys/2/2d/6x5q4v3ovlgtd5pk7xlqxjdqv1gvf0a.png',
        '宵宫': 'https://patchwiki.biligame.com/images/ys/3/34/pz347ut911ktapyemd3ewiyx1dwv3te.png',
        '迪卢克': 'https://patchwiki.biligame.com/images/ys/9/90/lwq85ylshqji1oozf7odkv3xlquvttt.png',
        '可莉': 'https://patchwiki.biligame.com/images/ys/2/26/59ohlo0p92xb8d7qut9ul8wi0ij6ehw.png',
        '莫娜': 'https://patchwiki.biligame.com/images/ys/f/f1/refnobe859mskudq9i634djaniepwvt.png',
        '达达利亚': 'https://patchwiki.biligame.com/images/ys/b/b1/4v48u6t4v9mfnwmabch9ojskqa1x61a.png',
        '珊瑚宫心海': 'https://patchwiki.biligame.com/images/ys/4/49/kyfltllg1qaoki7pwpefy7xh8022duy.png',
        '神里绫人': 'https://patchwiki.biligame.com/images/ys/f/f7/sdgu6bh00rod7lypt9l9c9xlxojtjhl.png',
        '夜兰': 'https://patchwiki.biligame.com/images/ys/6/68/e49y27vv8lhk0a9h199pgc0ramj3dk4.png',
        '琴': 'https://patchwiki.biligame.com/images/ys/8/8d/lm9cpnooekvfzfy7fbogykwjro3v5lw.png',
        '温迪': 'https://patchwiki.biligame.com/images/ys/4/42/l9a990crawlwa9ur42ld1cgjvqzj3m9.png',
        '魈': 'https://patchwiki.biligame.com/images/ys/3/33/5h47uueobwqzur6vo90ag9fewfgqxdq.png',
        '枫原万叶': 'https://patchwiki.biligame.com/images/ys/3/3a/e1n6v73p785ne6kew41q9snh45zrup2.png',
        '刻晴': 'https://patchwiki.biligame.com/images/ys/6/6a/goj6bb8yj190midok60n2fbkk872090.png',
        '雷电将军': 'https://patchwiki.biligame.com/images/ys/a/a7/e9o4gu6ztf7zytnvvkeoerbevkjfwjr.png',
        '八重神子': 'https://patchwiki.biligame.com/images/ys/e/e0/0plzsmjpgzzpldn00zqijgfybgx2pac.png',
        '七七': 'https://patchwiki.biligame.com/images/ys/0/05/049fpv6jcr66mln0nmbbfgigfrkgrzo.png',
        '甘雨': 'https://patchwiki.biligame.com/images/ys/1/18/qr9mmt4ryhcuovbx82pazu3xv7wr19p.png',
        '神里绫华': 'https://patchwiki.biligame.com/images/ys/3/38/8h931m3f8nkkvjfq3f03c8a9h0f6x69.png',
        '申鹤': 'https://patchwiki.biligame.com/images/ys/7/73/793kuoybdf409lnzwevsmd8ipnel1d2.png',
        '优菈': 'https://patchwiki.biligame.com/images/ys/8/86/jf6qfoaq6q48jx6lvqb88dgl4xta141.png',
        '钟离': 'https://patchwiki.biligame.com/images/ys/a/a4/eewy674lo37jwuq9qozyszhz8vo0d8j.png',
        '阿贝多': 'https://patchwiki.biligame.com/images/ys/e/e9/k1zod68cqa6e89r0poxm9ihfmqfmgvw.png',
        '荒泷一斗': 'https://patchwiki.biligame.com/images/ys/0/0c/5sdwiryq6bjb3tjxxtg1e8njdmggirp.png',
        '波乱月白经津': 'https://patchwiki.biligame.com/images/ys/e/e7/dgf1tthftk1ctxpapenad77tdb5g9k5.png',
        '风鹰剑': 'https://patchwiki.biligame.com/images/ys/f/f3/ifgr7v6spw00py7cvib4z4vjjprqfvy.png',
        '雾切之回光': 'https://patchwiki.biligame.com/images/ys/8/80/fvlvamw1kcodwxexze8svm89r9ju4zw.png',
        '斫峰之刃': 'https://patchwiki.biligame.com/images/ys/8/8d/t7wu6l7ykp0hmham4037fy8edr4ijte.png',
        '天空之刃': 'https://patchwiki.biligame.com/images/ys/7/71/dnte46o8llqfgw8c0xi262chjebvp6z.png',
        '苍古自由之誓': 'https://patchwiki.biligame.com/images/ys/b/b8/c8nzypf1q6lx3if55t7g0mu80ekoj7n.png',
        '磐岩结绿': 'https://patchwiki.biligame.com/images/ys/9/97/4ok03p2q6u81pf9itvnx7q0k5b8fyw8.png',
        '赤角石溃杵': 'https://patchwiki.biligame.com/images/ys/7/71/m4kbbg7kbc4x5jcmb6kks309zvj3kfu.png',
        '天空之傲': 'https://patchwiki.biligame.com/images/ys/7/70/s83n7y1z3pm1c7h89tfd046nq538hvx.png',
        '松籁响起之时': 'https://patchwiki.biligame.com/images/ys/8/83/3cxnw66k87k7chxe7ckzczp1a2nkq6r.png',
        '无工之剑': 'https://patchwiki.biligame.com/images/ys/a/a8/2w5n9a0nm151r2qe0gfixh5tch23uf9.png',
        '狼的末路': 'https://patchwiki.biligame.com/images/ys/c/c3/bodsgcpqa7sdxracqmnkrnfjxo32k30.png',
        '飞雷之弦振': 'https://patchwiki.biligame.com/images/ys/5/59/6gsvkz90vjkpd5qe04k79wma7q8kmeo.png',
        '若水': 'https://patchwiki.biligame.com/images/ys/0/09/00r3su6u4kxlz0ezyouv0vq9h722ayq.png',
        '冬极白星': 'https://patchwiki.biligame.com/images/ys/5/5d/ppmbyvsu0vbbczbzgifmunz6wx1ryfs.png',
        '天空之翼': 'https://patchwiki.biligame.com/images/ys/6/60/qldtuxjl9fwi55a8vlk7j1ykwb6y16w.png',
        '阿莫斯之弓': 'https://patchwiki.biligame.com/images/ys/c/cd/l7m6yvb42twd9t9bhgtlce591vz1hca.png',
        '终末嗟叹之诗': 'https://patchwiki.biligame.com/images/ys/5/59/44fro799bzyumh6dyv759of3cla9a0k.png',
        '天空之卷': 'https://patchwiki.biligame.com/images/ys/5/59/mvuckqfkhu76943f6wjufdx3rl84s0z.png',
        '尘世之锁': 'https://patchwiki.biligame.com/images/ys/8/8a/i5xsnotu7tro276f1vuoj5oepiokvev.png',
        '神乐之真意': 'https://patchwiki.biligame.com/images/ys/7/7f/t5eixn262z7h58n2srxq6on5o4nr2m0.png',
        '不灭月华': 'https://patchwiki.biligame.com/images/ys/d/de/9dn8u5ljoelgvnp36ye1h3qxxolr6im.png',
        '四风原典': 'https://patchwiki.biligame.com/images/ys/c/cd/fzsxpkqc4mjwh6btfxkasprae3oj2ie.png',
        '护摩之杖': 'https://patchwiki.biligame.com/images/ys/f/fd/9ke3hzgn2pksx3cjnn4sqy3w46l47oa.png',
        '和璞鸢': 'https://patchwiki.biligame.com/images/ys/2/23/51mugtbn28c0eyme1dljkz2dry13hkt.png',
        '天空之脊': 'https://patchwiki.biligame.com/images/ys/d/db/9kbm4s2vozajz6k24um2eom9mnqexgt.png',
        '贯虹之槊': 'https://patchwiki.biligame.com/images/ys/5/56/hel146r6z1y5oid6wltvkipajyttqz0.png',
        '息灾': 'https://patchwiki.biligame.com/images/ys/a/ad/1jmgglq2h6x9ypqy9pgn12w1jamp764.png',
        '薙草之稻光': 'https://patchwiki.biligame.com/images/ys/7/73/f9ktf9zqfv4kc1quxns3njft4rvatch.png'
      },
      charaCount: 0
    }
  },
  mounted () {
    this.chartName += this.id
  },
  methods: {
    // getData (uid, code) {
    //   this.uid = uid
    //   this.code = code
    //   this.$axios.get('summon/SummonCount', {
    //     params: {
    //       uid: uid,
    //       code: code
    //     }
    //   }).then(res => {
    //     const data = res.data.data
    //     let count = []
    //     // 抽到最后一个五星的总计数量
    //     const rs = data[data.length - 1]
    //     this.$emit('lastFiveStar', rs, code)
    //     count[0] = {count: data[0].count, name: data[0].name}
    //     for (let i = data.length - 1; i > 0; i--) {
    //       count[i] = {count: data[i].count - data[i - 1].count, name: data[i].name}
    //     }
    //     this.chart(count)
    //   })
    // },
    chart (value, code, showMark) {
      let ToImg = this.imgUrl
      if (this.echarts != null && this.echarts !== '' && this.echarts !== undefined) {
        this.echarts.dispose()
      }
      this.title(code)
      this.echarts = this.$echarts.init(document.getElementById(this.chartName))
      // 设置放大区块是否要缩小（预设：只显示20个）
      let startZoom = 0
      if (value.length > this.size) {
        startZoom = 100 - (this.size / value.length * 100)
      }
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
            let url = ToImg[params[0].name]
            let date = new Date(value[params[0].dataIndex].time)
            let yr = date.getFullYear() + '-'
            let mon = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
            let day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
            var htmlStr = ''
            htmlStr +=
              '<img style="width: 50px" src=' + url + '></img>' +
              '<div style="font-weight: bold; font-size: 1rem;">' + params[0].name + '</div>' +
              '<div style="text-align: left">' +
              '<span style="display:inline-block;margin-right:4px;border-radius:10px;width:10px;height:10px;background-color:#5470c6;"></span>' +
              '<span style="font-size:14px;color:#666;font-weight:400;margin-left:2px"> 祈愿次数：' + '</span>' +
              '<span style="float:right;margin-left:20px;font-size:14px;color:#666;font-weight:900">' + params[0].data + '</span>' +
              '</div>' +
              '<div style="text-align: left">' +
              '<span style="display:inline-block;margin-right:4px;border-radius:10px;width:10px;height:10px;background-color:#5470c6;"></span>' +
              '<span style="font-size:14px;color:#666;font-weight:400;margin-left:2px"> 祈愿时间：' + '</span>' +
              '<span style="float:right;margin-left:20px;font-size:14px;color:#666;font-weight:900">' + yr + mon + day + '</span>' +
              '</div>'
            htmlStr += '</div>'
            return htmlStr
          }
        },
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
                let url = this.imgUrl[item.name]
                return {
                  coord: [index, item.count],
                  symbol: 'image://' + url
                }
              }),
              symbolSize: 30
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
    title (code) {
      switch (code) {
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
        case 1000: {
          this.text = '全部祈愿'
          break
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
