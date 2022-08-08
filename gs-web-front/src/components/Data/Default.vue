<template>
    <div>
      <div class="announcement">
        提供直观的原神抽卡数据分析
      </div>
      <div>
      </div>
      <div>
        <Comment class="commentSection" :uid="100105164"/>
      </div>
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
