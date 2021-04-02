package fr.ensta.servPattern;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * ...
 * */
public interface IProtocole {

	public void execute(IContext c , BufferedReader is, PrintStream os, String inputReq, ProcessusEchange processusEchangeCourant );
	
}
