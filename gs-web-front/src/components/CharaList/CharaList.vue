<template>
  <div class="list">
<!--    <li v-for="(char, index) of CharaList" :key="index" :class='charaImg[char.name].rank==5?"five-star":"four-star"'>-->
    <li v-for="(char, index) of CharaList" :key="index" :class='charaImg[char.name].rank==5?"five-star item":"four-star item"'>
      <div class="item-head">
        <div class="item-count">
          {{char.count}}
        </div>
        <img class="img" :src=charaImg[char.name].site>
<!--         {{char.name}}-->
      </div>
    </li>
  </div>
</template>

<script>
import charaImgJson from '../../assets/CharacterImage.json'
export default {
  name: 'CharaList',
  data () {
    return {
      charaImgJson,
      charaImg: {'': [{}]},
      CharaList: ''
    }
  },
  mounted () {
    // this.$axios.get('../../assets/CharacterImage.json').then(res => {
    //   console.log(res)
    // })
    const {data} = this.charaImgJson
    this.charaImg = data
    console.log(data)
  },
  methods: {
    getCharaListData (data) {
      console.log('From character list')
      console.log(data)
      this.CharaList = data
      console.log(data)
    }
  }
}
</script>

<style scoped>
.list{
  text-align: left;
  margin:  10px 0 10px 0;
}
.item{
  position: relative;
  height: 60px;
  width: 44px;
}
.item-head{
  width: 100%;
  height: 100%;
}
.item {
  position: relative;
  height: 65px;
  width: 60px;
  margin: 0 10px 10px 0;
  border-radius: 5px;
  display: inline-block;
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
  border-radius: 0 0 0 10px;
  color: white;
  font-size: 0.8rem;
  line-height: 15px;
  text-align: center;
}
.img{
  width: 100%;
  padding-top: 5px;
}
</style>
