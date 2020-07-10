<template>
    <div id="app">
        <el-container v-if="show">
            <el-header style="height: 80px;">天气预测</el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu default-active="1" class="el-menu-vertical-demo">
                        <el-menu-item index="1">
                            <span slot="title">用户管理</span>
                        </el-menu-item>
                        <el-menu-item index="1">
                            <span slot="title">部门管理</span>
                        </el-menu-item>
                        <el-menu-item index="1">
                            <span slot="title">角色管理</span>
                        </el-menu-item>
                        <el-menu-item index="1">
                            <span slot="title">天气数据</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
        <router-view v-else></router-view>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: 'App',
        components:{},
        data() {
            return{
                show:false
            }
        },
        mounted() {
            let path=this.$route.path
            console.log(path)
            if (path==='/'){
                this.show=false
            }
            else{
                this.show=true
            }
        },
        methods:{
            getPersonData(){
                let that =this
                axios.get('http://182.92.66.200:8888/ssm-manage-system/user/find',{params:{
                        username:that.data,
                    }}).then(response=>{
                    let data=response.data
                    that.role=data.data.roles
                    for( var i=0;i<that.role.length;i++){
                        that.num.push(that.role[i].id)
                    }
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
