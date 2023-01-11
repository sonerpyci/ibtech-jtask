package com.payci.soner.models.IO;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "EXT")
public class CommandDefinition {

	private String commandName;
	
	
	@JacksonXmlProperty(localName = "Input")
	private CommandInput input;
	
	
	@JacksonXmlProperty(localName = "Output")
	private CommandOutput output;


	public String getCommandName() {
		return commandName;
	}


	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}


	public CommandInput getInput() {
		return input;
	}


	public void setInput(CommandInput input) {
		this.input = input;
	}


	public CommandOutput getOutput() {
		return output;
	}


	public void setOutput(CommandOutput output) {
		this.output = output;
	}
}
