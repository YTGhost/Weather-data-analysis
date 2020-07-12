<template>
    <div>
        <!--面包屑导航区域-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>角色管理</el-breadcrumb-item>
        </el-breadcrumb>

        <!--卡片视图区域-->
        <el-card style="margin-top: 10px">
            <!--搜索与添加区域-->
            <el-row :gutter="20" style="margin-bottom: 10px">
                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible = true" v-if="addShow">添加角色</el-button>
                </el-col>
            </el-row>

            <!--角色列表区域-->
            <el-table :data="roleList" border stripe>
                <el-table-column label="id" prop="id"></el-table-column>
                <el-table-column label="角色名称" prop="roleName"></el-table-column>
                <el-table-column label="操作" width="180px">
                    <template slot-scope="scope">
                        <!--修改按钮-->
                        <el-tooltip effect="dark" content="修改角色" placement="top" :enterable="false">
                            <el-button type="primary" icon="el-icon-edit" size="mini"
                                       @click="showEditDialog(scope.row.id)" v-if="modifyShow"></el-button>
                        </el-tooltip>
                        <!--删除按钮-->
                        <el-tooltip effect="dark" content="删除角色" placement="top" :enterable="false">
                            <el-button type="danger" icon="el-icon-delete" size="mini"
                                       @click="removeRoleById(scope.row.id)" v-if="deleteShow"></el-button>
                        </el-tooltip>
                        <!--分配角色权限按钮-->
                        <el-tooltip effect="dark" content="分配角色权限" placement="top" :enterable="false">
                            <el-button type="warning" icon="el-icon-setting" size="mini"
                                       @click="showSetRightDialog(scope.row)" v-if="assignShow"></el-button>
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
                title="添加角色"
                :visible.sync="addDialogVisible"
                width="50%"
                @close="addDialogClosed">
            <!--内容主体区-->
            <el-form :model="addForm" :rules="rules" ref="addFormRef" label-width="70px" class="demo-ruleForm">
                <el-form-item label="角色名" prop="roleName">
                    <el-input v-model="addForm.roleName"></el-input>
                </el-form-item>
            </el-form>
            <!--底部区域-->
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible= false">取 消</el-button>
                <el-button type="primary" @click="addRole">确 定</el-button>
            </span>
        </el-dialog>

        <!--修改用户的对话框-->
        <el-dialog
                title="修改角色"
                :visible.sync="editDialogVisible"
                width="50%"
                @close="editDialogClosed">
            <el-form :model="editForm" :rules="rules" ref="editFormRef" label-width="70px">
                <el-form-item label="id">
                    <el-input v-model="editForm.id" disabled></el-input>
                </el-form-item>
                <el-form-item label="角色名" prop="roleName">
                    <el-input v-model="editForm.roleName"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="editRoleInfo">确 定</el-button>
              </span>
        </el-dialog>

<!--        分配权限的对话框-->
        <el-dialog
                title="分配权限"
                :visible.sync="setRightDialogVisible"
                width="30%"
                @close="setRightDialogClosed">
            <!--树形控件-->
            <el-tree :data="menuList" :props="treeProps1"
                     show-checkbox node-key="id"
                     default-expand-all
                     :default-checked-keys="menuKeys"
                     ref="menuTree"></el-tree>
            <el-tree :data="permissionList" :props="treeProps2"
                     show-checkbox node-key="id"
                     default-expand-all
                     :default-checked-keys="permissionKeys"
                     ref="permissionTree"></el-tree>
            <span slot="footer" class="dialog-footer">
                <el-button @click="setRightDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="allotRights">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import qs from 'qs'
    export default {
        name: "Role",
        data() {
            // 校验角色是否存在
            const rulesName = (rule, value, callback) => {
                // 使用Axios进行校验
                this.$http.get('role/check', {
                    params: {
                        roleName: value
                    }
                })
                    .then((res => {
                        // 请求成功
                        if (res.data.code === 1) {
                            callback()
                        } else {
                            callback(new Error("角色已存在"))
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
                    roleName: [{
                        required: true,
                        message: '角色名不能为空',
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
                roleList: [],
                roleId : '',
                editForm: [],
                addForm: {
                    roleName: ''
                },
                editDialogVisible: false,
                addDialogVisible: false,
                setRightDialogVisible: false,
                permissionList: [],
                menuList: [],
                treeProps1: {
                    label: 'menuName'
                },
                treeProps2: {
                    label: 'permissionName'
                },
                // 默认选中的结点Id值数组
                menuKeys: [],
                permissionKeys: [],
                modifyShow: false,
                deleteShow: false,
                assignShow: false,
                addShow: false
            }
        },
        mounted() {
            this.init()
            this.getRoleList()
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
                        if(this.permission[i] === 8) this.modifyShow = true
                        else if(this.permission[i] === 9) this.deleteShow = true
                        else if(this.permission[i] === 10) this.assignShow = true
                        else if(this.permission[i] === 11) this.addShow = true
                    }
                }else{
                    this.modifyShow = true
                    this.deleteShow = true
                    this.addShow = true
                    this.assignShow = true
                }
                this.show = true
            },
            // 点击按钮，添加新部门
            addRole() {
                this.$refs.addFormRef.validate(async valid => {
                    if (!valid) return
                    // 可以发起添加用户的网络请求
                    await this.$http.get('role/create', {
                        params: {
                            roleName: this.addForm.roleName
                        }
                    })
                    this.$message.success('添加角色成功')
                    // 隐藏添加用户的对话框
                    this.addDialogVisible = false
                    // 重新获取用户列表数据
                    this.getRoleList()
                })
            },
            // 获得部门列表
            async getRoleList() {
                const {data: res} = await this.$http.get('role/find')
                this.roleList = res.data
            },
            // 展示编辑部门的对话框
            async showEditDialog(id) {
                const {data: res} = await this.$http.get('role/findRole/' + id)
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
            // 修改角色信息并提交
            editRoleInfo() {
                let that = this
                this.$refs.editFormRef.validate(async valid => {
                    if (!valid) return
                    // 发起修改部门信息的数据请求
                    await this.$http.put('role/modify', {
                        id: that.editForm.id,
                        roleName: that.editForm.roleName
                    })
                    // 关闭对话框
                    this.editDialogVisible = false
                    // 刷新数据列表
                    this.getRoleList()
                    // 提示修改成功
                    this.$message.success('更新角色信息成功！')
                })
            },
            async removeRoleById(id) {
                // 弹框询问用户是否删除数据
                const confirmResult = await this.$confirm('此操作将永久删除该角色, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).catch(err => err)
                // 如果用户确认删除，则返回值为字符串 confirm
                // 如果用户取消了删除，则返回值为字符串 cancel
                if (confirmResult !== 'confirm') {
                    return this.$message.info('已取消删除')
                }
                await this.$http.delete('role/delete/' + id)
                this.$message.success('删除角色成功！')
                this.getRoleList()
            },
            // 展示分配权限的对话框
            async showSetRightDialog (role) {
                // 获取当前角色id
                this.roleId = role.id
                // 获取所有权限的数据
                const {data : res1} = await this.$http.get('menu/findAll')
                this.menuList = JSON.parse(JSON.stringify(res1))
                const {data : res2} = await this.$http.get('permission/findAll')
                this.permissionList = JSON.parse(JSON.stringify(res2))
                const { data: res3} = await this.$http.get('role/getMenus/' + this.roleId)
                let temp = JSON.parse(JSON.stringify(res3))
                for(let i = 0; i < temp.length; i++)
                {
                    this.menuKeys.push(temp[i].id)
                }
                console.log(this.menuKeys)
                const { data: res4 } = await this.$http.get('role/getPermissions/' + this.roleId)
                temp = JSON.parse(JSON.stringify(res4))
                for(let i = 0; i < temp.length; i++)
                {
                    this.permissionKeys.push(temp[i].id)
                }
                // 展示对话框
                this.setRightDialogVisible = true
                this.$nextTick(function() {
                    this.$refs.menuTree.setCheckedKeys(this.menuKeys)
                    this.$refs.permissionTree.setCheckedKeys(this.permissionKeys);
                })

            },
            // 监听分配权限对话框的关闭事件
            setRightDialogClosed () {
                this.menuKeys = []
                this.permissionKeys = []
            },
            // 点击为角色分配权限
            async allotRights () {
                // 修改菜单权限
                let that = this
                let data1 = this.$refs.menuTree.getCheckedKeys()
                let data2 = this.$refs.permissionTree.getCheckedKeys()
                data1.push(0)
                data2.push(0)
                await this.$http.post('/menu/assign', qs.stringify({
                    ids: data1,
                    roleId: that.roleId
                }, {indices: false}))
                // 修改具体权限
                await this.$http.post('/permission/assign', qs.stringify({
                    ids: data2,
                    roleId: that.roleId
                }, {indices: false}))
                this.$message.success('分配权限成功')
                this.getRoleList()
                this.setRightDialogVisible = false
            }
        }
    }
</script>

<style scoped>

</style>
