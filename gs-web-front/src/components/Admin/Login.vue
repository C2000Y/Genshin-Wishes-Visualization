<template>
  <div class="area">
    <el-button class="login-section" @click="login" :disabled="able">
      <div class="id-container" onClick="event.cancelBubble = true">
        <div class="id-desc">
          你的身份：
        </div>
        <el-input v-model="loginForm.username" placeholder="你是？" class="id-input"></el-input>
      </div>
      <div style="height: 10px"></div>
      <div class="id-container" onClick="event.cancelBubble = true">
        <div class="id-desc">
          暗号：
        </div>
        <el-input v-model="loginForm.password" placeholder="请对暗号..." class="id-input" show-password ></el-input>
      </div>
    </el-button>
  </div>
</template>

<script>
import {getCookie, setCookie} from '../../util/util'

export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      able: false
    }
  },
  mounted () {
    // this.getCookieFunc()
  },
  methods: {
    login () {
      this.$axios
        .post('/user/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(res => {
          let expireDays = 60 * 60 * 24
          if (res.data.code === 0) {
            // console.log(res)
            setCookie('user', this.loginForm.username.toString(), expireDays) // 设置Session
            setCookie('u_uuid', '1', expireDays) // 设置用户编号
            // console.log('user', getCookie('user'))
            // console.log('u_uuid', getCookie('u_uuid'))
            this.$router.push('/admin/setting') // 跳转设置
          } else {
            console.log('fail')
          }
        })
        .catch(failResponse => {
        })
      // console.log(this.username, this.password)
      // this.able = true
      // const cond = true // 暂时设置为true
      // // this.$axios.get() // 发送账户和密码到后端，后端处理后发回成功或失败
      // if (cond === true) {
      //   // TODO: 进入设置
      //   let expireDays = 60 * 60 * 24
      //   // 用发回配置生成cookie和session
      //   if (this.username === 'admin' && this.password === 'json') {
      //     setCookie('session', 1, expireDays) // 设置Session
      //     setCookie('u_uuid', 1, expireDays) // 设置用户编号
      //     this.$router.push('/admin/setting') // 跳转设置
      //   }
      // } else {
      // // 验证失败
      // }
    }
    // getCookieFunc () {
    //   console.log(document.cookie)
    //   if (document.cookie.length > 0) {
    //     var arr = document.cookie.split('; ') // 这里显示的格式需要切割一下自己可输出看下
    //     for (var i = 0; i < arr.length; i++) {
    //       var arr2 = arr[i].split('=') // 再次切割
    //       // 判断查找相对应的值
    //       if (arr2[0] === 'userName') {
    //         this.loginCode = arr2[1] // 保存到保存数据的地方
    //       } else if (arr2[0] === 'userPwd') {
    //         this.loginPwd = arr2[1]
    //       }
    //     }
    //   }
    // }
  }
}
</script>

<style scoped>
.area{
  position: relative;
  height: 100vh;
  width: 100%;
}
.login-section{
  /*height: 100%;*/
  /*max-width: 1330px;*/
  position: relative;
  width: 30%;
  min-width: 460px;
  height: calc(12vh);
  min-height: 125px;
  /*min-height: 600px;*/
  /*margin-right: auto;*/
  /*margin-left: auto;*/
  background-color: rgba(245,245,245,0.8);
  top: 25%;
  margin: 0 auto;
  border-radius: 10px;
  font-family: 微软雅黑;
  /*padding: 1vh 0 0 0;*/
}
.id-container{
  display: flex;
}
.id-desc{
  font-size: 1rem;
  font-weight: bold;
  min-width: 80px;
  width: 25%;
  line-height: 40px;
}
.id-input{
  width: 67%;
}
/*.el-button:active {*/
/*  color: white !important;*/
/*  border-color: #3a8ee6;*/
/*  outline: 0;*/
/*}*/
/*.el-button:focus, .el-button:hover {*/
/*  color: white !important;*/
/*  border-color: rgba(164,190,227,0.9) !important;*/
/*  background: rgba(164,190,227,0.9) !important;*/
/*}*/
@media screen and (max-width: 460px){
  .login-section{
    width: 100%;
    min-width: unset;
  }
}
</style>
