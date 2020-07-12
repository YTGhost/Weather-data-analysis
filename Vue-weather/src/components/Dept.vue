<template>
    <div>
        <!--面包屑导航区域-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>部门管理</el-breadcrumb-item>
        </el-breadcrumb>

        <!--卡片视图区域-->
        <el-card style="margin-top: 10px">
            <!--搜索与添加区域-->
            <el-row :gutter="20" style="margin-bottom: 10px">
                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible = true" v-if="addShow">添加部门</el-button>
                </el-col>
            </el-row>

            <!--用户列表区域-->
            <el-table :data="deptList" border stripe>
                <el-table-column label="id" prop="id"></el-table-column>
                <el-table-column label="部门名称" prop="deptName"></el-table-column>
                <el-table-column label="操作" width="180px">
                    <template slot-scope="scope">
                        <!--修改按钮-->
                        <el-tooltip effect="dark" content="修改部门" placement="top" :enterable="false">
                            <el-button type="primary" icon="el-icon-edit" size="mini"
                                       @click="showEditDialog(scope.row.id)" v-if="modifyShow"></el-button>
                        </el-tooltip>
                        <!--删除按钮-->
                        <el-tooltip effect="dark" content="删除部门" placement="top" :enterable="false">
                            <el-button type="danger" icon="el-icon-delete" size="mini"
                                       @click="removeDeptById(scope.row.id)" v-if="deleteShow"></el-button>
                        </el-tooltip>
                    </template>
                </el-table-column>
            </el-table>
            <!--            &lt;!&ndash;分页区域&ndash;&gt;-->
            <!--            <el-pagination-->
            <!--                    @size-change="handleSizeChange"-->
            <!--                    @current-change="handleCurrentChange"-->
            <!--                    :current-page="queryInfo.pagenum"-->
            <!--                    :page-sizes="[1, 2, 5, 10]"-->
            <!--                    :page-size=queryInfo.pagesize-->
            <!--                    layout="total, sizes, prev, pager, next, jumper"-->
            <!--                    :total=total>-->
            <!--            </el-pagination>-->
        </el-card>

        <!--添加部门的对话框-->
        <el-dialog
                title="添加部门"
                :visible.sync="addDialogVisible"
                width="50%"
                @close="addDialogClosed">
            <!--内容主体区-->
            <el-form :model="addForm" :rules="rules" ref="addFormRef" label-width="70px" class="demo-ruleForm">
                <el-form-item label="部门名" prop="deptName">
                    <el-input v-model="addForm.deptName"></el-input>
                </el-form-item>
            </el-form>
            <!--底部区域-->
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible= false">取 消</el-button>
                <el-button type="primary" @click="addDept">确 定</el-button>
            </span>
        </el-dialog>

        <!--修改用户的对话框-->
        <el-dialog
                title="修改用户"
                :visible.sync="editDialogVisible"
                width="50%"
                @close="editDialogClosed">
            <el-form :model="editForm" :rules="rules" ref="editFormRef" label-width="70px">
                <el-form-item label="id">
                    <el-input v-model="editForm.id" disabled></el-input>
                </el-form-item>
                <el-form-item label="部门名" prop="deptName">
                    <el-input v-model="editForm.deptName"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="editDeptInfo">确 定</el-button>
              </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "Dept",
        data() {
            // 校验部门是否存在
            const rulesName = (rule, value, callback) => {
                // 使用Axios进行校验
                this.$http.get('dept/check', {
                    params: {
                        deptName: value
                    }
                })
                    .then((res => {
                        // 请求成功
                        if (res.data.code === 1) {
                            callback()
                        } else {
                            callback(new Error("部门已存在"))
                        }
                    }))
                    .catch((err) => {
                        // 如果请求失败则在控制台打印
                        console.log(err)
                    })
            }
            return {
                basic_info: [[1, 2, 3], [4, 5, 6]],
                this_index: -1,
                //basic_info:[],
                rules: {
                    deptName: [{
                        required: true,
                        message: '部门名不能为空',
                        trigger: 'blur'
                    },
                        {
                            validator: rulesName,
                            trigger: 'blur'
                        }
                    ]
                },
                dialogVisible: false,
                is_disabled: true,
                role: [],
                permission: [],
                show: false,
                username: document.cookie,
                deptList: [],
                editForm: [],
                addForm: {
                    deptName: ''
                },
                editDialogVisible: false,
                addDialogVisible: false,
                modifyShow: false,
                deleteShow: false,
                addShow: false
            }
        },
        mounted() {
            this.init()
            this.getDeptList()
        },
        methods: {
            async init() {
                let that = this
                const {data: res} = await this.$http.get('user/find', {
                    params: {
                        username: that.username
                    }
                })
                this.userId = res.data.id
                this.roleId = res.data.roles[0].id
                this.role = res.data.roles
                let permission = this.role[0].permissions
                for (var x = 0; x < permission.length; x++) {
                    this.permission.push(permission[x].id)
                }
                if(this.userId !== 1){
                    for(let i = 0; i < this.permission.length; i++)
                    {
                        if(this.permission[i] === 5) this.modifyShow = true
                        else if(this.permission[i] === 6) this.deleteShow = true
                        else if(this.permission[i] === 7) this.addShow = true
                    }
                }else{
                    this.modifyShow = true
                    this.deleteShow = true
                    this.addShow = true
                }
                this.show = true
            },
            // 点击按钮，添加新部门
            addDept () {
                this.$refs.addFormRef.validate(async valid => {
                    if (!valid) return
                    // 可以发起添加用户的网络请求
                    await this.$http.get('dept/create', {
                        params: {
                            deptName: this.addForm.deptName
                        }
                    })
                    this.$message.success('添加部门成功')
                    // 隐藏添加用户的对话框
                    this.addDialogVisible = false
                    // 重新获取用户列表数据
                    this.getDeptList()
                })
            },
            // 获得部门列表
            async getDeptList() {
                const {data: res} = await this.$http.get('dept/find')
                this.deptList = res.data
            },
            // 展示编辑部门的对话框
            async showEditDialog(id) {
                const {data: res} = await this.$http.get('dept/find/' + id)
                this.editForm = res.data
                this.editDialogVisible = true
            },
            // 监听修改部门对话框的关闭事件
            editDialogClosed() {
                this.$refs.editFormRef.resetFields()
            },
            // 监听添加部门对话框的关闭事件
            addDialogClosed () {
                this.$refs.addFormRef.resetFields()
            },
            // 修改部门信息并提交
            editDeptInfo() {
                let that = this
                this.$refs.editFormRef.validate(async valid => {
                    if (!valid) return
                    // 发起修改部门信息的数据请求
                    await this.$http.put('dept/modify', {
                        id: that.editForm.id,
                        deptName: that.editForm.deptName
                    })
                    // 关闭对话框
                    this.editDialogVisible = false
                    // 刷新数据列表
                    this.getDeptList()
                    // 提示修改成功
                    this.$message.success('更新部门信息成功！')
                })
            },
            async removeDeptById(id) {
                // 弹框询问用户是否删除数据
                const confirmResult = await this.$confirm('此操作将永久删除该部门, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).catch(err => err)
                // 如果用户确认删除，则返回值为字符串 confirm
                // 如果用户取消了删除，则返回值为字符串 cancel
                if (confirmResult !== 'confirm') {
                    return this.$message.info('已取消删除')
                }
                await this.$http.delete('dept/delete/' + id)
                this.$message.success('删除部门成功！')
                this.getDeptList()
            },
        }
    }
</script>

<style scoped>

</style>
