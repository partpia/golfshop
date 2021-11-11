package hh.swd20.golfshop.domain;

public enum Gender {
	MALE("Male"),
	FEMALE("Female"),
	UNISEX("Unisex");
	
	private final String genderName;
	
	private Gender(String genderName) {
		this.genderName = genderName;
	}
	
	public String genderName() {
		return genderName;
	}
}
