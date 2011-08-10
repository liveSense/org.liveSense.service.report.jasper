package org.liveSense.service.report.jasper;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface ReportService {
	
	public enum ReporOutputType {
		PDF, CSV, XLS, HTLM
	}
	
	/**
	 * Compile report and store it to CMS. 
	 * 
	 * @param xml  - The String representation of Jasper XML	
	 * @param path - The the compiled report output path
	 * @param name - The name of the report
	 * @return The URL of the compiled report
	 */
	String compileReportToCms(String xml, String path, String name);
	
	/**
	 * Compile report and store it to CMS. 
	 * 
	 * @param stream  - The InputStream representation of Jasper XML	
	 * @param path - The the compiled report output path
	 * @param name - The name of the report
	 * @return The URL of the compiled report
	 */
	String compileReportToCms(InputStream stream, String path, String name);

	/**
	 * Compile report and write to stream. 
	 * 
	 * @param xml - The String representation of Jasper XML	
	 * @param out - The OutputStream of the compiled report
	 */
	void compileReportToStream(String xml, OutputStream out);
	
	/**
	 * Compile report and write to stream. 
	 * 
	 * @param stream  - The InputStream representation of Jasper XML	
	 * @param out - The OutputStream of the compiled report
	 */
	void compileReportToStream(InputStream stream, OutputStream out);


	/**
	 * Generate report and store the output to CMS. 
	 * 
	 * @param path  - The CMS path of the compiled jasper report	
	 * @param type - The type of the report
	 * @param path - The the generated report output path
	 * @param name - The name of the report
	 * @param parameters - The parameters to be passed to Jasper 
	 * @return The URL of the generated report
	 */
	String generateReportToCms(String report, String type, String path, String name, List<?> parameters);
	
	/**
	 * Generate report and store the output to CMS. 
	 * 
	 * @param report  - The InputStream representation of the compiled report	
	 * @param type - The type of the report
	 * @param parameters - The parameters to be passed to Jasper 
	 * @return The URL of the generated report
	 */
	String generateReportToCms(InputStream report, String type,  List<?> parameters);

	
	

}
