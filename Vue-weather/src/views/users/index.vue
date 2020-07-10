<template>
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
            <el-main>
                <weather></weather>
            </el-main>
        </el-container>
    </el-container>
    
</template>
<script>
    // import _ from 'lodash'
    //import search from "./views/search";
    //import login from "./components/login";
    import Weather from "../../components/weather";
    export default {
        name: 'App',
        components: {Weather},
        data() {
            return {

            }
        },
        methods: {
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
