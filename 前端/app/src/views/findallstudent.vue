<template>
<div>
  <div id="body">
    <el-card class="box-card">
      <div  class="text item">
        <div id="body2">
          <el-table
              style="width: 100%;height: 450px"
              :data="student.slice((currentPage-1)*pagesize,currentPage*pagesize)"
          >
            <el-table-column
                prop="id"
                label="id"
                sortable
                width="80">
            </el-table-column>
            <el-table-column
                prop="name"
                label="姓名"
                sortable
                width="130">
            </el-table-column>
            <el-table-column
                prop="institute"
                label="专业班级"
                sortable
                width="150">
            </el-table-column>
            <el-table-column
                prop="qq"
                label="qq号"
                sortable
                width="180">
            </el-table-column>
            <el-table-column
                prop="majorOne"
                label="第一志愿"
                sortable
                width="150">
            </el-table-column>
            <el-table-column
                prop="majorTwo"
                label="第二志愿"
                sortable
                width="150">
            </el-table-column>
            <el-table-column
                prop="time"
                label="报名时间"
                sortable
                width="180">
            </el-table-column>
            <el-table-column label="删除任务">
              <template slot-scope="scope">
                <el-button type="danger" icon="el-icon-delete" circle @click="deleteid(scope.row.id)"></el-button>
              </template>
            </el-table-column>
            <el-table-column label="查看简介">
            <template slot-scope="scope">
              <el-button  type="info" icon="el-icon-message" circle @click="giao(scope.row.introduce)"></el-button>
            </template>
          </el-table-column>
          </el-table>
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[5]"
              :page-size="pagesize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="student.length">
          </el-pagination>
        </div>
      </div>
    </el-card>
  </div>
  <el-dialog
      :visible.sync="dialogVisible"
      width="50%">
    <div >
      <el-input type="textarea"
                :rows="8"
                resize="none"
                v-model="introduce"

      >
      </el-input>
    </div>
    <span slot="footer" class="dialog-footer">
  </span>
  </el-dialog>
</div>
</template>

<script>
import {httpGet} from "@/api/api";
export default {
  name: "findallstudent",
  data(){
    return{
      student:[],
      currentPage:1,
      pagesize:6,
      dialogVisible: false,
      introduce:''
    }
  },
  methods:{
    handleSizeChange: function (size) {
      this.pagesize = size;
      console.log(this.pagesize)  //每页下拉显示数据
    },
    handleCurrentChange: function(currentPage){
      this.currentPage = currentPage;
      console.log(this.currentPage)  //点击第几页
    },
    getdata(){
      httpGet({
        url:'/admin/getstudent',
        params:{
        }
      }).then(res=>{
        if(res.errorCode==0){
          this.student=res.data.student
        }
      }).catch(err=>{
        console.log(err)
      })
    },
    giao(introduce){
      this.introduce=introduce
      this.dialogVisible=true
    },
    deleteid(id){
      httpGet({
        url:'admin/deleteit',
        params:{
          id:id
        }
      }).then(res=>{
        if (res.errorCode==0){
          window.location.reload()
        }
      }).catch(err=>{
        console.log(err)
      })
    }
  },
  created() {
    this.getdata()
  }
}
</script>

<style scoped>
#body{
  width: 1250px;
  height:550px ;
  margin-right: auto;
  margin-left: auto;
  margin-top: 50px;
}
#body2{
  width: 1250px;
  height:550px ;
  overflow-y: auto;
  overflow-x: hidden;

}
.box-card {
  height: 550px;
  width: 1250px;
}
.item {
  padding: 18px 0;
}
</style>
