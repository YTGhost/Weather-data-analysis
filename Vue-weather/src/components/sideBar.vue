<template>
    <el-menu
            :default-active="activeIndex2"
            class="el-menu-demo"
            mode="vertical"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            style="width: 20%;height: 100%"
            active-text-color="#ffd04b">
        <el-submenu index="1">
            <template slot="title">用户管理</template>
            <el-menu-item index="1-1">个人信息</el-menu-item>
            <!--<el-menu-item index="1-2">删除用户</el-menu-item>
            <el-menu-item index="1-3">更改用户信息</el-menu-item>
            <el-menu-item index="1-4">用户查询</el-menu-item>-->
        </el-submenu>
        <el-menu-item index="2" disabled>消息中心</el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: "sideBar"
    }
</script>

<style scoped>

</style>
