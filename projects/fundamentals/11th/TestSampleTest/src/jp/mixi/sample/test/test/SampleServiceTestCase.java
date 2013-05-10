package jp.mixi.sample.test.test;

import android.content.Intent;
import android.os.IBinder;
import android.test.ServiceTestCase;

import jp.mixi.sample.test.TestTargetService;

public class SampleServiceTestCase extends ServiceTestCase<TestTargetService> {
    public SampleServiceTestCase() {
        this(TestTargetService.class);
    }

    public SampleServiceTestCase(Class<TestTargetService> serviceClass) {
        super(serviceClass);
    }

    public void testStartingProperly() throws Exception {
        // サービスを問題なく開始できること
        // 何かあれば例外が飛ぶこともチェック
        startService(new Intent(getContext(), TestTargetService.class));
    }

    public void testBinding() throws Exception {
        // 開始するサービスの場合で、バインドをサポートしない場合は、bindService の返り値が null となるので、それをチェック
        IBinder binder = bindService(new Intent(getContext(), TestTargetService.class));
        assertNull(binder);
    }
}