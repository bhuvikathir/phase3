<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<style>
h2 {
  text-align: center;
  font-family:verdana;
  font-size:300%;
  color:#006400;
}
body {
  background-color: #fefbd8;
}

</style>

<title>Insert title here</title>
</head>
<body>
<h2><i><b>Insert A New Dish</b></i></h2>

<form action="insertfood"  method="post" enctype="multipart/form-data">
<table class="table table-hover">
<tr><td>Food_ID:</td><td><input type="number" name="fid"></td></tr>
<tr><td>Dish_Name:</td><td><input type="text" name="fname"></td></tr>
<tr><td>Dish_Category:</td><td><input type="text" name="fcat"></td></tr>
<tr><td>Dish_Price:</td><td><input type="number" name="fprice"></td></tr>
<tr><td>Dish_Picture:</td><td><input type="file" name="file"></td></tr>
<tr><td><div style="text-align:center"><button type="submit" class="btn btn-primary">Insert This Food-Detail</button></div></td></tr>
</table><br>
</form>
<h2><b><i>Display/Edit Dish Details</i></b></h2>
<form action="showfoodadmin">
<div style="text-align:center">
<button type="submit" class="btn btn-primary">Show Dishes</button>
</div>
</form> 
<br><br>
<h2><b><i>Show All Users</i></b></h2>
<form action="displayalluser">
<div style="text-align:center">
<button type="submit" class="btn btn-primary">Show Users</button> 
</div>
</form>
<br><br>
<h2><b><i>Purchase Report</i></b></h2>
<form action="purchaserepo">
<div style="text-align:center">
<button type="submit" class="btn btn-primary">Purchase History</button> 
</div>
</form>
<br><br>

</body>
</html>