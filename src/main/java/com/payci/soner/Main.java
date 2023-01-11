package com.payci.soner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.payci.soner.entities.Account;
import com.payci.soner.entities.Address;
import com.payci.soner.entities.Customer;
import com.payci.soner.entities.Phone;
import com.payci.soner.entities.reflection.CommandTbl;
import com.payci.soner.entities.reflection.MethodTbl;
import com.payci.soner.helpers.ReflectionHelper;
import com.payci.soner.hibernate.ClassTblRepository;
import com.payci.soner.hibernate.CustomerRepository;
import com.payci.soner.models.IO.CommandDefinition;
import com.payci.soner.models.IO.CommandInput;
import com.payci.soner.models.IO.CommandOutput;
import com.payci.soner.models.IO.base.SingleInput;

public class Main {
	static boolean run = true;
	static CommandExecuter commandExecuter = new CommandExecuter();
	
	public static void main(String[] args) {
		//initializeEntities();
//		initializeCommands("com.payci.soner.operations", "CustomerOperations");
//		initializeCommands("com.payci.soner.operations", "AddressOperations");
//		initializeCommands("com.payci.soner.operations", "AccountOperations");
//		initializeCommands("com.payci.soner.operations", "PhoneOperations");
		
		try /*(Scanner scanner = new Scanner(System.in))*/ {
//			
//			System.out.print("Enter CommandName: ");  
//			String commandName = scanner.nextLine();
//			commandExecuter.Execute(commandName);
			
			JacksonXmlModule xmlModule = new JacksonXmlModule();
			xmlModule.setDefaultUseWrapper(false);
			ObjectMapper objectMapper = new XmlMapper(xmlModule);
			
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			
			//String cmdStr = "<EXT><Input><p n=\"AD\">Soner</p><p n=\"SOYAD\">PAYCI</p><p n=\"YAS\">27</p></Input><Output><p n=\"DOGUM_TARIHI\"/></Output></EXT>";
			
			//CommandDefinition cmd = objectMapper.readValue(cmdStr, CommandDefinition.class);
			
			//System.out.println(cmd.getCommandName());
			
			
			
			
			

			
			
			
			
			CommandDefinition cmd = getTestCommandDefinition();
			String xml = objectMapper.writeValueAsString(cmd);
			
			
			System.out.println(xml);
			
			
			
			
			
		} catch (Exception e) {
			
			System.out.println("\nException occured. Try Again.");
			e.printStackTrace();
		}
	}
	
	private static CommandDefinition getTestCommandDefinition() {
		CommandDefinition cmd = new CommandDefinition();
		
		CommandInput input = new CommandInput();
		CommandOutput output = new CommandOutput();
		
		List<SingleInput> inputPairs = new ArrayList<SingleInput>();
		List<SingleInput> outputPairs = new ArrayList<SingleInput>();
		
		inputPairs.add(new SingleInput("AD", "Soner"));
		inputPairs.add(new SingleInput("SOYAD", "PAYCI"));
		inputPairs.add(new SingleInput("YAS", 27));
		
		
		outputPairs.add(new SingleInput("DOGUM_YILI", 1995));
		
		input.setKeyValuePairs(inputPairs);
		
		output.setKeyValuePairs(outputPairs);
		
		cmd.setCommandName("test_command");
		cmd.setInput(input);
		cmd.setOutput(output);
		
		return cmd;
	}
	
	private static void initializeCommands(String packagePath, String className) {
		StringBuilder sb = new StringBuilder();
		String fullClassPath = sb.append(packagePath)
				.append('.')
				.append(className)
				.toString();
		sb.setLength(0);
		
		ClassTblRepository classTblRepository = new ClassTblRepository();
		
		if (classTblRepository.getByName(className) != null)
			return;
		
		CommandTbl repositoryTbl = new CommandTbl(className, packagePath);
		Class<?> repositoryClass = ReflectionHelper.ExtractClassType(fullClassPath);
		
		List<Method> methods = ReflectionHelper.getAllMethods(repositoryClass);
		
		for(Method method : methods) {
//			ArrayList<Class<?>> parameterTypesOfMethod = ReflectionHelper.getParameterTypes(method);
			String commandName = sb.append(className)
					.append('_')
					.append(method.getName())
					.toString();
			sb.setLength(0);
//			MethodTbl methodTbl = new MethodTbl(method.getName(), commandName, parameterTypesOfMethod);
			MethodTbl methodTbl = new MethodTbl(method.getName(), commandName);
			repositoryTbl.addMethod(methodTbl);
		}
		classTblRepository.save(repositoryTbl);
	}
	
	
	
	private static void testReflection() {
		for(Method declaredMethod : ReflectionHelper.getAllMethods(CustomerRepository.class)) {
			System.out.println(declaredMethod);
		}

		Method singleMethod = ReflectionHelper.getMethod(CustomerRepository.class, "getAll");
		
		System.out.println("single method : " + singleMethod);
	}
	
	private static void initializeEntities() {
		CustomerRepository customerRepository = new CustomerRepository();

		Customer customer = new Customer("Soner", "Paycı");
		customerRepository.saveOrUpdate(customer);

		Address address1 = new Address("Manisa", "Salihli", "45300", "bulunamayan adres.");
		Address address2 = new Address("Gebze", "Merkez", "????", "ibtech adres.");
		customer.addAddres(address1);
		customer.addAddres(address2);
		
		Account account = new Account(0.0);
		customer.addAccount(account);
		
		Phone phone = new Phone("+90", "555 222 11 00");
		customer.addPhone(phone);

		customerRepository.saveOrUpdate(customer);
		
		Customer persistentCustomer = customerRepository.get(customer.getId());
		
		System.out.println(persistentCustomer.toString());
		

		List<Address> persistentAddresses = persistentCustomer.getAddresses();
		for (Address addrs : persistentAddresses) {
			System.out.println(addrs.toString());
		}
	}
}
