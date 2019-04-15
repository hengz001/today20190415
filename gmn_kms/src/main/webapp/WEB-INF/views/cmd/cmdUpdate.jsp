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
    <title>Update</title>
</head>

<body>
        <br>
        <form id="form1" method="post" align="center">
            <input type="hidden" name="cId" value="${cmd.cId}"/>
            <input type="hidden" name="cCmd" value="${cmd.cCmd}"/>
            <input type="hidden" name="cFlag" value="${cmd.cFlag}"/>
            <br>
            <div>
                <label>  请求 </label>
                <input class="easyui-textbox"  style="width:80%" name="cRequest" value="${cmd.cRequest}">
            </div>
            <br>
            <div>
                <label>  响应 </label>
                <input class="easyui-textbox"  style="width:80%" name="cResponse" value="${cmd.cResponse}">
            </div>
            <br>
            <div>
                <label>  描述 </label>
                <input class="easyui-textbox"  style="width:80%" name="cDescrible" value="${cmd.cDescrible}">
            </div>
            <br>
            <div align="center">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="cmd_update('${pageContext.request.contextPath}/cmd/updateCmd');">提交</a>
            </div>
        </form>


</body>
</html>
