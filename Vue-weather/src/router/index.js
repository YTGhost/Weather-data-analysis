import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import login from "../components/login";

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'login',
    component: login
  },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
    {
      path:'/admin',
      name:'admin',
      component:()=>(import('../views/admin/index.vue'))
    },
    {
      path:'/users',
      name:'user',
      component:()=>(import('../views/users/index.vue'))
    }
]

const router = new VueRouter({
  routes
})

export default router
