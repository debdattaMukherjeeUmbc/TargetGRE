package collegeek.example.collegeek.scholoship.object;

public class Query {
	
	private String scholorshipTypeValue;
	private String admission;
	private String semester;
	private String field;
    private String state;
    private String difficultyLevel;
    private String collegeType;

    public String getCollegeType() {
        return collegeType;
    }

    public void setCollegeType(String collegeType) {
        this.collegeType = collegeType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getAdmission() {
		return admission;
	}

	public void setAdmission(String admission) {
		this.admission = admission;
	}

	public String getScholorshipTypeValue() {
		return scholorshipTypeValue;
	}

	public void setScholorshipTypeValue(String scholorshipTypeValue) {
		this.scholorshipTypeValue = scholorshipTypeValue;
	}
	
	

}
