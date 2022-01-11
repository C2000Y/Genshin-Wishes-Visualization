<template>
  <div id="app">
    <Content/>
  </div>
</template>

<script>
import Content from './components/Content'

export default {
  name: 'App',
  components: {
    Content
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /*margin-top: 60px;*/
  overflow: hidden;
  min-width: 1200px;
}
::-webkit-scrollbar
{
  /*display: none;*/
  position: absolute;
  width: 10px;  /*滚动条宽度*/
  height: 10px;  /*滚动条高度*/
  background-color: #FFFFFF;
}
/*定义滑块 内阴影+圆角*/
::-webkit-scrollbar-thumb
{
  -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
  background-color: rgb(221,222,224);  /*滚动条的背景颜色*/
  border-radius: 5px;
  border: 2px solid white;
}
</style>
