import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import axios from 'axios'
import echarts from 'echarts'
import 'echarts/map/js/china'
import VueRouter from 'vue-router'
import router from "./router";
import './router/permission'

axios.defaults.baseURL = 'http://localhost:8888/ssm_manage_system/'
Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.prototype.$echarts = echarts
Vue.use(VueRouter)

new Vue({
  render: h => h(App),
  router: router
}).$mount('#app')
