package com.javaunittest.data.enums;

public enum ItemTags {
	BOOK(true), MEDICAL(true), FOOD(true), MUSIC(false), PERFUME(false);

	private boolean isExempted;

	private ItemTags(final boolean isExempted) {
		this.isExempted = isExempted;
	}

	public boolean isExempted() {
		return isExempted;
	}

}
