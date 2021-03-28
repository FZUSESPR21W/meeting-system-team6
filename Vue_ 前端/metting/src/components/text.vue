<template>
    <div id="home">
        <nav class="navbar navbar-default" role="navigation"> 
			<div class="container-fluid"> 
			<div class="navbar-header"> 
				<a class="navbar-brand  navbar-right" href="#" >XX会议</a> 
			</div> 
			<div> 
				<ul class="nav navbar-nav navbar-right"> 
					<li class="active"><a href="#">首页</a></li> 
					<li><a href="#">XXX</a></li> 
					<li class="dropdown"> 
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
							用户名(这里要读取)<b class="caret"></b> 
						</a> 
						<ul class="dropdown-menu"> 
							<li><a href="#">如：小小的杨洁</a></li> 
							<li><a href="#">登出</a></li> 
						</ul> 
					</li> 
				</ul> 
			</div> 
			</div> 
		</nav>
		<div class="container">

            
				<ul class="pagination">
					<li><a href="#">&laquo;</a></li>
					<li><a href="#">1</a></li>
					<li class="active"><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">&raquo;</a></li>
				</ul>
			</div>
			<div class="col-md-6" id="postNotice">

			</div>
		</div>
    </div>
</template>
<script>
export default {
    
}
</script>
<style scoped>
@import '../assets/bootstrap/css/bootstrap.css';
@import '../assets/css/main.css';
</style>