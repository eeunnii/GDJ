package common;

public class ActionForward {
	private String view;		// 응답할 Jsp의 이름
	private boolean isRedirect; // 이동방식(true이면 리다이렉트, false이면 포워드)

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}
	
	
	
	
}
