import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
// 实例化Store对象
const store = new Vuex.Store({
	// 这里定义共享变量
    state:{
        counter:1000
    },

});

// 导出
export default store




