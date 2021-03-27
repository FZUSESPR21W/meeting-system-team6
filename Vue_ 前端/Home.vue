<template>
  <div class="app">
    <el-container>
      <el-header>
          <el-menu :default-active="activeIndex"  mode="horizontal" @select="handleSelect">
          <el-menu-item index="1">XXX</el-menu-item>
          <el-menu-item index="1">退出</el-menu-item>
          </el-menu>
      </el-header>
        <el-main>
          <div class="lunbo">
            <el-carousel :interval="4000" type="card" height="300px">
              <el-carousel-item v-for="item in imagesBox" :key="item.id">
                <img :src="item.url" class="image" />
              </el-carousel-item>
            </el-carousel>
          </div>
          <el-steps :active="2" align-center>
            <el-step title="8:30" description="开幕式"></el-step>
            <el-step title="9:00" description="智慧校园时代无线技术趋势及应用"></el-step>
            <el-step title="10:00" description="三盟科技大数据技术在院研究领域的应用和实践"></el-step>
            <el-step title="11:00" description="鲲鹏展翅，助推教育信息化升腾发展"></el-step>
            <el-step title="12:00" description="会间休息"></el-step>
            <el-step title="14:00" description="数字化转型，微软与您同行"></el-step>
            <el-step title="15:00" description="人工智能驱动的校园网络"></el-step>
            <el-step title="16:00" description="塞尔安全云服务平台介绍"></el-step>
            <el-step title="18:00" description="闭幕式"></el-step>
          </el-steps>
          <br />
          <br />
          <br />
          <el-collapse v-model="activeName" accordion>
            <el-collapse-item title="智慧校园时代无线技术趋势及应用" name="1">
              <div>主席：张三</div>
              <div>召开时间：9：00-10：00</div>
            </el-collapse-item>
            <el-collapse-item title="三盟科技大数据技术在院研究领域的应用和实践" name="2">
              <div>主席：王五</div>
              <div>召开时间：10：00-11：00</div>
            </el-collapse-item>
            <el-collapse-item title="鲲鹏展翅，助推教育信息化升腾发展" name="3">
              <div>主席：小明</div>
              <div>召开时间：11：00-12：00</div>
            </el-collapse-item>
            <el-collapse-item title="数字化转型，微软与您同行" name="4">
              <div>主席：麦克</div>
              <div>召开时间：14：00-15：00</div>
            </el-collapse-item>
          </el-collapse>
        </el-main>
    </el-container>
  </div>
</template>
<style scoped>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  height: 100%;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.lunbo {
  width: 100%;
  height: 400px;
}

.el-collapse {
  width: 1300px;
  height: 800px;
  padding-left: 120px;
}

.el-collapse-item {
  text-align: center;
  background-color: #99a9bf;
  
}

.lunbo img{
  width: 100%;
  height:100%;
}

.el-menu{
  float: right;
  background-color: #b3c0d1;
}


</style>
<script>
export default {
  data() {
    return {
      activeName: "1",
      imagesBox: [
        {
          id: 0,
          /* idView: require("~@/assets/p2") */
          url:require('../assets/p2.jpg')
        },
        {
          id: 1,
          /* idView: require("~@/assets/p2") */
          url:require('../assets/p1.jpg')
        },
        {
          id: 2,
          /* idView: require("~@/assets/p2") */
          url:require('../assets/p3.jpg')
        },
        /* {
          id: 3,
          
          url:require('../assets/timg.jpg')
        }, */
      ],
      fiveNews: []
    };
  },
  methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
  }
};
</script>
