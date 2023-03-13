import java.util.Date;

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
    if (title.startsWith("\"")) {
      title = title.substring(1);
    }
    if (title.endsWith("\"")) {
      title = title.substring(0, title.length() - 1);
    }
    this.title = title;
    if (instructors.startsWith("\"")) {
      instructors = instructors.substring(1);
    }
    if (instructors.endsWith("\"")) {
      instructors = instructors.substring(0, instructors.length() - 1);
    }
    this.instructors = instructors;
    if (subject.startsWith("\"")) {
      subject = subject.substring(1);
    }
    if (subject.endsWith("\"")) {
      subject = subject.substring(0, subject.length() - 1);
    }
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