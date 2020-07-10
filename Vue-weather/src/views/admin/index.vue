<template>
    <div>
        <search></search>
    </div>
</template>

<script>
import search from "../search";
//import router from "../router";
    export default {
        name: "index.vue",
        components: {search},
        data(){
            return{
                yyy:{},
                tt:1
            }
        },
        mounted() {
            //console.log(111111,this.$route.params.data)
            if(this.tt===1){
                this.yyy=this.$route.params.data
                this.tt=0
            }
            console.log(111111,this.yyy)
        }
    }
</script>

<style scoped>

</style>
