import { GETUSERINFO } from './mutation-types'

export default {
  [GETUSERINFO] (state, { info, isLogin }) {
    state.userinfo = info
    state.isLogin = isLogin
  }
}
