<template>
    <el-container>
        <el-container>
            <el-aside width="20%">
            <SideBar></SideBar>
                </el-aside>
        </el-container>
        <el-container>
        <search></search>
        </el-container>
    </el-container>
</template>

<script>
import search from "../search";
import SideBar from "../../components/sideBar";
//import router from "../router";
    export default {
        name: "index.vue",
        components: {SideBar, search},
        data(){
            return{

            }
        },
        mounted() {
            //console.log(111111,this.$route.params.data)
           console.log('11111',window.document.cookie)
        }
    }
</script>

<style scoped>

</style>
