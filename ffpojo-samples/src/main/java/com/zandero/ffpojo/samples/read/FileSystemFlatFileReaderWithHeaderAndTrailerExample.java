package com.zandero.ffpojo.samples.read;

import java.io.File;
import java.io.IOException;

import com.zandero.ffpojo.exception.FFPojoException;
import com.zandero.ffpojo.file.reader.FileSystemFlatFileReader;
import com.zandero.ffpojo.file.reader.FlatFileReader;
import com.zandero.ffpojo.file.reader.FlatFileReaderDefinition;
import com.zandero.ffpojo.file.reader.RecordType;
import com.zandero.ffpojo.samples.pojo.Customer;
import com.zandero.ffpojo.samples.pojo.Header;
import com.zandero.ffpojo.samples.pojo.Trailer;

/**
* Example how to read text file with Header, Body and Trailer.   
* 
* @author Gilberto Holms - gibaholms@hotmail.com 
* @author William Miranda -  blackstile@hotmail.com
*
*/
public class FileSystemFlatFileReaderWithHeaderAndTrailerExample {

	//copy the file "FileSystemFlatFileReaderWithHeaderAndTrailerExample.txt" (make sure you have permission to read in the specified path):
	private static final String FILE_NAME =  "FileSystemFlatFileReaderWithHeaderAndTrailerExample.txt";
	
	public static void main(String[] args) {
		FileSystemFlatFileReaderWithHeaderAndTrailerExample example = new FileSystemFlatFileReaderWithHeaderAndTrailerExample();
		try {
			System.out.println("Making POJO from file system TXT FILE...");
			example.readCustomers();
			System.out.println("END !");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FFPojoException e) {
			e.printStackTrace();
		}
	}
	
	public void readCustomers() throws IOException, FFPojoException {
		ClassLoader classLoader = this.getClass().getClassLoader();
    	File inputFile = new File(classLoader.getResource(FILE_NAME).getFile());
    	
		if (!inputFile.exists()) {
			throw new IllegalStateException("File not found: " + inputFile.getAbsolutePath());
		}
		FlatFileReaderDefinition ffDefinition = new FlatFileReaderDefinition(Customer.class);
		ffDefinition.setHeader(Header.class);
		ffDefinition.setTrailer(Trailer.class);
		FlatFileReader ffReader = new FileSystemFlatFileReader(inputFile, ffDefinition);
		for (Object record : ffReader) {
			RecordType recordType = ffReader.getRecordType();
			if (recordType == RecordType.HEADER) {
				System.out.print("HEADER FOUND: ");
				Header header = (Header)record;
				System.out.printf("[%d][%s]\n", header.getControlNumber(), header.getProcessDate());
			} else if (recordType == RecordType.BODY) {
				Customer cust = (Customer)record;
				System.out.printf("[%d][%s][%s]\n", cust.getId(), cust.getName(), cust.getEmail());
			} else if (recordType == RecordType.TRAILER) {
				System.out.print("TRAILER FOUND: ");
				Trailer trailer = (Trailer)record;
				System.out.printf("[%d]\n", trailer.getRecordsCount());
			}
		}
		ffReader.close();
	}
	
}
