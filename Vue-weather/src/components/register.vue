<template>
    <div class="login-wrapper">
        <div class="header">Weather Forecast</div>
        <div class="form-wrapper">
            <input type="text" name="username" placeholder="username" class="input-item" v-model="username">
            <input type="text" name="email" placeholder="email" class="input-item" v-model="email">
            <input type="password" name="password" placeholder="password" class="input-item" v-model="password">
            <input type="password" name="comfirm" placeholder="comfirm" class="input-item" v-model="confirm">
            <div class="btn" id="reg" @click="regist">注册</div>
        </div>
    </div>
</template>

<script>
    //import axios from 'axios'
    //import router from "../router";
    //import router from "../router";

    export default{
        name:"register",
        data(){
            return{
                username:'',
                password:'',
                confirm:'',
                email:'',
                baseUrl:'127.0.0.1'
            }
        },
        methods:{
            regist:function () {
                if(this.username.length===0||this.username==='') {
                    this.$message.error('账户不为空')
                    return
                }
                if(this.email.length===0||this.email==='') {
                    this.$message.error('邮箱不为空')
                    return
                }
                if( this.password != this.confirm ) {
                    alert("两次输入不一致！");
                    this.confirm=''
                    return
                }else if(this.password.length===0){
                    this.$message.error('密码不为空')
                    return
                }
                /*let that=this
                axios.post(that.baseUrl+'api/register',{
                    username:that.username,
                    password:that.password,
                    email:that.email
                }).then(response=>{
                    let code=response.code
                    if(code===1)
                        this.$message.error('请更换用户名')
                    else if(code===2)
                        this.$message.error('请更换邮箱')
                    else{
                        alert('注册成功')
                        router.push('/')//注册完跳转
                    }

                })*/
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
        margin: 0 auto;
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
