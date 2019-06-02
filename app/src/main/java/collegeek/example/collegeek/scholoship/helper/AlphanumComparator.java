package collegeek.example.collegeek.scholoship.helper;

import collegeek.example.collegeek.scholoship.object.CollegeResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Comparator;
import java.util.Date;

public class AlphanumComparator implements Comparator<CollegeResponse>
{
    final private long todayTime = new Date().getTime();

    @Override
    public int compare(CollegeResponse o1, CollegeResponse o2) {

        if (!(o1.getDeadline() instanceof String) || !(o2.getDeadline() instanceof String))
        {
            return 0;
        }

        if(!o1.getDeadline().equalsIgnoreCase("rolling") && !o1.getDeadline().equalsIgnoreCase("NA")
                && !o2.getDeadline().equalsIgnoreCase("rolling") && !o2.getDeadline().equalsIgnoreCase("NA"))
        {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("MMM dd");

            DateTime dt1 = formatter.parseDateTime(o1.getDeadline());
            DateTime dt2 = formatter.parseDateTime(o2.getDeadline());


            return  dt1.compareTo(dt2);
        }


        String s1 = (String)o1.getDeadline();
        String s2 = (String)o2.getDeadline();


        int thisMarker = 0;
        int thatMarker = 0;
        int s1Length = s1.length();
        int s2Length = s2.length();

        while (thisMarker < s1Length && thatMarker < s2Length)
        {
            String thisChunk = getChunk(s1, s1Length, thisMarker);
            thisMarker += thisChunk.length();

            String thatChunk = getChunk(s2, s2Length, thatMarker);
            thatMarker += thatChunk.length();

            // If both chunks contain numeric characters, sort them numerically
            int result = 0;
            if (isDigit(thisChunk.charAt(0)) && isDigit(thatChunk.charAt(0)))
            {
                // Simple chunk comparison by length.
                int thisChunkLength = thisChunk.length();
                result = thisChunkLength - thatChunk.length();
                // If equal, the first different number counts
                if (result == 0)
                {
                    for (int i = 0; i < thisChunkLength; i++)
                    {
                        result = thisChunk.charAt(i) - thatChunk.charAt(i);
                        if (result != 0)
                        {
                            return result;
                        }
                    }
                }
            } else
            {
                result = thisChunk.compareTo(thatChunk);
            }

            if (result != 0)
                return result;
        }

        return s1Length - s2Length;
    }

    private final boolean isDigit(char ch)
    {
        return ch >= 48 && ch <= 57;
    }

    /** Length of string is passed in for improved efficiency (only need to calculate it once) **/
    private final String getChunk(String s, int slength, int marker)
    {
        StringBuilder chunk = new StringBuilder();
        char c = s.charAt(marker);
        chunk.append(c);
        marker++;
        if (isDigit(c))
        {
            while (marker < slength)
            {
                c = s.charAt(marker);
                if (!isDigit(c))
                    break;
                chunk.append(c);
                marker++;
            }
        } else
        {
            while (marker < slength)
            {
                c = s.charAt(marker);
                if (isDigit(c))
                    break;
                chunk.append(c);
                marker++;
            }
        }
        return chunk.toString();
    }

}