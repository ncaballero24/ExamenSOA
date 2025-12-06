package mx.replica.exam.app.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.xml.transform.StringSource;
import javax.xml.transform.Source;
import mx.replica.exam.app.service.StudentService;
import mx.replica.exam.app.model.Student;

@Endpoint
public class StudentSoapEndpoint {

    private static final String NAMESPACE = "http://replica.mx/students";
    private final StudentService service;

    public StudentSoapEndpoint(StudentService service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getStudentRequest")
    @ResponsePayload
    public Source handleGetStudent(@RequestPayload Source request) throws Exception {
        String req = new java.util.Scanner(((javax.xml.transform.stream.StreamSource)request).getInputStream()).useDelimiter("\\A").next();
        String idStr = req.replaceAll("(?s).*<id>(\\d+)</id>.*", "$1");
        Long id = Long.parseLong(idStr);
        Student s = service.find(id);
        if (s == null) {
            String resp = "<ns:getStudentResponse xmlns:ns=\"" + NAMESPACE + "\">" +
                    "<id>0</id><name>NOT_FOUND</name><course/>" +
                    "</ns:getStudentResponse>";
            return new StringSource(resp);
        } else {
            String resp = "<ns:getStudentResponse xmlns:ns=\"" + NAMESPACE + "\">" +
                    "<id>" + s.getId() + "</id><name>" + escape(s.getName()) + "</name><course>" + escape(s.getCourse()) + "</course>" +
                    "</ns:getStudentResponse>";
            return new StringSource(resp);
        }
    }

    private String escape(String in) {
        if (in == null) return "";
        return in.replaceAll("&","&amp;").replaceAll("<","&lt;").replaceAll(">","&gt;");
    }
}
