<template>
  <div class="login">
    <div class="text">
      <el-row justify="center" type="flex">
        <el-col :span="5" style="margin-top:20px;">
          <i class="el-icon-user" 
          style="font-size:40px; border-radius:50%;border:2px #ddd solid;zoom:120%;padding:5px;margin-left:3px;"></i>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="20">
        <el-form ref="form" :model="loginForm" label-width="80px" style="margin-right:15px;margin-top:15px;" :rules="LoginFormRules">
          <!-- 用户类型 -->
          <el-form-item label="用户类型">
            <el-select v-model="loginForm.role" placeholder="请选择用户类型" >
              <el-option 
              v-for="item in options" 
              :key="item.value" 
              :label="item.label" 
              :value="item.value"></el-option>
              </el-select>
          </el-form-item>

          <!-- 用户名 -->
          <el-form-item label="用户名" prop="username">
              <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
              <el-input v-model="loginForm.password" placeholder="请输入密码" type="password"></el-input>
          </el-form-item> 
          <el-form-item>
            <el-button type="primary" @click="loginForaward" size="small">
                登录
            </el-button>
            <el-button type="info" style="margin-left:10px;" @click="clear" size="small">
                重置
            </el-button>
            <el-button type="success" @click="goRegister" size="small">注册</el-button>
          </el-form-item>
        </el-form>
        </el-col>
      </el-row>
  </div>
  </div>
</template>
<script>
import {request}from '../network/request';
export default {
  data() {
    return {

      // 登录api
      loginUrl : "auth/login",

      // 登录信息表单
      loginForm:{
        username:"",
        password : "",
        role:""
      },
      // 选择框内容
      options: [
        {
          value: "meetingChairMan",
          label: "会议主席",
        },
        {
          value: "subForumChairMan",
          label: "论坛主席",
        },
        {
          value: "secretary",
          label: "秘书",
        },
        {
          value: "commonMember",
          label: "普通参会者",
        },
      ],

      // 数据验证
      LoginFormRules:{
        username : [{required:true,message:"用户名不得为空!",trigger:"blur"},
                    {min:3,max:10,message: "用户名长度在三到十个字符",trigger:"blur"}],
        password : [{required:true,message:"密码不得为空",trigger:"blur"}]

      }
    };
  },


  methods:{
    /**
     * 执行登录操作
     */
    loginForaward(){
        this.$refs.form.validate( valid => {
          if (!valid) return;
            // 发起网络请求
            if (this.loginForm.role == "") {
              this.$message.error("请选择用户类型!")
              return ;
            }
            request({
              url:this.loginUrl,
              method:"post",
              data:this.loginForm})
            .then(res => {
              // 请求错误
              if (res.status !== 200){
                this.clear();
                this.$message.error("网络错误!")
              }else{
        
                let resData = res.data.result;
                // 登录失败
                if (resData.code !== 200){
                  this.loginForm.password = "";
                  this.$message.error(resData.msg);
                }else{
                  //登录成功，存token
                  let token = resData.token;
                  let currentUser = resData.user
                  console.log(currentUser)
                  window.localStorage.setItem("token",token);
                  window.localStorage.setItem("userId",currentUser.id)
                  window.localStorage.setItem("role",currentUser.role)
                  this.$message.success(resData.msg);
                  // 执行跳转到主页面
                  this.$router.push("/home")
                }
              }
            });
          })
    },

    goRegister(){
        this.$router.replace('/register');
    },

    clear(){
      this,this.loginForm.type = ""
      this.$refs.form.resetFields();
    }
  }
};
</script>
<style scoped>
body {
  background: "../assets/7.png";
}

.text {
  width: 100%;
  height: 80%;
}

.buttonFace {
  width: 100%;
  height: 20%;
  /* padding-left: 100px; */
}

/* .el-select {
  width: 200px;
  height: 50px;
  position: absolute;
  top: 80px;
  left: 100px;
} */

.login {
  width: 350px;
  height: 370px;
  background-color: #fff;
  position: absolute;
  top: 150px;
  left: 600px;
  border: 2px solid #409eff;
  border-radius:10px;
}

</style>