package com.acko.base;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONObject;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import io.restassured.response.Response;

public class ResponseComparator extends BaseAPI {

	public void compareResponses(String url1, String url2) throws ParserConfigurationException, SAXException, IOException {
		Response response1 = getAPIcall(url1);
		Response response2 = getAPIcall(url2);
		SoftAssert validate = new SoftAssert();
		if (response1.getContentType().equalsIgnoreCase("application/json; charset=utf-8")
				&& response2.getContentType().equalsIgnoreCase("application/json; charset=utf-8")) {
			JSONObject obj1=new JSONObject(response1.getBody().asString());
			JSONObject obj2=new JSONObject(response2.getBody().asString());
			if(obj1.keySet() !=obj2.keySet()){
				validate.assertEquals(obj1.keySet(), obj2.keySet());
			}
			else {
			if(obj1.has("page") && obj2.has("page")) {
				validate.assertEquals(addToListSort(response1.jsonPath().get("page").toString()), addToListSort(response2.jsonPath().get("page").toString()));
			}
			if(obj1.has("per_page") && obj2.has("per_page")) {
			validate.assertEquals(addToListSort(response1.jsonPath().get("per_page").toString()), addToListSort(response2.jsonPath().get("per_page").toString()));
			}
			if(obj1.has("total") && obj2.has("total")) {
			validate.assertEquals(addToListSort(response1.jsonPath().get("total").toString()), addToListSort(response2.jsonPath().get("total").toString()));
			}
			if(obj1.has("total_pages") && obj2.has("total_pages")) {
			validate.assertEquals(addToListSort(response1.jsonPath().get("total_pages").toString()), addToListSort(response2.jsonPath().get("total_pages").toString()));
			}
			if(obj1.has("data") && obj2.has("data")) {
			validate.assertEquals(addToListSort(response1.jsonPath().get("data").toString()), addToListSort(response2.jsonPath().get("data").toString()));
			}
		}	try {
			validate.assertAll();
			System.out.println(url1 + " is equals to " + url2);
		} catch (AssertionError error) {
			System.out.println(url1 + " is not equals to " + url2);

		}

			}
		else if(response1.getContentType().equalsIgnoreCase("application/xml; charset=utf-8")
				&& response2.getContentType().equalsIgnoreCase("application/xml; charset=utf-8")){
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc1 = dBuilder.parse(response1.getBody().asString());
			Document doc2 = dBuilder.parse(response1.getBody().asString());

			NodeList nList1 = doc1.getElementsByTagName("page");
			NodeList nList2 = doc2.getElementsByTagName("page");

			if((nList1.getLength()>0 && nList2.getLength()>0) && (nList1.getLength()==nList2.getLength())) {
				validate.assertEquals(addToListSort(response1.xmlPath().get("page").toString()), addToListSort(response2.xmlPath().get("page").toString()));
			}
			
			 nList1 = doc1.getElementsByTagName("per_page");
			 nList2 = doc2.getElementsByTagName("per_page");

			if((nList1.getLength()>0 && nList2.getLength()>0) && (nList1.getLength()==nList2.getLength())) {
				validate.assertEquals(addToListSort(response1.xmlPath().get("per_page").toString()), addToListSort(response2.xmlPath().get("per_page").toString()));
			}
			
			 nList1 = doc1.getElementsByTagName("total");
			 nList2 = doc2.getElementsByTagName("total");

			if((nList1.getLength()>0 && nList2.getLength()>0) && (nList1.getLength()==nList2.getLength())) {
				validate.assertEquals(addToListSort(response1.xmlPath().get("total").toString()), addToListSort(response2.xmlPath().get("total").toString()));
			}
			
			 nList1 = doc1.getElementsByTagName("total_pages");
			 nList2 = doc2.getElementsByTagName("total_pages");

			if((nList1.getLength()>0 && nList2.getLength()>0) && (nList1.getLength()==nList2.getLength())) {
				validate.assertEquals(addToListSort(response1.xmlPath().get("total_pages").toString()), addToListSort(response2.xmlPath().get("total_pages").toString()));
			}
			
			 nList1 = doc1.getElementsByTagName("data");
			 nList2 = doc2.getElementsByTagName("data");

			if((nList1.getLength()>0 && nList2.getLength()>0) && (nList1.getLength()==nList2.getLength())) {
				validate.assertEquals(addToListSort(response1.xmlPath().get("data").toString()), addToListSort(response2.xmlPath().get("data").toString()));
			}
			try {
				validate.assertAll();
				System.out.println(url1 + " is equals to " + url2);
			} catch (AssertionError error) {
				System.out.println(url1 + " is not equals to " + url2);

			}
			
		}
			
	}

	private List<String> addToListSort(String str) {
		List<String> list = Arrays.asList(str);
		Collections.sort(list);
		System.out.println(list);
		return list;

	}

}
