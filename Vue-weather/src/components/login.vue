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
               Don't have any account?<a @click="signin">注册</a>
        </div>
    </div>
    </div>
</template>

<script>
//import axios from 'axios'
import router from "../router";
export default {
    name: 'login',
    data(){
        return {
            username:'',
            password:'',
            baseUrl: '127.0.0.1'
        }
    },
    methods:{
        login:function(){
            //this.$router.push({path:`users/`+'1111'})
            window.document.cookie=2
            router.push({path:`users`})

           /* alert("登录"+this.username)
            axios.post(this.baseUrl+'/api/login',{
                username:this.username,
                password:this.password
            }).then(response=>{
                let code=response.status
                if(code === 0)
                    this.$message.error('账号或密码不对')
                else{
                    if(code ===1){
                        router.push('admin')
                    }else if(code===2){
                        router.push({path:`users`})
                    }
                }
            })*/
        },
        signin :function(){
            router.push({path:'register'})
        }
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
