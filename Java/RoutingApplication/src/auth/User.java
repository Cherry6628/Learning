package auth;

public record User(int userId, String uname, String emailId) {
    public void displayProfile() {
        String title = "PROFILE";
        String idLine = "ID: " + userId;
        String nameLine = "Username: " + uname;
        String emailLine = "Email: " + emailId;

        int contentWidth = Math.max(title.length(), 
                          Math.max(nameLine.length(), emailLine.length()));
        int padding = 4;
        int totalWidth = contentWidth + padding;

        String border = "+" + "-".repeat(totalWidth + 2) + "+";
        
        System.out.println("\n" + border);
        
        int titlePadding = (totalWidth - title.length()) / 2;
        System.out.printf("| %" + titlePadding + "s%s%" + (totalWidth - title.length() - titlePadding) + "s |\n", "", title, "");
        
        System.out.println("|" + "-".repeat(totalWidth + 2) + "|");

        System.out.printf("| %-" + totalWidth + "s |\n", idLine);
        System.out.printf("| %-" + totalWidth + "s |\n", nameLine);
        System.out.printf("| %-" + totalWidth + "s |\n", emailLine);
        
        System.out.println(border);
    }
}