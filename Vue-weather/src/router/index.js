import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../components/Login"
import Home from "../components/Home"
import Welcome from "../components/Welcome"
import Weather from "../components/Weather"
import User from "../components/User"
import Role from "../components/Role"
import Dept from "../components/Dept";

// import role_table from "../components/role_table";

Vue.use(VueRouter)

const router = new VueRouter({
    routes: [
        {path: '/', redirect: '/login'},
        {path: '/login', component: Login},
        {
            path: '/home',
            component: Home,
            redirect: '/welcome',
            children: [
                {path: '/welcome', component: Welcome},
                {path: '/weather', component: Weather},
                {path: '/user', component: User},
                {path: '/role', component: Role},
                {path: '/dept', component: Dept}
            ]
        }
    ]
})

// const routes = [
    // {path: '/weather', name: 'weather', component: weather},
    // {path: '/role_table', name: 'role_table', component: role_table},
    // {path: '/about', name: 'About',
    //     // route level code-splitting
    //     // this generates a separate chunk (about.[hash].js) for this route
    //     // which is lazy-loaded when the route is visited.
    //     component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    // },
    // {
    //     path: '/admin',
    //     name: 'admin',
    //     component: () => (import('../views/admin/index.vue'))
    // },
    // {
    //     path: '/users',
    //     name: 'user',
    //     component: () => (import('../views/users/index.vue'))
    // }
// ]

export default router
