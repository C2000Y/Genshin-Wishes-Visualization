<template>
  <div>
    <el-input placeholder="请输入网址" v-model="input" clearable class="web-input">
    <el-button slot="append" @click="clickHandler"><i class="el-icon-search"></i> 查找</el-button>
    </el-input>
    <el-dialog title="获取网址的方式" :visible.sync="dialogTableVisible" width="800px">
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Android',
  data () {
    return {
      input: ''
    }
  },
  methods: {
    async clickHandler () {
      if (this.input === '') {
        this.$message({
          message: '录入失败，失败原因：输入不能为空',
          type: 'error'
        })
        return
      }
      this.$loading.service({fullscreen: true, text: '加载时间取决于您近6个月的抽卡次数(1000抽需要20秒)，请耐心等待~'})
      const res = await this.$axios({
        url: '/summon/writeIn?url=' + this.input,
        method: 'get',
        contentType: false,
        processData: false
      })
      this.$emit('return', res)
    }
  }
}
</script>

<style scoped>

</style>
