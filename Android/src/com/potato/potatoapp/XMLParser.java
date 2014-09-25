package com.potato.potatoapp;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.joda.time.DateTime;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLParser {

	public XMLParser () {

	}

	public static ArrayList<ArrayList<Object>> parseXML(String feed) throws Exception {

		Document xml = loadXMLFromString(feed);
		NodeList nodes = xml.getElementsByTagName("problems");

		ArrayList<Problem> problems = extractProblems(nodes);
		ArrayList<Object> symptoms = extractSymptoms(nodes);






		return null;
	}

	private static ArrayList<Problem> extractProblems(NodeList nodes) {
		
		ArrayList<Problem> problems = new ArrayList();
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);
			Problem problem = new Problem();

			NodeList id = element.getElementsByTagName("id");
			Element line = (Element) id.item(0);
			problem.setId(Integer.parseInt(getCharacterDataFromElement(line)));
			System.out.println("id: " + getCharacterDataFromElement(line));

			NodeList name = element.getElementsByTagName("name");
			line = (Element) name.item(0);
			problem.setName(getCharacterDataFromElement(line));
			System.out.println("name: " + getCharacterDataFromElement(line));

			NodeList type = element.getElementsByTagName("type");
			line = (Element) type.item(0);
			problem.setType(getCharacterDataFromElement(line));
			System.out.println("type: " + getCharacterDataFromElement(line));

			NodeList updatetime = element.getElementsByTagName("updatetime");
			line = (Element) updatetime.item(0);
			problem.setUpdateTime(new DateTime(getCharacterDataFromElement(line)));
			System.out.println("updatetime: " + getCharacterDataFromElement(line));
			
			NodeList description = element.getElementsByTagName("description");
			line = (Element) description.item(0);
			problem.setDescription(getCharacterDataFromElement(line));
			System.out.println("description: " + getCharacterDataFromElement(line));
			
			NodeList symptoms = element.getElementsByTagName("symptom");
			for (int j = 0; j < symptoms.getLength(); j++) {
				Element symptom = (Element)symptoms.item(j);
				problem.addSymptom(Integer.parseInt(getCharacterDataFromElement(symptom)));
			}
			problems.add(problem);
		}
		
		return problems;
	}
	
	private static ArrayList<Object> extractSymptoms(NodeList nodes) {
		ArrayList<Symptom> symptoms = new ArrayList();
		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);
			Symptom symptom = new Symptom();

			NodeList id = element.getElementsByTagName("id");
			Element line = (Element) id.item(0);
			symptom.setId(Integer.parseInt(getCharacterDataFromElement(line)));
			System.out.println("id: " + getCharacterDataFromElement(line));

			NodeList description = element.getElementsByTagName("description");
			line = (Element) description.item(0);
			symptom.setDescription(getCharacterDataFromElement(line));
			System.out.println("description: " + getCharacterDataFromElement(line));

			NodeList updatetime = element.getElementsByTagName("updatetime");
			line = (Element) updatetime.item(0);
			symptom.setUpdateTime(new DateTime(getCharacterDataFromElement(line)));
			System.out.println("updatetime: " + getCharacterDataFromElement(line));
			
			NodeList parentSymptom = element.getElementsByTagName("parent");
			line = (Element) parentSymptom.item(0);
			symptom.setParent(Integer.parseInt(getCharacterDataFromElement(line)));
			System.out.println("parent: " + getCharacterDataFromElement(line));
			
			symptoms.add(symptom);
		}
		
		return null;
	}

	private static Document loadXMLFromString(String xml) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}


	/*
	 * code referenced from http://www.rgagnon.com/javadetails/java-0573.html
	 * viewed on 25/09/14
	 * 
	 */
	private static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "?";
	}

}
