package com.danidemi.tutorial.tdd.showcase.junit.rules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class TemporaryFolderRuleTest {

	@Rule public TemporaryFolder temp = new TemporaryFolder();
	
	@Test
	public void shouldWriteFile() throws FileNotFoundException {
		
		PrintWriter printWriter = new PrintWriter( new File(temp.getRoot(), "hello.txt") );
		printWriter.write("hello world");
		
	}
	
	@Test
	public void shouldCountFiles() throws FileNotFoundException {
		
		assertThat( temp.getRoot().listFiles().length, equalTo(0) );
		
	}
	
}
