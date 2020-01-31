package com.zandero.ffpojo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.zandero.ffpojo.dsl.AddClassBuilder;
import com.zandero.ffpojo.dsl.FFPojoRandomReaderBuilder;
import com.zandero.ffpojo.dsl.FFReaderBuilder;
import com.zandero.ffpojo.dsl.ReadProcessor;
import com.zandero.ffpojo.exception.FFPojoException;
import com.zandero.ffpojo.exception.RecordProcessorException;
import com.zandero.ffpojo.file.processor.FlatFileProcessor;
import com.zandero.ffpojo.file.processor.ThreadPoolFlatFileProcessor;
import com.zandero.ffpojo.file.processor.record.DefaultRecordProcessor;
import com.zandero.ffpojo.file.processor.record.event.RecordEvent;
import com.zandero.ffpojo.file.reader.FlatFileReader;
import com.zandero.ffpojo.file.reader.FlatFileReaderDefinition;
import com.zandero.ffpojo.file.reader.InputStreamFlatFileReader;

@SuppressWarnings("all")
public class FFPojoFlatFileReaderBuilder {

	private final Set<Class> classes =  new HashSet<Class>();

	public AddClassBuilder withFile(final File file){
		return new AddClassBuilder() {

			public FFReaderBuilder withRecordClasses(List<Class<?>> clazz) {
				classes.addAll(clazz);
				return read(file);
			}

			public FFReaderBuilder withRecordClass(Class<?> clazz) {
				classes.add(clazz);
				return read(file);
			}
		};
	}

	public  AddClassBuilder withInputStream(final InputStream inputStream) {
		return new AddClassBuilder() {
			public FFReaderBuilder withRecordClasses(List<Class<?>> clazz) {
				classes.addAll(clazz);
				return read(inputStream);
			}

			public FFReaderBuilder withRecordClass(Class<?> clazz) {
				classes.add(clazz);
				return read(inputStream);
			}
		};
	}

	private FFReaderBuilder read(final File file){
		try {
			return read(new FileInputStream(file));
		} catch (Exception e) {
			throw new FFPojoException(e);
		}
	}

	private FFReaderBuilder read(final InputStream inputStream ) throws FFPojoException{
		return new FFReaderBuilder() {
			public List<?> read() {
				final List itens =  new ArrayList();
				read(new ReadProcessor() {
					public void process(Object item) {
						itens.add(item);
					}
				});
				return itens;
			}

			public void read(ReadProcessor processor) {
				FlatFileReader reader = null;
				try {
					reader = new InputStreamFlatFileReader(inputStream, new FlatFileReaderDefinition(classes));
					while(reader.hasNext()){
						processor.process(reader.next());
					}
				} catch (Exception e) {
					throw new FFPojoException(e);
				}finally {
					try {
						if (reader != null){
							reader.close();
						}
					} catch (IOException e) {
						throw new FFPojoException(e);
					}
				}
			}

			public FFPojoRandomReaderBuilder withThreads(final int qtdeThreads) {
				return new FFPojoRandomReaderBuilder() {
					public List<?> randomRead() {
						final List itens =  new ArrayList();
						randomRead(new ReadProcessor() {
							public void process(Object item) {
								itens.add(item);
							}
						});
						return itens;
					}

					public void randomRead(final ReadProcessor processor) {
						FlatFileReader reader = null;
						try{
							inputStream.available();
							if (isClosed(inputStream) && inputStream.markSupported()){
								inputStream.reset();
							}
							reader = reader = new InputStreamFlatFileReader(inputStream, new FlatFileReaderDefinition(classes));
							FlatFileProcessor ffProcessor = new ThreadPoolFlatFileProcessor(reader, qtdeThreads, 2, TimeUnit.HOURS);
							synchronized(this){
								ffProcessor.processFlatFile(new DefaultRecordProcessor(){
									@Override
									public void processBody(RecordEvent event) throws RecordProcessorException {
										processor.process(event.getRecord());
									}
								});
							}
						} catch (Exception e) {
							throw new FFPojoException(e);
						}finally {
							try {
								if (reader != null){
									reader.close();
								}
							} catch (IOException e) {
								throw new FFPojoException(e);
							}
						}

					}

					private boolean isClosed(final InputStream inputStream)  {
						try {
							return inputStream.available() == 0;
						} catch (IOException e) {
							return true;
						}
					}
				};
			}
		};
	}

}