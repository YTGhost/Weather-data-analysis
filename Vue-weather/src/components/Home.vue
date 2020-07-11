<template>
    <div id="app">
        <el-container>
            <el-header style="height: 80px;">天气预测</el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu default-active="1" class="el-menu-vertical-demo">
                        <!--<el-menu-item index="1" v-if="num.indexOf(1)!==-1" @click="toUser">
                           <span slot="title">用户管理</span>
                        </el-menu-item>
                       <el-menu-item index="2" v-if="num.indexOf(2)!==-1" @click="toDept">
                            <span slot="title">部门管理</span>
                       </el-menu-item>
                       <el-menu-item index="3" v-if="num.indexOf(3)!==-1" @click="toRole">
                           <span slot="title">角色管理</span>
                      </el-menu-item>-->
                        <el-menu-item index="1" @click="toUser">
                            <span slot="title">用户管理</span>
                        </el-menu-item>
                        <el-menu-item index="2" @click="toDept">
                            <span slot="title">部门管理</span>
                        </el-menu-item>
                        <el-menu-item index="3" @click="toRole">
                            <span slot="title">角色管理</span>
                        </el-menu-item>
                        <el-menu-item index="4" @click="toWeather">
                            <span slot="title">天气数据</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: 'Home',
        components:{},
        data() {
            return{
                show:true,
                num:[]
            }
        },
        mounted() {
            this.getPersonData()
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
            },
            toWeather(){
                this.$router.push('/weather')
            },
            toUser(){
                this.$router.push('/user')
            },
            toRole(){
                this.$router.push('/role')
            },
            toDept(){
                this.$router.push('/dept')
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
