<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html>
<html>
<head>
<title>CAR SHOWROOM</title>
<link type="text/css"  rel="stylesheet"  href="style.css"/>
</head>
<body bgcolor="lightblue">

<h2 align="center">DREAM WHEELS</h2>
  <hr color="black"/><hr color="black"/>
  <br/>
  



<h2 align="center"><u>Add Vehicle</u></h2>
<form method="post"  action="insertvehicle" enctype="multipart/form-data">
<table align="center">
<tr><td></td></tr>
<tr>
    <td><b>Brand</b></td>
    <td>
        <select name="vbrand" required>
	        <option value="">---Select---</option>
	        <option value="TataMotors">Tata Motors</option>
	        <option value="Mahindra">Mahindra</option>
	        <option value="MaruthiSuzuki">Mahindra</option>
	         <option value="Hyundai">Hyundai</option>
        </select>
    </td>
</tr>
<tr><td></td></tr>
<tr>
    <td><b>Engine Type</b></td>
    <td>
        <select name="vengine" required>
	        <option value="">---Select---</option>
	        <option value="TurboCharged">Turbo Charged</option>
	        <option value="Hydric">Hydric</option>
	        <option value="Electric">Electric</option>
        </select>
    </td>
</tr>
<tr><td></td></tr>
<tr>
    <td><b>Fuel Type</b></td>
    <td>
        <select name="vfuel" required>
	        <option value="">---Select---</option>
	        <option value="Petrol">Petrol</option>
	        <option value="Diesel">Diesel</option>
        </select>
    </td>
</tr>
<tr><td></td></tr>
<tr>
    <td><b>Seating Capacity</b></td>
    <td>
        <input type="number" name="vcapacity" required>
    </td>
</tr>
<tr><td></td></tr>																																																																																																																																																																																																																																																																																										
<tr>
    <td><b>Image</b></td>
    <td>
         <input type="file" name="vimage" required>
    </td>
</tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr align="center">
    <td>
        <input type="submit" value="Add" required>
    </td>
    <td>
        <input type="reset" value="Clear" required>
    </td>
</tr>
</table>

</form>

</body>
</html>