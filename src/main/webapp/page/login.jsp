<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/3/7
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="jquery.min.js" charset="UTF-8"></script>
</head>
<body>
    <div class="login">
        <label>用户名：</label><input type="text" name="userName" class="userName"><br/>
        <label>密码：</label><input type="password" name="passWord" class="passWord"><br/>
        <input type="button" class="submit" value="登录"><br/>

        <span></span>
    </div>

    <div class="regist" style="background-color: #7fffd4;margin-top: 50px;">
        <label>用户名：</label><input type="text" name="userName" class="userName"><br/>
        <label>密码：</label><input type="password" name="passWord" class="passWord"><br/>
        <input type="button" class="submit" value="注册"><br/>

        <span></span>
    </div>

<script>
    $(function(){
        $(".login .submit").click(function(){
            var userName = $(".login .userName").val();
            var passWord = $(".login .passWord").val();
            var code = 1234;

            $.ajax({
                url:"/task/user/login/"+userName+"/"+passWord+"/"+code,
                type:"POST",
                dataType:"json",
                success:function(data){
                    if (data.errorCode === "0000"){
                        if (!window.localStorage) {
                            return false;
                        } else {
                            localStorage.setItem("user_token",data.data);
                            $(".login span").html(data.data)
                        }
                    }
                }
            })
        });

        $(".regist .submit").click(function(){
            var userName = $(".regist .userName").val();
            var passWord = $(".regist .passWord").val();

            var head = localStorage.getItem("user_token");
            $.ajax({
                url:"/task/user/regist",
                type:"POST",
                data:"userName="+userName+"&passWord="+passWord,
                dataType:"json",
                beforeSend:function(request){
                    request.setRequestHeader("Authorization", head);
                },
                success:function(data){
                    if (data.errorCode === "0000"){
                        $(".regist span").html(data.data)
                    }
                }
            })
        });
    })
</script>
</body>
</html>
