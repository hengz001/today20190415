<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/21
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/views/common/common.jsp"%>
    <title>Add</title>
</head>

<body>
        <br>
        <form id="form1" method="post" align="center">
            <br>
            <div>
                <label>  请求 </label>
                <input class="easyui-textbox"  style="width:80%" name="cRequest">
            </div>
            <br>
            <div>
                <label>  响应 </label>
                <input class="easyui-textbox"  style="width:80%" name="cResponse">
            </div>
            <br>
            <div>
                <label>  描述 </label>
                <input class="easyui-textbox"  style="width:80%" name="cDescrible">
            </div>
            <br>
            <div align="center">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="cmd_add('${pageContext.request.contextPath}/cmd/insert');">添加</a>
            </div>
        </form>
</body>
</html>
