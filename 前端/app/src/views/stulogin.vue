<template>
<div>
  <div>
    <div id="img">
      <img src="../../public/iot.png"  style="height: 80px;width: 80px;">
    </div>
    <div style="text-align: center"><h3>物联网实验室招新报名</h3></div>
    <div class="tongyong">
      <hr align=center width=90% color=black SIZE=1>
      <el-input
          placeholder="请输入姓名"
          v-model="name"
          clearable>
      </el-input>
      <hr align=center width=90% color=black SIZE=1>
      <el-input
          placeholder="请输入学院/班级"
          v-model="institute"
          clearable>
      </el-input>
      <hr align=center width=90% color=black SIZE=1>
      <el-input
          placeholder="请输入qq号"
          v-model="qq"
          onkeyup="this.value = this.value.replace(/[^\d.]/g,'');"
          maxlength="12"
          clearable>
      </el-input>
      <hr align=center width=90% color=black SIZE=1>
    </div>
    <div class="tongyong2" style="text-align: center">
      <el-select v-model="majorOne" placeholder="请选择第一志愿">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
    </div>
    <div class="tongyong2" style="text-align: center">
      <el-select v-model="majorTwo" placeholder="请选择第二志愿">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
    </div>
    <div class="tongyong">
      <hr align=center width=90% color=black SIZE=1>
      <el-input type="textarea"
                :rows="4"
                resize="none"
                v-model="introduce"
                placeholder="请输入一段简单的自我介绍"
                >
      </el-input>
      </div>
      <hr align=center width=90% color=black SIZE=1>
    </div>
  <div style="text-align: center;margin-top: 40px;">
    <el-button type="primary" round @click="login">点击提交</el-button>
  </div>
  <div style="height: 30px"></div>
  </div>
</template>

<script>
import {httpPost} from "@/api/api";
import router from "@/router";

export default {
name: "stulogin",
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
          // this.open2()
          // var giao=setTimeout(" window.location.reload();",500)
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
  // created() {
  //   console.log("asd")
  //   // if (screen.width>600) {
  //   //   alert("asd")
  //   //   router.push('/pcstulogin')
  //   // }
  // }
}
</script>

<style scoped>
#img{
  width: 100%;
  height: 80px;
  text-align: center;
  margin-top: 20px;
}
.tongyong{
  margin-top: 30px;
  padding-left: 6px;
  padding-right: 6px;
}
.tongyong2{
  margin-top: 30px;
  padding-left: 6px;
  padding-right: 6px;
}
.tongyong>>>.el-textarea__inner{
  border: 0;
}

.tongyong>>>.el-input__inner{
  border: 0;
}

</style>
