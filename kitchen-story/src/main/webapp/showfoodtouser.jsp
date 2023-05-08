<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*" %>
	<%@page import="com.example.demo.model.*" %>
    <%@page import="com.example.demo.service.*"%>
    <%@page import="java.nio.file.Files" %>
    <%@page import="java.io.File" %>
    <%@page import="java.util.Base64.*" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<style>
h1 {
  text-align: center;
  font-family:verdana;
  font-size:300%;
  color:#006400;
}
h2 {
  text-align: center;
  font-family:verdana;
  font-size:300%;
  color:DarkRed;
}
body {
  background-color: #fefbd8;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2><b><i><u>"Search by Name Or Category"</u></i></b></h2><br>

<form action="searchfood">
Food Name Or Category:<input type="text" name="keyword">
<input type="hidden" name="cname" value=<%=request.getAttribute("cname") %>>

<button type="submit" class="btn btn-primary">Search</button>
</form>
<h1><b><i>"Showing Food List"</i></b></h1><br><br>
<%List<FoodItem> e=(List<FoodItem>)request.getAttribute("list"); %>
<table  class="table table-hover">
<tr><th>Food_ID</th><th>Dish_Name</th><th>Dish_Category</th><th>Dish_Price</th><th>Picture</th><th>Buy</th></tr>

<%for(FoodItem es:e){%>
<tr><td><%=es.getFid()%></td><td><%=es.getFood_name()%></td><td><%=es.getFood_category()%></td><td><%=es.getFood_price()%></td>
<td>
<%String path=es.getFile_path();
byte[] images=Files.readAllBytes(new File(path).toPath());
byte[] encodeBase64 = Base64.getEncoder().encode(images);
String base64Encoded = new String(encodeBase64, "UTF-8");
%>
<img alt="img" src="data:image/png;base64,<%=base64Encoded%>" width="100" height="100"/>
</td>
<td>
<form action="buy1.jsp">
<input type="hidden" name="cname" value=<%=request.getAttribute("cname")%>>
<input type="hidden" name="fname" value=<%=es.getFood_name() %>>
<input type="hidden" name="fprice" value=<%=es.getFood_price() %>>

<div style="text-align:center">
<button type="submit" class="btn btn-primary">Buy Now</button>
</div>
</form>
</td></tr>
<%}%>
</table>
</body>
</html>