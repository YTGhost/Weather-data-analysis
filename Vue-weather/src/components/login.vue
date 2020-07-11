<template>
    <div  class ='page' style="height: 725px;padding-top: 180px">
    <div class="login-wrapper">
        <div class="header">Weather Forecast</div>
            <div class="form-wrapper">
                <input type="text" name="username" placeholder="username" class="input-item" v-model="username">
                <input type="password" name="password" placeholder="password" class="input-item" v-model="password">
                <div class="btn" id="log" @click="login">登录</div>
            </div>
        <div class="msg">
               Don't have any account?<a>注册</a>
        </div>
    </div>
    </div>
</template>

<script>
import axios from 'axios'
import router from "../router";
export default {
    name: 'login',
    data(){
        return {
            username:'admin',
            password:'123456',
            baseUrl: 'http://182.92.66.200:8888/ssm-manage-system'
        }
    },
    methods:{
        login:function(){
            //this.$router.push({path:`users/`+'1111'})
            let that =this
            //alert("登录"+this.username)
            axios.get('http://182.92.66.200:8888/ssm-manage-system/user/find',{
                params:{
                username:this.username}
            }).then(response=>{
                console.log(response.data)
                let res=response.data
                if(res.code === 0){
                    this.$message.error('账号错误')
                    return
                }
                if(that.password!==res.data.password){
                    this.$message.error('密码错误')
                    return
                }
                window.document.cookie=that.username
                let data=res.data
                if( data.id=== 1){
                        //router.push({name:'role_table'})
                        router.push({path:`users`})
                    }else if(data.id===2){
                        router.push({path:`users`})
                    }
            })
        },
    }
}
</script>


<style scoped>
    .page{
        background-image: linear-gradient(-90deg, #29bdd9 0%, #276ace 100%);
    }

     .login-wrapper{
        background-color:#fff;
        width: 400px;
        height: 500px;
        border-radius: 20px;
        padding: 0 50px;
        margin: auto

    }

    .login-wrapper .header{
        font-size: 30px;
        font-weight: bold;
        text-align: center;
        line-height: 200px;
    }
    .login-wrapper .form-wrapper .input-item{
        display: block;
        width: 100%;
        margin-bottom: 20px;
        border:0;
        padding:10px;
        border-bottom: 1px solid rgb(128,125,125);
        font-size: 15px;
    }

    .login-wrapper .form-wrapper .input-item::placeholder{
        text-transform: uppercase;
    }

    .login-wrapper .form-wrapper .btn {
        text-align: center;
        padding: 10px;
        width:100%;
        margin-top: 40px;
        background-image: linear-gradient(to right,#fbc2eb,#a6c1ee);
        color: #fff;
    }

    .login-wrapper .msg{
        text-align: center;
        line-height: 80px;
    }

    .login-wrapper .msg a{
        text-decoration-line: none;
        color:#a6c1ee;
    }
</style>
