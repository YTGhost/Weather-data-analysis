<template>
    <div class="login-wrapper">
        <div class="header">Weather Forecast</div>
            <div class="form-wrapper">
                <input type="text" name="username" placeholder="username" class="input-item" v-model="username"> 
                <input type="password" name="password" placeholder="password" class="input-item" v-model="password"> 
                <div class="btn" id="log" @click="login">登录</div>
            </div>
        <div class="msg">
               Don't have any account?<a href="#" @click="signin">注册</a>
        </div>
    </div>
</template>

<script>
export default {
    name: 'login',
    data(){
        return {
            username:'',
            password:'',
        }
    },
    methods:{
        login:function(){
            alert("登录"+this.username)
        },
        signin :function(){
            alert("注册")
        }
    }
}
</script>


<style scoped>
     .login-wrapper{
        background-color: #fff;
        width: 400px;
        height: 500px;
        border-radius: 20px;
        padding: 0 50px;
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