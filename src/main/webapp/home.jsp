<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stock value</title>
</head>
<body>
<h1 align="center">Stock Exchange value calculation </h1>
<br/>
<h3 style="margin-left:20%"> Current price value of  ${companyStockValue.companyName} is ${companyStockValue.price} </h3>
<form style="margin-left:20%" action="/yahoo-api/" method="post" autocomplete="off">
 <table>
   <tr>
   <td>
   <label> Enter number of units you have:  </label></td>
   <td><input type="number" name="units" min="1" value=${units} /></td>
   <td><input type="submit" value="submit" /> </td>
   </tr>
 </table>
</form>
<h3 style="margin-left:20%">Total value of your units is : ${totalValue} </h3>
</body>
</html>