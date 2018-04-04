<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <title>选择上传</title>
    <%@include file="inc/meta.jsp" %>
    <script type="text/javascript">
        $(function () {
            var submit = function (This) {
                if (!confirm('确定?')) {
                    return;
                }
                var data = new FormData();
                jQuery.each($('input[type=file]'), function (i, e) {
                    if (e.files && e.files.length > 0) {
                        data.append(e.name, e.files[0]);
                    }
                });

                $.ajax({
                    data: data,
                    url: "/welcome/upload",
                    method: "POST",
                    context: This,
                    cache: false,
                    contentType: false,
                    processData: false
                }).done(function (r) {
                    if (r.success) {
                        alert("success!!!");
                    }else{
                        alert("fail!!!"+r.message);
                    }
                });

            };

            $('.btn-save').click(function () {
                submit(this);
            });

        });
    </script>
</head>
<body>
<form enctype="multipart/form-data">
    <input type="file" id="file" name="file"/>
    <input type="button" value="上传" class="btn-save">
</form>
</body>
</html>
