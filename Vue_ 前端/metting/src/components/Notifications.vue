<template>
  <div id="notification-list">
    <el-row type="flex" justify="center">
      <el-col :span="23">
        <el-table :data="notificationList" style="width: 100%" border stripe>
          <!-- 列表每一列中，prop指定字典列表中每一项的键，label为要在列表中显示的列名 -->
          <el-table-column prop="title" label="标题" width="180">
          </el-table-column>
          <el-table-column prop="belongSubForum" label="来自论坛" width="180">
          </el-table-column>
          <el-table-column prop="belongChairMan" label="来自主席">
          </el-table-column>
          <el-table-column>
            <!-- template内部获取元数据-->
            <template v-slot="scope">
              <!-- row属性获取数据，如：{“id":1,"username":"yzx"}-->
              <el-button type="primary" @click="check(scope.row)">查看</el-button>
              <el-button type="success" @click="read(scope.row)">已读</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {request}from '../network/request';
export default {
  data: () => {
    return {
      notificationUrl: "note/has_note/1",

      readUrl:"note/read/",

      notificationList: [],
    };
  },
  created: function () {
    this.getNotificationData();
  },
  methods: {
    getNotificationData() {
      request({
          url:this.notificationUrl,
          method:"get",
          // headers:{
          //   Authorization:
          //     "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoieXp4In0.gkwEKNva7sgdLEoaAfb2cURQVxcPy4XJU9UXrYgAK2M",
          // }
          })
        .then((res) => {
          this.notificationList = res.data;
        });
    },

    check(scope){
        console.log(scope)
        this.$alert(scope.content, scope.title, {
          confirmButtonText: '确定',
        });
    },

    read(scope){
        this.$http.get(this.readUrl+scope.id,{
          headers: {
            Authorization:
              "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoieXp4In0.gkwEKNva7sgdLEoaAfb2cURQVxcPy4XJU9UXrYgAK2M",
          },
        })
        .then( res => {
            let resData = res.data.data;
            if (resData.code !== 200){
                this.$message.error(resData.msg);
            }
            else{
                this.$message.success(resData.msg);
                this.getNotificationData();
            }
        })
    }

  },
};
</script>

<style scoped>
#notification-list {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  height: 500px;
  width: 900px;
}
</style>
