
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		performance: {},
		oneTypeList:{

		},
		twoTypeList:{

		}
	},
	created:function(){

	},
	methods: {
		getTwoTypeList:function(){
			$.get(baseURL + "core/performancetype/selectlist/"+vm.performance.typeOneId, function(r){
				vm.twoTypeList = r.list;
			});
		},
		getTypeList:function(){
			$.get(baseURL + "core/performancetype/selectlist/0", function(r){
				vm.oneTypeList = r.list;
			});
		},
		shenhe:function(){
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			 var status=$("#"+id).html();
			if(status.indexOf("审核通过")!=-1){
				alert("请选择未审核或审核不通过的业绩")
				return;
			}

			$.ajax({
				type: "POST",
				url: baseURL + "core/performance/shenhe?id="+id,
				success: function(r){
					if(r.code === 0){
						alert("操作成功");
						vm.reload();
					}else{
						alert(r.msg);
					}
				}
			});

		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.performance = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}

			var status=$("#"+id).html();
			if(status.indexOf("未通过")==-1){
				alert("只能修改审核未通过的业绩");
				return;
			}
			vm.getInfo(id);
			alert(vm.performance);
			return;
			vm.showList = false;
			vm.title = "修改";
			vm.getTypeList();
			vm.getTwoTypeList();


		},
		saveOrUpdate: function (event) {
			var url = vm.performance.id == null ? "core/performance/save" : "core/performance/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.performance),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "core/performance/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "core/performance/info/"+id, function(r){
                vm.performance = r.performance;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});