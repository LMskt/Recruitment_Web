import Vue from 'vue'
import VueRouter from 'vue-router'
import homepage from "@/views/homepage";
import success from "@/views/success";
import adilogin from "@/views/adilogin";
import findallstudent from "@/views/findallstudent";
import store from "@/store/index";
import pcstulogin from "@/views/pcstulogin";
Vue.use(VueRouter)

const routes = [
  {
    path:'',
    redirect:'/homepage'
  },
  {
    path: '/homepage',
    name:'homepage',
    component:homepage
  },
  {
    path: '/success',
    name:'syccess',
    component: success
  },
  {
    path: '/adilogin',
    name: 'adilogin',
    component: adilogin
  },
  {
    path: '/findallstudent',
    name: 'findallstudent',
    component: findallstudent
  },
  {
    path:'/pcstulogin',
    name: 'pcstulogin',
    component: pcstulogin
  }
]

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// router.beforeEach(((to, from, next) => {
//   if(store.state.identity==0){
//     if(to.path.indexOf('/findallstudent')>=0){
//       next('/adilogin')
//     }else {
//       next()
//     }
//   }
// }
// ))



export default router
