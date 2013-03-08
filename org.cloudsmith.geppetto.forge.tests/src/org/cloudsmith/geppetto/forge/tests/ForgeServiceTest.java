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
package org.cloudsmith.geppetto.forge.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.cloudsmith.geppetto.forge.v2.model.Metadata;
import org.cloudsmith.geppetto.forge.v2.model.ModuleName;
import org.junit.Test;

public class ForgeServiceTest extends AbstractForgeTest {

	@Test
	public void testLoadJSONMetadata__File() {
		try {
			Metadata md = getForge().loadJSONMetadata(Activator.getTestData("puppetlabs-apache/metadata.json"));
			assertEquals("Unexpected module name", new ModuleName("puppetlabs-apache"), md.getName());
		}
		catch(IOException e) {
			fail(e.getMessage());
		}
	}
}
