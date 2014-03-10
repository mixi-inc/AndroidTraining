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
	public String getPurchaseToken (final boolean isInApp, final String packageName) {
		return
				(isInApp ? "inapp" : "subs") + ":" +
				packageName + ":" +
				getId();
	}
}
