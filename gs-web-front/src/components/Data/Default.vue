<template>
    <div>
      <div class="announcement">
        观星台讨论区
      </div>
      <div>
      </div>
      <div>
        <Comment class="commentSection" :uid="100105164"/>
      </div>
      <!--      TODO: 平均出金次数欧皇榜-->
      <!--      TODO: 平均出金次数非酋榜-->
      <!--      TODO: 最近6个月内，各个池子的出金次数欧皇榜，可以并列第一-->
      <!--      TODO: 最近6个月内，各个池子的出金次数非酋榜，可以并列第一-->
    </div>
</template>

<script>
import Comment from '../Chat/Comment'
export default {
  name: 'Default',
  components: {
    Comment
  }
}
</script>

<style scoped>
.announcement{
  padding: 10px 0 10px 0;
  position: relative;
  margin: 0 auto;
  width: 92%;
  text-align: left;
  color: #537fb5;
}
.commentSection{
  margin-top: 10px;
}
</style>
