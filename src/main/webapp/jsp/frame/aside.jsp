<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!--_menu 作为公共模版分离出去-->
	<aside class="Hui-aside">

		<div class="menu_dropdown bk_2">
			<dl id="menu-article">
				<dt>
					<i class="Hui-iconfont">&#xe616;</i>科研业绩<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="${pageContext.request.contextPath}/project/findAllById.do?id=${sessionScope.user.user_Id}"
							data-title="科研项目管理">科研项目业绩管理</a></li>
						<li><a data-href="${pageContext.request.contextPath}/paper/findAllById.do?id=${sessionScope.user.user_Id}" data-title="论文业绩管理">论文业绩管理</a></li>
						<li><a data-href="${pageContext.request.contextPath}/patent/findAllById.do?id=${sessionScope.user.user_Id}" data-title="知识产权业绩管理">知识产权业绩管理</a></li>
						<li><a data-href="${pageContext.request.contextPath}/writings/findAllById.do?id=${sessionScope.user.user_Id}"
							data-title="著作业绩管理">著作业绩管理 </a></li>
							<li><a data-href="${pageContext.request.contextPath}/conclude/findAllById.do?id=${sessionScope.user.user_Id}" data-title="项目结项管理">项目结项管理</a></li>
						<li><a data-href="${pageContext.request.contextPath}/assess/findAllById.do?id=${sessionScope.user.user_Id}" data-title="项目评价管理">项目评价管理</a></li>
						<li><a data-href="${pageContext.request.contextPath}/reward/findAllById.do?id=${sessionScope.user.user_Id}" data-title="科研奖励业绩管理">科研奖励业绩管理</a></li>
						<li><a data-href="${pageContext.request.contextPath}/plateform/findAllById.do?id=${sessionScope.user.user_Id}" data-title="科研、学科平台业绩">科研、学科平台业绩管理</a></li>
						<li><a data-href="${pageContext.request.contextPath}/subject/findAllById.do?id=${sessionScope.user.user_Id}" data-title="学科建设业绩管理">学科建设业绩管理</a></li>
					</ul>
				
				</dd>
			</dl>
			<dl id="menu-picture">
				<dt>
					<i class="Hui-iconfont">&#xe613;</i> 教研业绩<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="${pageContext.request.contextPath}/paper/findAll.do" data-title="论文业绩录入">论文业绩录入</a></li>
						<li><a data-href="article-list.html" data-title="业绩点分配">业绩点分配</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-product">
				<dt>
					<i class="Hui-iconfont">&#xe620;</i> 知识产权业绩管理<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="${pageContext.request.contextPath}/patent/findAll.do" data-title="知识产权业绩管理">知识产权业绩录入</a></li>
						<li><a data-href="product-category.html" data-title="业绩点分配">业绩点分配</a></li>
					</ul>
				</dd>
			</dl>
			
		</div>
	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" data-href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>