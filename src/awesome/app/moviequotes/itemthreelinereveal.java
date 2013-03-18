package awesome.app.moviequotes;


public class itemthreelinereveal {

	private String texte1;

	private String texte2;
	private String texte3;

	private boolean cb1;

	public itemthreelinereveal(String texte1, String texte2, String texte3,
			boolean cb1) {
		super();

		this.texte1 = texte1;
		this.texte2 = texte2;
		this.texte3 = texte3;
		this.cb1 = cb1;

	}

	public String getTexte1() {
		return texte1;
	}

	public String getTexte2() {
		return texte2;
	}

	public String getTexte3() {
		return texte3;
	}

	public boolean getCb1() {
		return cb1;
	}

	public void setCb1(boolean state) {
		this.cb1 = state;
	}

}