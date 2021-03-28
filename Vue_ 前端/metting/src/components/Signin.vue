<template>
  <div class="login">
    <div class="text">
      <el-row justify="center" type="flex">
        <el-col :span="5" style="margin-top:20px;">
          <i class="el-icon-user" 
          style="font-size:40px; border-radius:50%;border:2px #ddd solid;zoom:120%;padding:5px;margin-left:3px;"></i>
          <p style="margin-left : 20px;">注册</p>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="20">
        <el-form ref="registerFormRef" :model="registerForm" label-width="80px" style="margin-right:15px;margin-top:10px;" :rules="RegisterFormRules">
          <!-- 用户类型 -->
          <el-form-item label="用户类型">
            <el-select v-model="registerForm.role" placeholder="请选择注册用户类型" >
              <el-option 
              v-for="item in options" 
              :key="item.value" 
              :label="item.label" 
              :value="item.value"></el-option>
              </el-select>
          </el-form-item>

          <!-- 用户名 -->
          <el-form-item label="用户名" prop="username">
              <el-input v-model="registerForm.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item label="输入密码" prop="password">
              <el-input v-model="registerForm.password" placeholder="请输入密码" type="password"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkpassword">
              <el-input v-model="registerForm.checkpassword" placeholder="请确认密码" type="password"></el-input>
          </el-form-item>
          <a href="/register"></a>
          <el-form-item>
            <el-button type="success" @click="toLogin" size="small">
                登录
            </el-button>
            <el-button type="primary" @click="registerForward" size="small">
                注册
            </el-button>
            <el-button type="info" style="margin-left:10px;" @click="clear" size="small">
                重置
            </el-button>
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

      registerUrl : "auth/register",

      registerForm:{
        role:"",
        username:"",
        password:"",
        checkpassword:""
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
      RegisterFormRules:{
        username : [{required:true,message:"用户名不得为空!",trigger:"blur"},
                    {min:3,max:10,message: "用户名长度在三到十个字符",trigger:"blur"}],
        password : [{required:true,message:"密码不得为空",trigger:"blur"}],
        checkpassword:[{required:true,message:"确认密码不得为空",trigger:"blur"}]
      },
    }
  },
  methods:{
      

      clear(){
      this.registerForm.role = ""
      this.$refs.registerFormRef.resetFields();
    },


    registerForward(){
      this.$refs.registerFormRef.validate( valid => {
          if (!valid) return;
          if (this.registerForm.role == "") {
              this.$message.error("请选择注册用户类型!")
              return ;
            }
          if (this.registerForm.password !== this.registerForm.checkpassword) {
            this.$message.error("确认密码不一致")
            return ;
          }
          request({
            url:this.registerUrl,
            method:"post",
            data:{
            username:this.registerForm.username,
            password:this.registerForm.password,
            role:this.registerForm.role
            }
          })
          .then(res => {
            if (res.status !== 200){
                this.clear();
                this.$message.error("网络错误!")
              }else{
        
                let resData = res.data.result;
                // 注册失败
                if (resData.code !== 200){
                  this.clear();
                  this.$message.error(resData.msg);
                }else{
                  //注册成功，存token
                  this.$message.success(resData.msg);
                  // 执行跳转到主页面
                  this.$router.push("/home")
                }
              }
          })
     })
    },

    toLogin(){
      this.$router.replace("/login")
    }
  }
}
</script>
<style scoped>

.re{
  padding-top: 20px;
}



body {
  text-align: left;
}
.login {
  width: 350px;
  height: 480px;
  background-color:#fff;
  margin: 150px auto;
  font-weight: 900;
  border: 2px #409eff solid;
  border-radius: 10px;
}
.top {
  width: 100%;
  height: 50px;
  background-color:#9ddfd3;
  float: left;
  color: black;
  text-align: center;
  line-height: 50px;
}
.ref {
  width: 280px;
  height: 40px;
  margin-top: 20px;
  margin-left: 25px;
  float: left;
  text-align: center;
  line-height: 40px;
}
.refp {
  width: 280px;
  height: 40px;
  margin-top: 20px;
  margin-left: 25px;
  letter-spacing: 5px;
  float: left;
  text-align: center;
  line-height: 40px;
}
.re {
  width: 280px;
  height: 40px;
  margin-top: 20px;
  margin-left: 45px;
  float: left;
  text-align: center;
  line-height: 40px;
}
#lg {
  width: 100px;
  height: 40px;
  font-size: 12pt;
  border: 1px #eee solid;
}

.button {
  margin-top: 300px;
  margin-left: 10px;
  padding-left: 120px;
}
#input {
  border: 1px solid #ccc;
  padding: 7px 0px;
  border-radius: 3px;
  padding-left: 5px;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
  -webkit-transition: border-color ease-in-out 0.15s,
    -webkit-box-shadow ease-in-out 0.15s;
  -o-transition: border-color ease-in-out 0.15s, box-shadow ease-in-out 0.15s;
  transition: border-color ease-in-out 0.15s, box-shadow ease-in-out 0.15s;
}
#input:focus {
  border-color: #66afe9;
  outline: 0;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),
    0 0 8px rgba(102, 175, 233, 0.6);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),
    0 0 8px rgba(102, 175, 233, 0.6);
}

.shortselect {
  background: #fafdfe;
  height: 60px;
  width: 180px;
  line-height: 28px;
  border: 1px solid #9bc0dd;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
}
</style>