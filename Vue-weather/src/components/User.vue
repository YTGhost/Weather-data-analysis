<template>
    <div>
        <!--面包屑导航区域-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        </el-breadcrumb>

        <!--卡片视图区域-->
        <el-card style="margin-top: 10px">
            <!--搜索与添加区域-->
            <el-row :gutter="20" style="margin-bottom: 10px">
                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible = true" v-if="addShow">添加用户</el-button>
                </el-col>
            </el-row>

            <!--用户列表区域-->
            <el-table :data="userList" border stripe>
                <el-table-column label="id" prop="id"></el-table-column>
                <el-table-column label="用户名" prop="username"></el-table-column>
                <el-table-column label="密码" prop="password"></el-table-column>
                <el-table-column label="邮箱" prop="email"></el-table-column>
                <el-table-column label="部门" prop="depts[0].deptName"></el-table-column>
                <el-table-column label="角色" prop="roles[0].roleName"></el-table-column>
                <el-table-column label="操作" width="240px">
                    <template slot-scope="scope">
                        <!--修改用户按钮-->
                        <el-tooltip effect="dark" content="修改用户" placement="top" :enterable="false">
                            <el-button type="primary" icon="el-icon-edit" size="mini"
                                       @click="showEditDialog(scope.row.id)" v-if="modifyShow"></el-button>
                        </el-tooltip>
                        <!--删除用户按钮-->
                        <el-tooltip effect="dark" content="删除用户" placement="top" :enterable="false">
                            <el-button type="danger" icon="el-icon-delete" size="mini"
                                       @click="removeUserById(scope.row.id)" v-if="deleteShow"></el-button>
                        </el-tooltip>
                        <!--分配角色按钮-->
                        <el-tooltip effect="dark" content="分配角色" placement="top" :enterable="false">
                            <el-button type="warning" icon="el-icon-setting" size="mini"
                                       @click="setRole(scope.row)" v-if="assignRoleShow"></el-button>
                        </el-tooltip>
                        <!--分配部门-->
                        <el-tooltip effect="dark" content="分配部门" placement="top" :enterable="false">
                            <el-button type="warning" icon="el-icon-setting" size="mini"
                                       @click="setDept(scope.row)" v-if="assignDeptShow"></el-button>
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
                title="添加用户"
                :visible.sync="addDialogVisible"
                width="50%"
                @close="addDialogClosed">
            <!--内容主体区-->
            <el-form :model="addForm" :rules="rules" ref="addFormRef" label-width="70px" class="demo-ruleForm">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="addForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="addForm.password"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="addForm.email"></el-input>
                </el-form-item>
            </el-form>
            <!--底部区域-->
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible= false">取 消</el-button>
                <el-button type="primary" @click="addUser">确 定</el-button>
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
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="editForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="editForm.password"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="editForm.email"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="editUserInfo">确 定</el-button>
            </span>
        </el-dialog>

        <!--分配角色的对话框-->
        <el-dialog
                title="分配角色"
                :visible.sync="setRoleDialogVisible"
                width="50%"
                @close="setRoleDialogClosed">
            <div>
                <p>当前的用户: {{userInfo.username}}</p>
                <p>当前的角色: {{roleName}}</p>
                <p>分配新角色:
                    <el-select v-model="selectedRoleId" placeholder="请选择">
                        <el-option
                                v-for="item in rolesList"
                                :key="item.id"
                                :label="item.roleName"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </p>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="setRoleDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveRoleInfo">确 定</el-button>
              </span>
        </el-dialog>
        <!--分配部门的对话框-->
        <el-dialog
                title="分配部门"
                :visible.sync="setDeptDialogVisible"
                width="50%"
                @close="setDeptDialogClosed">
            <div>
                <p>当前的用户: {{userInfo.username}}</p>
                <p>当前的部门: {{deptName}}</p>
                <p>分配新部门:
                    <el-select v-model="selectedDeptId" placeholder="请选择">
                        <el-option
                                v-for="item in deptsList"
                                :key="item.id"
                                :label="item.deptName"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </p>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="setDeptDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveDeptInfo">确 定</el-button>
              </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "User",
        data() {
            // 校验用户名是否存在
            const rulesName = (rule, value, callback) => {
                // 使用Axios进行校验
                this.$http.get('user/checkUsername', {
                    params: {
                        username: value
                    }
                })
                    .then((res => {
                        // 请求成功
                        if (res.data.code === 1) {
                            callback()
                        } else {
                            callback(new Error("用户名已存在"))
                        }
                    }))
                    .catch((err) => {
                        // 如果请求失败则在控制台打印
                        console.log(err)
                    })
            }
            const rulesEmail = (rule, value, callback) => {
                // 使用Axios进行校验
                this.$http.get('user/checkEmail', {
                    params: {
                        email: value
                    }
                })
                    .then((res => {
                        // 请求成功
                        if (res.data.code === 1) {
                            callback()
                        } else {
                            callback(new Error("该邮箱已存在"))
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
                    username: [{
                        required: true,
                        message: '用户名不能为空',
                        trigger: 'blur'
                    },
                        {
                            validator: rulesName,
                            trigger: 'blur'
                        }
                    ],
                    password: [{
                        required: true,
                        message: '密码不能为空',
                        trigger: 'blur'
                    }],
                    email: [{
                        required: true,
                        message: '邮箱不能为空',
                        trigger: 'blur'
                    },
                        {
                            type: 'email',
                            message: '请输入正确的邮箱地址',
                            trigger: ['blur', 'change']
                        },
                        {
                            validator: rulesEmail,
                            trigger: 'blur'
                        }]
                },
                dialogVisible: false,
                is_disabled: true,
                userId: 0,
                roleId: 0,
                role: [],
                permission: [],
                show: false,
                username: document.cookie,
                roleName: '',
                userList: [],
                userInfo: {},
                editForm: [],
                rolesList: [],
                selectedRoleId: '',
                addForm: {
                    username: '',
                    password: '',
                    email: ''
                },
                editDialogVisible: false,
                addDialogVisible: false,
                setRoleDialogVisible: false,
                modifyShow: false,
                deleteShow: false,
                assignRoleShow: false,
                assignDeptShow: false,
                addShow: false,
                deptsList: [],
                deptName: '',
                setDeptDialogVisible: false,
                selectedDeptId: ''
            }
        },
        mounted() {
            this.init()
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
                        if(this.permission[i] === 1) this.modifyShow = true
                        else if(this.permission[i] === 2) this.deleteShow = true
                        else if(this.permission[i] === 3) this.assignRoleShow = true
                        else if(this.permission[i] === 4) this.addShow = true
                        else if(this.permission[i] === 12) this.assignDeptShow = true
                    }
                }else{
                    this.modifyShow = true
                    this.deleteShow = true
                    this.assignDeptShow = true
                    this.assignRoleShow = true
                    this.addShow = true
                }
                this.show = true
                this.getUserList()
            },
            // 点击按钮，添加新部门
            addUser() {
                this.$refs.addFormRef.validate(async valid => {
                    if (!valid) return
                    // 可以发起添加用户的网络请求
                    await this.$http.post('user/create', {
                        username: this.addForm.username,
                        password: this.addForm.password,
                        email: this.addForm.email
                    })
                    this.$message.success('添加用户成功')
                    // 隐藏添加用户的对话框
                    this.addDialogVisible = false
                    // 重新获取用户列表数据
                    this.getUserList()
                })
            },
            // 获得部门列表
            async getUserList() {
                let that = this
                const {data: res} = await this.$http.get('user/findAll', {
                    params: {
                        userId: that.userId,
                        roleId: that.roleId
                    }
                })
                this.userList = res.data
            },
            // 展示编辑部门的对话框
            async showEditDialog(id) {
                const {data: res} = await this.$http.get('user/find/' + id)
                this.editForm = res.data
                this.editDialogVisible = true
            },
            // 监听修改部门对话框的关闭事件
            editDialogClosed() {
                this.$refs.editFormRef.resetFields()
            },
            // 监听添加部门对话框的关闭事件
            addDialogClosed() {
                this.$refs.addFormRef.resetFields()
            },
            // 修改部门信息并提交
            editUserInfo() {
                let that = this
                this.$refs.editFormRef.validate(async valid => {
                    if (!valid) return
                    // 发起修改部门信息的数据请求
                    await this.$http.put('user/modify', {
                        id: that.editForm.id,
                        username: that.editForm.username,
                        password: that.editForm.password,
                        email: that.editForm.email
                    })
                    // 关闭对话框
                    this.editDialogVisible = false
                    // 刷新数据列表
                    this.getUserList()
                    // 提示修改成功
                    this.$message.success('更新用户信息成功！')
                })
            },
            async removeUserById(id) {
                // 弹框询问用户是否删除数据
                const confirmResult = await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).catch(err => err)
                // 如果用户确认删除，则返回值为字符串 confirm
                // 如果用户取消了删除，则返回值为字符串 cancel
                if (confirmResult !== 'confirm') {
                    return this.$message.info('已取消删除')
                }
                await this.$http.delete('user/delete/' + id)
                this.$message.success('删除用户成功！')
                this.getUserList()
            },
            async setRole(userInfo) {
                this.userInfo = userInfo
                const {data: res} = await this.$http.get('role/find')
                this.rolesList = res.data
                this.setRoleDialogVisible = true
                if(this.userInfo.roles.length){
                    this.roleName = this.userInfo.roles[0].roleName
                }else{
                    this.roleName = "该用户还未分配角色"
                }
            },
            // 监听分配角色对话框的关闭事件
            setRoleDialogClosed () {
                this.selectedRoleId = ''
                this.roleName = ''
                this.userInfo = {}
            },
            // 点击按钮，分配角色
            async saveRoleInfo () {
                if (!this.selectedRoleId) {
                    return this.$message.error('请选择要分配的角色！')
                }
                await this.$http.post('role/assign', {
                    userId: this.userInfo.id,
                    roleId: this.selectedRoleId
                })
                this.$message.success('更新角色成功！')
                this.getUserList()
                this.setRoleDialogVisible = false
            },
            async setDept(userInfo) {
                this.userInfo = userInfo
                const {data: res} = await this.$http.get('dept/find')
                this.deptsList = res.data
                this.setDeptDialogVisible = true
                if(this.userInfo.depts.length){
                    this.deptName = this.userInfo.depts[0].deptName
                }else{
                    this.deptName = "该用户还未分配部门"
                }
            },
            // 监听分配角色对话框的关闭事件
            setDeptDialogClosed () {
                this.selectedDeptId = ''
                this.deptName = ''
                this.userInfo = {}
            },
            // 点击按钮，分配角色
            async saveDeptInfo () {
                if (!this.selectedDeptId) {
                    return this.$message.error('请选择要分配的部门！')
                }
                await this.$http.post('dept/assign', {
                    userId: this.userInfo.id,
                    deptId: this.selectedDeptId
                })
                this.$message.success('更新角色成功！')
                this.getUserList()
                this.setDeptDialogVisible = false
            },
        }
    }
</script>

<style scoped>

</style>
