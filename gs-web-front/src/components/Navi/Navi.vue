<template>
  <div class="Navigate">
    <div class="Navigate-main">
      <div style="display: flex; align-items: center;">
        <!--<div class="Logo">-->
          <!--<img style="width: 100%;" src="/static/Icon.png"/>-->
        <!--</div>-->
        <ul class="Nav-items">
          <li class="item">
            提瓦特观星台
          </li>
          <li class="item">
            <!--小游戏-->
          </li>
        </ul>
        <div>
        </div>
        <div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Navigate'
}
</script>

<style scoped>
  .Navigate {
    height: 3rem;
    width: 100%;
    background: #74ebd5;  /* fallback for old browsers */
    background: -webkit-linear-gradient(to right, #ACB6E5, #74ebd5);  /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(to right, #ACB6E5, #74ebd5); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    box-shadow: -1px 2px 7px rgba(0, 0, 0, 0.5);
    position: relative;
    min-width: 1160px;
  }
  .Navigate-main{
    width: 1160px;
    height: 100%;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
  }
  .Nav-items{
    display: flex;
    align-items: center;
    line-height: 1.3rem;
    font-size: 1.3rem;
    list-style: none;
  }
  .item{
    box-sizing: border-box;
    margin: 0 1rem 0 1rem;
    padding: 0;
    /*font-weight: bold;*/
    color: white;
    /*font-family: 华文细黑;*/
  }
  .Logo{
    margin-right: 40px;
    font-size: 14px;
    width: 40px;
    height: 24px;
    align-self: flex-start;
    margin-top: 12px;
  }
</style>
