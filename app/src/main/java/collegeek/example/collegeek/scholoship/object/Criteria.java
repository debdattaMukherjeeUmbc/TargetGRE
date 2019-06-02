package collegeek.example.collegeek.scholoship.object;

import android.os.Parcel;
import android.os.Parcelable;

public class Criteria implements Parcelable {
	
	public Criteria(){}
	
	public Criteria(Parcel in) {
		this.admission = in.readString();
		this.semester = in.readString();
		this.field = in.readString();
        this.state = in.readString();
        this.collegeType = in.readString();
        this.difficultyLevel = in.readString();
	}
	
	
	private String admission;
	private String semester;
	private String field;
    private String state;
    private String collegeType;
    private String difficultyLevel;

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

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
    public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getAdmission() {
		return admission;
	}

	public void setAdmission(String admission) {
		this.admission = admission;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(admission);
		dest.writeString(semester);
		dest.writeString(field);
        dest.writeString(state);
        dest.writeString(collegeType);
        dest.writeString(difficultyLevel);
	}


public static final Creator<Criteria> CREATOR = new Creator<Criteria>() {
	
	@Override
	public Criteria createFromParcel(Parcel pc) {

	return new Criteria(pc);

	}
	
	@Override
	public Criteria[] newArray(int size) {
	
	return new Criteria[size];
	
	}
	
	};

}