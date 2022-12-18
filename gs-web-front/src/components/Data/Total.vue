<template>
  <div style="float: left">
    <ul class="Nav-items">
      <li class="item">
        <div>
          {{ $t('stat.total') }}
          <el-tooltip effect="dark" :content='total >= 0 ? $t("stat.equals_to") + total * 150 + $t("stat.primo_gem") : total' placement="right">
            <span class="item-value">
              {{total === '' ? $t('stat.no_data') : total}}
            </span>
          </el-tooltip>
        </div>
<!--        <div v-else>-->
<!--          总计祈愿：暂无-->
<!--        </div>-->
      </li>
      <li class="item">
        <div>
          {{ $t('stat.character_wishes') }}
          <span class="item-value">
            {{count301 === '' ? $t('stat.no_data') : count301}}
          </span>
        </div>
<!--        <div v-else>-->
<!--          角色祈愿：暂无-->
<!--        </div>-->
        <div>
          {{ $t('stat.to_pity') }}
          <el-tooltip
            effect="dark"
            :content='detail301 >= 0 ? $t("stat.still_need") + detail301 * 150  + $t("stat.primo_gem") : detail301'
            placement="right"
          >
            <span class="item-value">
              {{detail301 === '' ? $t('stat.no_data') : detail301}}
            </span>
          </el-tooltip>
        </div>
<!--        <div v-else>-->
<!--          距离保底：暂无-->
<!--        </div>-->
      </li>
      <li class="item">
        <div>
          {{ $t('stat.weapon_wishes') }}
          <span class="item-value">
            {{count302 === '' ? $t('stat.no_data') : count302}}
          </span>
        </div>
<!--        <div v-else>-->
<!--          武器祈愿：暂无-->
<!--        </div>-->
        <div>
          {{ $t('stat.to_pity') }}
          <el-tooltip
            effect="dark"
            :content='detail302 >= 0 ? $t("stat.still_need") + detail302 * 150  + $t("stat.primo_gem") : detail302'
            placement="right"
          >
            <span class="item-value">
              {{detail302 === '' ? $t('stat.no_data') : detail302}}
            </span>
          </el-tooltip>
        </div>
<!--        <div v-else>-->
<!--          距离保底：暂无-->
<!--        </div>-->
      </li>
      <li class="item">
        <div>
          {{ $t('stat.standard_wishes') }}
          <span class="item-value">
            {{count200 === '' ? $t('stat.no_data') : count200}}
          </span>
        </div>
<!--        <div v-else>-->
<!--          常驻祈愿：暂无-->
<!--        </div>-->
        <div>
          {{ $t('stat.to_pity') }}
          <el-tooltip
            effect="dark"
            :content='detail200 >= 0 ? $t("stat.still_need") + detail200 * 150  + $t("stat.primo_gem") : detail200'
            placement="right"
          >
            <span class="item-value">
              {{detail200 === '' ? $t('stat.no_data') : detail200}}
            </span>
          </el-tooltip>
        </div>
<!--        <div v-else>-->
<!--          距离保底：暂无-->
<!--        </div>-->
      </li>
    </ul>
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
      total: ''
    }
  },
  methods: {
    changeUid (uid) {
      this.$axios.get('summon/GetTotalCount', {
        params: {
          uid: uid
        }
      }).then(res => {
        const data = res.data.data
        console.log(data)
        this.total = data[0].count
        this.count301 = data[1].count
        this.count302 = data[2].count
        this.count200 = data[3].count
        this.detail301 = data[1].left
        this.detail302 = data[2].left
        this.detail200 = data[3].left
      })
    }
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
    // uidChanged (uid) {
    //   this.count301 = ''
    //   this.count302 = ''
    //   this.count200 = ''
    //   this.detail301 = ''
    //   this.detail302 = ''
    //   this.detail200 = ''
    //   this.count = 0
    // }
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
    font-size: 14px;
    list-style: none;
    margin: 5px 0px 0px 0px;
  }
  .item{
    box-sizing: border-box;
    margin: 0 1rem 0 1rem;
    padding: 0;
    font-size: 1.1rem;
    /*font-weight: bold;*/
    color: #537fb5;
    text-align: left;
    /*font-family: 华文细黑;*/
  }
  .item-value {
    font-weight: bold;
  }
  @media screen and (max-width: 1200px){
    .Nav-items{
      margin: unset;
      padding: unset;
    }
    .item{
      margin: 0 0.5rem 0 0.5rem;
      font-size: 1rem;
    }
  }

  @media screen and (max-width: 550px){
    .item{
      /*margin: 0 0.5rem 0 0.5rem;*/
      font-size: 0.73rem;
    }
  }

  @media screen and (max-width: 420px){
    .Nav-items{
      flex-wrap: wrap;
    }
    .item{
      margin: 0 0.3rem 0 0.3rem;
    }
  }
</style>
