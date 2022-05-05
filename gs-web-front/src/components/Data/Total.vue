<template>
  <div style="float: left">
    <ul class="Nav-items">
      <li class="item">
        <div v-if="total !== 0">
          总计祈愿：{{total}}
        </div>
        <div v-else>
          总计祈愿：暂无
        </div>
      </li>
      <li class="item">
        <div v-if="chara !== 0">
          角色祈愿：{{chara}}
        </div>
        <div v-else>
          角色祈愿：暂无
        </div>
        <div v-if="charaLeft !== 0">
          距离保底：{{charaLeft}}
        </div>
        <div v-else>
          距离保底：暂无
        </div>
      </li>
      <li class="item">
        <div v-if="weapon !== 0">
          武器祈愿：{{weapon}}
        </div>
        <div v-else>
          武器祈愿：暂无
        </div>
        <div v-if="weaponLeft !== 0">
          距离保底：{{weaponLeft}}
        </div>
        <div v-else>
          距离保底：暂无
        </div>
      </li>
      <li class="item">
        <div v-if="stand !== 0">
          常驻祈愿：{{stand}}
        </div>
        <div v-else>
          常驻祈愿：暂无
        </div>
        <div v-if="standLeft !== 0">
          距离保底：{{standLeft}}
        </div>
        <div v-else>
          距离保底：暂无
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Total',
  props: ['total', 'chara', 'charaLeft', 'weapon', 'weaponLeft', 'stand', 'standLeft'],
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
    // getTotal (data, code) {
    //   switch (code) {
    //     case 301: {
    //       this.count301 = data
    //       break
    //     }
    //     case 302: {
    //       this.count302 = data
    //       break
    //     }
    //     case 200: {
    //       this.count200 = data
    //       break
    //     }
    //   }
    // },
    // async getBaoDi (data, code) {
    //   this.count++
    //   if (this.count >= 3) {
    //     // this.$loading.service().close()
    //   }
    //   switch (code) {
    //     case 301: {
    //       while (this.count301 === '') {
    //         await new Promise(resolve => setTimeout(resolve, 500))
    //         console.log(this.count301)
    //       }
    //       this.detail301 = 90 - this.count301 + data
    //       break
    //     }
    //     case 302: {
    //       while (this.count302 === '') {
    //         await new Promise(resolve => setTimeout(resolve, 500))
    //         console.log(this.count302)
    //       }
    //       this.detail302 = 90 - this.count302 + data
    //       break
    //     }
    //     case 200: {
    //       while (this.count200 === '') {
    //         await new Promise(resolve => setTimeout(resolve, 500))
    //         console.log(this.count200)
    //       }
    //       this.detail200 = 90 - this.count200 + data
    //       break
    //     }
    //   }
    // },
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
  /*.item{*/
    /*border: 2px solid rgb(78,164,220);*/
    /*width: 200px;*/
    /*height: 55px;*/
    /*line-height: 35px;*/
    /*margin-right: 100px;*/
    /*border-radius: 10px;*/
    /*background-color: rgb(78,164,220);*/
    /*color: white;*/
    /*font-weight: bold;*/
    /*font-size: 17px;*/
    /*display: inline-block;*/
  /*}*/
  /*.total{*/
    /*margin-top: 15px;*/
  /*}*/
  /*.toBaodi{*/
    /*font-size: 10px;*/
    /*height: 20px;*/
    /*line-height: 20px;*/
  /*}*/
  .Nav-items{
    display: flex;
    align-items: center;
    line-height: 22px;
    font-size: 14px;
    list-style: none;
    margin: 5px 0px 0px 0px;
  }
  .item{
    box-sizing: border-box;
    margin: 0 1rem 0 1rem;
    padding: 0;
    font-size: 1rem;
    font-weight: bold;
    color: #537fb5;
    text-align: left;
    /*font-family: 华文细黑;*/
  }
</style>
