<template>
    <div class="out_div">

        <div style="margin: auto;width: 40%">
            <el-button style="width: 20%;margin-top: 10px;">添加</el-button>
            <el-input v-model="input" placeholder="请输入内容" style="margin-left: 3px;width:50%;margin-top: 10px;margin-bottom: 5px"></el-input>
            <el-button @click="get_info" style="width: 20%;margin-left: 3px;margin-top: 10px;margin-bottom: 5px;">搜索</el-button>
        </div>

        <div style="margin: auto;width: 90%;width: 90%;padding-left: 200px">
        <el-table
                :data="basic_info"
                border
                type="index"
                style="width: 75%;border-radius: 35px;height: 720px;padding-left: 30px;padding-right: 30px;padding-top: 30px;float: left">
            <el-table-column
                    type="index"
                    style="width: 20px">
                <template slot-scope="scope">
                    {{scope.$index}}
                </template>
            </el-table-column>
            <el-table-column
                    prop="[0]"
                    label="邮箱"
                    width="200px">
            </el-table-column>
            <el-table-column
                    prop="[1]"
                    label="用户名"
                    width="150px">
            </el-table-column>
            <el-table-column
                    prop="[2]"
                    label="密码"
                    width="150px">
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="300px">
                <template slot-scope="scope">
                    <el-button class="el-icon-edit" style="margin-right: 5%" @click="edit_users(scope.$index)"></el-button>
                    <el-button class="el-icon-more" style="margin-right: 5%" @click="view_users(scope.$index)"></el-button>
                    <el-button class="el-icon-delete" style="margin-right: 5%" @click="delete_users(scope.$index)">
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        </div>

        <el-dialog
                title="提示"
                :visible.sync="dialogVisible"
                width="30%"
                v-if="this_index >= 0"
        >
            <el-form  :model="basic_info[this_index]" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="邮箱" prop="[0]">
                    <el-input :disabled="is_disabled" v-model="basic_info[this_index][0]"></el-input>
                </el-form-item>
                <el-form-item label="用户名" prop="[1]">
                    <el-input :disabled="is_disabled" v-model="basic_info[this_index][1]"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="[2]">
                    <el-input :disabled="is_disabled" v-model="basic_info[this_index][2]"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="modify">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "search",
        data(){
           var mail_validate = (rule, value, callback) => {
                if (value.length === 0) {
                    callback(new Error('请输入邮箱'));
                } else {
                    var verify = /^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
                    if (!verify.test(value)) {
                        callback(new Error('邮箱格式不对'));
                    }
                }
               callback()
            };
            var user_validate = (rule, value, callback) => {
                console.log(value)
                if (value === '') {
                    callback(new Error('请输入用户名'));
                } else{
                    if(value.length<5)
                        callback(new Error('用户名长度必须大于5'))
                    callback()
                }
            };
            var passward_validate = (rule, value, callback) =>{
                console.log(value)
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else{
                    if(value.length <5)
                        callback(new Error('密码长度必须大于5'))
                    callback()
                }
            };
            return {
                basic_info:[[1,2,3],[4,5,6]],
                this_index:-1,
                //basic_info:[],
                rules:{//表单验证
                          [0]:  { validator: mail_validate, trigger: 'blur' } ,
                          [1]:{ validator: user_validate, trigger: 'blur' },
                          [2]:{ validator: passward_validate, trigger: 'blur' }
                    //{ validator: user_validate, trigger: 'blur' }{ validator: passward_validate, trigger: 'blur' }
                },
                dialogVisible:false,
                is_disabled:true,

                baseUrl:'127.0.0.1',
                input:''
            }
        },
        methods:{
            edit_users(data){
                this.this_index=data
                this.dialogVisible=true
                this.is_disabled=false

            },
            view_users(data){
                this.this_index=data
                this.dialogVisible=true
                this.is_disabled=true
            },
            delete_users(data){
                this.this_index=data

                let that =this
                axios.get(that.baseUrl+'/api/delete_info',{//请求并且发送要删除的数据
                    params:{info:that.basic_info[that.this_index]}
                }).then(response=>{//返回的状态码
                    let code=response.code
                    if(code === 3000){
                        alert('删除成功')
                        this.get_info()//界面更新
                    }
                    else
                        alert('删除失败')

                })
            },
            get_info(){
                let that=this
                axios.get(that.baseurl+'/api/get_info',{//请求并且发送要获得的关键字
                    params:{info:that.input}
                }).then(response=>{//返回的数据
                    that.basic_info=response.data
                    if(that.basic_info===null){
                        that.basic_info=''
                        this.$message.error('没有找到')
                    }
                })
            },
            modify(){
                if(this.is_disabled===true)//判断是否为编辑
                    return
                this.$refs.ruleForm.validate((valid) => {
                    if (valid) {
                        alert('submit!');
                        this.dialogVisible = false
                        return true
                    } else {
                        this.$message.error('error submit!!');
                        return false;
                    }
                })
                /*let that =this
                              axios.get(that.baseurl+'/api/send_info',{//请求并且发送修改的数据
                                  params:{info:that.basic_info[that.this_index]}
                              }).then(response=>{
                                  let code=response.code//返回的状态码
                                  if(code ===3000){
                                      alert('修改成功')
                                      this.get_info()//用于界面更新
                                  }
                                  else
                                      alert('修改失败')
                              })*/
            },
        }
    }


</script>

<style scoped>
    .table_column{
        height: 30px;
        background-color: #87ebff;
        margin-left: auto;
        position: relative;
        text-align: center;
        width: 100%;
    }
    .out_div{
        margin: auto;
        height: 900px;
    }

</style>
