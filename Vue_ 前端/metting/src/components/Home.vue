<template>
    <div id="home">
        
        <div v-if= "role=='subForumChairMan'">
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="数据显示" name="first">
                    <el-col :span="16"><div class="grid-content bg-purple">
                    <el-card class="box-card">
                        <div id=Numbers>会议参与人数:{{meeting_numbers}}</div>
                        <div v-for="(item,index) in Subforum" :key="index" class="text item">
    {{'分论坛标题: ' + item.title }}
    <br />
    {{'分论坛主席: ' + item.chairman }}
  </div>
                    </el-card>
                    </div></el-col>
                </el-tab-pane>
                <el-tab-pane label="发布会议" name="second">
                    <el-card class="box-card">
    <h2>信息发布</h2>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="标题" class="input1">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="内容">
        <el-input type="textarea" v-model="form.content" class="input2"></el-input>
      </el-form-item>
      <el-form-item label="分论坛">
            <el-select v-model="form.belongSubForum" placeholder="选择分论坛" >
              <el-option 
              v-for="item in options" 
              :key="item.value" 
              :label="item.label" 
              :value="item.value"></el-option>
              </el-select>
          </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即发布</el-button>
        <el-button @click="rest">重置</el-button>
      </el-form-item>
    </el-form>
  </el-card>
                </el-tab-pane>
            </el-tabs>
        </div>

        <div v-if= "role=='meetingChairMan'">
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="数据显示" name="first">
                    <el-col :span="16"><div class="grid-content bg-purple">
                    <el-card class="box-card">
                        <div id=Numbers>会议参与人数:{{meeting_numbers}}</div>
                        <div v-for="(item,index) in Subforum" :key="index" class="text item">
    {{'分论坛标题: ' + item.title }}
    <br />
    {{'分论坛主席: ' + item.chairman }}
  </div>
                    </el-card>
                    </div></el-col>
                </el-tab-pane>
                <el-tab-pane label="发布会议" name="second">
                    <el-card class="box-card">
    <h2>信息发布</h2>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="标题" class="input1">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="内容">
        <el-input type="textarea" v-model="form.content" class="input2"></el-input>
      </el-form-item>
      <el-form-item label="分论坛">
            <el-select v-model="form.belongSubForum" placeholder="选择分论坛" >
              <el-option 
              v-for="item in options" 
              :key="item.value" 
              :label="item.label" 
              :value="item.value"></el-option>
              </el-select>
          </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即发布</el-button>
        <el-button @click="rest">重置</el-button>
      </el-form-item>
    </el-form>
  </el-card>
                </el-tab-pane>
            </el-tabs>
        </div>

    <div v-if= "role=='secretary'">
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="数据显示" name="first">
                    <el-col :span="16"><div class="grid-content bg-purple">
                    <el-card class="box-card">
                        <div id=Numbers>会议参与人数:{{meeting_numbers}}</div>
                        <div v-for="(item,index) in Subforum" :key="index" class="text item">
    {{'分论坛标题: ' + item.title }}
    <br />
    {{'分论坛主席: ' + item.chairman }}
  </div>
                    </el-card>
                    </div></el-col>
                </el-tab-pane>
                
            </el-tabs>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
import {request} from '../network/request'
export default {
    data (){
        return {
        role:'',
        meeting_numbers:100,
        form: {
        title: "",
        content: "",
        subForumId:""
      },
      options:[],
        Subforum:[],
        activeName: 'first'
      };
    },
    computed:{
        city:function(){
            return this.$store.state.counter;
        }
    },
    methods:{
        getRole(){
            return "role";
        },
        handleClick(tab, event) {
        console.log(tab, event);
      },
      getSubForm(i){
          return this.Subform[i]
      },
      onSubmit() {
        console.log(this.form)
        axios.post("http://47.96.231.121:8079/default/add/"+this.form.belongSubForum,{
          title:this.form.title,
          content:this.form.content
        },{
          headers:{
            "Authorization":localStorage.getItem("token")
          }
        }).then( res => {
      if (res.status === 200){
        this.$message.success("发布成功!");
        this.form.title = "";
        this.form.content = "";
        this.form.belongSubForum = "";
      }
		}).catch(err => {
			console.log(err) 
})
    },
    rest() {
      (this.form.title = ""), (this.form.content = "");
    }
    },

	created(){
    this.role = localStorage.getItem("role");
		request({
    		url:"/default/info",
    		method:'get'
		}).then( res => {
			let subData = res.data;
      this.Subforum = subData.data;
      this.Subforum.forEach(item => {
       this.options.push({
          label:item.title,
          value:item.id
        })
      })
		}).catch(err => {
			console.log(err) 
})

	}
  
}
</script>
<style scoped>
.text {
    font-size: 14px;
  }

  .item {
    padding: 18px 0;
  }


  .box-card {
  width: 700px;
}
</style>