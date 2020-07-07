<template>
    <div id="app">
        <search></search>
        <el-container>
            <el-header style="height: 80px;">天气预测</el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu default-active="1" class="el-menu-vertical-demo">
                        <el-menu-item index="1">
                            <i class="el-icon-user"></i>
                            <span slot="title">数据报表</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-container>
                    <el-main>
                        <!-- 面包屑导航 -->
                        <el-breadcrumb separator-class="el-icon-arrow-right">
                            <el-breadcrumb-item>首页</el-breadcrumb-item>
                            <el-breadcrumb-item>数据报表</el-breadcrumb-item>
                        </el-breadcrumb>
                        <!--放置地图和折线图的地方-->
                        <div id="map"></div>>
                        <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                        <div id="main" style="width: 600px;height:400px;"></div>
                    </el-main>
                    <el-footer style="height: 30px;">天气预测 HIHIA | 2020-07-03</el-footer>
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    // import _ from 'lodash'
    import search from "./views/search";
    export default {
        name: 'App',
        components: {search},
        data() {
            return {
                baseURL: "https://www.hihia.top/vue-weather-api/api/weather",
                data: [],
                datalist: [
                    {name:"南海诸岛",value:0},
                    {name: '北京', value: 0},
                    {name: '天津', value: 0},
                    {name: '上海', value: 0},
                    {name: '重庆', value: 0},
                    {name: '河北', value: 0},
                    {name: '河南', value: 0},
                    {name: '云南', value: 0},
                    {name: '辽宁', value: 0},
                    {name: '黑龙江', value: 0},
                    {name: '湖南', value: 0},
                    {name: '安徽', value: 0},
                    {name: '山东', value: 0},
                    {name: '新疆', value: 0},
                    {name: '江苏', value: 0},
                    {name: '浙江', value: 0},
                    {name: '江西', value: 0},
                    {name: '湖北', value: 0},
                    {name: '广西', value: 0},
                    {name: '甘肃', value: 0},
                    {name: '山西', value: 0},
                    {name: '内蒙古', value: 0},
                    {name: '陕西', value: 0},
                    {name: '吉林', value: 0},
                    {name: '福建', value: 0},
                    {name: '贵州', value: 0},
                    {name: '广东', value: 0},
                    {name: '青海', value: 0},
                    {name: '西藏', value: 0},
                    {name: '四川', value: 0},
                    {name: '宁夏', value: 0},
                    {name: '海南', value: 0},
                    {name: '台湾', value: 0},
                    {name: '香港', value: 0},
                    {name: '澳门', value: 0}
                ],
            }
        },
        created() {
        },
        mounted() {
            this.initEchart()
        },
        methods: {
            initEchart() {
                var myChart = this.$echarts.init(document.getElementById('map'))

                var that = this
                var option = {
                    tooltip: {
                        //数据格式化
                        formatter:function(params){
                            return params.seriesName+'<br />'+params.name+'：'+params.value
                        }
                    },
                    visualMap: {
                        min: 0,
                        max: 1000,
                        left: 'left',
                        top: 'bottom',
                        text: ['高','低'],//取值范围的文字
                        inRange: {
                            color: ['#e0ffff', 'red']//取值范围的颜色
                        },
                        show:true//图注
                    },
                    geo: {
                        map: 'china',
                        roam: false,//不开启缩放和平移
                        zoom:1.23,//视角缩放比例
                        label: {
                            normal: {
                                show: true,
                                fontSize:'10',
                                color: 'rgba(0,0,0,0.7)'
                            }
                        },
                        itemStyle: {
                            normal:{
                                borderColor: 'rgba(0, 0, 0, 0.2)'
                            },
                            emphasis:{
                                areaColor: 'yellow',//鼠标选择区域颜色
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                                shadowBlur: 20,
                                borderWidth: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    },
                    // 鼠标悬浮提示框
                    series : [
                        {
                            name: '省份',
                            type: 'map',
                            geoIndex: 0,
                            data: that.datalist
                        }
                    ]
                }
                var option1 = {
                    xAxis: {
                        type: 'category',
                        data: ['07/02', '07/03', '07/04', '07/05', '07/06', '07/07', '07/08']
                    },
                    yAxis: {
                        type: 'value',
                        min:function (value) {
                            return value.min;
                        }
                    },
                    series: [{
                        data: [0, 0, 0, 0, 0, 0, 0],
                        type: 'line'
                    }]
                }
                myChart.setOption(option)
                myChart.on('click', function (params) {
                    that.$http
                        .get(that.baseURL, {params:{ name: params.name }})
                        .then(function (res) {
                            console.log(res)
                            // 请求成功后执行的函数
                            if (res.data.code === 1) {
                                // 把数据给users
                                that.data = res.data.data;

                                var lineChart = that.$echarts.init(document.getElementById('main'))
                                option1.series[0].data = res.data.data
                                console.log(option1)
                                lineChart.setOption(option1)

                                // 提示
                                that.$message({
                                    message: '数据加载成功',
                                    type: 'success'
                                })
                            } else {
                                // 失败的提示
                                that.$message.error(res.data.msg)
                            }
                        })
                        .catch(function (err) {
                            // 请求失败后执行的函数
                            console.log(err)
                        })
                });
            },
            // 获取所有用户信息
            getData: function () {
                // 记录this的地址
                let that = this
                // 使用Axios实现Ajax请求
                this.$http
                    .get(that.baseURL, {params:{
                            name: '北京' }
                    })
                    .then(function (res) {
                        console.log(res)

                        // 请求成功后执行的函数
                        if (res.data.code === 1) {
                            // 把数据给users
                            that.data = res.data.data;

                            // 提示
                            that.$message({
                                message: '数据加载成功',
                                type: 'success'
                            })
                        } else {
                            // 失败的提示
                            that.$message.error(res.data.msg)
                        }
                    })
                    .catch(function (err) {
                        // 请求失败后执行的函数
                        console.log(err)
                    })
            }
        }
    }
</script>

<style>
    html, body, #app, .el-container {
        margin: 0px;
        padding: 0px;
        height: 100%;
    }

    #map {
        width: 100%;
        height: 600px;
    }

    .el-header {
        background-color: #B3C0D1;
        color: #333;
        text-align: left;
        line-height: 80px;
        font-size: 32px;
        font-weight: bold;
    }

    .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 30px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        /* text-align: center; */
        /* line-height: 160px; */
    }
</style>
