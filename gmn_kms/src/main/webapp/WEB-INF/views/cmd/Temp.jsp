<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/21
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息记录</title>
    <%@include file="/WEB-INF/views/common/common.jsp"%>
    <script type="text/javascript">
        function cellTip(value){
            return "<span title='"+value+"'>"+value+"</span>";
        }

        function search(value,name){
            var name = $("#search").searchbox('getName');
            var value = $("#search").searchbox('getValue');
            $('#content').datagrid('load',{name:name,value:value});
        }

        function createWin(title,icon){
            $('#win').window({
                width:600,
                height:400,
                modal:true,
                title:title,
                iconCls:icon,
                top:100,
                left:100
            });
        }

        function add(){
            createWin('添加','icon-save')
            $('#win').window('refresh','${pageContext.request.contextPath}/cmd/add');
        }

        //insert
        function commit(){
            $('#form1').form({
                url:'${pageContext.request.contextPath}/cmd/insert',
                onSubmit:function () {
                    //alert("onSubmit");
                },
                success:function(){
                    alert("Success");
                    $('#win').window('close');
                    $('#content').datagrid('load');
                },
                error:function () {
                    alert("Error");
                    $('#win').window('close');
                    $('#content').datagrid('load');
                }
            });
            $("#form1").submit();
        }

        function update(){
            var index = $("#content").datagrid('getSelected');
            if(index == null){
                alert("请选取一条记录");
                return;
            }
            createWin('编辑','icon-edit');
            $('#win').window('refresh','${pageContext.request.contextPath}/cmd/update?cId='+index.cId);
        }

        function updateCmd(){
            $('#form1').form({
                url:'${pageContext.request.contextPath}/cmd/updateCmd',
                onSubmit:function () {
                    //alert("onSubmit");
                },
                success:function(){
                    alert("Success");
                    $('#win').window('close');
                    $('#content').datagrid('load');
                },
                error:function () {
                    alert("Error");
                    $('#win').window('close');
                    $('#content').datagrid('load');
                }
            });
            $("#form1").submit();
        }

        function remove(){
            var index = $("#content").datagrid('getSelected');
            if(index == null){
                alert("请选取一条记录");
                return;
            }

            if(!confirm("删除不可恢复，确认删除么？")){
                return ;
            }

            $('#content').datagrid('loading');
            $.ajax({
                url:'${pageContext.request.contextPath}/cmd/remove',
                data:{cId:index.cId},
                success:function(){
                    alert("Success");
                    $('#content').datagrid('loaded');
                    $('#content').datagrid('load');
                },
                error:function(){
                    alert("Error");
                    $('#content').datagrid('loaded');
                    $('#content').datagrid('load');
                }
            });

        }
    </script>
</head>

<body>

<%--工具栏--%>
<div id="toolbar">
    <input id="search" class="easyui-searchbox"
           data-options="searcher:search,
                        prompt:'Please input value',
                        menu:'#menu',
                        width:320,
                        height:24" />
    <div id="menu" >
        <div data-options="name:'c_cmd'">命令</div>
        <div data-options="name:'c_request'">请求</div>
        <div data-options="name:'c_response'">响应</div>
        <div data-options="name:'c_describle'">描述</div>
    </div>

    <a id='add' class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="add();">添加</a>
    <a id='update' class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="update();">编辑</a>
    <a id='delete' class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="remove();">删除</a>
</div>


<%--分页列表--%>
<table id="content" class="easyui-datagrid" title="指令查询"
       data-options="
                url:'${pageContext.request.contextPath}/cmd/selectPage',
                fitColumns:true,
                singleSelect:true,
                rownumbers:true,
                pagination:true,
                iconCls:'icon-man',
                striped:false,
                toolbar:'#toolbar'">
    <thead>
    <tr>
        <th field="cId" checkbox="true" align="center"></th>
        <th field="cCmd" align="center" width="10%" sortable="true" formatter="cellTip">命令</th>
        <th field="cRequest"  align="center" width="30%" sortable="true" formatter="cellTip">请求</th>
        <th field="cResponse"  align="center" width="30%" sortable="true" formatter="cellTip">响应</th>
        <th field="cDescrible"  align="center" width="28%" sortable="true" formatter="cellTip">描述</th>
    </tr>
    </thead>
</table>

<%--窗口--%>
<div id="win" align="center"></div>

</body>
</html>
