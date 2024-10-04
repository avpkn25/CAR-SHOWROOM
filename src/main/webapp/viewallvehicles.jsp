
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*,java.util.List" %>
<%@ page import="com.klef.ep.services.AdminService" %>
<%@ page import="javax.naming.InitialContext, javax.naming.NamingException" %>
<%@ page import="com.klef.ep.models.Vehicle" %>
<!DOCTYPE html>
<html>
<head>
<title>EP PROJECT</title>
<link type="text/css" rel="stylesheet" href="style.css"/>
</head>

<body>

<h2 align="center">CAR SHOWROOM MANAGEMENT</h2>
<hr color="black"/><hr color="black"/>
<br/>

<h2 align="center"><u>View All Vehicles</u></h2>
<table align="center" border="2">
<tr>
<th>ID</th>
<th>Brand</th>
<th>EngineType</th>
<th>FuelType</th>
<th>SeatingCapacity</th>
<th>Image</th>
<th>Action</th>
</tr>
<%
try {
    InitialContext context = new InitialContext();
    AdminService adminService = (AdminService) context.lookup("java:global/CARSHOWROOM/AdminServiceImpl!com.klef.ep.services.AdminService");
    
    List<Vehicle> Vehiclelist = adminService.ViewAllVehicles();

    for (Vehicle vehicle : Vehiclelist) {
%>
    <tr>
        <td><%= vehicle.getId() %></td>
        <td><%= vehicle.getBrand() %></td>
        <td><%= vehicle.getEnginetype() %></td>
        <td><%= vehicle.getFueltype() %></td>
        <td><%= vehicle.getSeatingcapacity() %></td>
        <td><img src="viewvehiclebyid.jsp?vid=<%= vehicle.getId()%>" width="25%" height="25%" /></td>
        <td><a href="#">Edit</a></td>
    </tr>
<%
    }
} catch (NamingException e) {
    out.println("NamingException: " + e.getMessage());
} catch (Exception e) {
    out.println("Exception: " + e.getMessage());
}
%>
</table>

</body>
</html>
