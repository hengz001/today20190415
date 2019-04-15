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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/cmdShow.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/cmdRequest.js"></script>
</head>

<body>

    <%--工具栏--%>
    <div id="toolbar">
        <a id='req' class="easyui-linkbutton" data-options="iconCls:'icon-man'" onclick=" openWin('测试','icon-man','${pageContext.request.contextPath}/cmd/request');">测试</a>
        &nbsp;
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
        &nbsp;
        <a id='add' class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="open_add('${pageContext.request.contextPath}/cmd/add')">添加</a>
        <a id='update' class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="open_update('${pageContext.request.contextPath}/cmd/update?cId=');">编辑</a>
        <a id='delete' class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="cmd_remove('${pageContext.request.contextPath}/cmd/remove');">删除</a>

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
