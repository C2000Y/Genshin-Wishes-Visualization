<template>
  <div class="total">
    <div class="item">
      <div>
        常驻祈愿：{{count200}}
      </div>
      <div class="toBaodi">
        距离保底：{{detail200}}
      </div>
    </div>
    <div class="item">
      <div>
        角色祈愿：{{count301}}
      </div>
      <div class="toBaodi">
        距离保底：{{detail301}}
      </div>
    </div>
    <div class="item">
      <div>
        武器祈愿：{{count302}}
      </div>
      <div class="toBaodi">
        距离保底：{{detail302}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Total',
  data () {
    return {
      count301: '',
      count302: '',
      count200: '',
      detail301: '',
      detail302: '',
      detail200: '',
      count: 0
    }
  },
  methods: {
    getTotal (data, code) {
      switch (code) {
        case 301: {
          this.count301 = data
          break
        }
        case 302: {
          this.count302 = data
          break
        }
        case 200: {
          this.count200 = data
          break
        }
      }
    },
    async getBaoDi (data, code) {
      this.count++
      if (this.count >= 3) {
        this.$loading.service().close()
      }
      switch (code) {
        case 301: {
          while (this.count301 === '') {
            await new Promise(resolve => setTimeout(resolve, 500))
            console.log(this.count301)
          }
          this.detail301 = 90 - this.count301 + data
          break
        }
        case 302: {
          while (this.count302 === '') {
            await new Promise(resolve => setTimeout(resolve, 500))
            console.log(this.count302)
          }
          this.detail302 = 90 - this.count302 + data
          break
        }
        case 200: {
          while (this.count200 === '') {
            await new Promise(resolve => setTimeout(resolve, 500))
            console.log(this.count200)
          }
          this.detail200 = 90 - this.count200 + data
          break
        }
      }
    },
    uidChanged (uid) {
      this.count301 = ''
      this.count302 = ''
      this.count200 = ''
      this.detail301 = ''
      this.detail302 = ''
      this.detail200 = ''
      this.count = 0
    }
    // charaDetail (chData) {
    //   this.clean()
    //   this.$axios.get('summon/totalCount', {
    //     params: {
    //       uid: this.uid
    //     }
    //   }).then(res => {
    //     let data = res.data.data
    //     this.count200 = data.standard
    //     this.count301 = data.character
    //     this.count302 = data.weapon
    //     this.detail301 = '; 其中'
    //     chData.forEach(item => {
    //       this.detail301 += item.rankType + '星' + item.itemType + ': ' + (item.count / parseInt(this.count301) * 100).toFixed(2) + '%; '
    //     })
    //   })
    // },
    // clean () {
    //   this.detail301 = ''
    //   this.detail302 = ''
    //   this.detail200 = ''
    //   this.count301 = '-'
    //   this.count302 = '-'
    //   this.count200 = '-'
    // }
  }
}
</script>

<style scoped>
  .item{
    border: 2px solid rgb(78,164,220);
    width: 200px;
    height: 55px;
    line-height: 35px;
    margin-right: 100px;
    border-radius: 10px;
    background-color: rgb(78,164,220);
    color: white;
    font-weight: bold;
    font-size: 17px;
    display: inline-block;
  }
  .total{
    margin-top: 15px;
  }
  .toBaodi{
    font-size: 10px;
    height: 20px;
    line-height: 20px;
  }
</style>
