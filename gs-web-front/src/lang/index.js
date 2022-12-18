import Vue from 'vue'
import VueI18n from 'vue-i18n'

// 引入自定义中文包
import customZH from './locale/zh'

// 引入自定义英文包
import customEN from './locale/en'

Vue.use(VueI18n)

// 准备翻译的语言环境信息
const messages = {
  en: {
    ...customEN // 将自定义英文包加入
  },
  zh: {
    ...customZH // 将自定义英文包加入
  }
}

// 通过选项创建 VueI18n 实例
const i18n = new VueI18n({
  locale: 'zh', // 设置地区
  messages // 设置地区信息
})

export default i18n
