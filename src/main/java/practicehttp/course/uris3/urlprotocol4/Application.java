package practicehttp.course.uris3.urlprotocol4;

public class Application {
	public static void main(String[] args) {
		String email = "slayer@frequest.app";
		System.out.println(getMailtoLinkForEmmail(email));
	}

	private static String getMailtoLinkForEmmail(String email) {
		return "mailto:" + email;
	}
}
