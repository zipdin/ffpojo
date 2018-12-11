package com.zandero.ffpojo.decorator;

import com.zandero.ffpojo.metadata.DefaultFieldDecorator;

public class LongDecorator extends DefaultFieldDecorator {
	@Override
	public Object fromString(String str) {
		return Long.valueOf(str);
	}
}
