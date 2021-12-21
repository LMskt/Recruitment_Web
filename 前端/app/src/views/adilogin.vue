<template>
<div>
  <div>
    <div id="box">
      <div id="img">
        <img src="../../public/ren1.png" alt="" style="height: 100px;width: 100px;margin-top: 40px">
      </div>
      <form>
        <div class="input" style="margin-top: 80px;">
          <el-input v-model="username" placeholder="请输入账号"></el-input>
        </div>
        <div class="input" style="margin-top: 12px">
          <el-input placeholder="请输入密码" v-model="password" show-password></el-input>
        </div>
        <div style="height: 20px;width: 94px;margin-left: auto;margin-right: auto;margin-top: 10px">
          <el-button type="primary" @click="login">点击登录</el-button>
        </div>
      </form>
    </div>
  </div>

</div>
</template>

<script>
import router from "@/router";
import axios from "axios";
import store from "@/store"
export default {
name: "adilogin",
  data(){
  return{
    username:'',
    password:''
  }
  },
  methods:{
    open(var1) {
      this.$alert(var1, '警告', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${ action }`
          });
        }
      });
    },
    login:function (){
      var that=this
      axios({
        url:'admin/login',
        method:"post",
        params:{
          username:this.username,
          password:this.password
        }
      }).then(function (res){
        if(res.data.errorCode==0){
          store.commit('del_all')
          store.commit('set_jwt',res.data.data.jwt)
          store.commit('set_identity',1)
          //登录成功,跳转页面
          router.push('/findallstudent')
        }else{
          that.open('信息错误，请重新输入')
        }
      }).catch(function (err){
        that.open('系统故障，联系管理员')
        console.log(err)
      })
    }
  }
}
</script>

<style scoped>
#box{
  height: 450px;
  width: 600px;
  margin-left: auto;
  margin-right: auto;
  background-color: white;
  margin-top: 100px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

#img{
  width: 100%;
  height: 100px;
  text-align: center;
}

.input{
  height: 60px;
  width: 500px;
  margin-right: auto;
  margin-left: auto;
  /*background-color: red;*/
}


</style>
