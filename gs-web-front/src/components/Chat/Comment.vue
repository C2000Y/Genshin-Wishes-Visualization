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
        <el-button type="text" :autofocus="false" @click.native="sendComment" :loading="isLoading">发送</el-button>
      </div>
      <div class="commentArea">
        <div v-for="(x, index) in data" :key="index">
          <div class = "aComment">
            <div class="timeStamp">[{{x.postDate}}]</div>
            <div class="commentText">{{x.comment}}</div>
          </div>
          <div class="bottomLine"></div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: 'Comment',
  data () {
    return {
      tips: '',
      data: [],
      commentText: '',
      isLoading: false,
      placeholderText: '',
      placeholderTextData: [
        '说点什么好呢?',
        '欧吃矛！',
        // '你知道吗？在稻妻大火的《沉秋拾剑录》曾在璃月别人调侃为字典',
        '骚话在嘴边说不出来的感觉如何？'
      ],
      commentInCD: false,
      loadingText: [
        '寻找前人留下的痕迹'
      ]
    }
  },
  props: ['uid'],
  mounted () {
    let loadingText = this.loadingText[Math.floor(Math.random() * this.loadingText.length)]
    ELEMENT.Loading.service({ fullscreen: true, text: loadingText })
    this.initializeCommentSection()
    this.placeholderTextInit()
  },
  methods: {
    initializeCommentSection () {
      this.tips = '↓下面是评论区↓'
      //  TODO: 从后端获取公告或者提示之类
      this.$axios.get('comment/getCommentList', {
        params: {
          uid: this.uid
        }
      }).then(res => {
        if (res.data.code === 0) {
          this.data = res.data.data
          this.data = this.data.map((data) => {
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
        }
        ELEMENT.Loading.service().close()
      })
    },
    async sendComment (event) {
      if (this.commentInCD) {
        //  TODO: 提示评论在cd
        alert('comment in cd')
      } else {
        this.isLoading = true
        // console.log(this.commentText)
        // let reg = /(^\\s)/
        if (this.commentText.trim() !== '') {
          this.$axios.get('comment/sendComment', {
            params: {
              uid: 100105164,
              id: -1,
              pid: -1,
              comment: this.commentText
            }
          }).then(res => {
            if (res.data.code === 0) {
              // this.commentCoolDown()
              this.initializeCommentSection()
            } else {
            // TODO: 发送失败的提示
              ELEMENT.Notification.error({
                title: '评论发送错误'
              })
            }
          })
        } else {
          alert('输入内容不符合规范')
        }
        this.commentText = ''
        this.isLoading = false
      }
    },
    placeholderTextInit () {
      this.placeholderText = this.placeholderTextData[Math.floor(Math.random() * this.placeholderTextData.length)]
    },
    // async commentCoolDown () {
    //   this.commentInCD = true
    //   let count = 0
    //   while (count < 5) {
    //     count++
    //     console.log(count)
    //     await new Promise(resolve => setTimeout(resolve, 10000))
    //   }
    //   this.commentInCD = false
    // }
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
    width: 90%;
    position: relative;
    margin: 0 auto;
  }
  .aComment{
    margin-top: 10px;
    text-align: left;
    color: #537fb5;
    font-size: 1rem;
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
  .el-timeline{
    text-align: left;
  }
  .el-button{
    line-height: 28px;
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

</style>
