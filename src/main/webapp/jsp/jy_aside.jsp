<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--_menu 作为公共模版分离出去-->
<aside class="Hui-aside">

	<div class="menu_dropdown bk_2">
		<dl id="">
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
		<dl>
			<dt>
				<i class="Hui-iconfont"></i>教研项目管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<!-- <li><a data-href="team.jsp" data-title="教研项目管理">教研项目申报</a></li> -->
					<li><a data-href="${pageContext.request.contextPath}/jy_project/findAll_category.do" data-title="教研项目管理">教研项目申报</a></li>
				</ul>
			</dd>
		</dl>
		<dl>	
			<dt>
				<i class="Hui-iconfont"></i> 教材业绩管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a data-href="jy_book.jsp"
						data-title="著作业绩管理">教材项目申报 </a></li>
			    </ul>
			</dd>
		</dl>
		
		<dl>
			<dt>
				<i class="Hui-iconfont"></i> 教研平台业绩管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
			 		<li><a 
					data-href="${pageContext.request.contextPath}/jy_pingtai/FindAll_lev.do" data-title="教研平台业绩申报">教研平台业绩录入</a></li>
		
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
					<li><a
						data-href="jy_wenyi.jsp"
						data-title="文艺项目申报">文艺项目申报</a></li>
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
						data-href="jy_record.jsp"
						data-title="科研奖励业绩管理">查看申报记录</a></li>
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
						data-title="科研奖励业绩管理">图表查看</a></li>
					</ul>
			</dd>
		</dl>



	</div>
</aside>
 <div class="dislpayArrow hidden-xs">
	<a class="pngfix" data-href="javascript:void(0);"
		onClick="displaynavbar(this)"></a>
</div>