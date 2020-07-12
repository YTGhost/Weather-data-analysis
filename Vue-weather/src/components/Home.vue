<template>
    <div id="app">
        <el-container>
            <el-header style="height: 80px;">天气预测</el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu default-active="$route.path"
                             class="el-menu-vertical-demo">
                        <el-menu-item index="/user" @click="toUser" v-if="userShow">
                            <span slot="title">用户管理</span>
                        </el-menu-item>
                        <el-menu-item index="/dept" @click="toDept" v-if="deptShow">
                            <span slot="title">部门管理</span>
                        </el-menu-item>
                        <el-menu-item index="/role" @click="toRole" v-if="roleShow">
                            <span slot="title">角色管理</span>
                        </el-menu-item>
                        <el-menu-item index="/weather" @click="toWeather">
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
    export default {
        name: 'Home',
        components: {},
        data() {
            return {
                show: true,
                num: [],
                username: document.cookie,
                menus: [],
                userShow: false,
                deptShow: false,
                roleShow: false
            }
        },
        mounted() {
            this.getPersonData()
        },
        methods: {
            getPersonData() {
                let that = this
                this.$http.get('user/find', {
                    params: {
                        username: that.username,
                    }
                }).then(response => {
                    let data = response.data
                    let menus = data.data.roles[0].menus
                    console.log(data.data.id)
                    if(data.data.id !== 1){
                        for(let i = 0; i < menus.length; i++)
                        {
                            if(menus[i].id === 1) that.userShow = true
                            else if(menus[i].id === 2) that.deptShow = true
                            else if(menus[i].id === 3)that.roleShow = true
                        }
                    }else{
                        that.userShow = true
                        that.deptShow = true
                        that.roleShow = true
                    }
                })
            },
            toWeather() {
                this.$router.push('/weather')
            },
            toUser() {
                this.$router.push('/user')
            },
            toRole() {
                this.$router.push('/role')
            },
            toDept() {
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
