package jp.mixi.assignment.layout.beg;


public class CalcModel {

    @SuppressWarnings("unused")
    private static final String TAG = CalcModel.class.getSimpleName();

    private double mTargetValue;
    private double mNowValue;
    private double base;
    private OPERATOR mOperator;
    /** 入力状態のステータス */
    private STATUS status;

    public CalcModel() {
        init();
    }

    private void init() {
        mNowValue = 0;
        mTargetValue = 0;
        mOperator = OPERATOR.NO_OPERATOR;
        status = STATUS.INITIAL;
        base = 1;
    }

    public double getNowValue() {
        return mNowValue;
    }

    public void putOperator(OPERATOR op) {
        if (op == OPERATOR.CLEAR) {
            init();
            return;
        }
        if (status != STATUS.INPUT_OPERATION) {
            base = 1;
            mTargetValue = mOperator.call(mTargetValue, mNowValue);
            status = op.nextStatus();
            mNowValue = 0;
        }
        mOperator = op;
        if (op == OPERATOR.EQUAL) {
            mNowValue = mTargetValue;
        }
    }

    public void putNumber(int num) {
        switch (status) {
        case RESULT:
            mOperator = OPERATOR.NO_OPERATOR;
            mTargetValue = 0;
            mNowValue = num;
            break;
        case INITIAL:
        case INPUT_OPERATION:
            mNowValue = num;
            break;
        case INPUT_NUMBER:
            if (base == 1) {
                mNowValue = mNowValue * 10 + num;
            } else {
                mNowValue = mNowValue + num * base;
                base /= 10;
            }
            break;
        }
        status = STATUS.INPUT_NUMBER;
    }

    public void putDot() {
        if (base == 1 && status == STATUS.INPUT_NUMBER) base = 0.1;
    }

    private enum STATUS {
        INPUT_NUMBER, INPUT_OPERATION, RESULT, INITIAL;
    }

    /** 演算子の定義 */
    public enum OPERATOR {
        PLUS {
            @Override
            public double call(double val1, double val2) {
                return val1 + val2;
            }
        },
        MINUS {
            @Override
            public double call(double val1, double val2) {
                return val1 - val2;
            }
        },
        TIMES {
            @Override
            public double call(double val1, double val2) {
                return val1 * val2;
            }
        },
        DIVIDE {
            @Override
            public double call(double val1, double val2) {
                return val1 / val2;
            }
        },
        EQUAL {
            @Override
            public double call(double val1, double val2) {
                return val1;
            }

            @Override
            public STATUS nextStatus() {
                return STATUS.RESULT;
            }
        },
        CLEAR {
            @Override
            public double call(double val1, double val2) {
                return 0;
            }

            @Override
            public STATUS nextStatus() {
                return STATUS.INITIAL;
            }
        },
        NO_OPERATOR {
            @Override
            public double call(double val1, double val2) {
                return val2;
            }
        };

        public STATUS nextStatus() {
            return STATUS.INPUT_OPERATION;
        }

        /** 定義に従った演算を実装 */
        abstract public double call(double val1, double val2);
    }
}
