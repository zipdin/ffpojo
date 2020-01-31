package com.zandero.ffpojo.samples.multithread;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.zandero.ffpojo.exception.FFPojoException;
import com.zandero.ffpojo.file.processor.FlatFileProcessor;
import com.zandero.ffpojo.file.processor.ThreadPoolFlatFileProcessor;
import com.zandero.ffpojo.file.reader.FileSystemFlatFileReader;
import com.zandero.ffpojo.file.reader.FlatFileReader;
import com.zandero.ffpojo.file.reader.FlatFileReaderDefinition;
import com.zandero.ffpojo.processor.CustomerRecordProcessor;
import com.zandero.ffpojo.samples.pojo.Customer;

public class SimpleFileSystemFlatFileReaderWithThreadPoolFlatFileProcessorExample {

	//copy the file "SimpleFileSystemFlatFileReaderWithThreadPoolFlatFileProcessorExample.txt" (make sure you have permission to read in the specified path):
	private static final String INPUT_TXT_OS_PATH = "SimpleFileSystemFlatFileReaderWithThreadPoolFlatFileProcessorExample.txt";


	public static void main(String[] args) {
		SimpleFileSystemFlatFileReaderWithThreadPoolFlatFileProcessorExample example = new SimpleFileSystemFlatFileReaderWithThreadPoolFlatFileProcessorExample();
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
		File inputFile = new File(classLoader.getResource(INPUT_TXT_OS_PATH).getFile());
		if (!inputFile.exists()) {
			throw new IllegalStateException("File not found: " + INPUT_TXT_OS_PATH);
		}


		FlatFileReaderDefinition ffDefinition = new FlatFileReaderDefinition(Customer.class);
		FlatFileReader ffReader = new FileSystemFlatFileReader(inputFile, ffDefinition);
		FlatFileProcessor ffProcessor = new ThreadPoolFlatFileProcessor(ffReader, 5, 2, TimeUnit.HOURS);
		ffProcessor.processFlatFile(new CustomerRecordProcessor());
		ffReader.close();
	}

}
