package com.zandero.ffpojo.decorator;

import java.util.Set;

public class InternalSetDecorator extends CollectionDecorator{

	public InternalSetDecorator(Class<?> itensCollectionType) {
		super(itensCollectionType, Set.class);
	}

}
