// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// import VueAxios from 'vue-axios'
import {Input, Icon, Button, Loading, Message, Select, Option, Dialog, Tabs, TabPane, Card, Upload} from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import * as echarts from 'echarts'

var axios = require('axios')

Vue.prototype.$axios = axios
Vue.prototype.$loading = Loading
Vue.prototype.$message = Message
Vue.prototype.$echarts = echarts

// axios.defaults.withCredentials = true
axios.defaults.baseURL = 'http://175.24.165.47:6480/'
Vue.config.productionTip = false
Vue.use(router)
// element-ui
Vue.use(Input)
Vue.use(Icon)
Vue.use(Button)
Vue.use(Select)
Vue.use(Option)
Vue.use(Dialog)
Vue.use(Tabs)
Vue.use(TabPane)
Vue.use(Card)
Vue.use(Upload)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  render: h => h(App),
  template: '<App/>'
})
