<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>查看已添加课程</title>
    <style type="text/css">
        #body1 {
            background-color: #fff;
        }
    </style>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/bootstrap/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/respond.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-paginator.js"></script>
    <![endif]-->
</head>
<body id="body1">
<div class="container1">
    <div class="row" style="height: auto; overflow: auto;">
        <div class="col-md-12" style="background-color:#fff;">
            <table class="table table-hover table-bordered table-striped">
                <thead>
                <tr>
                    <th>课程编号</th>
                    <th>课程名称</th>
                    <th>授课教师</th>
                    <th>选课人数</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="tbody1">

                </tbody>
            </table>
            <!--分页栏-->
            <div style="width: 100%;text-align: center;">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">首页</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">上一页</span>
                            </a>
                        </li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">下一页</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">尾页</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>


        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    $(function () {
        //定义个用来给表格中装载数据的函数
        var fillToTable = function (courseInfos) {
            var tbody1 = $("#tbody1");
            tbody1.empty();
            $.each(courseInfos, function (index, courseInfo) {
                var tr = $("<tr>");
                var td = $("<td>");
                td.text(courseInfo.courseId);
                tr.append(td);

                td = $("<td>");
                td.text(courseInfo.coursename);
                tr.append(td);

                td = $("<td>");
                td.text(courseInfo.teacher);
                tr.append(td);


                td = $("<td>");
                td.text(courseInfo.number);
                tr.append(td);

                td = $("<td>");
                td.html("<button type=\"button\" class=\"btn btn-default btn-sm\">" +
                    "<span class=\"glyphicon glyphicon-edit\"></span> 编辑" +
                    "</button> <button type=\"button\" class=\"btn btn-default btn-sm\">" +
                    "          <span class=\"glyphicon glyphicon-trash\"></span> 删除" +
                    "        </button>");
                tr.append(td);

                tbody1.append(tr);

            });
        };


        //发送请求获取已添加的课程
        $.get("${pageContext.request.contextPath}/course/ajax/findCourses", {}, function (resp) {
            if (resp.code < 0) {
                console.debug("------------"+resp.msg);
                alert(resp.msg);
            } else {
                console.debug("-------------"+resp.data);
                fillToTable(resp.data);
            }
        }, "json");
    });
</script>
</body>
</html>
