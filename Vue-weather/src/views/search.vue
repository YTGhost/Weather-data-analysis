<template>
    <div class="out_div">
        <el-input placeholder="请输入内容" style="width:20%"></el-input>
        <el-button>搜索</el-button>
        <table style="width: 50%;margin-left: 25%;">
            <tr class="table_column" >
                <td class="table_column" >邮箱</td>
                <td class="table_column" >用户名</td>
                <td class="table_column" >密码</td>
                <td class="table_column" >操作</td></tr>
            <tr class="table_column" v-for="items in basic_info" :key="items" style="padding-bottom: 3px ">
                <td class="table_column" style="padding-right: 3px " v-for="item in items" :key="item">{{item}}</td>
                <td class="table_column"><el-button>删除</el-button></td>
            </tr>
        </table>
    </div>
</template>

<script>
    export default {
        name: "search",
        data(){
            return {
                basic_info:[[1,2,3],[4,5,6]]
            }

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
    }
    .out_div{
        background-color: #dbf7ff;
    }

</style>
