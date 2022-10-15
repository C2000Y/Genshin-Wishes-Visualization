<template>
  <div class="content">
    <div class="list" v-if="jsonFlag">
      <div v-for='(item, index) in charaStrList' :key="charaStrList[index]" class="list-item">
        <div v-if="charaList[charaStrList[index]]!=undefined" :class='charaList[charaStrList[index]].rank===5?"five-star list-item":"four-star list-item"'>
          <el-tooltip effect="light" :content='charaStrList[index]' placement="bottom" :open-delay=100>
            <div class="item-head" @click="change(charaStrList[index])">
              <img class="img" :src='charaList[charaStrList[index]].url' :id="charaStrList[index]"/>
            </div>
          </el-tooltip>
        </div>
        <div v-else class="list-item">
          <el-tooltip effect="light" :content='charaStrList[index]' placement="bottom" :open-delay=100>
            <div class="item-head">
              <img class="img" src="https://cdn-icons-png.flaticon.com/512/2748/2748441.png"/>
            </div>
          </el-tooltip>
        </div>
      </div>
      <div class="list-item">
        <div class="item-head five-star list-item" @click="addDict">
            <img class="img" src="https://icons.iconarchive.com/icons/custom-icon-design/mono-general-1/512/add-icon.png" style="padding: 2px 0 3px 0"/>
        </div>
      </div>
    </div>
    <div v-else>
      加载中~
    </div>
<!--    <div>-->
<!--      <el-input-->
<!--        type="textarea"-->
<!--        placeholder="请输入内容"-->
<!--        v-model="strList"-->
<!--        :rows="20"-->
<!--        resize="none">-->
<!--      </el-input>-->
<!--    </div>-->
<!--    <div class="data-change">-->
<!--      <el-button type="primary" @click="update">测试</el-button>-->
<!--      <el-button type="success" icon="el-icon-check" circle @click="upload"></el-button>-->
<!--    </div>-->
    <el-dialog
      :title="isChange ? '修改': '添加'"
      :visible.sync="dialogTableVisible"
      :lock-scroll="true"
      :before-close="changeClose">
      <div class="box">
        <div class="item">
          <div class="item-info front">
            ID:
          </div>
          <div class="item-info back" v-if="isChange">
            {{EditObject.id}}
          </div>
          <div class="item-info back" v-else>
            N/A
          </div>
        </div>
        <div class="item">
          <div class="item-info front">
            name:
          </div>
          <div class="item-info back">
            <el-input v-model = "EditObject.name" placeholder="名称，必填"></el-input>
          </div>
        </div>
        <div class="item">
          <div class="item-info front">
            url:
          </div>
          <div class="item-info back">
            <el-input class="input-url" type="textarea" resize="none" v-model = "EditObject.url" style="width: 100%" placeholder="网址，必填"></el-input>
          </div>
        </div>
        <div class="item">
          <div class="item-info front">
            rank:
          </div>
          <div class="item-info back">
            <el-input v-model = "EditObject.rank" placeholder="星级，必填"></el-input>
          </div>
        </div>
        <div class="item">
          <div class="item-info front">
            type:
          </div>
          <div class="item-info back">
            <el-input v-model = "EditObject.type" placeholder="对象类型，必填"></el-input>
          </div>
        </div>
        <div class="item">
          <div class="item-info front">
            name_EN:
          </div>
          <div class="item-info back">
            <el-input v-model = "EditObject.nameEn" placeholder="英文名，选填"></el-input>
          </div>
        </div>
      </div>
      <div class="data-change">
        <el-button type="danger" icon="el-icon-delete" circle @click="changeDelete" style="margin-right: 30px" v-if="isChange === true"></el-button>
        <el-button type="primary" icon="el-icon-close" circle @click="changeClose"></el-button>
        <el-button type="success" icon="el-icon-check" circle @click="changeUpload" v-if="isChange"></el-button>
        <el-button type="success" icon="el-icon-check" circle @click="changeCreate" v-else></el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Setting',
  data () {
    return {
      strList: '',
      charaList: {},
      charaStrList: {},
      jsonFlag: false,
      dialogTableVisible: false,
      EditObject: {},
      isChange: true
    }
  },
  mounted () {
  },
  async created () {
    await this.getData()
  },
  methods: {
    change (objName) {
      this.EditObject = {...this.charaList[objName]}
      this.isChange = true
      this.dialogTableVisible = true
    },
    changeClose () {
      this.EditObject = {}
      this.dialogTableVisible = false
    },
    // async test () {
    //   while (this.$common.hasData() !== true) {
    //     await new Promise(resolve => setTimeout(resolve, 300))
    //   }
    //   console.log(this.charaImg)
    //   // for (let i = 0; i < this.charaStrList.length; i++) {
    //   //   console.log(this.charaStrList[i].name, this.charaImg[this.CharaList[i].name])
    //   // }
    // },
    // update () {
    //   // 把json放在后端，开始时先获取，更新时丢到后端
    //   this.charaList = this.strList
    // },
    async getData () {
      this.$common.resetHasJsData()
      this.$common.getImgJson().then(res => {
        this.charaList = {...res}
      })
      this.$axios.get('file/getAllItem').then(res => {
        this.charaStrList = res.data.data
        this.jsonFlag = true
      })
    },
    async changeUpload () {
      if (this.EditObject.name !== '' && this.EditObject.name !== undefined && this.EditObject.url !== '' && this.EditObject.url !== undefined && this.EditObject.rank !== '' && this.EditObject.rank !== undefined && this.EditObject.type !== '' && this.EditObject.type !== undefined) {
        this.$axios.put('file/updateDictionary', this.EditObject).then(res => {
          // console.log(res)
          if (res.data.data === 1) {
            ELEMENT.Message.success('修改成功')
            this.changeClose()
            this.getData()
          } else {
            ELEMENT.Message.error('修改失败喽~')
          }
        })
      } else {
        ELEMENT.Message.error('数据不全')
      }
      // this.jsonFlag = false
      // const data = await this.$axios({
      //   method: 'post',
      //   url: 'file/updateImgJson',
      //   data: this.strList
      // })
      // await this.uploadData(data)
    },
    async changeDelete () {
      console.log(this.EditObject)
      this.$axios.put('file/deleteDictionary', this.EditObject).then(res => {
        // console.log(res)
        if (res.data.data === 1) {
          ELEMENT.Message.success('删除成功')
          this.changeClose()
          this.getData()
        } else {
          ELEMENT.Message.error('删除失败喽~')
        }
      })
    },
    async changeCreate () {
      if (this.EditObject.name !== '' && this.EditObject.name !== undefined && this.EditObject.url !== '' && this.EditObject.url !== undefined && this.EditObject.rank !== '' && this.EditObject.rank !== undefined && this.EditObject.type !== '' && this.EditObject.type !== undefined) {
        this.$axios.post('file/addDictionary', this.EditObject).then(res => {
          // console.log(res)
          if (res.data.data === 1) {
            ELEMENT.Message.success('添加成功')
            this.changeClose()
            this.getData()
          } else {
            ELEMENT.Message.error('添加失败喽~')
          }
        })
      } else {
        ELEMENT.Message.error('数据不全')
      }
    },
    addDict () {
      this.EditObject = {name: '', url: '', rank: '', type: '', nameEn: ''}
      this.isChange = false
      this.dialogTableVisible = true
    }
  }
}
</script>

<style scoped>
.list{
  text-align: left;
}
.content{
  min-height: 700px;
  max-width: 1920px;
  width: 90%;
  margin-right: auto;
  margin-left: auto;
  background-color: rgba(245, 245, 245, 0.8);
  padding: 20px 10px 10px 10px;
}
.item-head{
  width: 100%;
  height: 100%;
}
.five-star{
  background: rgb(178,134,81);
}
.four-star{
  background: rgb(123,103,158);
}
.list-item {
  position: relative;
  max-height: 65px;
  width: 60px;
  margin: 0 10px 10px 0;
  border-radius: 5px;
  display: inline-block;
  cursor: pointer;
}
.img{
  width: 100%;
  padding-top: 5px;
}
.data-change{
  margin: 15px 0 5px 0;
}
.item-info{
  display: inline-block;
}
/deep/ .el-dialog{
  width: 40%;
}
.box{
  position: relative;
  text-align: left;
  padding: 0 15px;
}
.item{
  margin: 10px 0;
}
.front{
  width: 15%;
}
.back{
  width: 80%;
}
@media screen and (max-width: 1920px){
  .content{
    max-width: 1330px;
    width: 90%;
    min-width: 1200px;
  }
}
@media screen and (max-width: 1200px){
  .content{
    width: 100%;
    min-width: unset;
  }
}
@media screen and (max-width: 790px){
  .content{
    width: 100%;
    min-width: unset;
  }
}

@media screen and (max-width: 1200px){
  /deep/ .el-dialog{
    width: 80%;
  }
}

@media screen and (max-width: 790px){
  /deep/ .el-dialog{
    width: 100%;
  }
  .item-info{
    display: unset;
  }
  .input-url /deep/ textarea{
    height: 100px;
  }
}
</style>
