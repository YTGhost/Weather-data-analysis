import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import axios from 'axios'
import echarts from 'echarts'
import 'echarts/map/js/china'

Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.prototype.$echarts = echarts

new Vue({
  render: h => h(App),
}).$mount('#app')
