package collegeek.example.collegeek.scholoship.translator;


import java.util.HashMap;
import java.util.Map;

import collegeek.example.collegeek.scholoship.object.Criteria;
import collegeek.example.collegeek.scholoship.object.Query;

public class CriteriaToQuery {

    private Map<String,String> states = new HashMap<String,String>();
    private Map<String,String> fields = new HashMap<String,String>();

    public Query translateCollegeCriteriaToQuery(Criteria criteria) {

        Query query = new Query();

        states = generateStates();
        fields = getFields();


        query.setState(criteria.getState() == null ? null : states.get(criteria.getState().trim()));
        query.setCollegeType(criteria.getCollegeType() != null ? criteria.getCollegeType().trim() : null);
        query.setField(criteria.getField() != null ? fields.get(criteria.getField().trim()) : null);
        query.setAdmission(criteria.getAdmission() != null ? criteria.getAdmission().trim() : null);

        String difficultyLevel = criteria.getDifficultyLevel() != null ? criteria.getDifficultyLevel().trim() : null;

        if(difficultyLevel != null)
        {
            if(difficultyLevel.equalsIgnoreCase("All"))
            {
                query.setDifficultyLevel("All");
            }
            if(difficultyLevel.startsWith("Easy"))
            {
                query.setDifficultyLevel("Easy");
            }
            if(difficultyLevel.startsWith("Average"))
            {
                query.setDifficultyLevel("Average");
            }
            if(difficultyLevel.startsWith("Above"))
            {
                query.setDifficultyLevel("Above Average");
            }
            if(difficultyLevel.startsWith("Difficult"))
            {
                query.setDifficultyLevel("Difficult");
            }
        }

        if(criteria.getSemester() == null)
        {
            query.setSemester("Fall_Date");
         }
        else {

            if (criteria.getSemester().equalsIgnoreCase("Fall [ Aug - Dec ]")) {
                query.setSemester("Fall_Date");
            } else if (criteria.getSemester().equalsIgnoreCase("Spring [ Jan - May ]")) {
                query.setSemester("Spring_Date");
            } else if (criteria.getSemester().equalsIgnoreCase("Summer [ May - July ]")) {
                query.setSemester("Summer_Date");
            }
        }

        return query;
    }

    private Map<String,String> generateStates()
    {
        states.put("Alabama","AL");
        states.put("Alaska","AK");
        states.put("Arizona","AZ");
        states.put("Arkansas","AR "); // space in AR
        states.put("California","CA");
        states.put("Colorado","CO");
        states.put("Connecticut","CT");
        states.put("Delaware","DE");
        states.put("Florida","FL");
        states.put("Georgia","GA");
        states.put("Hawaii","HI");
        states.put("Idaho","ID");
        states.put("Illinois","IL");
        states.put("Indiana","IN");
        states.put("Iowa","IA");
        states.put("Kansas","KS");
        states.put("Kentucky","KY");
        states.put("Louisiana","LA");
        states.put("Maine","ME");
        states.put("Maryland","MD");
        states.put("Massachusetts","MA");
        states.put("Michigan","MI");
        states.put("Minnesota","MN");
        states.put("Mississippi","MS");
        states.put("Missouri","MO");
        states.put("Montana","MT");
        states.put("Nevada","NV");
        states.put("Nebraska","NE");
        states.put("New Hampshire","NH");
        states.put("New Jersey","NJ");
        states.put("New Mexico","NM");
        states.put("New York","NY");
        states.put("North Carolina","NC");
        states.put("North Dakota","ND");
        states.put("Ohio","OH");
        states.put("Oklahoma","OK");
        states.put("Oregon","OR");
        states.put("Pennsylvania","PA");
        states.put("Rhode Island","RI");
        states.put("South Carolina","SC");
        states.put("South Dakota","SD");
        states.put("Tennessee","TN");
        states.put("Texas","TX");
        states.put("Utah","UT");
        states.put("Vermont","VT");
        states.put("Virginia","VA");
        states.put("Washington","WA");
        states.put("West Virginia","WV");
        states.put("Wisconsin","WI");
        states.put("Wyoming","WY");

        return states;
    }

    private Map<String,String> getFields()
    {
        fields.put("Aviation","Aviation");
        fields.put("Chemical Engineering","Chemical_Engineering");
        fields.put("Chemistry","Chemistry");
        fields.put("Civil","Civil");
        fields.put("Computer Engineering","Computer_Engineering");
        fields.put("Computer Science","Computer_Science");
        fields.put("Electrical Engineering","Electrical_Engineering");
        fields.put("Electronics Communication","Electronics_Communication");
        fields.put("Industrial Engineering","Industrial_Engineering");
        fields.put("Management in information Systems","MIS_IS");
        fields.put("Mechanical Engineering","Mechanical_Engineering");
        fields.put("Engineering Management","Engineering_Management");
        fields.put("Architecture","Architecture");
        fields.put("Physics","Physics");

        return fields;
    }
}
