package jp.mixi.sample.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 再利用可能なコンポーネントを切り出すための {@link Fragment} です。
 *
 * {@link Fragment} は Android 3.x 以降で導入されたものなので、Android 2.x で {@link Fragment} を扱うには
 * Support Package を導入する必要があります（通常、プロジェクトの新規作成直後に、libs ディレクトリに android-support-v4.jar が配置されるはずです）。
 * Android 3.x 以降のみをサポートする場合は、{@link android.app.Fragment} を import しますが、Android 2.x 系をサポートする場合は、
 * {@link android.support.v4.app.Fragment} を import するようにします。
 *
 * A {@link Fragment} aims to make various UI components more re-usable to support particular devices or screens.
 * {@link Fragment} is introduced on Android 3.x, so we need to put Support Package library into the android project,
 * and it will automatically be installed by the sdk.
 * To support Android 2.x, import {@link Fragment} from {@link android.support.v4.app} package, instead of {@link android.app} package.
 *
 * @author keishin.yokomaku
 */
public class MainFragment extends Fragment {
    public static final String TAG = MainFragment.class.getSimpleName();

    /**
     * {@link Fragment} を継承するクラスには、空の public なデフォルトコンストラクタを定義する必要があります。
     *
     * <blockquote>
     * すべての {@link Fragment} を継承する子クラスは、必ず空の public なコンストラクタを定義する必要があります。
     * これは、フレームワークがしばしば、{@link Fragment} クラスを再インスタンス化する場合があるためです。特に、
     * 状態を復帰する目的で、{@link Fragment} をインスタンス化しようとするために、このコンストラクタが必要です。
     * もし空のっコンストラクタが見つからない場合、状態を復帰しようとして実行時例外が投げられることがあります。
     * </blockquote>
     *
     * フレームワークが、破棄された {@link Fragment} を再インスタンス化する際には、
     * {@link Fragment#instantiate(Context, String, Bundle)}を利用します。
     * このメソッドは、フラグメントをインスタンス化するために、リフレクションを用いて空のコンストラクタを呼び出しています。
     * このため、空のコンストラクタに引数を渡すことで、フラグメントに何かしらの値を渡すことはできません。
     * また、このメソッドの中で実施される再インスタンス化処理のプロセスでは、いかなる setter メソッドも呼び出されない（呼び出せない）ため
     * setter メソッド経由で {@link Fragment} に値を渡して初期化することもできません。
     *
     * もし {@link Fragment} に何かしらの値を渡して初期化したい場合は、{@link Fragment#setArguments(Bundle)} を使います。
     * ここで渡す {@link Bundle} オブジェクトは、再インスタンス化の時にも {@link Fragment} に渡されます。
     *
     * We always need to declare an empty constructor in a class that extends {@link Fragment}.
     *
     * <blockquote>
     * All subclasses of {@link Fragment} must include a public empty constructor.
     * The framework will often re-instantiate a {@link Fragment} class when needed,
     * in particular during state restore,
     * and needs to be able to find this constructor to instantiate it.
     * If the empty constructor is not available,
     * a runtime exception will occur in some cases during state restore.
     * </blockquote>
     *
     * This is because android framework will re-instantiate a destroyed {@link Fragment} using
     * {@link Fragment#instantiate(Context, String, Bundle)}.
     * This method will call an empty constructor of a {@link Fragment} to instantiate,
     * so that we cannot pass any values to the {@link Fragment} via constructors that accepts arguments.
     * Also, setter methods will not be called during re-instantiation, so do not pass any values
     * through setter methods.
     *
     * If you would like to pass some values to the {@link Fragment}, use {@link Fragment#setArguments(Bundle)}.
     * The {@link Bundle} object that comes from this method will be used during {@link Fragment} re-instantiation.
     *
     * Reference： http://y-anz-m.blogspot.jp/2012/04/androidfragment-setarguments.html
     */
    public MainFragment() {
        Log.v(TAG, "constructor");
    }

    /**
     * 新しい {@link Fragment} インスタンスを生成するメソッドです。
     * {@link Fragment} が何らかの初期値を必要とする場合、コンストラクタでそれを初期化するのは良くない実装です。
     * 代わりに、static なメソッドを定義し、そこに必要な値を渡して、このメソッドが {@link Fragment} へ値を渡すのを肩代わりするようにします。
     * このようにすることで、{@link Fragment} が期待する初期値を明示し、また、Android システムのライフサイクルに従った実装ができるようになります。
     *
     * Create a new instance of this {@link Fragment}.
     * When a {@link Fragment} needs some kind of initial value,
     * set {@link Bundle} object containing them to the {@link Fragment} through {@link Fragment#setArguments(Bundle)},
     * and do not use constructor that it is a bad implementation.
     *
     * @param title the title of this dialog
     * @param message the message of this dialog
     * @return {@link MainDialogFragment} instance
     */
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        return fragment;
    }

    /**
     * <blockquote>
     * onAttach(Activity) は {@link Fragment} が、{@link Activity} と紐付いた時に呼ばれます。
     * </blockquote>
     *
     * もしこの {@link Fragment} が {@link Activity} に対してコールバックする必要が有る場合には、
     * Observer パターンにしたがって、Listener インタフェースを定義して、それを {@link Activity} に実装させます。
     * コールバックを呼ぶときは、ここで対象の {@link Activity} への参照を保存しておいて、
     * その参照からコールバックを呼び出します。
     *
     * <blockquote>
     * onAttach(Activity) called once the {@link Fragment} is associated with its {@link Activity}.
     * </blockquote>
     *
     * If this {@link Fragment} should call back something to the {@link Activity},
     * you should declare a listener interface and a activity that attaches this {@link Fragment}
     * implement it. And store the listener object on this method like this.
     *
     * {@code}
     * try {
     *     mListener = (ListenerInterface) activity;
     * } catch (ClassCastException e) {
     *     throw new IllegalArgumentException("The activity should implement listener interface!", e);
     * }
     * {@code}
     */
    @Override
    public void onAttach(Activity activity) {
        Log.v(TAG, "onAttach");
        super.onAttach(activity);
    }

    /**
     * <blockquote>
     * システムは、 {@link Fragment} を作るときに、onCreate(Bundle) を呼び出します。
     * このコールバックメソッドでは、{@link Fragment} に必要なコンポーネントの初期化を行います。
     * </blockquote>
     *
     * <blockquote>
     * The system calls this when creating the {@link Fragment}.
     * Within your implementation, you should initialize essential components of the {@link Fragment}
     * that you want to retain when the {@link Fragment} is paused or stopped, then resumed.
     *
     * onCreate(Bundle) called to do initial creation of the {@link Fragment}.
     * </blockquote>
     *
     * @param savedInstanceState saved {@link Fragment} state before the {@link Fragment} destroyed.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    /**
     * {@link Fragment} のレイアウトを作ります。
     *
     * Create a layout of this {@link Fragment}.
     *
     * <blockquote>
     * システムは、{@link Fragment} が最初に UI を描画する時に、onCreateView(LayoutInflater, ViewGroup, Bundle) を呼び出します。
     * UI を描画する {@link Fragment} は、必ずこのメソッドは、 {@link Fragment} のレイアウトの一番親にあたる {@link View} オブジェクトを返す必要があります。
     * nullを返すことで、UI を持たない {@link Fragment} を作ることもできます。
     * </blockquote>
     *
     * <blockquote>
     * The system calls this when it's time for the {@link Fragment} to draw its user interface for the first time.
     * To draw a UI for your {@link Fragment} , you must return a {@link View} object from this method
     * that is the root of your {@link Fragment}'s layout.
     * You can return null if the {@link Fragment} does not provide a UI.
     *
     * onCreateView(LayoutInflater, ViewGroup, Bundle) creates and returns the view hierarchy
     * associated with the {@link Fragment}.
     * </blockquote>
     *
     * @param inflater the layout object creator.
     * @param container the layout that contains this dialog.
     * @param savedInstanceState saved {@link Fragment} state before the {@link Fragment} destroyed.
     * @return {@link View} object of this dialog.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView");
        return null;
    }


    /**
     * <blockquote>
     * {@link Fragment}  が紐付いている {@link Activity} の{@link Activity#onCreate(Bundle)}の処理が終わると、
     * onActivityCreated(Bundle) が呼ばれます。(Activity の Context を得るのはこれ以後のライフサイクルメソッドが良いです。)
     * </blockquote>
     *
     * <blockquote>
     * onActivityCreated(Bundle) tells the {@link Fragment} that
     * its {@link Activity} has completed its own {@link Activity#onCreate(Bundle)}.
     * Prefer to obtain {@link Activity} context after this method is called.
     * </blockquote>
     *
     * @param savedInstanceState saved {@link Fragment} state before the {@link Fragment} destroyed.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.v(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * <blockquote>
     * onStart() 以後、{@link Fragment} はユーザに見える状態にになります。
     * </blockquote>
     *
     * <blockquote>
     * onStart() makes the {@link Fragment} visible to the user
     * (based on its containing {@link Activity} being started).
     * </blockquote>
     */
    @Override
    public void onStart() {
        Log.v(TAG, "onStart");
        super.onStart();
    }

    /**
     * <blockquote>
     * onResume()以後、{@link Fragment} はユーザの操作を受け付けることができるようになります。
     * </blockquote>
     *
     * <blockquote>
     * onResume() makes the {@link Fragment} interacting with the user
     * (based on its containing {@link Activity} being resumed).
     * </blockquote>
     */
    @Override
    public void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
    }

    /**
     * メモリ不足や画面回転などの理由で {@link Fragment} が破棄されても復帰可能とするために
     * {@link Fragment} の状態を一時保存する目的で onSaveInstanceState(Bundle) が呼ばれます。
     * 保存したい状態は、引数の {@link Bundle} オブジェクトに保存します。
     * この {@link Bundle} オブジェクトは、{@link Bundle} オブジェクトを受け付ける様々なライフサイクルメソッドで受け取ることができます。
     * 例えば、{@link Fragment#onCreate(Bundle)}や、
     * {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}、
     * {@link DialogFragment#onCreateDialog(Bundle)}、
     * {@link Fragment#onActivityCreated(Bundle)}が該当します。
     *
     * これらのメソッドで受け取る {@link Bundle} オブジェクトには、以前onSaveInstanceState(Bundle)で保存した状態が格納されています。
     *
     * To save temporary states of this fragment,
     * put them into a {@link Bundle} object.
     * It can be restored in methods such as {@link Fragment#onCreate(Bundle)},
     * {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)},
     * {@link DialogFragment#onCreateDialog(Bundle)}, or
     * {@link Fragment#onActivityCreated(Bundle)}.
     *
     * All these methods accepts a {@link Bundle} argument
     * that contains previously saved states in this method.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.v(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    /**
     * <blockquote>
     * ユーザが画面遷移などで {@link Fragment} から去ることを最初に通知するものとして、システムは onPause() を呼び出します。
     * ただしこれは、即ち {@link Fragment} が破棄されつつあることを意味するものではありません。
     * ここでは、{@link Activity} の {@link Context} などを超える、永続化すべきデータを保存しておきます(ユーザが戻ってこないこともあるため)。
     *
     * onPause() が呼ばれたら、{@link Fragment} はユーザの操作を受け付けなくなります。これは、 {@link Activity#onPause()} が呼ばれた後に
     * {@link Fragment#onPause()} が呼ばれるため、{@link Activity} もまた操作を受け付けなくなるからです。
     * </blockquote>
     *
     * <blockquote>
     * The system calls this method as the first indication that the user is leaving the {@link Fragment}
     * (though it does not always mean the {@link Fragment} is being destroyed).
     * This is usually where you should commit any changes that should be persisted
     * beyond the current user session (because the user might not come back).
     *
     * After onPause(), {@link Fragment} is no longer interacting with the user
     * either because its activity is being paused
     * or a {@link Fragment} operation is modifying it in the {@link Activity}.
     * </blockquote>
     */
    @Override
    public void onPause() {
        Log.v(TAG, "onPause");
        super.onPause();
    }

    /**
     * <blockquote>
     * onStop() 実行後は、{@link Fragment} がユーザから見えなくなります。
     * これは {@link Fragment} が紐付く {@link Activity} も、onStop() が実行されユーザから見えなくなるためです。
     * </blockquote>
     *
     * <blockquote>
     * After onStop(), {@link Fragment} is no longer visible to the user
     * either because its activity is being stopped
     * or a {@link Fragment} operation is modifying it in the {@link Activity}.
     * </blockquote>
     */
    @Override
    public void onStop() {
        Log.v(TAG, "onStop");
        super.onStop();
    }

    /**
     * <blockquote>
     * onDestroyView() が呼ばれたら、{@link Fragment} は {@link View} に紐付くリソースの開放を行います。
     * </blockquote>
     *
     * <blockquote>
     * onDestroyView() allows the {@link Fragment} to clean up resources associated with its {@link View}s .
     * </blockquote>
     */
    @Override
    public void onDestroyView() {
        Log.v(TAG, "onDestroyView");
        super.onDestroyView();
    }

    /**
     * onDestroy() が呼ばれるのは、{@link Fragment} がもはや使われなくなる時です。
     * メモリリークを防ぐため、Listener オブジェクトなどの参照を切る必要があります。
     *
     * <blockquote>
     * onDestroy() は、{@link Fragment} を何時でもメモリから破棄できる状態にするための最後の処理を行う場所です。
     * </blockquote>
     *
     * This method will be called when this {@link Fragment} is no longer used.
     * To avoid memory leak, you should release references to this {@link Fragment} such as listener object.
     *
     * <blockquote>
     * onDestroy() called to do final cleanup of the {@link Fragment}'s state.
     * </blockquote>
     */
    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    /**
     * <blockquote>
     * onDetach() は、{@link Activity} との紐付きがなくなったときに呼ばれます。
     * </blockquote>
     *
     * <blockquote>
     * onDetach() called immediately prior to the fragment no longer being associated with its {@link Activity}.
     * </blockquote>
     */
    @Override
    public void onDetach() {
        Log.v(TAG, "onDetach");
        super.onDetach();
    }
}