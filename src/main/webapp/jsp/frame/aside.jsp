<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!--_menu 作为公共模版分离出去-->
	<aside class="Hui-aside">

		<div class="menu_dropdown bk_2">
			<dl id="menu-article">
				<dt>
					<i class="Hui-iconfont">&#xe616;</i>科研项目业绩管理<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="${pageContext.request.contextPath}/project/findAll.do"
							data-title="科研项目管理">科研项目立项录入</a></li>
						<li><a data-href="jsp/keyan/project-list.jsp"
							data-title="科研项目管理">业绩点分配</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-picture">
				<dt>
					<i class="Hui-iconfont">&#xe613;</i> 论文业绩管理<i
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
			<dl id="menu-comments">
				<dt>
					<i class="Hui-iconfont">&#xe622;</i> 著作业绩管理<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="${pageContext.request.contextPath}/writings/findAll.do"
							data-title="著作业绩管理">著作业绩录入 </a></li>
						<li><a data-href="product-category.html" data-title="业绩点分配">业绩点分配</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-member">
				<dt>
					<i class="Hui-iconfont">&#xe60d;</i> 项目结项、评价管理<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="${pageContext.request.contextPath}/conclude/findAll.do" data-title="项目结项、评价管理">项目结项录入</a></li>
						<li><a data-href="${pageContext.request.contextPath}/assess/findAll.do" data-title="项目结项、评价管理">项目评价录入</a></li>
						<li><a data-href="product-category.html" data-title="业绩点分配">业绩点分配</a></li>

					</ul>
				</dd>
			</dl>
			<dl id="menu-admin">
				<dt>
					<i class="Hui-iconfont">&#xe62d;</i> 科研奖励业绩管理<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="${pageContext.request.contextPath}/reward/findAll.do" data-title="科研奖励业绩管理">科研奖励业绩录入</a></li>
						<li><a data-href="product-category.html" data-title="业绩点分配">业绩点分配</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-tongji">
				<dt>
					<i class="Hui-iconfont">&#xe61a;</i> 科研、学科平台业绩管理<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="${pageContext.request.contextPath}/plateform/findAll.do" data-title="科研、学科平台业绩">科研平台业绩录入</a></li>
						<li><a data-href="charts-2.html" title="时间轴折线图">学科平台业绩录入</a></li>
						<li><a data-href="product-category.html" data-title="业绩点分配">业绩点分配</a></li>

					</ul>
				</dd>
			</dl>
			<dl id="menu-system">
				<dt>
					<i class="Hui-iconfont">&#xe62e;</i> 学科建设业绩管理<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="${pageContext.request.contextPath}/subject/findAll.do" data-title="学科建设业绩管理">学科建设业绩录</a></li>
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