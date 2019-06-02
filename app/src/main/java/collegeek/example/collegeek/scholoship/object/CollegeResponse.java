package collegeek.example.collegeek.scholoship.object;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CollegeResponse implements Parcelable, Serializable, Comparable<CollegeResponse> {
    @Override
    public int compareTo(CollegeResponse another) {

        String collegeName1 = ((CollegeResponse) another).getCollegeName().toUpperCase();
        String collegeName2 = collegeName.toUpperCase();

        //ascending order
        return collegeName2.compareTo(collegeName1);

    }


    public static Comparator<CollegeResponse> InStateTuitionComparator
            = new Comparator<CollegeResponse>() {

        @Override
        public int compare(CollegeResponse lhs, CollegeResponse rhs) {


            String[] lhsArray = lhs.getCollegeTuition().split("\\|");
            String[] rhsArray = rhs.getCollegeTuition().split("\\|");

            if(lhsArray[0] != null && rhsArray[0] != null) {

                String lhsTrim = lhsArray[0].trim();
                String rhsTrim = rhsArray[0].trim();

                int tuition1 = Integer.valueOf(lhsTrim.replaceAll("[$,]", ""));
                int tuition2 = Integer.valueOf(rhsTrim.replaceAll("[$,]", ""));

                if (tuition1 > tuition2) {
                    return 1;
                } else if (tuition1 < tuition2) {
                    return -1;
                } else {
                    return 0;
                }
            }
            return 0;

        }
    };

    public static Comparator<CollegeResponse> OutStateTuitionComparator
            = new Comparator<CollegeResponse>() {

        @Override
        public int compare(CollegeResponse lhs, CollegeResponse rhs) {


            String[] lhsArray = lhs.getCollegeTuition().split("\\|");
            String[] rhsArray = rhs.getCollegeTuition().split("\\|");

            if(lhsArray[1] != null && rhsArray[1] != null) {

                String lhsTrim = lhsArray[1].trim();
                String rhsTrim = rhsArray[1].trim();

                int tuition1 = Integer.valueOf(lhsTrim.replaceAll("[$,]", ""));
                int tuition2 = Integer.valueOf(rhsTrim.replaceAll("[$,]", ""));

                if (tuition1 > tuition2) {
                    return 1;
                } else if (tuition1 < tuition2) {
                    return -1;
                } else {
                    return 0;
                }
            }
            return 0;

        }
    };

    public static Comparator<CollegeResponse> ExpenditureComparator
            = new Comparator<CollegeResponse>() {

        @Override
        public int compare(CollegeResponse lhs, CollegeResponse rhs) {


            if(lhs.getRdExpenditure() != null && rhs.getRdExpenditure() != null) {

                int tuition1 = Integer.valueOf(lhs.getRdExpenditure().replaceAll("[$,]", ""));
                int tuition2 = Integer.valueOf(rhs.getRdExpenditure().replaceAll("[$,]", ""));

                if (tuition1 > tuition2) {
                    return -1;
                } else if (tuition1 < tuition2) {
                    return 1;
                } else {
                    return 0;
                }
            }
            return 0;

        }
    };

    public static Comparator<CollegeResponse> DeadlineComparator
            = new Comparator<CollegeResponse>() {

        @Override
        public int compare(CollegeResponse lhs, CollegeResponse rhs) {

            if(lhs.getDeadline().equalsIgnoreCase("rolling") || lhs.getDeadline().equalsIgnoreCase("NA"))
            {
                return 1;
            }

            if(rhs.getDeadline().equalsIgnoreCase("rolling"))
            {
                return -1;
            }
            DateTimeFormatter formatter = DateTimeFormat.forPattern("MMM dd");

            DateTime dt1 = formatter.parseDateTime(lhs.getDeadline());
            DateTime dt2 = formatter.parseDateTime(rhs.getDeadline());

           return  dt1.compareTo(dt2);

        }
    };

    private static final long serialVersionUID = -3941468843934715459L;

	private String id;
	private String collegeName;
	private String locationPlace;
	private String collegeTuition;
	private String deadline;
	private String website;
    private String locationState;
    private String phone;
    private String type;
    private String admissionDifficulty;
    private String malePercent;
    private String femalePercent;
    private String acceptanceRate;
    private String totalStudents;
    private String totalGraduates;
    private String totalStudentsByTotalGrads;
    private String applicationFee;
    private String percentInternationalGrad;
    private String rdExpenditure;
    private boolean isFavoriteSelected;
    private Map<String,String> subjects = new HashMap<String,String>();
    private String latitude;
    private String longitude;
    private String minToeflIBT;
    private String minToeflPBT;
    private String ielts;

    public String getIelts() {
        return ielts;
    }

    public void setIelts(String ielts) {
        this.ielts = ielts;
    }

    public String getMinToeflPBT() {
        return minToeflPBT;
    }

    public void setMinToeflPBT(String minToeflPBT) {
        this.minToeflPBT = minToeflPBT;
    }

    public String getMinToeflIBT() {
        return minToeflIBT;
    }

    public void setMinToeflIBT(String minToeflIBT) {
        this.minToeflIBT = minToeflIBT;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Map<String, String> getSubjects() {
        return subjects;
    }

    public void setSubjects(Map<String, String> subjects) {
        this.subjects = subjects;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPercentInternationalGrad() {
        return percentInternationalGrad;
    }

    public void setPercentInternationalGrad(String percentInternationalGrad) {
        this.percentInternationalGrad = percentInternationalGrad;
    }

    public String getRdExpenditure() {
        return rdExpenditure;
    }

    public void setRdExpenditure(String rdExpenditure) {
        this.rdExpenditure = rdExpenditure;
    }

    public boolean isFavoriteSelected() {
        return isFavoriteSelected;
    }

    public void setFavoriteSelected(boolean isFavoriteSelected) {
        this.isFavoriteSelected = isFavoriteSelected;
    }

    public CollegeResponse() {
	}

	public CollegeResponse(Parcel in) {
		this.id = in.readString();
		this.collegeName = in.readString();
        this.locationPlace = in.readString();
        this.collegeTuition = in.readString();
        this.deadline = in.readString();
        this.website = in.readString();
        this.phone = in.readString();
        this.locationState = in.readString();
        this.type = in.readString();
        this.admissionDifficulty = in.readString();
        this.malePercent = in.readString();
        this.femalePercent = in.readString();
        this.acceptanceRate = in.readString();
        this.totalStudents = in.readString();
        this.totalGraduates = in.readString();
        this.applicationFee = in.readString();
        this.totalStudentsByTotalGrads = in.readString();
        this.rdExpenditure = in.readString();
        this.percentInternationalGrad = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.minToeflIBT = in.readString();
        this.minToeflPBT = in.readString();
        this.ielts = in.readString();

        int size = in.readInt();
        for(int i = 0; i < size; i++){
            String key = in.readString();
            String value = in.readString();
            subjects.put(key,value);
        }
	}

    public String getTotalStudentsByTotalGrads() {
        return totalStudentsByTotalGrads;
    }

    public void setTotalStudentsByTotalGrads(String totalStudentsByTotalGrads) {
        this.totalStudentsByTotalGrads = totalStudentsByTotalGrads;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdmissionDifficulty() {
        return admissionDifficulty;
    }

    public void setAdmissionDifficulty(String admissionDifficulty) {
        this.admissionDifficulty = admissionDifficulty;
    }

    public String getMalePercent() {
        return malePercent;
    }

    public void setMalePercent(String malePercent) {
        this.malePercent = malePercent;
    }

    public String getFemalePercent() {
        return femalePercent;
    }

    public void setFemalePercent(String femalePercent) {
        this.femalePercent = femalePercent;
    }

    public String getAcceptanceRate() {
        return acceptanceRate;
    }

    public void setAcceptanceRate(String acceptanceRate) {
        this.acceptanceRate = acceptanceRate;
    }

    public String getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(String totalStudents) {
        this.totalStudents = totalStudents;
    }

    public String getTotalGraduates() {
        return totalGraduates;
    }

    public void setTotalGraduates(String totalGraduates) {
        this.totalGraduates = totalGraduates;
    }

    public String getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(String applicationFee) {
        this.applicationFee = applicationFee;
    }

   public String getLocationState() {
        return locationState;
    }
    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getLocationPlace() {
		return locationPlace;
	}

	public void setLocationPlace(String locationPlace) {
		this.locationPlace = locationPlace;
	}

	public String getCollegeTuition() {
		return collegeTuition;
	}

	public void setCollegeTuition(String collegeTuition) {
		this.collegeTuition = collegeTuition;
	}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(collegeName);
		dest.writeString(locationPlace);
		dest.writeString(collegeTuition);
		dest.writeString(deadline);
		dest.writeString(website);
        dest.writeString(phone);
        dest.writeString(locationState);
        dest.writeString(type);
        dest.writeString(admissionDifficulty);
        dest.writeString(malePercent);
        dest.writeString(femalePercent);
        dest.writeString(acceptanceRate);
        dest.writeString(totalGraduates);
        dest.writeString(totalStudents);
        dest.writeString(applicationFee);
        dest.writeString(totalStudentsByTotalGrads);
        dest.writeString(rdExpenditure);
        dest.writeString(percentInternationalGrad);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(minToeflPBT);
        dest.writeString(minToeflIBT);
        dest.writeString(ielts);

        dest.writeInt(subjects.size());
        for(Map.Entry<String,String> entry : subjects.entrySet()){
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }

	}
	
	public static final Creator<CollegeResponse> CREATOR = new Creator<CollegeResponse>() {
		
		@Override
		public CollegeResponse createFromParcel(Parcel pc) {

		return new CollegeResponse(pc);

		}
		
		@Override
		public CollegeResponse[] newArray(int size) {
		
		return new CollegeResponse[size];
		
		}
		
		};

	
	

}
