<template>
    <div>
      <div class="sendSection">
        <el-input
          class="textSection"
          type="textarea"
          :placeholder="placeholderText"
          v-model="commentText"
          maxlength="100"
          show-word-limit
          resize="none"
        >
        </el-input>
        <el-button type="text" :autofocus="false" @click.native="sendComment" :loading="sendLoading">发送</el-button>
      </div>
      <div class="commentArea" v-loading="commentLoading" element-loading-background="rgba(255, 255, 255, 0.5)" :element-loading-text="loadingText">
        <div v-for="(x, index) in data" :key="index">
          <div :class="x.isTop===1 ? 'aComment topComment' : 'aComment'">
            <div class="timeStamp" v-if="x.isTop===1" style="color: azure">[置顶 {{x.postDate}}]</div>
            <div class="timeStamp" v-else>[{{x.postDate}}]</div>
            <div class="commentText">{{x.comment}}</div>
          </div>
          <div class="bottomLine"></div>
        </div>
        <div class="end-line" @click="addPage">
          {{endLineText}}
        </div>
      </div>
    </div>
</template>

<script>
// import Loading from '../Effect/Loading'
export default {
  // components: {Loading},
  name: 'Comment',
  data () {
    return {
      tips: '',
      data: [],
      commentText: '',
      sendLoading: false,
      commentLoading: false,
      placeholderText: '',
      placeholderTextData: [
        '说点什么好呢?',
        '欧吃矛！',
        '骚话在嘴边说不出来的感觉如何？'
      ],
      commentInCD: false,
      loadingText: '寻找前人留下的痕迹',
      page: 1,
      isEndOfComment: false,
      endLineText: '点击探索未知的星空',
      countDown: 0
    }
  },
  props: ['uid'],
  mounted () {
    // let loadingText = this.loadingText[Math.floor(Math.random() * this.loadingText.length)]
    // ELEMENT.Loading.service({ fullscreen: true, text: loadingText })
    this.commentLoading = true
    this.getCommentSection(0)
    this.placeholderTextInit()
  },
  methods: {
    // status 0 代表初始化，status 1代表append
    getCommentSection (status) {
      this.tips = '↓下面是评论区↓'
      //  TODO: 从后端获取公告或者提示之类
      this.$axios.get('comment/getCommentList', {
        params: {
          uid: this.uid,
          page: this.page
        }
      }).then(res => {
        if (res.data.code === 0) {
          let data = res.data.data
          data = data.map((data) => {
            let date = new Date(data.postDate)
            let yr = date.getFullYear() + '-'
            let mon = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
            let day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
            // let hr = date.getHours() + ':'
            // let min = date.getMinutes() + ':'
            // let sec = date.getSeconds()
            // 如果评论发自一小时内
            let timeDiff = new Date(new Date().getTime() - data.postDate)
            // console.log(new Date().getTime(), data.postDate)
            let dateString = yr + mon + day
            if (timeDiff < 5 * 1000) {
              dateString = '刚刚'
            } else if (timeDiff < 60 * 1000) {
              dateString = Math.round(timeDiff / 1000) + '秒前'
            } else if (timeDiff < 60 * 60 * 1000) {
              dateString = Math.round(timeDiff / 60 / 1000) + '分钟前'
            } else if (timeDiff < 24 * 60 * 60 * 1000) {
              dateString = Math.round(timeDiff / 60 / 60 / 1000) + '小时前'
            }
            return {
              ...data, postDate: dateString
            }
          })
          if (data.length < 10) {
            this.isEndOfComment = true
            this.endLineText = '星空已尽在眼底'
          }
          status === 0 ? this.data = data : this.data.push.apply(this.data, data)
        }
        // ELEMENT.Loading.service().close()
        this.commentLoading = false
      })
    },
    async sendComment (event) {
      if (this.commentInCD) {
        //  TODO: 提示评论在cd
        ELEMENT.Message.error('发射塔冷却中，剩余：' + (60 - this.countDown) + '秒')
      } else {
        this.loading(true)
        // console.log(this.commentText)
        // let reg = /(^\\s)/
        if (this.commentText.trim() !== '') {
          this.$axios({
            method: 'post',
            url: 'comment/sendComment',
            data: {
              uid: 100105164,
              id: -1,
              pid: -1,
              comment: this.commentText
            }
          }).then(res => {
            if (res.data.code === 0) {
              this.commentCoolDown()
              this.getCommentSection(0)
            } else {
              ELEMENT.Notification.error({
                title: '消息被拦截了'
              })
            }
          })
        } else {
          ELEMENT.Notification.error({
            title: '发送失败，不可以发空白信息哦'
          })
        }
        this.loading(false)
        this.commentText = ''
      }
    },
    placeholderTextInit () {
      this.placeholderText = this.placeholderTextData[Math.floor(Math.random() * this.placeholderTextData.length)]
    },
    addPage () {
      if (this.isEndOfComment === false) {
        this.page++
        this.getCommentSection(1)
      }
    },
    async commentCoolDown () {
      this.commentInCD = true
      this.countDown = 0
      while (this.countDown < 60) {
        this.countDown++
        await new Promise(resolve => setTimeout(resolve, 1000))
      }
      this.countDown = 0
      this.commentInCD = false
    },
    loading (status) {
      this.commentLoading = status
      this.sendLoading = status
    }
  }
}
</script>

<style scoped>
  .sendSection{
    position: relative;
    margin: 0 auto;
    /*display: flex;*/
  }
  .textSection{
    width: 90%;
  }
  .commentArea{
    padding: 10px 5% 0 5%;
    position: relative;
    margin: 0 auto;
    min-height: 700px;
  }
  .aComment{
    margin-top: 10px;
    text-align: left;
    color: #537fb5;
    font-size: 1rem;
    font-family: 微软雅黑;
    /*text-shadow: -1px -1px 0 #fff, 1px -1px 0 #fff, -1px 1px 0 #fff, 1px 1px 0 #fff;*/
  }
  .timeStamp{
    font-size: 0.8rem;
    opacity: 0.6;
    margin-bottom: 2px;
  }
  .commentText{
    white-space: pre-wrap;
  }
  .bottomLine{
    border-bottom: 5px solid;
    margin-top: 14px;
    border-image: linear-gradient(to right, rgba(255,255,255,0.7), transparent) 1;
    width: 90%;
  }
  .end-line{
    position: relative;
    margin: 0 auto;
    cursor: pointer;
    width: 300px;
    padding: 10px 0 10px 0;
    color: rgb(128,209,200);
    background-image: radial-gradient(rgba(248,245,214,1), rgba(255,255,255,0.1), transparent);
  }
  .el-timeline{
    text-align: left;
  }
  .el-button{
    line-height: 28px;
  }
  .topComment{
    background-image: linear-gradient(rgba(83,127,181,0.4), rgba(83,127,181,0.2), transparent);
  }
  /deep/
  .el-timeline-item__tail{
    display: none;
  }
  /deep/
  .el-timeline-item__timestamp{
    color: #537fb5;
  }
  /deep/
  .el-timeline-item__node{
    background-color: #537fb5;
  }
  /deep/
  .el-timeline-item__node--normal{
    display: none;
  }

  /deep/ .el-loading-spinner{
    top: 20%;
  }

</style>
