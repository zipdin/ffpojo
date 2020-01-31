package com.zandero.ffpojo.file.reader;

import com.zandero.ffpojo.exception.RecordParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public class InputStreamFlatFileReader extends BaseFlatFileReader implements FlatFileReader {

	private static final boolean IS_RESET_SUPPORTED = false;

	private BufferedReader inputStreamReader;
	private String nextLine;

	public InputStreamFlatFileReader(InputStream inputStream, FlatFileReaderDefinition flatFile) throws IOException {
		if (inputStream == null) {
			throw new IllegalArgumentException("InputStream object is null");
		}


		InputStreamReader reader;
		if (flatFile.getCharset() == null) {
			reader = new InputStreamReader(inputStream);
		} else {
			reader = new InputStreamReader(inputStream, flatFile.getCharset());
		}

		this.inputStreamReader = new BufferedReader(reader);
		this.flatFileDefinition = flatFile;
		this.nextLine = inputStreamReader.readLine();
	}

	public boolean isResetSupported() {
		return IS_RESET_SUPPORTED;
	}

	@Deprecated
	public void reset() throws IOException {
		throw new UnsupportedOperationException("Reset method not supported by " + getClass());
	}

	public void close() throws IOException {
		if (inputStreamReader != null) {
			this.inputStreamReader.close();
		}
		this.closed = true;
		System.gc();
	}

	public boolean hasNext() {
		return nextLine != null;
	}

	public Object next() {

		while (this.hasNext()) {

			String currLine = nextLine;
			try {
				nextLine = inputStreamReader.readLine();
			} catch (IOException e) {
				throw new RuntimeException("Error while decoding the line number " + (recordIndex + 1), e);
			} catch (RecordParserException e) {
				throw new RuntimeException("Error while parsing from text the line number " + (recordIndex + 1), e);
			}

			if (accept(currLine)) {
				try {
					Object record = parseRecordFromText(currLine);
					this.recordText = nextLine;
					return record;
				} catch (RecordParserException e) {
					e.setLineNumber(recordIndex + 1);
					e.setLine(currLine);
					throw e;
				} finally {
					recordIndex++;
				}
			}
		}

		throw new NoSuchElementException("There are no more records to read");
	}

	/**
	 * Override to perform line filtering if desired
	 *
	 * @param line to be filtered
	 * @return true OK, false to skip
	 */
	public boolean accept(String line) {
		return true;
	}
}
