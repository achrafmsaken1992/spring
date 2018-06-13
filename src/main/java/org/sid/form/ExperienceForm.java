package org.sid.form;

public class ExperienceForm {
	private String description;
	private String entreprise;
	private String lieu;
	private String date1;
	private String date2;
	private String titre;
private Long id;
private String motCle;

	
	public String getMotCle() {
	return motCle;
}

public void setMotCle(String motCle) {
	this.motCle = motCle;
}

	public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	

	
    
}
