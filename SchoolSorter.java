public class SchoolSorter {
    String [] soh;
    String [] som;
    String [] sose;
    String [] soss;
    public SchoolSorter () {
        soh = new String[]{"AB HUM", "AB IS", "AB LIT", "AB PH", "BFA AM", "BFA CW", "BFA ID", "BFA TA"};
        som = new String[]{"BS COMTECH", "BS ITE", "BS LM", "BS MAC", "BS ME", "BS MGT", "BG MGT-H", "BS MGMT"};
        sose = new String[]{"BS APS-MSE", "BS BIO", "BS CH", "BS CH-MSE", "BS COE", "BS CS", "BSMS CS", "BS CS-DGDD",
                            "BS ECE", "BS ES", "BS HS", "BS LFSCI", "BS MA", "BS MIS", "BSMIS-MSCS", "BSM AMF", "BS PS"};
        soss = new String[]{"AB CHNS-B", "AB COM", "AB DIP IR", "AB DS", "AB EC", "AB EC-H", "AB EU", "AB HI", "AB MEC",
                            "AB POS", "AB POS-MPM", "AB MA-POS", "AB PSY", "BS PSY", "AB SOS"};
    }

    public String getSchool (String courseID) {
        for (String s : soh) {
            if (courseID.toUpperCase().contains(s.toUpperCase()))
                return "SOH";
        }

        for (String s : som) {
            if (courseID.toUpperCase().contains(s.toUpperCase()))
                return "SOM";
        }

        for (String s : sose) {
            if (courseID.toUpperCase().contains(s.toUpperCase()))
                return "SOSE";
        }

        for (String s : soss) {
            if (courseID.toUpperCase().contains(s.toUpperCase()))
                return "SOSS";
        }

        return "";
    }
}
