<template>
    <div class="back" id="bc" @click="bc">
        <div style="margin: auto">
            返回
        </div>
    </div>
</template>

<script>
    export default {
        name:'backbtn',
        methods:{
            bc:function () {
                alert("back to login page")
            }
        }
    }


</script>

<style>
    .back{
        width: 70px;
        border-radius:15px;
        padding-left:48px ;
        padding-right:40px ;
        padding-top:20px ;
        padding-bottom:20px ;
        font-size:x-large;
        font-weight: 600;
    }
    .back:hover{
        background-color: gray;
    }
</style>