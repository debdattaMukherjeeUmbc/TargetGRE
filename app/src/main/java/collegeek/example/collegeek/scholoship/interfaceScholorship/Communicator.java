package collegeek.example.collegeek.scholoship.interfaceScholorship;

import collegeek.example.collegeek.scholoship.object.CollegeResponse;

import java.util.List;
import java.util.Properties;

public interface Communicator {

    public void respond(List<CollegeResponse> response, Properties properties);
}
