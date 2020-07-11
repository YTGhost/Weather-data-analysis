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
          <el-button type="primary" @click="addDialogVisible = true">添加用户</el-button>
        </el-col>
      </el-row>

      <!--用户列表区域-->
      <el-table :data="userList" border stripe>
        <el-table-column label="id" prop="id"></el-table-column>
        <el-table-column label="用户名" prop="username"></el-table-column>
        <el-table-column label="密码" prop="password"></el-table-column>
        <el-table-column label="邮箱" prop="email"></el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <!--修改用户按钮-->
            <el-tooltip effect="dark" content="修改用户" placement="top" :enterable="false">
              <el-button type="primary" icon="el-icon-edit" size="mini"
                         @click="showEditDialog(scope.row.id)"></el-button>
            </el-tooltip>
            <!--删除用户按钮-->
            <el-tooltip effect="dark" content="删除用户" placement="top" :enterable="false">
              <el-button type="danger" icon="el-icon-delete" size="mini"
                         @click="removeDeptById(scope.row.id)"></el-button>
            </el-tooltip>
            <!--分配角色按钮-->
            <el-tooltip effect="dark" content="分配角色" placement="top" :enterable="false">
              <el-button type="warning" icon="el-icon-setting" size="mini" @click="setRole(scope.row)"></el-button>
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
        <el-form-item label="部门名称" prop="deptName">
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
        <el-form-item label="部门名称" prop="deptName">
          <el-input v-model="editForm.deptName"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="editDeptInfo">确 定</el-button>
              </span>
    </el-dialog>

    <!--        &lt;!&ndash;分配角色的对话框&ndash;&gt;-->
    <!--        <el-dialog-->
    <!--                title="分配角色"-->
    <!--                :visible.sync="setRoleDialogVisible"-->
    <!--                width="50%"-->
    <!--                @close="setRoleDialogClosed">-->
    <!--            <div>-->
    <!--                <p>当前的用户: {{userInfo.username}}</p>-->
    <!--                <p>当前的角色: {{userInfo.role_name}}</p>-->
    <!--                <p>分配新角色:-->
    <!--                    <el-select v-model="selectedRoleId" placeholder="请选择">-->
    <!--                        <el-option-->
    <!--                                v-for="item in rolesList"-->
    <!--                                :key="item.id"-->
    <!--                                :label="item.roleName"-->
    <!--                                :value="item.id">-->
    <!--                        </el-option>-->
    <!--                    </el-select></p>-->
    <!--            </div>-->
    <!--            <span slot="footer" class="dialog-footer">-->
    <!--        <el-button @click="setRoleDialogVisible = false">取 消</el-button>-->
    <!--        <el-button type="primary" @click="saveRoleInfo">确 定</el-button>-->
    <!--      </span>-->
    <!--        </el-dialog>-->
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
        id: '',
        role: [],
        permission: [],
        show: false,
        username: document.cookie,
        userList: [],
        editForm: [],
        addForm: {
          deptName: ''
        },
        editDialogVisible: false,
        addDialogVisible: false
      }
    },
    mounted() {
      this.init()
      this.getUserList()
    },
    methods: {
      init() {
        let that = this
        this.$http.get('user/find', {
          params: {
            username: that.username
          }
        }).then(response => {
          let data = response.data
          that.id = data.data.id
          that.role = data.data.roles
          let permission = this.role[0].permissions
          for (var x = 0; x < permission.length; x++) {
            this.permission.push(permission[x].id)
          }
          this.show = true
        })
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
      async getUserList() {
        let that = this
        const {data: res} = await this.$http.get('user/findAll', {
          params: {
            userId: that.id,
            roleId: that.role[0].id
          }
        })
        this.userList = res.data
        console.log(this.userList)
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
