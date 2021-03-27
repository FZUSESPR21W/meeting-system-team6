<template>
    <div id="user-list">
        <h3>论坛参与者列表</h3>
        <el-row type="flex" justify="center">
      <el-col :span="23">
        <el-table :data="userList" border stripe style="width:627px;">
          <!-- 列表每一列中，prop指定字典列表中每一项的键，label为要在列表中显示的列名 -->
          <el-table-column prop="username" label="用户名" width="300px">
          </el-table-column>
          <el-table-column label="角色" width="300px">
              <template>
                  普通参与者
              </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    </div>
</template>

<script>

export default{

    data:() => {
        return {
                userList : [],
                userUrl : "default/members/1"
        }
    },

    created:function(){
        this.getUserData();
    },

    methods:{
        getUserData(){
            this.$http
        .get(this.userUrl, {
          headers: {
            Authorization:
              "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoieXp4In0.gkwEKNva7sgdLEoaAfb2cURQVxcPy4XJU9UXrYgAK2M",
          },
        })
        .then((res) => {
          let resData = res.data.data;
          if (resData.code !== 200){
                this.$message.error(resData.msg);
            }
            else{
                console.log(resData.data)
                this.userList = resData.data;
            }
        });
        }
    }

}
</script>

<style scoped>
#user-list {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  height: 500px;
  width: min-900px;
}
</style>
