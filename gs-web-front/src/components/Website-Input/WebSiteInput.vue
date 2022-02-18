<template>
  <div style="width: 56px; height: 56px; position: absolute; right: 0">
    <div class="setting">
      <el-button class="el-icon-setting setting-icon" @click="dialogTableVisible = true" style=" width: 52px; height: 52px; padding: 2px 2px 2px 2px; border-radius: 50%; border: unset"></el-button>
    </div>
    <el-dialog title="上传数据" :visible.sync="dialogTableVisible" width="800px" :lock-scroll="true">
      <el-tabs v-model="activeName" type="border-card" style="height: 350px;">
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
              第一步：登录原神（国服），打开【祈愿】界面
            </div>
            <div>
              第二步：点击【历史记录】
            </div>
            <div>
              第三步：点击【点击上传】，<br>
              在文件名处输入：<b>%USERPROFILE%\AppData\LocalLow\miHoYo\原神\output_log.txt</b>
              <el-button icon="el-icon-document-copy" @click="doCopy" class="copy"></el-button>
              <img src="../../../static/enter.png" style="width: 700px; margin-top: 7px">
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="网址(安卓, PC)" name="second">
          <android @return="dataValidation"/>
        </el-tab-pane>
        <el-tab-pane label="IOS端" name="third">
          太麻烦了，要咱不换个设备？
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
export default {
  name: 'WebSiteInput',
  components: {
    Android
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
      //     this.$message({
      //       message: res.data.data === 0 ? '录入失败' : '录入完成',
      //       type: res.data.data === 0 ? 'error' : 'success'
      //     })
      //     this.uid = res.data.data
      //     this.$loading.service().close()
      //     this.$emit('update', this.uid)
      //   })
      // } else {
      //   this.$message({message: '请输入网址', type: 'warning'})
      // }
    },
    handleExceed () {
      this.$message({message: '一次只能上传一个哦', type: 'warning'})
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
    handleFileChange () {
      this.$loading.service({ fullscreen: true, text: '加载时间取决于您近6个月的抽卡次数(1000抽需要20秒)，请耐心等待~' })
      var form = document.getElementById('fileUpload')
      console.log(form)
      const formdata = new FormData(form)
      this.insertData(formdata)
      this.$refs.upload.clearFiles()
    },
    async insertData (formdata) {
      // // 模仿单文件上传给接口传参
      const res = await this.$axios({
        url: '/file/reader',
        method: 'post',
        contentType: false,
        processData: false,
        data: formdata
      })
      this.dataValidation(res)
    },
    dataValidation (res) {
      this.$loading.service().close()
      switch (res.data.code) {
        case 500: {
          this.$message({
            message: '录入失败，失败原因：' + res.data.msg + '',
            type: 'error'
          })
          return false
        }
        case 0: {
          this.$message({
            message: '录入成功',
            type: 'success'
          })
          this.uid = res.data.data
          this.dialogTableVisible = false
          this.$emit('update', this.uid)
          return true
        }
      }
    },
    doCopy () {
      let that = this
      this.$copyText(this.message).then(
        function (e) {
          console.log(e)
          that.$message({
            message: '复制成功',
            type: 'success'
          })
        }, function (e) {
          console.log(e)
          that.$message({
            message: '复制失败',
            type: 'error'
          })
        }
      )
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
  @keyframes rotate {
    100% {
      transform: rotate(120deg);
    }
  }
  .setting-icon:hover{
    animation: rotate .5s;
    cursor: pointer;
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
    color: white;
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
</style>
