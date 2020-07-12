import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import axios from 'axios'
import echarts from 'echarts'
import 'echarts/map/js/china'
import VueRouter from 'vue-router'
import router from "./router"
import './router/permission'
import TreeTable from 'vue-table-with-tree-grid'

axios.defaults.baseURL = 'https://www.hihia.top/vue-manage-api/'
Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.prototype.$echarts = echarts
Vue.component('tree-table', TreeTable)
Vue.use(VueRouter)

new Vue({
  render: h => h(App),
  router: router
}).$mount('#app')
