package jp.mixi.training.inapppurchase.helper;

public enum DummySku {
	PURCHASED("android.test.purchased"),
	CANCELED("android.test.canceled");

	private final String mId;
	
	private DummySku (final String id) {
		this.mId = id;
	}
	public String getId () {
		return mId;
	}
}
