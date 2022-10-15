import Vue from 'vue'
import VueRouter from 'vue-router'
// // 导入组件
// import Main from '../components/Main'
// import Login from "../views/Login";
// import NotFound from "../views/NotFound";
//
// import UserList from "../views/user/List";
// import UserProfile from "../views/user/Profile";
// import Index from "../views/chpts/index";
import OverallData from '../components/Data/OverallData'
import Default from '../components/Data/Default'
import CharacterData from '../components/Data/CharacterData'
import WeaponData from '../components/Data/WeaponData'
import StandardData from '../components/Data/StandardData'
import Login from '../components/Admin/Login'
import Setting from '../components/Admin/Setting'
import MainPage from '../components/MainPage'
import FourOFour from '../components/404'
// import store from '../store/index'
// import c3 from "../views/chpts/c3";
// import c4 from "../views/chpts/c4";
// import c5 from "../views/chpts/c5";
// import c6 from "../views/chpts/c6";
// import c7 from "../views/chpts/c7";
// import c8 from "../views/chpts/c8";
// import c9 from "../views/chpts/c9";
// import c10 from "../views/chpts/c10";
// import c11 from "../views/chpts/c11";
// import c12 from "../views/chpts/c12";
// import request from "../views/chpts/request"
import {delCookie, getCookie} from '@/util/util'
// // 使用
Vue.use(VueRouter)
// // 导出
const router = new VueRouter({
  mode: 'hash',
  routes: [
    {
      path: '/',
      redirect: '/main/overall'
    },
    {
      path: '/main',
      component: MainPage,
      children: [
        {
          path: '/main/default',
          component: Default
        },
        {
          path: '/main/overall',
          component: OverallData
        },
        {
          path: '/main/character',
          component: CharacterData
        },
        {
          path: '/main/weapon',
          component: WeaponData
        },
        {
          path: '/main/standard',
          component: StandardData
        }
      ]
    },
    {
      path: '/admin',
      redirect: '/admin/setting'
    },
    {
      path: '/admin/login',
      component: Login
    },
    {
      path: '/admin/setting',
      meta: {
        requireAuth: true
      },
      component: Setting
    },
    {
      path: '/404',
      component: FourOFour
    },
    {
      path: '*',
      redirect: '/404'
    }

    // {
    //   path: '/main/overall',
    //   component: OverallData
    // },
    // {
    //   path: '/main/character',
    //   component: CharacterData
    // },
    // {
    //   path: '/main/weapon',
    //   component: WeaponData
    // },
    // {
    //   path: '/main/standard',
    //   component: StandardData
    // },
    // {
    //   path: '/setting',
    //   component: Login
    // }
  ]
})

// router.beforeEach((to, from, next) => {
//   // 全局钩子函数
//   to.matched.some((route) => {
//     // to.matched.forEach((route) => {
//     // 通过此操作可以判断哪些页面需要登录
//     if (route.meta.need2Login) {
//       if (store.state.isLogin || sessionStorage.getItem('isLogin')) {
//         next()
//       } else {
//         next({name: 'login', params: {path: route.path}})
//       }
//     } else {
//       next()
//     }
//   })
// })

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    // console.log(to.meta.requireAuth)
    // 获取
    // fetch('admin/setting').then(res => {
    if (getCookie('user') === 'admin' && getCookie('u_uuid') === '1') {
      next()
    } else {
      if (getCookie('user')) {
        delCookie('user')
      }
      if (getCookie('u_uuid')) {
        delCookie('u_uuid')
      }
      next({
        path: '/admin/login'
      })
    }
    // })
  } else {
    next()
  }
})

export default router

// {
//       //登录页
//       path: '/main',
//       component: Main,
//       props:true,
//       children:[
//         {path: '/user/profile/:id',name:'UserProfile',component:UserProfile, props:true},
//         {path: '/user/List',component:UserList},
//       ]
//     },
//     //首页
//     {
//       path: '/login',
//       component: Login,
//       //嵌套路由
//     },{
//       path: '/goHome',
//       redirect: '/main'
//     }
//     // ,{
//     //   path: '*',
//     //   component: NotFound
//     // }
//     ,{
//       path: '/c1',
//       component: c1,
//     }
//     ,{
//       path: '/c2',
//       component: c2,
//     }
//     ,{
//       path: '/c3',
//       component: c3,
//     }
//     ,{
//       path: '/c4',
//       component: c4,
//     }
//     ,{
//       path: '/c5',
//       component: c5,
//     }
//     ,{
//       path: '/c6',
//       component: c6,
//     }
//     ,{
//       path: '/c7',
//       component: c7,
//     },
//     {
//       path: '/c8',
//       component: c8,
//     },
//     {
//       path: '/c9',
//       component: c9,
//     },
//     {
//       path: '/c10',
//       component: c10,
//     },
//     {
//       path: '/c11',
//       component: c11,
//     },
//     {
//       path: '/c12',
//       component: c12,
//     },
//     {
//       path: '/index',
//       component: Index,
//     },
//     {
//       path: '/request',
//       component: request,
//     },
//     {
//       path: '/goIndex',
//       redirect: '/index'
//     }
//   ]
//
// })
//
