<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>商品展示页面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
<style>
body {
	margin: 10px;
}

.demo-carousel {
	height: 200px;
	line-height: 200px;
	text-align: center;
}
</style>
</head>
<body>
	<div>
		<input type="text" name="" />
	</div>
	<table class="layui-hide" id="demo" lay-filter="iteToolBar"></table>
	<div id="toolbarDemo" style="display: none;" class="layui-btn-container">
	    <button class="layui-btn layui-btn-sm" lay-event="deleteItem">选中删除</button>
	    <button class="layui-btn layui-btn-sm" lay-event="addItemItem">新增商品</button>
	    <button class="layui-btn layui-btn-sm" lay-event="updateItem">修改商品</button>
	    <button class="layui-btn layui-btn-sm" lay-event="upItem">上架</button>
	    <button class="layui-btn layui-btn-sm" lay-event="downItem">下架</button>
  	</div>
	<div style="display: none;" id="barDemo">
	  
	  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</div>

	

	

	<script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/jquery-2.1.0.min.js"></script>
	<script>
		
		layui.use( 'table',function(){
				table=layui.table;				
							

							

							//执行一个 table 实例
							table.render({
								elem : '#demo',
								height : 420,
								url : '/item/itemJson' ,//数据接口
								title : '商品表',
								page : true ,//开启分页
								toolbar : '#toolbarDemo' ,//开启工具栏，此处显示默认图标，可以自定义模板，详见文档
								defaultToolbar : [ 'filter', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
									layEvent : 'LAYTABLE_TIPS',
									icon : 'layui-icon-tips'
								} ],
								
								cols : [ [ //表头
								           {type: 'checkbox', fixed: 'left'}
								           ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'}
								           ,{field: 'title', title: '商品名称', width:80}
								           ,{field: 'sellPoint', title: '描述', width: 90,  totalRow: true}
								           ,{field: 'price', title: '价格', width:80}
								           ,{field: 'num', title: '数量', width: 80,  totalRow: true}
								           ,{field: 'barcode', title: 'barcode', width:150} 
								           ,{field: 'image', title: '图片路径', width: 200}
								           ,{field: 'cid', title: 'cid', width: 100}
								           ,{field: 'status', title: '状态', width: 100 
								        	   ,templet:function(item){
								        	    	  if(item.status=='1'){
								        	    		  return "上架";
								        	    	  }else if(item.status=='2'){
								        	    		  return "下架";
								        	    	  }
								           		}
								           }		
								           ,{field: 'created', title: '创建时间', templet:"<div>{{layui.util.toDateString(d.created,'yyyy-MM-dd HH:mm:ss')}}</div>" , width: 135, totalRow: true}
								           ,{field: 'updated', title: '修改时间', templet:"<div>{{layui.util.toDateString(d.updated,'yyyy-MM-dd HH:mm:ss')}}</div>" , width: 100}
								           ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
								 ] ]
							});

							//监听头工具栏事件
							table.on('toolbar(iteToolBar)',
											function(obj) {
												var checkStatus = table
														.checkStatus(obj.config.id); //获取选中的数据
												switch (obj.event) {
												
												case 'deleteItem':
													var data = checkStatus.data;
											    	
											    	$.ajax({
											    		   type: "post",
											    		   url: "/item/deleteItem",
											    		   contentType: "application/json;charset=utf-8",
											    		   data:JSON.stringify(data),
											    		   dataType: "json",
											    		   success: function(message){
											    			   if(message.status==200){
												            	   layer.alert('删除商品成功');
												            	   table.reload('demo',{  });
												               }else{
												            	   layer.alert(message.msg);
												               }
											    		   }
											    		});
												      break;
												    case 'upItem':
													        var data = checkStatus.data;
													             $.ajax({
														            type: "POST",
														            url: "/item/upItem",
														            contentType: "application/json;charset=utf-8",
														            data:JSON.stringify(data),
														            dataType: "json",
														            success:function (message) {
														               if(message.status==200){
														            	   layer.alert(message.msg);
														            	   table.reload('demo',{  });
														               }else{
														            	   layer.alert(message.msg);
														               }
														            }
														        });
													      break;
												    case 'downItem':
												        var data = checkStatus.data;
												             $.ajax({
													            type: "POST",
													            url: "/item/downItem",
													            contentType: "application/json;charset=utf-8",
													            data:JSON.stringify(data),
													            dataType: "json",
													            success:function (message) {
													               if(message.status==200){
													            	   layer.alert(message.msg);
													            	   table.reload('demo',{  });
													               }else{
													            	   layer.alert(message.msg);
													               }
													            }
													        });
												      break;
												    };
												  });
												
											});
	</script>
	<script type="text/html" id="titleTpl">
    {{#  if(d.status ==0){ }}
        	下架 
    {{#  }  else if(d.status==1){ }}
       	上架
 	{{#  }  else if(d.status==2){ }}
       	删除
    {{#  } }}
	</script>
</body>
</html>
