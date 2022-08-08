<template>
  <div id="app">
    <Navigate/>
<!--    <Content/>-->
    <MainPage/>
  </div>
</template>

<script>
import Content from './components/Content'
import Navigate from './components/Navi/Navi'
import MainPage from './components/MainPage'

export default {
  name: 'App',
  components: {
    Content,
    Navigate,
    MainPage
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
  width: 100%;
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

/*@media screen and (max-width: 550px){*/
  /*.a{*/
    /*background-color:#6633FF;*/
  /*}*/
/*}*/

/*@media screen and (max-width: 320px){*/
  /*.a{*/
    /*background-color:#FFFF00;*/
  /*}*/
/*}*/

</style>
