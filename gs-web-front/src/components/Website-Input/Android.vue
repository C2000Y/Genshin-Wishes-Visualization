<template>
  <div>
    <!--<Loading :isLoading="isLoading" text="加载时间取决于您近6个月的抽卡次数(1000抽需要20秒)，请耐心等待~"/>-->
    <el-input placeholder="在这儿输入链接" v-model="input" clearable class="web-input">
    <el-button slot="append" @click="clickHandler"><i class="el-icon-search"></i> 上传</el-button>
    </el-input>
    <!--<el-dialog title="获取网址的方式" :visible.sync="dialogTableVisible" width="800px">-->
    <!--</el-dialog>-->
  </div>
</template>

<script>
// import Loading from '../Effect/Loading'
export default {
  name: 'Android',
  // components: {
  //   Loading
  // },
  data () {
    return {
      input: ''
    }
  },
  methods: {
    async clickHandler () {
      if (this.input === '') {
        ELEMENT.Message({
          message: '录入失败，失败原因：输入不能为空',
          type: 'error'
        })
        return
      }
      // ELEMENT.Loading.service({fullscreen: true, text: '加载时间取决于您近6个月的抽卡次数(1000抽需要20秒)，请耐心等待~'})
      // const res = await this.$axios({
      //   url: '/summon/writeIn?url=' + this.input,
      //   method: 'get',
      //   contentType: false,
      //   processData: false
      // })
      this.$emit('return', this.input)
      this.input = ''
    }
  }
}
</script>

<style scoped>

</style>
