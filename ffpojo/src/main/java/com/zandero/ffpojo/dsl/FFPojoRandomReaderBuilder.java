package com.zandero.ffpojo.dsl;

import java.util.List;

public interface FFPojoRandomReaderBuilder<T> {

	public List<T> randomRead ();
	
	public void randomRead(ReadProcessor processor);
	
}
