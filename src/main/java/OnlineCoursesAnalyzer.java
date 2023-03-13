import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * This is just a demo for you, please run it on JDK17 (some statements may be not allowed in lower version).
 * This is just a demo, and you can extend and implement functions
 * based on this demo, or implement it in a different way.
 */
public class OnlineCoursesAnalyzer {

    List<Course> courses = new ArrayList<>();

    public OnlineCoursesAnalyzer(String datasetPath) {
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new FileReader(datasetPath, StandardCharsets.UTF_8));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
                Course course = new Course(info[0], info[1], new Date(info[2]), info[3], info[4], info[5],
                        Integer.parseInt(info[6]), Integer.parseInt(info[7]), Integer.parseInt(info[8]),
                        Integer.parseInt(info[9]), Integer.parseInt(info[10]), Double.parseDouble(info[11]),
                        Double.parseDouble(info[12]), Double.parseDouble(info[13]), Double.parseDouble(info[14]),
                        Double.parseDouble(info[15]), Double.parseDouble(info[16]), Double.parseDouble(info[17]),
                        Double.parseDouble(info[18]), Double.parseDouble(info[19]), Double.parseDouble(info[20]),
                        Double.parseDouble(info[21]), Double.parseDouble(info[22]));
                courses.add(course);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //1
    public Map<String, Integer> getPtcpCountByInst() {
        TreeMap<String, Integer> res = new TreeMap<>();
        for (Course course : courses) {
            Integer num = res.get(course.institution);
            if (num == null) res.put(course.institution, course.participants);
            else res.put(course.institution, num + course.participants);
        }
        return res;
    }

    //2
    public Map<String, Integer> getPtcpCountByInstAndSubject() {
        Map<String, Integer> res = new HashMap<>();
        for (Course course : courses) {
            StringBuffer tmp = new StringBuffer();
            tmp.append(course.institution);
            tmp.append("-"); tmp.append(course.subject);
            Integer num = res.get(tmp.toString());
            if (num == null) res.put(tmp.toString(), course.participants);
            else res.put(tmp.toString(), num + course.participants);
        }
        return res.entrySet().stream().sorted((item1, item2) -> {
            int compare = item2.getValue().compareTo(item1.getValue());
            if (compare == 0) {
                compare = item1.getKey().compareTo(item2.getKey());
            }
            return compare;
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    //3
    public Map<String, List<List<String>>> getCourseListOfInstructor() {
        Map<String, Set<String>> res0 = new HashMap<>(), res1 = new HashMap<>();
        Set<String>allInstructors = new HashSet<>();
        for (Course course: courses) {
            Set<String> courseInfo = new HashSet<>();
            String [] tmp = course.instructors.split(", ");
            for (String instructor: tmp) {
                allInstructors.add(instructor);
                courseInfo.add(instructor);
                if (res0.get(instructor) == null) res0.put(instructor, new HashSet<>());
                if (res1.get(instructor) == null) res1.put(instructor, new HashSet<>());
            }
             for (String instructor: tmp) {
                if (courseInfo.size() == 1) {
                    Set<String> now = res0.get(instructor);
                    now.add(course.title);
                    res0.put(instructor, now);
                } else {
                    Set<String> now = res1.get(instructor);
                    now.add(course.title);
                    res1.put(instructor, now);
                }
            }
        }
        Map<String, List<List<String>>> res = new HashMap<>();
        for (String instructor: allInstructors) {
            List<String> list0 = new ArrayList<>(), list1 = new ArrayList<>();
            Set<String> tmp = res0.get(instructor);
            for (String courseTitle: tmp) {
                list0.add(courseTitle);
            }
            tmp = res1.get(instructor);
            for (String courseTitle: tmp) {
                list1.add(courseTitle);
            }
            Collections.sort(list0);
            Collections.sort(list1);
            List<List<String>> w = new ArrayList<>();
            w.add(list0); w.add(list1);
            res.put(instructor, w);
        }
        return res;
    }

    //4
    public List<String> getCourses(int topK, String by) {
        return null;
    }

    //5
    public List<String> searchCourses(String courseSubject, double percentAudited, double totalCourseHours) {
        return null;
    }

    //6
    public List<String> recommendCourses(int age, int gender, int isBachelorOrHigher) {
        return null;
    }

}

class Course {
    String institution;
    String number;
    Date launchDate;
    String title;
    String instructors;
    String subject;
    int year;
    int honorCode;
    int participants;
    int audited;
    int certified;
    double percentAudited;
    double percentCertified;
    double percentCertified50;
    double percentVideo;
    double percentForum;
    double gradeHigherZero;
    double totalHours;
    double medianHoursCertification;
    double medianAge;
    double percentMale;
    double percentFemale;
    double percentDegree;

    public Course(String institution, String number, Date launchDate,
                  String title, String instructors, String subject,
                  int year, int honorCode, int participants,
                  int audited, int certified, double percentAudited,
                  double percentCertified, double percentCertified50,
                  double percentVideo, double percentForum, double gradeHigherZero,
                  double totalHours, double medianHoursCertification,
                  double medianAge, double percentMale, double percentFemale,
                  double percentDegree) {
        this.institution = institution;
        this.number = number;
        this.launchDate = launchDate;
        if (title.startsWith("\"")) title = title.substring(1);
        if (title.endsWith("\"")) title = title.substring(0, title.length() - 1);
        this.title = title;
        if (instructors.startsWith("\"")) instructors = instructors.substring(1);
        if (instructors.endsWith("\"")) instructors = instructors.substring(0, instructors.length() - 1);
        this.instructors = instructors;
        if (subject.startsWith("\"")) subject = subject.substring(1);
        if (subject.endsWith("\"")) subject = subject.substring(0, subject.length() - 1);
        this.subject = subject;
        this.year = year;
        this.honorCode = honorCode;
        this.participants = participants;
        this.audited = audited;
        this.certified = certified;
        this.percentAudited = percentAudited;
        this.percentCertified = percentCertified;
        this.percentCertified50 = percentCertified50;
        this.percentVideo = percentVideo;
        this.percentForum = percentForum;
        this.gradeHigherZero = gradeHigherZero;
        this.totalHours = totalHours;
        this.medianHoursCertification = medianHoursCertification;
        this.medianAge = medianAge;
        this.percentMale = percentMale;
        this.percentFemale = percentFemale;
        this.percentDegree = percentDegree;
    }
}