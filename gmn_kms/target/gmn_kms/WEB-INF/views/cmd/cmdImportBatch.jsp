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
    <title>IMPORT_BATCH</title>
    <script type="text/javascript">
        function cmd_import_batch()
        {
            $("#import1").submit();
        }
    </script>
</head>
<body>
        <br>
        <form id="import1" method="post" align="center" enctype="multipart/form-data" action="${pageContext.request.contextPath}/cmd/cmdImportBatch">
            <br>
            <div>
                <input class="easyui-filebox"  style="width:60%" name="file" id="file" multiple="multiple">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="cmd_import_batch();">import_batch</a>
            </div>
            <br>
        </form>
</body>
</html>
