## vue文档规范

>组件名应该始终是多个单词的，根组件 App 以及 <transition>、<component> 之类的 Vue 内置组件除外。这样做可以避免跟现有的以及未来的 HTML 元素相冲突，因为所有的 HTML 元素名称都是单个单词的。
```
Vue.component('todo-item', {
  // ...
})
export default {
  name: 'TodoItem',
  // ...
}
```
>组件的 data 必须是一个函数。当在组件中使用 data property 的时候 (除了 new Vue 外的任何地方)，它的值必须是返回一个对象的函数。

>Prop 定义应该尽量详细。

在你提交的代码中，prop 的定义应该尽量详细，至少需要指定其类型。
```
props: {
  status: String
}
// 更好的做法！
props: {
  status: {
    type: String,
    required: true,
    validator: function (value) {
      return [
        'syncing',
        'synced',
        'version-conflict',
        'error'
      ].indexOf(value) !== -1
    }
  }
}
```

>用 key 配合 v-for。在组件上总是必须用 key 配合 v-for，以便维护内部组件及其子树的状态。

>避免v-ifhe v-for一起使用


>使用模块作用域保持不允许外部访问的函数的私有性。如果无法做到这一点，就始终为插件、混入等不考虑作为对外公共 API 的自定义私有 property 使用 $_ 前缀。
```
<ul>
  <li
    v-for="user in activeUsers"
    :key="user.id"
  >
    {{ user.name }}
  </li>
</ul>
<ul v-if="shouldShowUsers">
  <li
    v-for="user in users"
    :key="user.id"
  >
    {{ user.name }}
  </li>
</ul>
```
>对于应用来说，顶级 App 组件和布局组件中的样式可以是全局的，但是其它所有组件都应该是有作用域的。对于组件库，我们应该更倾向于选用基于 class 的策略而不是 scoped attribute。


>使用模块作用域保持不允许外部访问的函数的私有性。如果无法做到这一点，就始终为插件、混入等不考虑作为对外公共 API 的自定义私有 property 使用 $_ 前缀。并附带一个命名空间以回避和其它作者的冲突

## html文档规范
>除id、name为驼峰命名法外，标签名及属性名全部字母小写。属性值使用双引号包裹。
id、name属性为驼峰命名；class为短横分隔命名
id、name同一页面必须唯一 

```
<div id="todayNews" class="container-fluid">
  <input name="username">
</div>
```

>同一页面，不同元素，应避免使用相同的 name 与 id

```
<input name="foo">
<div id="foo"></div>
<script>
// IE6 将显示 INPUT
alert(document.getElementById('foo').tagName);
</script>
```

>代码缩进和换行
使用 2 个空格做为一个缩进层级，不允许使用 4 个空格 或 tab 字符。
建议每行不得超过 120 个字符。

>禁止为了 hook 脚本，创建无样式信息的 class
不允许 class 只用于让 JavaScript 选择某些元素，class 应该具有明确的语义和样式。否则容易导致 css class 泛滥。使用 id、属性选择作为 hook 是更好的方式。
