import {login} from '@/service/api'
export default {
  async getUserInfo ({ state, commit }, payload) {
    let res = await login(payload)
    if (res.result.code === 10000) {
      commit('GETUSERINFO', { info: res.data, isLogin: true })
    } else {
      commit('GETUSERINFO', { info: res, isLogin: false })
    }
  }
}
