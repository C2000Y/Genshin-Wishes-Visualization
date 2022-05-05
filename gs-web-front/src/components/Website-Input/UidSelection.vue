<template>
  <div>
    <el-select v-model="uid" @change="UidChangeHandler" placeholder="选个玩家才能看数据分析">
      <el-option v-for="item in uids" :value="item.uid" :key="item.uid">
        {{item.name}} - [{{item.uid}}]
      </el-option>
    </el-select>
  </div>
</template>

<script>
export default {
  name: 'UidSelection',
  data () {
    return {
      uid: '',
      uids: []
      // loadingText: ['让我康康！', '转啊，转~', '我超，原……来我在加载中', '♪好运来，祝你好运来♪', '今天的爆率那叫一个高啊.jpg']
    }
  },
  mounted () {
    this.getUids()
  },
  methods: {
    getUids (uid) {
      this.$axios.get('uid/list').then(res => {
        this.uids = res.data.data
        this.uid = uid
        // console.log(this.uids)
      })
    },
    UidChangeHandler (event) {
      // TODO
      // this.$loading.service({ fullscreen: true, text: this.loadingText[this.getRandomInt(this.loadingText.length)] })
      this.$emit('uid', this.uid)
    }
    /**
     * 取随机数
     * @param: max, 输入随机数的个数，假如随机数值为X, X >= 0 && X < max
     */
    // getRandomInt (max) {
    //   return Math.floor(Math.random() * max)
    // }
  }
}
</script>

<style scoped>
  /deep/
  .el-input__inner {
    background-color: rgba(255,255,255, 0.8);
    border: 1px solid transparent;
    color: #abb6e5;
  }
</style>
