package com.bk.util;
/**
 * @author Andrei Petaru
 * 15 Mar 2013
 */
public enum Genre {
	// FICTION
	ACTION_AND_ADVENTURE("Action and Adventure"),
	CHILDRENS("Children’s"),
	CONTEMPORARY("Contemporary"),
	CRIME("Crime"),
	EROTICA("Erotica"),
	FAMILIY_SAGA("Familiy Saga"),
	FANTASY("Fantasy"),
	DARK_FANTASY("Dark Fantasy"),
	URBAN_FANTASY("Urban Fantasy"),
	GAY_AND_LESBIAN("Gay and Lesbian"),
	GENERAL_FICTION("General Fiction"),
	GRAPHIC_NOVEL("Graphic Novel"),
	HISTORICAL_FICTION("Historical Fiction"),
	HUMOR("Humor"),
	LITERARY_FICTION("Literay Fiction"),
	MYSTERY("Mystery"),
	RELIGIOUS("Religious"),
	INSPIRATIONAL("Inspirational"),
	ROMANCE("Romance"),
	SCIENCE_FICTION("Science Fiction"),
	SHORT_STORY_COLLECTION("Short Story Collection"),
	THRILLER("Thriller"),
	SUSPENSE("Suspense"),
	WESTERN("Western"),
	YOUNG_ADULT("Young Adult"),
	
	// NON-FICTION
	ART_AND_PHOTOGRAPHY("Art and Photography"),
	BIOGRAPHY_AND_MEMOIRS("Biography and Memoirs"),
	BUSINESS_AND_FINANCE("Business and Finance"),
	COOKBOOK("Cookbook"),
	CURRENT_AFFAIRS_AND_POLITICS("Current Affairs and Politics"),
	FOOD_AND_LIFESTYLE("Food and Lifestyle"),
	GARDENING("Gardening"),
	GENERAL_NON_FICTION("General Non Fiction"),
	HOW_TO("How To"),
	JOURNALISM("Journalism"),
	JUVENILE("Juvenile"),
	MEDICAL_HEALTH_AND_FITNESS("Medican Health and Fitness"),
	MULTICULTURAL("Multicultural"),
	NARRATIVE("Narrative"),
	PARENTING("Parenting"),
	PETS("Pets"),
	PSYCHOLOGY("Psychology"),
	RELATIONSHIPS_AND_DATING("Relationships and Dating"),
	SCIENCE_AND_TECHNOLOGY("Science and Technology"),
	SELF_HELP("Self Help"),
	SPORTS("Sports"),
	TRAVEL("Travel");
	
	private String genre;
	
	private Genre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return genre;
    }
}
