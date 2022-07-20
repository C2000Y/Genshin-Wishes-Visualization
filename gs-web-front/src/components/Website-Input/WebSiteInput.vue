<template>
  <div style="width: 56px; height: 56px; position: relative; float: right">
    <div class="setting">
      <el-button class="el-icon-setting" @click="dialogTableVisible = true" style=" width: 52px; height: 52px; padding: 2px 2px 2px 2px; border-radius: 50%; border: unset"></el-button>
    </div>
    <el-dialog title="上传数据" :visible.sync="dialogTableVisible" :lock-scroll="true" class="web-upload-dialog-box">
      <el-tabs v-model="activeName" type="border-card" style="height: 350px;" class="web-upload-dialog">
        <el-tab-pane label="文件上传(PC端)" name="first">
          <div class="tips-words box-card">
            <form action='dic/uploadWord' enctype='multipart/form-data' method='post' id="fileUpload">
              <el-upload
                drag
                name='file'
                ref="upload"
                :file-list="fileList"
                accept=".txt"
                action=""
                :limit="1"
                :on-exceed="handleExceed"
                :auto-upload="false"
                :on-change="handleFileChange"
                :http-request="handleUpload"
              >
                <!--<el-button slot="trigger" size="small" type="primary">选取文件</el-button>-->
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <!--<div slot="tip" class="el-upload__tip">点击按钮即可开导</div>-->
              </el-upload>
            </form>
            <div>
              第一步：登录原神，打开【祈愿】界面
            </div>
            <div>
              第二步：点击【历史记录】
            </div>
            <div>
              第三步：点击【点击上传】，<br>
              在文件名处输入：<b>%USERPROFILE%\AppData\LocalLow\miHoYo\原神\output_log.txt</b>
              <el-button icon="el-icon-document-copy" @click="doCopy" class="copy"></el-button>
              <img src="../../../static/enter.png" style="width: 100%; margin-top: 7px">
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="网址(安卓, PC)" name="second" id="web-input">
          <android @return="insertData" id="web-input-bar"/>
          <div class="tips-words box-card">
            <div>
              第一步：登录原神，打开【祈愿】界面，点击【历史记录】
            </div>
            <div>
              第二步：关闭所有网络连接（例，飞行模式）
            </div>
            <div>
              第三步：点击【历史记录】右上角的刷新按钮
            </div>
            <div>
              第四步：【历史记录】界面出现错误，并显示一串链接
              <img src="static/link-min.jpg" style="width: 95%; margin-top: 7px">
            </div>
            <div>
              第五步：复制这段链接，粘贴至上面的输入栏中
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="iOS端" name="third">
          这个选项只是想告诉你，不是作者忘了iOS系统的存在，而是确实不知道苹果用户要怎么查。
        </el-tab-pane>
      </el-tabs>
    </el-dialog>

    <!--<el-input placeholder="请输入网址" v-model="input" clearable class="web-input">-->
      <!--<el-button slot="prepend" @click="dialogTableVisible = true"><i class="el-icon-question"></i> 教程</el-button>-->
      <!--<el-button slot="append" @click="clickHandler"><i class="el-icon-search"></i> 查找</el-button>-->
    <!--</el-input>-->
    <!--<el-dialog title="获取网址的方式" :visible.sync="dialogTableVisible" width="800px">-->
    <!--</el-dialog>-->
  </div>
</template>

<script>
import Android from './Android'
import Loading from '../Effect/Loading'
export default {
  name: 'WebSiteInput',
  components: {
    Android, Loading
  },
  data () {
    return {
      input: '',
      isActive: true,
      uid: undefined,
      dialogTableVisible: false,
      activeName: 'first',
      fileList: [],
      textData: '',
      file: '',
      isLoading: false,
      message: '%USERPROFILE%\\AppData\\LocalLow\\miHoYo\\原神\\output_log.txt'
    }
  },
  methods: {
    clickHandler () {
      // if (this.input !== '') {
      //   this.$loading.service({ fullscreen: true, text: '加载时间取决于您近6个月的抽卡次数(1000抽需要20秒)，请耐心等待~' })
      //   this.$axios.get('summon/writeIn', {
      //     params: {
      //       url: this.input
      //     }
      //   }).then(res => {
      //     this.input = ''
      //     ELEMENT.Message({
      //       message: res.data.data === 0 ? '录入失败' : '录入完成',
      //       type: res.data.data === 0 ? 'error' : 'success'
      //     })
      //     this.uid = res.data.data
      //     this.$loading.service().close()
      //     this.$emit('update', this.uid)
      //   })
      // } else {
      //   ELEMENT.Message({message: '请输入网址', type: 'warning'})
      // }
    },
    handleExceed () {
      ELEMENT.Message.warning('一次只能上传一个哦')
    },
    handleUpload (file) {
      // reader.readAsText(file)
      // console.log(file)
      // let reader = new FileReader()
      // reader.readAsText(file.raw, 'gb2312')
      // reader.readAsArrayBuffer(file.raw)
      // reader.onload = function (e) {
      //   var ints = new Uint8Array(e.target.result) // 要使用读取的内容，所以将读取内容转化成Uint8Array
      //   ints = ints.slice(0, 5000) // 截取一段读取的内容
      //   let snippets = new TextDecoder('gb2312').decode(ints) // 二进制缓存区内容转化成中文（即也就是读取到的内容）
      //   console.log('读取的内容如下：')
      //   console.log(snippets)
      // }
    },
    handleFileChange (file) {
      // 判断后缀名是否为txt
      if (file.name.split('.').pop() !== 'txt') {
        this.dataValidation({data: {code: 500, msg: '文件类型错误'}})
        this.$refs.upload.clearFiles()
        return
      }
      // console.log('读取文件', file)
      const reader = new FileReader()
      let url = 'empty'
      reader.readAsText(file.raw, 'utf-8')
      // 分行读取，匹配信息
      reader.onload = () => {
        const result = reader.result.split(/\r\n|\n/)
        for (let line = 0; line < result.length - 1; line++) {
          // 读到关键数据后，将值赋予变量url
          if (result[line].includes('#/log')) {
            url = result[line]
          }
        }
        // 如果变量url没有改变，说明文件中缺少关键信息
        if (url === 'empty') {
          this.dataValidation({data: {code: 500, msg: '文件中的关键信息不存在'}})
        } else {
          this.insertData(url)
        }
      }
      this.$refs.upload.clearFiles()
    },
    async insertData (url) {
      ELEMENT.Loading.service({ fullscreen: true, text: '加载时间取决于您近6个月的抽卡次数(1000抽需要20秒)，请耐心等待~' })
      console.log(url)
      url = encodeURI(url)
      console.log(url)
      // 模仿单文件上传给接口传参
      const res = await this.$axios({
        url: '/file/reader',
        method: 'post',
        // contentType: false,
        // processData: false,
        data: url
      })
      this.dataValidation(res)
    },
    async dataValidation (res) {
      switch (res.data.code) {
        case 500: {
          ELEMENT.Loading.service().close()
          ELEMENT.Message.error('录入失败，失败原因：' + res.data.msg)
          return false
        }
        case 0: {
          ELEMENT.Message.success('录入成功')
          this.uid = res.data.data
          this.dialogTableVisible = false
          const name = await this.$axios({
            url: '/uid/getName?uid=' + this.uid,
            method: 'get',
            contentType: false,
            processData: false
          })
          await this.open(name.data.data, this.uid)
          ELEMENT.Loading.service().close()
          this.$emit('update', this.uid, 0)
          return true
        }
      }
    },
    doCopy () {
      this.$copyText(this.message).then(
        function (e) {
          console.log(e)
          ELEMENT.Message.success('复制成功')
        }, function (e) {
          console.log(e)
          ELEMENT.Message.error('复制失败')
        }
      )
    },
    async open (name, uid) {
      this.$prompt('输入昵称', '提示', {
        inputValue: name,
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        if (value === name) {
          ELEMENT.Message.info('取消修改')
        } else {
          this.$axios({
            url: '/uid/rewriteName?uid=' + this.uid + '&name=' + value,
            method: 'get',
            contentType: false,
            processData: false
          }).then(res => {
            console.log(res)
            if (res.data.data === 0) {
              ELEMENT.Message.success('昵称修改为: ' + value)
              this.$emit('update', uid, -1)
            } else {
              ELEMENT.Message.error('修改失败')
            }
          })
        }
      }).catch(() => {
        ELEMENT.Message.info('取消修改')
      })
    }
  }
}
</script>

<style scoped>
  .web-input{
    width: 800px;
  }
  .tips-words{
    text-align: left;
    overflow-y: scroll;
    /*height: 325px;*/
  }
  .box-card{
    overflow-y: scroll;
    height: 280px;
  }
  .tips-words div{
    font-size: 14px;
    margin-bottom: 10px;
  }
  .el-icon-setting{
    font-size: 35px;
    /*position: absolute;*/
    /*float: right;*/
  }
  /deep/ .el-upload-dragger{
   width: 700px;
    height: 150px;
    background: white;
  }
  .el-icon-upload{
    margin: 26px 0 17px;
  }
  /deep/ .setting .el-button{
    background: transparent;
    /*color: white;*/
  }
  /deep/.el-button.is-circle{
    padding: 6px;
  }
  .copy{
    height: 22px;
    width: 22px;
    padding: 1px;
    border: unset;
    color: rgb(78,164,220);
  }
  #web-input-bar{
    margin-bottom: 13px;
  }
  #web-input .box-card{
    overflow-y: scroll;
    height: 225px;
  }
  /deep/ .el-dialog{
    width: 800px;
  }
  @media screen and (max-width: 800px){
    /deep/ .web-upload-dialog-box .el-dialog{
      width: 42rem;
    }
    /deep/ .web-upload-dialog-box .el-upload-dragger{
      width: 36rem;
    }
    .web-upload-dialog{
      width: 39rem;
    }
  }
  @media screen and (max-width: 675px){
    /deep/ .web-upload-dialog-box .el-dialog{
      width: 30rem;
    }
    /deep/ .web-upload-dialog-box .el-upload-dragger{
      width: 24.5rem;
    }
    .web-upload-dialog{
      width: 27.5rem;
    }
  }
  @media screen and (max-width: 480px){
    /deep/ .web-upload-dialog-box .el-dialog{
      width: 23rem;
    }
    /deep/ .web-upload-dialog-box .el-upload-dragger{
      width: 18rem;
    }
    .web-upload-dialog{
      width: 21rem;
    }
  }
</style>
