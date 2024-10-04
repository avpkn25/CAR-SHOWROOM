package com.klef.ep.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.klef.ep.models.Vehicle;
import com.klef.ep.services.AdminService;


@WebServlet("/insertvehicle")
@MultipartConfig
public class InsertVehicle extends HttpServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;





	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try
		{
			String vbrand = request.getParameter("vbrand");
			System.out.println(vbrand);
			String vengine = request.getParameter("vengine");
			String vfuel = request.getParameter("vfuel");
			String vcapacity = request.getParameter("vcapacity");
			int capacity = Integer.parseInt(vcapacity);
			Part file = request.getPart("vimage");
			
			System.out.println(file);
			
			InitialContext context = new InitialContext();
			AdminService adminService = (AdminService) context.lookup("java:global/CARSHOWROOM/AdminServiceImpl!com.klef.ep.services.AdminService");
			
			
	        InputStream inputStream = file.getInputStream();
	        Blob blob = new javax.sql.rowset.serial.SerialBlob(getBytesFromInputStream(inputStream));


		Vehicle vehicle= new Vehicle();
		vehicle.setBrand(vbrand);
		vehicle.setEnginetype(vengine);
		vehicle.setFueltype(vfuel);
		vehicle.setSeatingcapacity(capacity);
		vehicle.setImagedata(blob);
		

		adminService.AddVehicle(vehicle);

		response.sendRedirect("viewallvehicles.jsp");
		}
		catch(Exception e)
		{
			out.print(e);
		}
	}
	
	 // Method to convert InputStream to byte array
    private byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

}
