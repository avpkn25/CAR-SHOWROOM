<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*,java.util.List" %>
<%@ page import="com.klef.ep.services.AdminService" %>
<%@ page import="javax.naming.InitialContext, javax.naming.NamingException" %>
<%@ page import="com.klef.ep.models.Vehicle" %>
<!DOCTYPE html>
<html>
<head>
<title>EP PROJECT</title>
<style>
    .vehicle-card {
        border: 1px solid #000;
        padding: 10px;
        margin: 10px;
        text-align: center;
        width: 250px;
        display: inline-block;
        vertical-align: top;
    }
    .vehicle-card img {
        width: 100%;
        height: auto;
    }
    .vehicle-details {
        text-align: left;
        margin-top: 10px;
    }
    .vehicle-details table {
        width: 100%;
    }
    .vehicle-details th, .vehicle-details td {
        text-align: left;
        padding: 5px;
    }
</style>
</head>

<body>

<h2 align="center">CAR SHOWROOM MANAGEMENT</h2>
<hr color="black"/><hr color="black"/>
<br/>

<h2 align="center"><u>View All Vehicles</u></h2>
<div align="center">
<%
try {
    InitialContext context = new InitialContext();
    AdminService adminService = (AdminService) context.lookup("java:global/CARSHOWROOM/AdminServiceImpl!com.klef.ep.services.AdminService");
    
    List<Vehicle> Vehiclelist = adminService.ViewAllVehicles();

    for (Vehicle vehicle : Vehiclelist) {
%>
    <div class="vehicle-card">
        <img src="viewvehiclebyid.jsp?vid=<%= vehicle.getId()%>" alt="Vehicle Image" />
        <div class="vehicle-details">
            <table>
                <tr>
                    <th>Brand:</th>
                    <td><%= vehicle.getBrand() %></td>
                </tr>
                <tr>
                    <th>Engine Type:</th>
                    <td><%= vehicle.getEnginetype() %></td>
                </tr>
                <tr>
                    <th>Fuel Type:</th>
                    <td><%= vehicle.getFueltype() %></td>
                </tr>
                <tr>
                    <th>Seating Capacity:</th>
                    <td><%= vehicle.getSeatingcapacity() %></td>
                </tr>
            </table>
        </div>
    </div>
<%
    }
} catch (NamingException e) {
    out.println("NamingException: " + e.getMessage());
} catch (Exception e) {
    out.println("Exception: " + e.getMessage());
}
%>
</div>

</body>
</html>
