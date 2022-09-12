<template>
  <div class="list">
<!--    <li v-for="(char, index) of CharaList" :key="index" :class='charaImg[char.name].rank==5?"five-star":"four-star"'>-->
<!--    <div>-->
      <div v-for='(char) in CharaList' :key="char.name" :class='charaImg[char.name].rank===5?"five-star list-item":"four-star list-item"'>
        <el-button @click='clickPop(char.name)' :ref='`bt`+char.name'>
          <div class="item-head">
            <div class="item-count">
              {{char.count}}
            </div>
            <img class="img" :src='charaImg[char.name].site' :id="char.name"/>
            <!--         {{char.name}}-->
          </div>
        </el-button>
      </div>
      <el-popover
        v-if='showPop'
        ref='pop'
        :reference='reference'
        placement="top-start"
        :title="name"
        trigger="click"
        popper-class="pop-class"
      >
      </el-popover>
    </div>
<!--    <el-popover-->
<!--      ref="pop"-->
<!--      placement="bottom"-->
<!--      title="标题"-->
<!--      width="200"-->
<!--      trigger="manual"-->
<!--      :content="name"-->
<!--      v-model="visible"-->
<!--      :append-to-body="false"-->
<!--    />-->
<!--    <div v-for="(char, index) of CharaList" :key="index" :class='charaImg[char.name].rank==5?"five-star item":"four-star item"' @mousedown="showDetail" @mouseup="notShowDetail" :ref='`bt`+char.name'>-->
<!--      <div class="item-head">-->
<!--        <div class="item-count">-->
<!--          {{char.count}}-->
<!--        </div>-->
<!--        <img class="img" :src=charaImg[char.name].site :id="char.name">-->
<!--&lt;!&ndash;         {{char.name}}&ndash;&gt;-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
</template>

<script>
import charaImgJson from '../../assets/CharacterImage.json'
export default {
  name: 'CharaList',
  data () {
    return {
      charaImgJson,
      charaImg: {'胡桃': {site: '', rank: 5}},
      CharaList: '',
      visible: false,
      name: '',
      list: ['1', '2', '3'],
      reference: {},
      // 控制渲染条件 如果不用v-if会报错 具体可自行尝试
      showPop: false,
      // 保存当前激活的refrence id
      activeId: ''
    }
  },
  mounted () {
    // this.$axios.get('../../assets/CharacterImage.json').then(res => {
    //   console.log(res)
    // })
    const {data} = this.charaImgJson
    this.charaImg = data
    // console.log(this.charaImg)
  },
  methods: {
    getCharaListData (data) {
      console.log('From character list')
      console.log(data)
      this.CharaList = data
    },
    showDetail (e) {
      this.visible = true
      this.name = e.target.id
      console.log(e.target.id)
    },
    notShowDetail (e) {
      this.visible = false
      console.log(e)
    },
    clickPop (item) {
      this.name = item
      // 这个操作是为了避免与源码中的点击reference doToggle方法冲突
      if (this.activeId === item && this.showPop) return
      this.showPop = false
      this.activeId = item
      // 因为reference是需要获取dom的引用 所以需要是$el
      this.reference = this.$refs['bt' + item][0].$el
      this.$nextTick(() => {
        // 等待显示的popover销毁后再 重新渲染新的popover
        this.showPop = true
        this.$nextTick(() => {
          // 此时才能获取refs引用
          this.$refs.pop.doShow()
        })
      })
    }
  }
}
</script>

<style scoped>
.list{
  text-align: left;
  margin:  10px 0 10px 0;
}
.item-head{
  width: 100%;
  height: 100%;
}
.list-item {
  position: relative;
  height: 65px;
  width: 60px;
  margin: 0 10px 10px 0;
  border-radius: 5px;
  display: inline-block;
  cursor: pointer;
}
.five-star{
  background: rgb(178,134,81);
}
.four-star{
  background: rgb(123,103,158);
}
.item-count{
  position: absolute;
  right: 0;
  height: 15px;
  width: 20px;
  background: rgba(0,0,0,0.6);
  border-radius: 0 0 0 6px;
  color: white;
  font-size: 0.8rem;
  line-height: 15px;
  text-align: center;
}
.img{
  width: 100%;
  padding-top: 5px;
}
.list-item .el-button{
  white-space: unset;
  padding: unset;
  background: transparent;
  line-height: unset;
  height: 100%;
  width: 100%;
  border: unset;
}
@media screen and (max-width: 600px){
  .list-item{
    height: 40px;
    width: 40px;
  }
  .img{
    padding-top: unset;
  }
}
</style>
<style >
.pop-class {
  background: -webkit-gradient(linear, left top, right top, from(#ACB6E5), to(#74ebd5)) !important;
  background: linear-gradient(to right, #ACB6E5, #74ebd5) !important;
  min-width: 100px !important;
  border: transparent !important;
}
.popper__arrow{
  border-top-color: #ACB6E5 !important;
}
.el-popper[x-placement^=top] .popper__arrow::after {
  border-top-color: transparent !important;
}
.el-popover__title {
  color: white !important;
  /*font-weight: bold;*/
  font-family: JXZK;
}
</style>
