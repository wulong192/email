<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传excel</title>

<style type="text/css">
img {
	width: 250px;
}

</style>

</head>
<body>
	
	
	<form action="down" method="post">
		
		开始位置：<input type="number" name="start" min="1"><br/><br/>
		
		结束位置：<input type="number" name="end" min="1"><br/><br/>
	
		<input type="submit" value="导出本页数据">
		
	</form>
	

</body>
</html>



