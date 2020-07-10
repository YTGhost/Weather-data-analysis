<template>
    <div>
        <search></search>
    </div>
</template>

<script>
import search from "../search";
import axios from 'axios'
//import router from "../router";
    export default {
        name: "index.vue",
        components: {search},
        data(){
            return{
                data:window.document.cookie,
                role:[]
            }
        },
        mounted() {
            let that =this
            axios.get('http://182.92.66.200:8888/ssm-manage-system/user/find',{params:{
                username:that.data,
                }}).then(response=>{
                    let data=response.data
                    that.role=data.data.roles
                    console.log('11111',that.role)
                if(that.role[0].indexOf('1')===-1){
                    alert('1')
                }
            })


        }
    }
</script>

<style scoped>

</style>
