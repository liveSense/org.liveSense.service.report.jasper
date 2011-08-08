package org.liveSense.service.report.jasper;


import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.liveSense.service.report.jasper.actions.JasperReportGenerator;

/**
 * The <code>JasperServlet</code> returns a generated report with jasper 
 * servlet.
 */
@Component(label="%jasperservlet.name", description="%jasper.servlet.descpription", immediate=false, metatype=true)
@Service
@Properties(value={
	@Property(name="sling.servlet.paths", value="/session/jasper"),
	@Property(name="sling.servlet.methods", value={"GET"})
})
public class ReportInputServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		handleDo(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		handleDo(req, resp);
	}
	protected void handleDo(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		JasperReportGenerator.doAction(req, resp);
	}
}
