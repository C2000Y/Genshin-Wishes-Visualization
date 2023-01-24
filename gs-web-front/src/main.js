// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
// import {Message} from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'
// import VueElementLoading from 'vue-element-loading'
import * as echarts from 'echarts'
import VueClipboard from 'vue-clipboard2'
import './assets/font/text.css'
import common from './components/Common'
import i18n from '@/lang'

Vue.prototype.$common = common

VueClipboard.config.autoSetContainer = true // add this line
Vue.use(VueClipboard)

Vue.prototype.$axios = axios
// Vue.prototype.$loading = VueElementLoading
// Vue.prototype.$message = message
Vue.prototype.$echarts = echarts

// axios.defaults.withCredentials = true
axios.defaults.baseURL = 'http://20.163.229.170:6480/'
// axios.defaults.baseURL = 'http://localhost:6480/'
Vue.config.productionTip = false
// Vue.use(router)
// Vue.prototype.$route = router
// // element-ui
// Vue.use(Input)
// Vue.use(Icon)
// Vue.use(Button)
// Vue.use(Select)
// Vue.use(Option)
// Vue.use(Dialog)
// Vue.use(Tabs)
// Vue.use(TabPane)
// Vue.use(Card)
// Vue.use(Upload)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  i18n,
  components: {App},
  render: h => h(App),
  template: '<App/>'
})
