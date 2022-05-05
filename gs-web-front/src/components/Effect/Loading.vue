<template>
  <div>
    <div v-loading.fullscreen.lock="isLoading === true" :element-loading-text=text></div>
  </div>
</template>

<script>
export default {
  name: 'Loading',
  props: ['isLoading', 'text']
}
</script>

<style scoped>
  .el-loading-mask {
    background-color: rgba(255, 255, 255, 0.1)
  }
</style>
