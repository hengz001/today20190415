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
    <title>Request</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/cmdRequest.js"></script>
</head>

<body>
        <div>
            <form id="form1" method="post" align="center">
                <div>
                    IP<input id="ip"
                             class="easyui-textbox"
                             data-options="width:200,height:24" value="${sessionScope.ip}"/>   &nbsp
                    端口<input id="port"
                             class="easyui-textbox"
                             data-options="width:100,height:24" value="${sessionScope.port}"/> &nbsp
                    <a id="con_button"
                       class="easyui-linkbutton"
                       data-options="iconCls:'icon-ok',width:64,height:24" onclick="req_connect('${pageContext.request.contextPath}/cmd/connect');">连接</a>
                </div>
                <br>
                <div>
                    请求<input id="request"
                             class="easyui-textbox"
                             data-options="width:600,disabled:true"/>
                </div>
                <div>
                    响应<input id="response"
                             class="easyui-textbox"
                             data-options="width:600,disabled:true"/>
                </div>
                <div>
                    <input id="hexButton"
                           class="easyui-switchbutton"
                           data-options="disabled:true,onText:'HEX',offText:'字符',onChange:req_get_flag"/>
                    &nbsp;
                    <a id="req_button" onclick="send_cmd('${pageContext.request.contextPath}/cmd/sendCmd');"
                       class="easyui-linkbutton"
                       data-options="iconCls:'icon-ok',width:64,height:24,disabled:true">发送</a>
                </div>
            </form>
        </div>
</body>
</html>
