import Vue from 'vue'
import VueRouter from 'vue-router'
// // 导入组件
import main from '../components/Content'
// import Login from "../views/Login";
// import NotFound from "../views/NotFound";
//
// import UserList from "../views/user/List";
// import UserProfile from "../views/user/Profile";
// import Index from "../views/chpts/index";
// import c1 from "../views/chpts/c1";
// import c2 from "../views/chpts/c2";
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
// // 使用
Vue.use(VueRouter)
// // 导出
export default new VueRouter({
  mode: 'hash',
  routes: [
    {
      path: '/main',
      component: main,
      props: true
    }
  ]
})
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
