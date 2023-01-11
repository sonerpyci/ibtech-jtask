package com.payci.soner.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.payci.soner.CommandExecuter;
import com.payci.soner.helpers.data.carrier.Bag;
import com.payci.soner.models.IO.CommandDefinition;
import com.payci.soner.models.IO.CommandOutput;
import com.payci.soner.models.IO.base.InputBase;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CommandExecuter commandExecuter = new CommandExecuter();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// Set response content type
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String title = "Using GET Method to Read Form Data";
		out.println("Hello.");
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			JacksonXmlModule xmlModule = new JacksonXmlModule();
			xmlModule.setDefaultUseWrapper(false);
			ObjectMapper objectMapper = new XmlMapper(xmlModule);
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			
			String xmlInput = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			
			CommandDefinition cmd = objectMapper.readValue(xmlInput, CommandDefinition.class);
			Bag resultBag = commandExecuter.Execute(cmd.getCommandName(), cmd.getInput().toBag());
			
			
			
			CommandOutput output = new CommandOutput();
			output.setKeyValuePairs(resultBag.toIOList());
			cmd.setOutput(output);
			
				
			String xmlOutput = objectMapper.writeValueAsString(cmd);
			PrintWriter writer = response.getWriter();
			writer.println(xmlOutput);
			writer.close();
			response.setContentType("application/xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
