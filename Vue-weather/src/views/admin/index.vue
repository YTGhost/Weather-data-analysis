<template>
    <div>
        <role_table></role_table>
    </div>
</template>

<script>
//import search from "../search";
import axios from 'axios'
import role_table from "../../components/role_table";
//import router from "../router";
    export default {
        name: "index.vue",
        components: {role_table},
        data(){
            return{
                data:window.document.cookie,
                role:[],
                num:[]
            }
        },
        mounted() {
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
</script>

<style scoped>

</style>
