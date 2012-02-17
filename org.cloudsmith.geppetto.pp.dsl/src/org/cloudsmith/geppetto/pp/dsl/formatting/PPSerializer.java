/**
 * Copyright (c) 2011 Cloudsmith Inc. and other contributors, as listed below.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Cloudsmith
 * 
 */
package org.cloudsmith.geppetto.pp.dsl.formatting;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parsetree.reconstr.ITokenStream;
import org.eclipse.xtext.parsetree.reconstr.impl.TokenStringBuffer;
import org.eclipse.xtext.parsetree.reconstr.impl.WriterTokenStream;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.util.ReplaceRegion;

/**
 * @author henrik
 * 
 */
public class PPSerializer implements ISerializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.xtext.serializer.ISerializer#serialize(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public String serialize(EObject obj) {
		return serialize(obj, SaveOptions.defaultOptions());
	}

	protected void serialize(EObject obj, ITokenStream tokenStream, SaveOptions options) throws IOException {
		throw new UnsupportedOperationException("Please implement me...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.xtext.serializer.ISerializer#serialize(org.eclipse.emf.ecore.EObject, org.eclipse.xtext.resource.SaveOptions)
	 */
	@Override
	public String serialize(EObject obj, SaveOptions options) {
		TokenStringBuffer tokenStringBuffer = new TokenStringBuffer();
		try {
			serialize(obj, tokenStringBuffer, options);
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
		return tokenStringBuffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.xtext.serializer.ISerializer#serialize(org.eclipse.emf.ecore.EObject, java.io.Writer, org.eclipse.xtext.resource.SaveOptions)
	 */
	@Override
	public void serialize(EObject obj, Writer writer, SaveOptions options) throws IOException {
		serialize(obj, new WriterTokenStream(writer), options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.xtext.serializer.ISerializer#serializeReplacement(org.eclipse.emf.ecore.EObject, org.eclipse.xtext.resource.SaveOptions)
	 */
	@Override
	public ReplaceRegion serializeReplacement(EObject obj, SaveOptions options) {
		ICompositeNode node = NodeModelUtils.findActualNodeFor(obj);
		String text = serialize(obj);
		return new ReplaceRegion(node.getTotalOffset(), node.getTotalLength(), text);
	}

}