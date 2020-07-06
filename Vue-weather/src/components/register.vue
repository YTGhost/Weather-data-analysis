<template>
    <div class="login-wrapper">
        <div class="header">Weather Forecast</div>
        <div class="form-wrapper">
            <input type="text" name="username" placeholder="username" class="input-item" v-model="username">
            <input type="password" name="password" placeholder="password" class="input-item" v-model="password">
            <input type="password" name="comfirm" placeholder="comfirm" class="input-item" v-model="confirm">
            <div class="btn" id="reg" @click="regist">注册</div>
        </div>
    </div>
</template>

<script>
    export default{
        name:"register",
        data(){
            return{
                username:'',
                password:'',
                confirm:'',
            }
        },
        methods:{
            regist:function () {
                if( this.password != this.confirm ) {
                    alert("两次输入不一致！");
                    this.username='';
                    this.password='';
                    this.confirm='';
                }else{
                    alert("注册成功")
                }
            }
        }

    }


</script>

<style>
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
</style>