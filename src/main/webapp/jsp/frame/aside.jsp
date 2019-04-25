<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!--_menu 作为公共模版分离出去-->
	<aside class="Hui-aside">

		<div class="menu_dropdown bk_2">
			
	
	
				<dl id="menu-article">
				<dt>
					<i class="Hui-iconfont"></i>创建小组<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="${pageContext.request.contextPath}/team/findAll_user.do" data-title="创建小组">创建小组</a></li>
					</ul>
				</dd>
			</dl>		
			
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
					<%-- 	<li><a data-href="${pageContext.request.contextPath}/jy_project/findAll_category.do" data-title="教研项目管理">教研项目申报</a></li>
						 --%>
						<li><a data-href="${pageContext.request.contextPath}/jy_project/Jiaogai_Lev.do" data-title="教改项目管理">教改项目申报</a></li>
						<li><a data-href="${pageContext.request.contextPath}/jy_project/Zhuanye_Lev.do" data-title="专业项目管理">专业项目申报</a></li>
						<li><a data-href="${pageContext.request.contextPath}/jy_project/Kecheng_Lev.do" data-title="课程项目管理">课程项目申报</a></li>
						<li><a data-href="${pageContext.request.contextPath}/jy_project/Huojiang_Lev.do" data-title="获奖项目管理">获奖项目申报</a></li>
						
						<li><a data-href="${pageContext.request.contextPath}/jy_book/FindBookLev.do" data-title="教材业绩管理">教材项目申报 </a></li>
						<li><a data-href="${pageContext.request.contextPath}/jy_project/lunwen_Lev.do" data-title="论文业绩录入">论文业绩录入</a></li>
						<li><a data-href="${pageContext.request.contextPath}/jy_pingtai/FindAll_lev.do" data-title="教研平台业绩申报">教研平台业绩录入</a></li>
					</ul>
				</dd>
			</dl>
			<dl >
			<dt>
				<i class="Hui-iconfont"></i>文艺项目业绩管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a data-href="../jsp/wy_chuban.jsp" data-title="作品出版和发表申报">作品出版和发表申报</a></li>
					<li><a data-href="../jsp/wy_biaoyan.jsp" data-title="艺术展览与文艺演出业绩申报">艺术展览与演出业绩申报</a></li>
						
				</ul>
			</dd>
		</dl>
			<dl>
			<dt>
				<i class="Hui-iconfont"></i>申报记录<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a
						data-href="../jsp/jy_record.jsp"
						data-title="查看申报记录">查看申报记录</a></li>
				</ul>
					
			</dd>
		</dl>
			<dl>
			<dt>
				<i class="Hui-iconfont"></i>个人业绩浏览<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a
						data-href="${pageContext.request.contextPath}/reward/findAll.do"
						data-title="图表查看">图表查看</a></li>
					</ul>
			</dd>
			</dl>
			<dl>
			<dt>
				<i class="Hui-iconfont"></i>参数管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a
						data-href="${pageContext.request.contextPath}/dictpara/findAll.do"
						data-title="图表查看">参数管理</a></li>
					</ul>
			</dd>
			</dl>
		</div>
	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" data-href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>