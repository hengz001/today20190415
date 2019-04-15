

function cellTip(value){
    return "<span title='"+value+"'>"+value+"</span>";
}
//END

function search(){
    var name = $("#search").searchbox('getName');
    var value = $("#search").searchbox('getValue');
    $('#content').datagrid('load',{name:name,value:value});
}
//END

function openWin(title,icon,url){
    $('#win').window({
        width:600,
        height:400,
        modal:true,
        title:title,
        iconCls:icon,
        top:100,
        left:100
    });
    $('#win').window('refresh',url);
}
//END

//ADD OPEN
function open_add(url){
    openWin('添加','icon-save',url);
}
//END

//ADD ACTION
function cmd_add(url){
    $('#form1').form({
        url:url,
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
//END

//UPDATE OPEN
function open_update(url){
    var index = $("#content").datagrid('getSelected');
    if(index == null){
        alert("请选取一条记录");
        return;
    }
    openWin('编辑','icon-edit',url+''+index.cId);
}
//END

//UPDATE ACTION
function cmd_update(url){
    $('#form1').form({
        url:url,
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

//REMOVE
function cmd_remove(url){
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
        url:url,
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
//END
