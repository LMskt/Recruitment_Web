<template>
<div id="bac">

  <div id="body">
    <div id="img">
      <img src="../../public/iot.png"  style="height: 80px;width: 80px;">
      <h3>物联网实验室招新报名</h3>
    </div>
    <hr align=center width=90% color=black SIZE=1>
    <div><div id="left">
      <p>姓名:</p>
      <el-input v-model="name" placeholder="请输入内容"></el-input>
      <el-divider></el-divider>
      <p>学院/班级:</p>
      <el-input v-model="institute" placeholder="请输入内容"></el-input>
      <el-divider></el-divider>
      <p>qq:</p>
      <el-input v-model="qq" onkeyup="this.value = this.value.replace(/[^\d.]/g,'');" maxlength="12" placeholder="请输入内容"></el-input>
    </div>
      <div id="right">
        <div style="height: 90px">
          <div style="float: left">
            <p>选择第一志愿:</p>
            <el-select v-model="majorOne" placeholder="请选择志愿">
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </div>
          <div style="float: right">
            <p>选择第二志愿:</p>
            <el-select v-model="majorTwo" placeholder="请选择志愿">
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </div>
        </div>
        <el-divider></el-divider>
        <div>
          <p>请输入一段简单的自我介绍:</p>
          <el-input type="textarea"
                    :rows="5"
                    resize="none"
                    v-model="introduce"
                    placeholder="请输入内容" >
          </el-input>
        </div>

        <div style="width: 100%;height: 100px;text-align: center;margin-top: 40px">
          <el-button type="primary" @click="login">点击添加</el-button>
        </div>
      </div></div>
  </div>
</div>
</template>

<script>
import {httpPost} from "@/api/api";
import router from "@/router";

export default {
  name: "pcstulogin",
  data(){
    return{
      name:'',
      institute:'',
      qq:'',
      dialogVisible: false,
      options:[
        {
          value:'Web前端',
          label:'Web前端'
        }, {
          value:'Web后端',
          label:'Web后端'
        }, {
          value:'网络安全',
          label:'网络安全'
        }, {
          value:'安卓端',
          label:'安卓端'
        },{
          value:'ios端',
          label:'ios端'
        },{
          value:'人工智能',
          label:'人工智能'
        },{
          value:'微信小程序',
          label:'微信小程序'
        }
      ],
      majorOne:'',
      majorTwo:'',
      introduce:''
    }
  },
  methods:{
    open3() {
      this.$message({
        message: '所填信息都不可为空',
        type: 'warning'
      });
    },
    open2() {
      this.$message({
        message: '报名成功',
        type: 'success'
      });
    },
    open4() {
      this.$message.error('报名失败，请联系管理员');
    },
    login(){
      if(this.introduce==''||this.name==''||this.qq==''||this.majorOne==''||this.majorTwo==''||this.introduce==''){
        this.open3()
      }else {
        httpPost({
          url:'/student/login',
          params:{
            name:this.name,
            institute:this.institute,
            qq:this.qq,
            majorOne:this.majorOne,
            majorTwo:this.majorTwo,
            introduce:this.introduce
          }
        }).then(res=>{
          if(res.errorCode==0){
            router.push('/success')
          }else {
            this.open4()
          }
        }).catch(err=>{
          this.open4()
          console.log(err)
        })
      }
    }
  },
  mounted() {
    if (screen.width<=600) {
      router.push('/stulogin')
    }
  }
}
</script>

<style scoped>
#bac{
  background: url("../../public/background.png");
  background-size: 100% 100%;
  height: 100%;
  position: fixed;
  width: 100%;
}
#body{
  background-color: white;
  height: 550px;
  width: 1000px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 75px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
#left{
  height: 400px;
  width: 35%;
  margin-left: 3%;
  float: left;
  margin-top: 10px;
}
#right{
  height: 400px;
  width: 50%;
  margin-right: 3%;
  float: right;
  margin-top: 10px;
}
#img{
  width: 100%;
  height: 120px;
  text-align: center;
  margin-top: 20px;
}
</style>
