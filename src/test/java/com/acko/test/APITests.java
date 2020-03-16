package com.acko.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.acko.base.ResponseComparator;

public class APITests  {

	@Test
	public void compareAPIResponses() throws IOException, ParserConfigurationException, SAXException {
		BufferedReader br1 = new BufferedReader(new FileReader("File1.txt"));//Assuming the first file as File1.txt which is available in class path
		BufferedReader br2 = new BufferedReader(new FileReader("File2.txt"));//Assuming the second file as File2.txt which is available in class path
		String file1_line = br1.readLine();
		file1_line=file1_line.trim();
		String file2_line = br2.readLine();
		file2_line=file2_line.trim();
		while (file1_line != null && file2_line != null) { 
			new ResponseComparator().compareResponses(file1_line, file2_line);
			file1_line = br1.readLine();
			file2_line = br2.readLine();
		}
		br1.close();
		br2.close();
	}
}  
